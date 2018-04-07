package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;

import game.screen.GameScreen;
import game.screen.MenuScreen;
import game.screen.SplashScreen;

public class Game extends com.badlogic.gdx.Game {

	public final static String TITEL = "Unknown";

	@Override
	public void create() {
		Gdx.app.log(TITEL, "create()");
		super.setScreen(new GameScreen(new AssetManager()));

	}

	@Override
	public void resize(int width, int height) {
		Gdx.app.log(TITEL, "resize(" + width + ", " + height + ")");
		super.getScreen().resize(width, height);

	}

	@Override
	public void render() {
		super.getScreen().render(Gdx.graphics.getDeltaTime());

	}

	@Override
	public void pause() {
		Gdx.app.log(TITEL, "pause()");
		super.getScreen().pause();

	}

	@Override
	public void resume() {
		Gdx.app.log(TITEL, "resume()");
		super.getScreen().resume();

	}

	@Override
	public void dispose() {
		Gdx.app.log(TITEL, "dispose()");
		super.getScreen().dispose();

	}

}
