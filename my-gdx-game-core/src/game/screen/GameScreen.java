package game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import game.Application;
import game.Controller;
import game.World;

public class GameScreen extends AbstractScreen {

	private World world;
	private Controller player;

	public GameScreen(Application app) {
		super(app);
		this.player = new Controller();
		this.world = new World(player);

	}

	@Override
	public void show() {
		stage.clear();
		InputMultiplexer inputMultiplexer = new InputMultiplexer();
		inputMultiplexer.addProcessor(player);
		inputMultiplexer.addProcessor(stage);
		Gdx.input.setInputProcessor(inputMultiplexer);

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		update();

		player.update(delta, world.getEntityManager(), world.getCamera());
		world.update(player);
		stage.act(delta);

		world.render(app.getSpriteBatch());
		stage.draw();
	}

	public void update() {

		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {

			if (stage.getActors().size > 0) {
				stage.clear();
			} else {
				showGameMenu();
			}

		}
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	public void showGameMenu() {
		stage.clear();
		Label header = new Label("Unkown", app.getAssetManager().get("skin/metal-ui.json", Skin.class));
		header.setColor(1, 1, 1, 1);
		header.setFontScale(2f);

		TextButton exit = new TextButton("Exit", app.getAssetManager().get("skin/metal-ui.json", Skin.class));

		exit.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				app.setScreen(app.getMenuScreen());
			}

		});

		TextButton resume = new TextButton("Resume", app.getAssetManager().get("skin/metal-ui.json", Skin.class));
		resume.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				stage.clear();
			}

		});

		TextButton settings = new TextButton("Settings", app.getAssetManager().get("skin/metal-ui.json", Skin.class));
		settings.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				showSettings();
			}

		});

		Table table = new Table();
		table.setFillParent(true);

		table.addAction(Actions.sequence(Actions.alpha(0),
				Actions.parallel(Actions.fadeIn(0.5f), Actions.moveBy(0, -20, 0.5f, Interpolation.pow5Out))));

		table.add(header).pad(100, 300, 100, 300);
		table.row();
		table.add(resume).padBottom(30);
		table.row();
		table.add(settings).padBottom(30);
		table.row();
		table.add(exit);

		stage.addActor(table);
	}

	public void showSettings() {
		stage.clear();
		Label header = new Label("Settings", getApp().getAssetManager().get("skin/metal-ui.json", Skin.class));
		header.setFontScale(1.5f);

		TextButton back = new TextButton("Back", getApp().getAssetManager().get("skin/metal-ui.json", Skin.class));
		back.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {

				showGameMenu();

			}

		});

		CheckBox fullscreen = new CheckBox(" Fullscreen",
				getApp().getAssetManager().get("skin/metal-ui.json", Skin.class));

		if (Gdx.graphics.isFullscreen()) {

			fullscreen.setChecked(true);

		}

		fullscreen.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {

				if (!(Gdx.graphics.isFullscreen())) {
					Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
				} else {

					Gdx.graphics.setWindowedMode(1024, 768);
				}

			}

		});

		Table table = new Table();

		table.setFillParent(true);
		table.add(header).pad(100, 300, 100, 300);
		table.row();
		table.add(fullscreen).padBottom(30);
		table.row();
		table.add(back);

		table.addAction(Actions.sequence(Actions.alpha(0),
				Actions.parallel(Actions.fadeIn(0.5f), Actions.moveBy(0, -20, 0.5f, Interpolation.pow5Out))));

		stage.addActor(table);

	}

}
