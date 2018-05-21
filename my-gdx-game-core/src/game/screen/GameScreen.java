package game.screen;

import com.badlogic.gdx.Gdx;
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
	private Controller controller;

	public GameScreen(Application app) {
		super(app);
		this.world = new World();
		this.controller = new Controller(this);
	}

	@Override
	public void show() {
		stage.clear();
		InputMultiplexer inputMultiplexer = new InputMultiplexer();
		inputMultiplexer.addProcessor(controller);
		inputMultiplexer.addProcessor(stage);
		Gdx.input.setInputProcessor(inputMultiplexer);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		controller.update(delta);
		stage.act(delta);

		world.render();
		stage.draw();

	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		world.getViewport().update(width, height);
	}

	@Override
	public void dispose() {
		super.dispose();
		world.dispose();
	}

	public void showGameMenu() {
		stage.clear();
		Label header = new Label("Unkown", app.getAssetManager().get("skin/uiskin.json", Skin.class));
		header.setFontScale(1.5f);

		TextButton exit = new TextButton("Exit", app.getAssetManager().get("skin/uiskin.json", Skin.class));

		exit.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				app.setScreen(app.getMenuScreen());
			}

		});

		TextButton resume = new TextButton("Resume", app.getAssetManager().get("skin/uiskin.json", Skin.class));
		resume.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				stage.clear();
			}

		});

		TextButton settings = new TextButton("Settings", app.getAssetManager().get("skin/uiskin.json", Skin.class));
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
		table.add(resume).padBottom(30).width(85);
		table.row();
		table.add(settings).padBottom(30).width(85);
		table.row();
		table.add(exit).width(85);

		stage.addActor(table);
	}

	public void showSettings() {
		stage.clear();
		Label header = new Label("Settings", getApp().getAssetManager().get("skin/uiskin.json", Skin.class));
		header.setFontScale(1.5f);

		TextButton back = new TextButton("Back", getApp().getAssetManager().get("skin/uiskin.json", Skin.class));
		back.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {

				showGameMenu();

			}

		});

		CheckBox fullscreen = new CheckBox(" Fullscreen",
				getApp().getAssetManager().get("skin/uiskin.json", Skin.class));

		if (Gdx.graphics.isFullscreen()) {

			fullscreen.setChecked(true);

		} else {
			fullscreen.setChecked(false);
		}

		fullscreen.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {

				if (!(Gdx.graphics.isFullscreen())) {
					app.getPreferences().putBoolean("fullscreen", true);
					Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
				} else {

					app.getPreferences().putBoolean("fullscreen", false);
					Gdx.graphics.setWindowedMode(1024, 768);
				}

				app.getPreferences().flush();
			}

		});

		Table table = new Table();

		table.setFillParent(true);
		table.add(header).pad(100, 300, 100, 300);
		table.row();
		table.add(fullscreen).padBottom(30);
		table.row();
		table.add(back).width(85);

		table.addAction(Actions.sequence(Actions.alpha(0),
				Actions.parallel(Actions.fadeIn(0.5f), Actions.moveBy(0, -20, 0.5f, Interpolation.pow5Out))));

		stage.addActor(table);

	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

}
