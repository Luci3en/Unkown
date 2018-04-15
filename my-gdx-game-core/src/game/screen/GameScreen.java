package game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import game.entity.Tile;
import game.entity.World;

public class GameScreen extends AbstractScreen {

	private SpriteBatch spriteBatch;
	private OrthogonalTiledMapRenderer tiledMapRenderer;
	private World world;
	private ShapeRenderer shapeRenderer;

	public GameScreen(AssetManager assetManager) {
		super(assetManager, 600f, 400f);
		this.shapeRenderer = new ShapeRenderer();
		shapeRenderer.setAutoShapeType(true);
		this.spriteBatch = new SpriteBatch();
		this.world = new World(20, 20);
		this.tiledMapRenderer = new OrthogonalTiledMapRenderer(world.getMap());

	}

	@Override
	public void show() {
		InputMultiplexer inputMultiplexer = new InputMultiplexer();
		inputMultiplexer.addProcessor(this);
		inputMultiplexer.addProcessor(world.getPlayer());
		Gdx.input.setInputProcessor(inputMultiplexer);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		updateCamera();

		tiledMapRenderer.setView((OrthographicCamera) getCamera());
		tiledMapRenderer.render();

		spriteBatch.setProjectionMatrix(getCamera().combined);
		spriteBatch.begin();

		world.render(spriteBatch);

		spriteBatch.end();
		
		shapeRenderer.setProjectionMatrix(getCamera().combined);
		shapeRenderer.begin();

		for (Tile iterable_element : world.getEntityManager().getListe()) {
			
			shapeRenderer.rect(iterable_element.getX() * 32, iterable_element.getY() * 32, 32, 32);
			
		}

		shapeRenderer.end();

		super.act(delta);
		super.draw();

	}

	@Override
	public void buildStage() {

	}

	public Dialog getExitDialog() {

		return new Dialog("Exit", getAssetManager().get("skin/metal-ui.json", Skin.class)) {
			{
				setName("exit");
				button("Yes", "Yes");
				button("No", "No");

			}

			@Override
			protected void result(Object object) {

				if (object.equals("Yes")) {

					Gdx.app.exit();

				}

			}

		}.show(this);
	}

	public void updateCamera() {

		getCamera().position.x = world.getPlayer().getX();

		getCamera().position.x = MathUtils.clamp(getCamera().position.x, getCamera().viewportWidth / 2,
				World.MAP_PIXEL_WIDTH - (getCamera().viewportWidth / 2));

		getCamera().position.y = world.getPlayer().getY();

		getCamera().position.y = MathUtils.clamp(getCamera().position.y, getCamera().viewportHeight / 2,
				World.MAP_PIXEL_HEIGHT - (getCamera().viewportHeight / 2));

		getCamera().update();

	}

	@Override
	public void dispose() {
		super.dispose();
		spriteBatch.dispose();
		tiledMapRenderer.dispose();
	}

	@Override
	public boolean keyDown(int keyCode) {

		if (keyCode == Keys.ESCAPE) {

			int temp = 0;

			if (getActors().size > 0) {

				for (Actor iterable_element : getActors()) {

					if (iterable_element.getName().equals("exit")) {
						temp++;
					} else {

					}

				}
			}
			if (temp == 0) {
				getExitDialog();
			}

		}

		return false;
	}

}
