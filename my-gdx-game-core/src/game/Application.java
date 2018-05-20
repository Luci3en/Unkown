package game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.screen.GameScreen;
import game.screen.MenuScreen;
import game.screen.SplashScreen;

public class Application extends Game {

	public final static String TITEL = "Unknown";
	public final static int WIDTH = 1024;
	public final static int HEIGHT = 768;
	public final static float VERSION = 0.012f;

	private OrthographicCamera camera;
	private SpriteBatch spriteBatch;
	private AssetManager assetManager;

	private SplashScreen splashSreen;
	private GameScreen gameScreen;
	private MenuScreen menuScreen;

	@Override
	public void create() {
		Gdx.app.log("Application: ", "create()");
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false, Application.WIDTH, Application.HEIGHT);
		this.assetManager = new AssetManager();
		this.spriteBatch = new SpriteBatch();

		this.splashSreen = new SplashScreen(this);
		this.menuScreen = new MenuScreen(this);
		this.gameScreen = new GameScreen(this);

		super.setScreen(gameScreen);

	}

	@Override
	public void resize(int width, int height) {
		Gdx.app.log("Application", "resize(" + width + ", " + height + ")");
		super.resize(width, height);

	}

	@Override
	public void render() {
		super.render();

	}

	@Override
	public void dispose() {
		Gdx.app.log("Application", "dispose()");
		spriteBatch.dispose();
		assetManager.dispose();
		splashSreen.dispose();
		gameScreen.dispose();
		menuScreen.dispose();

	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	public void setCamera(OrthographicCamera camera) {
		this.camera = camera;
	}

	public SpriteBatch getSpriteBatch() {
		return spriteBatch;
	}

	public void setSpriteBatch(SpriteBatch spriteBatch) {
		this.spriteBatch = spriteBatch;
	}

	public AssetManager getAssetManager() {
		return assetManager;
	}

	public void setAssetManager(AssetManager assetManager) {
		this.assetManager = assetManager;
	}

	public SplashScreen getSplashSreen() {
		return splashSreen;
	}

	public void setSplashSreen(SplashScreen splashSreen) {
		this.splashSreen = splashSreen;
	}

	public GameScreen getGameScreen() {
		return gameScreen;
	}

	public void setGameScreen(GameScreen gameScreen) {
		this.gameScreen = gameScreen;
	}

	public MenuScreen getMenuScreen() {
		return menuScreen;
	}

	public void setMenuScreen(MenuScreen menuScreen) {
		this.menuScreen = menuScreen;
	}

}
