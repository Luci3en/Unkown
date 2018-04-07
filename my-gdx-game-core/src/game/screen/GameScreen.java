package game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import game.entity.Player;
import game.entity.World;

public class GameScreen extends AbstractScreen {

	private SpriteBatch spriteBatch;
	private OrthogonalTiledMapRenderer tiledMapRenderer;
	private World map;
	private Player player;
	private ShapeRenderer shapeRenderer;

	public GameScreen(AssetManager assetManager) {
		super(assetManager, 600f, 400f);
		this.buildStage();

		assetManager.load("fonts/blackFont.fnt", BitmapFont.class);
		assetManager.finishLoading();

		this.shapeRenderer = new ShapeRenderer();
		shapeRenderer.setAutoShapeType(true);

		this.spriteBatch = new SpriteBatch();

		this.map = new World();
		this.tiledMapRenderer = new OrthogonalTiledMapRenderer(map.getMap());

		this.player = new Player((TiledMapTileLayer) map.getMap().getLayers().get("Kachelebene 2"));

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
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		updateCamera();

		// render()

		Label label = (Label) getActors().get(0);
		label.setX(getCamera().position.x - getViewport().getWorldWidth());
		label.setY(getCamera().position.y - getViewport().getWorldHeight());
		label.setText("Fps: " + Gdx.graphics.getFramesPerSecond());

		tiledMapRenderer.setView((OrthographicCamera) getCamera());
		tiledMapRenderer.render();

		spriteBatch.setProjectionMatrix(getCamera().combined);
		spriteBatch.begin();
		map.renderItems(spriteBatch);
		player.draw(spriteBatch);

		spriteBatch.end();

		shapeRenderer.setProjectionMatrix(getCamera().combined);
		shapeRenderer.begin();
		shapeRenderer.rect(player.getHitBox().getX(), player.getHitBox().getY(), player.getHitBox().getWidth(),
				player.getHitBox().getHeight());
		shapeRenderer.end();
		
		act();
		draw();
	}

	@Override
	public void buildStage() {

		BitmapFont blackFont = new BitmapFont(Gdx.files.internal("fonts/blackFont.fnt"));

		LabelStyle labelStyle = new LabelStyle();
		labelStyle.font = blackFont;
		Label header = new Label("Fps: ", labelStyle);
		header.setFontScale(2f);
		addActor(header);

	}

	public void updateCamera() {

		getCamera().position.x = player.getX();

		getCamera().position.x = MathUtils.clamp(getCamera().position.x, getCamera().viewportWidth / 2,
				World.MAP_PIXEL_WIDTH - (getCamera().viewportWidth / 2));

		getCamera().position.y = player.getY();

		getCamera().position.y = MathUtils.clamp(getCamera().position.y, getCamera().viewportHeight / 2,
				World.MAP_PIXEL_HEIGHT - (getCamera().viewportHeight / 2));

		getCamera().update();

	}

	@Override
	public void dispose() {
		super.dispose();
	}

}
