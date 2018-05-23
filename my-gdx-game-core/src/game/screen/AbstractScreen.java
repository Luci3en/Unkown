package game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import game.Application;

public abstract class AbstractScreen implements Screen {

	protected final Application app;
	protected Stage stage;

	public AbstractScreen(Application app) {
		this.app = app;
		this.stage = new Stage(new StretchViewport(Application.WIDTH, Application.HEIGHT, app.getCamera()));

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		stage.act(delta);
		stage.draw();
	}

	public void showSettings() {
		stage.clear();

		Table table = new Table();

		Label header = new Label("Settings", getApp().getAssetManager().get("skin/uiskin.json", Skin.class));
		header.setFontScale(1.5f);

		TextButton back = new TextButton("Back", getApp().getAssetManager().get("skin/uiskin.json", Skin.class));

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

		CheckBox fps = new CheckBox(" Show FPS (coming soon)",
				getApp().getAssetManager().get("skin/uiskin.json", Skin.class));

		fps.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {

			}
		});

		if (this instanceof GameScreen) {
			back.addListener(new ClickListener() {

				@Override
				public void clicked(InputEvent event, float x, float y) {

					app.getGameScreen().showGameMenu();

				}

			});

		}

		else if (this instanceof MenuScreen) {
			Image background = new Image(getApp().getAssetManager().get("img/background.jpg", Texture.class));
			background.addAction(Actions.sequence(Actions.alpha(0.5f), Actions.fadeIn(0.4f)));
			stage.addActor(background);

			back.addListener(new ClickListener() {

				@Override
				public void clicked(InputEvent event, float x, float y) {

					stage.clear();
					show();

				}

			});
		}

		table.setFillParent(true);
		table.add(header).pad(100, 300, 100, 300);
		table.row();
		table.add(fps).padBottom(30);
		table.row();
		table.add(fullscreen).padBottom(30);
		table.row();
		table.add(back).width(85);

		table.addAction(Actions.sequence(Actions.alpha(0),
				Actions.parallel(Actions.fadeIn(0.5f), Actions.moveBy(0, -20, 0.5f, Interpolation.pow5Out))));

		stage.addActor(table);

	}

	@Override
	public void show() {
		Gdx.app.log(getClass().getName(), "show()");
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void resize(int width, int height) {
		Gdx.app.log(getClass().getName(), "resize()");
		stage.getViewport().update(width, height);
	}

	@Override
	public void hide() {
		Gdx.app.log(getClass().getName(), "hide()");
	}

	@Override
	public void pause() {
		Gdx.app.log(getClass().getName(), "pause()");
	}

	@Override
	public void resume() {
		Gdx.app.log(getClass().getName(), "resume()");
	}

	@Override
	public void dispose() {

		Gdx.app.log(getClass().getName(), "dispose()");

	}

	public Application getApp() {
		return app;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

}
