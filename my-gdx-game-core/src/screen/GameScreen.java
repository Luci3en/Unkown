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
import com.mygdx.game.Player;
import com.mygdx.game.TileMap;

public class GameScreen implements Screen {

	public static int VIEWPORT_WIDTH = 800;
	public static int VIEWPORT_HEIGHT = 480;
	private OrthographicCamera camera;
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

		this.camera = new OrthographicCamera();
		camera.setToOrtho(false, GameScreen.VIEWPORT_WIDTH, GameScreen.VIEWPORT_HEIGHT);
		System.out.println(camera.viewportHeight);
		System.out.println(camera.viewportWidth);

		this.map = new TileMap();
		this.tiledMapRenderer = new OrthogonalTiledMapRenderer(map.getMap());

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

		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();

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
