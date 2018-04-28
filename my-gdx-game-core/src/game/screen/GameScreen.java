package game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import game.World;

public class GameScreen extends AbstractScreen {

	private World world;

	public GameScreen(AssetManager assetManager) {
		super(assetManager, 600f, 400f);
		this.world = new World(600, 400);

	}

	@Override
	public void show() {
		InputMultiplexer inputMultiplexer = new InputMultiplexer();
		inputMultiplexer.addProcessor(world.getPlayer());
		inputMultiplexer.addProcessor(this);
		Gdx.input.setInputProcessor(inputMultiplexer);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		Label temp = (Label) getActors().get(0);
		temp.setText("Fps: " + Gdx.graphics.getFramesPerSecond());

		world.update();
		super.act(delta);

		world.render();
		super.draw();
	}

	@Override
	public void buildStage() {

		Label header = new Label("FPS: ", getAssetManager().get("skin/metal-ui.json", Skin.class));
		header.setName("label");
		header.setColor(0, 0, 0, 1);
		header.setFontScale(2f);
		header.setPosition(4, getViewport().getWorldHeight() - 20);
		addActor(header);

	}

	public Dialog getExitDialog() {

		return new Dialog("Exit", getAssetManager().get("skin/metal-ui.json", Skin.class)) {
			{

				Skin skin = getAssetManager().get("skin/metal-ui.json", Skin.class);
				ImageButtonStyle temp = new ImageButtonStyle();
				temp.up = skin.getDrawable("close");
				temp.down = skin.getDrawable("close-down");

				ImageButton close = new ImageButton(temp);

				close.addListener(new ClickListener() {
					@Override
					public void clicked(InputEvent event, float x, float y) {
						System.out.println("click");

					}
				});

				getTitleTable().add(close).pad(10);

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
						getExitDialog();
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
