package game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;

import game.screen.GameScreen;
import game.screen.MenuScreen;
import game.screen.SplashScreen;

public class Application extends Game {

	public final static String TITEL = "Unknown";
	public final static int WIDTH = 1024;
	public final static int HEIGHT = 768;
	public final static float VERSION = 0.012f;

	private Preferences preferences;
	private OrthographicCamera camera;
	private AssetManager assetManager;

	private SplashScreen splashSreen;
	private GameScreen gameScreen;
	private MenuScreen menuScreen;

	@Override
	public void create() {
		Gdx.app.log("Application: ", "create()");

		this.preferences = Gdx.app.getPreferences("Preferences");
		this.loadPreferences();
		
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false, Application.WIDTH, Application.HEIGHT);
		this.assetManager = new AssetManager();
		

		
		this.splashSreen = new SplashScreen(this);
		this.menuScreen = new MenuScreen(this);
		this.gameScreen = new GameScreen(this);

		super.setScreen(menuScreen);

	}

	public void loadPreferences() {

		if (preferences.contains("fullscreen") && preferences.getBoolean("fullscreen") == true) {

			Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
		} else {

			Gdx.graphics.setWindowedMode(1024, 768);
		}

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

	public Preferences getPreferences() {
		return preferences;
	}

	public void setPreferences(Preferences preferences) {
		this.preferences = preferences;
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
