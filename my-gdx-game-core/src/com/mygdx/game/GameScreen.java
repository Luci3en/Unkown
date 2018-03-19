package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameScreen implements Screen, InputProcessor {

	public static int VIEWPORT_WIDTH = 800;
	public static int VIEWPORT_HEIGHT = 480;

	private Stage stage;
	private OrthographicCamera camera;
	private SpriteBatch batch;

	private TiledMap map;
	private OrthogonalTiledMapRenderer mapRendere;
	private Player player;

	@Override
	public void show() {

		stage = new Stage();

		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, GameScreen.VIEWPORT_WIDTH, GameScreen.VIEWPORT_HEIGHT);
		batch.setProjectionMatrix(camera.combined);

		map = new TmxMapLoader().load("maps/map.tmx");
		mapRendere = new OrthogonalTiledMapRenderer(map);

		Texture texture = new Texture(Gdx.files.internal("img/player.png"));
		player = new Player(new Sprite(texture));

		InputMultiplexer multiplexer = new InputMultiplexer();
		Gdx.input.setInputProcessor(multiplexer);
		multiplexer.addProcessor(this);
		multiplexer.addProcessor(player);

		// ExampleCode using Listener
		stage.addListener(new InputListener() {

			public boolean keyDown(com.badlogic.gdx.scenes.scene2d.InputEvent event, int keycode) {

				if (keycode == Input.Keys.O) {
					Gdx.app.log("show()", "set inputProcessor for stage KEY O");
				}
				return false;

			};
		}

		);

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float arg0) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// update()

		// ExampleCode
		if (Gdx.input.isKeyPressed(Input.Keys.U)) {
			Gdx.app.log("movePlayer()", "static usage of inputProcessor KEY U");
		}

		// render()
		mapRendere.setView(camera);
		mapRendere.render();

		batch.begin();
		player.draw(batch, 1);
		batch.end();

		// camerMovment
		// camera.translate(player.getX(), player.getY(), 0);

		camera.position.x = player.getX();
		camera.position.y = player.getY();
		camera.update();

	}

	@Override
	public void resize(int width, int height) {
		camera.viewportHeight = height;
		camera.viewportWidth = width;
		camera.update();

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean keyDown(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keyCode) {
		// ExampleCode
		if (keyCode == Input.Keys.I) {
			Gdx.app.log("keyUp()", "using InputProcessor Interface KEY I");
		}

		return false;
	}

	@Override
	public boolean mouseMoved(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		return false;
	}

}
