package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class GameScreen implements Screen {

	public static int VIEWPORT_WIDTH = 800;
	public static int VIEWPORT_HEIGHT = 480;
	public static int MAP_HEIGHT = 50;
	public static int MAP_WIDTH = 50;

	private OrthographicCamera camera;
	private SpriteBatch batch;

	private TiledMap map;
	private OrthogonalTiledMapRenderer mapRendere;
	private Player player;

	@Override
	public void show() {

		batch = new SpriteBatch();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, GameScreen.VIEWPORT_WIDTH, GameScreen.VIEWPORT_HEIGHT);

		map = new TmxMapLoader().load("maps/map.tmx");
		mapRendere = new OrthogonalTiledMapRenderer(map);

		Texture texture = new Texture(Gdx.files.internal("img/player.png"));
		player = new Player(new Sprite(texture), map.getLayers().get(2).getObjects(),
				(TiledMapTileLayer) map.getLayers().get(1));

		Gdx.input.setInputProcessor(player);

		// InputMultiplexer multiplexer = new InputMultiplexer();
		// Gdx.input.setInputProcessor(multiplexer);
		// multiplexer.addProcessor(player);

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

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
