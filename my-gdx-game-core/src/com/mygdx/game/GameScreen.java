package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameScreen implements Screen {

	public static int VIEWPORT_WIDTH = 800;
	public static int VIEWPORT_HEIGHT = 480;
	public static int MAP_HEIGHT;
	public static int MAP_WIDTH;
	public static int MAP_PIXEL_WIDTH;
	public static int MAP_PIXEL_HEIGHT;
	public static int TILE_PIXEL_WIDTH;
	public static int TILE_PIXEL_HEIGHT;

	private OrthographicCamera camera;
	private SpriteBatch batch;
	private OrthogonalTiledMapRenderer mapRendere;
	private AssetManager assetManager;
	private Stage stage;

	private TiledMap map;
	private Player player;

	@Override
	public void show() {

		this.assetManager = new AssetManager();
		assetManager.load("skin/neutralizer-ui.atlas", TextureAtlas.class);
		assetManager.load("img/player.png", Texture.class);
		assetManager.load("fonts/blackFont.fnt", BitmapFont.class);
		assetManager.finishLoading();

		this.batch = new SpriteBatch();

		this.camera = new OrthographicCamera();
		camera.setToOrtho(false, GameScreen.VIEWPORT_WIDTH, GameScreen.VIEWPORT_HEIGHT);

		this.map = new TmxMapLoader().load("maps/map.tmx");

		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(0);
		MAP_HEIGHT = layer.getHeight();
		MAP_WIDTH = layer.getWidth();
		MAP_PIXEL_HEIGHT = (int) (layer.getTileWidth() * layer.getWidth());
		MAP_PIXEL_WIDTH = (int) (layer.getTileHeight() * layer.getHeight());
		TILE_PIXEL_HEIGHT = (int) layer.getTileHeight();
		TILE_PIXEL_WIDTH = (int) layer.getTileWidth();

		this.mapRendere = new OrthogonalTiledMapRenderer(map);

		this.player = new Player(new Sprite(assetManager.get("img/player.png", Texture.class)),
				(TiledMapTileLayer) map.getLayers().get(1));

		InputMultiplexer multiplexer = new InputMultiplexer();
		Gdx.input.setInputProcessor(multiplexer);
		multiplexer.addProcessor(player);
		multiplexer.addProcessor(stage);

	}

	@Override
	public void dispose() {
		map.dispose();
		assetManager.dispose();

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float arg0) {

		camera.position.x = player.getX();
		camera.position.y = player.getY();
		// camera.translate(player.getX(), player.getY(), 0);
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// render()

		mapRendere.setView(camera);
		mapRendere.render();

		batch.begin();
		player.draw(batch, 1);
		batch.end();

	}

	@Override
	public void resize(int width, int height) {
		camera.viewportHeight = height;
		camera.viewportWidth = width;
		camera.update();

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

}
