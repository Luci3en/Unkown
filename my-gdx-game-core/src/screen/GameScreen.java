package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Player;
import com.mygdx.game.TileMap;

public class GameScreen implements Screen {

	public static int VIEWPORT_WIDTH = 800;
	public static int VIEWPORT_HEIGHT = 480;
	private OrthographicCamera camera;
	private Viewport viewport;
	private SpriteBatch batch;
	private OrthogonalTiledMapRenderer tiledMapRenderer;
	private AssetManager assetManager;
	private TileMap map;
	private Player player;

	@Override
	public void show() {

		this.assetManager = new AssetManager();
		assetManager.load("img/player.png", Texture.class);
		assetManager.finishLoading();

		this.batch = new SpriteBatch();

		this.map = new TileMap();
		this.tiledMapRenderer = new OrthogonalTiledMapRenderer(map.getMap());

		float aspectRatio = (float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth();

		camera = new OrthographicCamera(TileMap.MAP_PIXEL_WIDTH * aspectRatio, TileMap.MAP_PIXEL_HEIGHT);
		
//		viewport = new FillViewport(TileMap.MAP_PIXEL_WIDTH * aspectRatio, TileMap.MAP_PIXEL_HEIGHT);
//		viewport.apply();
		camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
		camera.update();

		this.player = new Player(new Sprite(assetManager.get("img/player.png", Texture.class)),
				(TiledMapTileLayer) map.getMap().getLayers().get("Kachelebene 2"));

		InputMultiplexer multiplexer = new InputMultiplexer();
		Gdx.input.setInputProcessor(multiplexer);
		multiplexer.addProcessor(player);

	}

	@Override
	public void dispose() {
		assetManager.dispose();
		batch.dispose();

	}
	

	@Override
	public void resize(int width, int height) {
		//viewport.update(width, height);
		camera.viewportHeight = height;
		camera.viewportWidth = width;
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

		camera.translate(player.getVelocity().x, player.getVelocity().y, 0);
		camera.update();

		// render()

		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		player.draw(batch, 1);
		batch.end();

	}


	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

}
