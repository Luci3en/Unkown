package game.screen;

import java.util.Map.Entry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import game.entity.Entity;
import game.entity.World;

public class GameScreen extends AbstractScreen {

	private SpriteBatch spriteBatch;
	private World world;

	public GameScreen(AssetManager assetManager) {
		super(assetManager, 600f, 400f);

		this.spriteBatch = new SpriteBatch();
		this.world = new World();
	}

	@Override
	public void show() {
		InputMultiplexer inputMultiplexer = new InputMultiplexer();
		inputMultiplexer.addProcessor(this);
		inputMultiplexer.addProcessor(world.getEntityManager().getPlayer());
		Gdx.input.setInputProcessor(inputMultiplexer);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		spriteBatch.setProjectionMatrix(getCamera().combined);
		spriteBatch.begin();

		world.render(spriteBatch, (OrthographicCamera) getCamera());

		spriteBatch.end();

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

	@Override
	public void dispose() {
		super.dispose();
		this.spriteBatch.dispose();
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
