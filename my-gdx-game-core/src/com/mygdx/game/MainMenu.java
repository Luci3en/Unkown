package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class MainMenu implements Screen {

	
	private Stage stage;
	private TextureAtlas atlas;
	private Skin skin;
	private Label label;
	private TextButton exit;
	private BitmapFont blackFont;
	
	
	
	
	@Override
	public void show() {
		stage = new Stage();
		blackFont = new BitmapFont(Gdx.files.internal("fonts/blackFont.fnt"));
		
		
		LabelStyle labelStyle = new LabelStyle();
		labelStyle.font = blackFont;
		
		label = new Label("Unkown", labelStyle);
		
		stage.addActor(label);
		
		
		
		
	
	}

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		stage.draw();

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
