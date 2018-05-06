package game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
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
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.6f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act();
		stage.draw();
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
