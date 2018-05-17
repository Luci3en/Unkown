package game.screen;

import com.badlogic.gdx.Gdx;
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

public class MenuScreen extends AbstractScreen {

	public MenuScreen(Application app) {
		super(app);
		app.getAssetManager().load("skin/uiskin.json", Skin.class);
		app.getAssetManager().finishLoading();

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0.6f, 0, 0, 1);

		stage.act(delta);
		stage.draw();
	}

	@Override
	public void show() {
		super.show();
		showMainMenu();

	}

	public void showMainMenu() {
		stage.clear();
		Label header = new Label("Unkown", app.getAssetManager().get("skin/uiskin.json", Skin.class));
		header.setColor(1, 1, 1, 1);
		header.setFontScale(2f);

		TextButton exit = new TextButton("Exit", app.getAssetManager().get("skin/uiskin.json", Skin.class));

		exit.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {

				Gdx.app.exit();

			}

		});

		TextButton play = new TextButton("Play", app.getAssetManager().get("skin/uiskin.json", Skin.class));

		play.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				app.setScreen(app.getGameScreen());
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
		table.add(play).padBottom(30).width(85);
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

				stage.clear();
				show();

			}

		});

		CheckBox fullscreen = new CheckBox(" Fullscreen",
				getApp().getAssetManager().get("skin/uiskin.json", Skin.class));

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
		table.add(back).width(85);

		table.addAction(Actions.sequence(Actions.alpha(0),
				Actions.parallel(Actions.fadeIn(0.5f), Actions.moveBy(0, -20, 0.5f, Interpolation.pow5Out))));

		stage.addActor(table);

	}

}
