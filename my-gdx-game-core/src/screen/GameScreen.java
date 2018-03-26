package screen;

import javax.swing.plaf.synth.SynthSpinnerUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Player;
import com.mygdx.game.TileMap;

public class GameScreen implements Screen {

	public static int VIEWPORT_WIDTH = 800;
	public static int VIEWPORT_HEIGHT = 800;
	private OrthographicCamera camera;
	private SpriteBatch spriteBatch;
	private OrthogonalTiledMapRenderer tiledMapRenderer;
	private Viewport viewport;
	private AssetManager assetManager;
	private TileMap map;
	private Player player;

	@Override
	public void show() {

		this.assetManager = new AssetManager();
		assetManager.load("img/player.png", Texture.class);
		assetManager.finishLoading();

		this.spriteBatch = new SpriteBatch();

		this.map = new TileMap();
		this.tiledMapRenderer = new OrthogonalTiledMapRenderer(map.getMap());

		this.camera = new OrthographicCamera();
		this.viewport = new StretchViewport(GameScreen.VIEWPORT_WIDTH, GameScreen.VIEWPORT_HEIGHT, camera);
		viewport.apply();
		camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);

		this.player = new Player(new Sprite(assetManager.get("img/player.png", Texture.class)),
				(TiledMapTileLayer) map.getMap().getLayers().get("Kachelebene 2"));

		Gdx.input.setInputProcessor(player);
		// InputMultiplexer multiplexer = new InputMultiplexer();
		// multiplexer.addProcessor(player);

	}

	@Override
	public void dispose() {
		assetManager.dispose();
		spriteBatch.dispose();
		player.getTexture().dispose();

	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
		camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);

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
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.position.x = MathUtils.clamp(camera.position.x, camera.viewportWidth / 2,
				TileMap.MAP_PIXEL_WIDTH - (camera.viewportWidth / 2));
		camera.position.y = MathUtils.clamp(camera.position.y, camera.viewportHeight / 2,
				TileMap.MAP_PIXEL_HEIGHT - (camera.viewportHeight / 2));

//		if (player.getX() < camera.viewportWidth / 2) {
//			camera.position.x = camera.viewportWidth / 2;
//		} else if (player.getX() > TileMap.MAP_PIXEL_WIDTH - (camera.viewportWidth / 2)) {
//			camera.position.x = TileMap.TILE_PIXEL_WIDTH - (camera.viewportWidth / 2);
//		} else {
//
//			System.out.println("moveX");
//
//			camera.position.x = player.getX();
//		}
//
//		if (player.getY() < camera.viewportHeight / 2) {
//			camera.position.y = camera.viewportHeight / 2;
//		} else if (player.getY() > TileMap.MAP_PIXEL_HEIGHT - (camera.viewportHeight / 2)) {
//			camera.position.y = TileMap.TILE_PIXEL_HEIGHT - (camera.viewportHeight / 2);
//		} else {
//
//			System.out.println("move y");
//			camera.position.y = player.getY();
//		}

		camera.update();

		// render()

		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();

		spriteBatch.setProjectionMatrix(camera.combined);
		spriteBatch.begin();
		player.draw(spriteBatch);
		spriteBatch.end();

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

}
