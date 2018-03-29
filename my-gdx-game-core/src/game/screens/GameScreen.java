package game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;

import game.Player;
import game.World;

public class GameScreen extends AbstractScreen {

	private SpriteBatch spriteBatch;
	private OrthogonalTiledMapRenderer tiledMapRenderer;
	private World map;
	private Player player;

	public GameScreen(AssetManager assetManager) {
		super(assetManager, 600f, 400f);

		assetManager.load("fonts/blackFont.fnt", BitmapFont.class);
		assetManager.load("img/player.png", Texture.class);
		assetManager.finishLoading();

		this.spriteBatch = new SpriteBatch();

		this.map = new World();
		this.tiledMapRenderer = new OrthogonalTiledMapRenderer(map.getMap());

		this.player = new Player(new Sprite(assetManager.get("img/player.png", Texture.class)),
				(TiledMapTileLayer) map.getMap().getLayers().get("Kachelebene 2"));

	}

	@Override
	public void show() {
		InputMultiplexer inputMultiplexer = new InputMultiplexer();
		inputMultiplexer.addProcessor(this);
		inputMultiplexer.addProcessor(player);
		Gdx.input.setInputProcessor(inputMultiplexer);
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		getCamera().position.x = player.getX();

		getCamera().position.x = MathUtils.clamp(getCamera().position.x, getCamera().viewportWidth / 2,
				World.MAP_PIXEL_WIDTH - (getCamera().viewportWidth / 2));

		getCamera().position.y = player.getY();

		getCamera().position.y = MathUtils.clamp(getCamera().position.y, getCamera().viewportHeight / 2,
				World.MAP_PIXEL_HEIGHT - (getCamera().viewportHeight / 2));

		getCamera().update();
		// render()

		tiledMapRenderer.setView((OrthographicCamera) getCamera());
		tiledMapRenderer.render();

		spriteBatch.setProjectionMatrix(getCamera().combined);
		spriteBatch.begin();
		map.renderItems(spriteBatch);
		player.draw(spriteBatch);

		spriteBatch.end();

	}

	@Override
	public void buildStage() {
		// TODO Auto-generated method stub
	}

}
