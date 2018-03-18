package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameScreen implements Screen, InputProcessor {

	private Stage stage;
	private TiledMap map;
	private OrthogonalTiledMapRenderer mapRendere;
	private FirstActor player;

	@Override
	public void show() {
		
		stage = new Stage();
		InputMultiplexer multiplexer = new InputMultiplexer();
		Gdx.input.setInputProcessor(multiplexer);
		multiplexer.addProcessor(this);
		multiplexer.addProcessor(stage);
		
		
		
	
		map = new TmxMapLoader().load("maps/map.tmx");
		mapRendere = new OrthogonalTiledMapRenderer(map);

		player = new FirstActor();
		
		stage.addListener(new InputListener() {
			
			public boolean keyDown(com.badlogic.gdx.scenes.scene2d.InputEvent event, int keycode) {
				
				
				if(keycode == Input.Keys.O) {
					Gdx.app.log("show()", "set inputprocessor for stage KEY O");
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

		if (Gdx.input.isKeyPressed(Input.Keys.K))

		{
			map.getLayers().get(0).setVisible(false);
		}
		
		
		movePlayer();
		
		stage.getCamera().position.x = player.getX();
		stage.getCamera().position.y = player.getY();
		stage.getCamera().update();
		stage.getBatch().setProjectionMatrix(stage.getCamera().combined);

		mapRendere.setView((OrthographicCamera) stage.getCamera());
		mapRendere.render();

		stage.getBatch().begin();

		player.draw(stage.getBatch(), 1);

		stage.getBatch().end();


	}

	public void movePlayer() {

		if (Gdx.input.isKeyPressed(Input.Keys.U)) {
			Gdx.app.log("movePlayer()", "static usage of inputprocessor KEY U");
		}
		
		
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			player.setY(player.getY() + 2);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			player.setY(player.getY() - 2);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			player.setX(player.getX() - 2);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			player.setX(player.getX() + 2);
		}

	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height);

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
		
		if(keyCode == Input.Keys.I) {
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
