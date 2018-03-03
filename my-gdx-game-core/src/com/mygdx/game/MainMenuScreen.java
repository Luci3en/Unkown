package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MainMenuScreen implements Screen {

	private Stage stage;
	private TextureAtlas atlas;
	private Skin skin;
	private Label label;
	private TextButton exit, play;
	private BitmapFont blackFont;

	@Override
	public void show() {

		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		blackFont = new BitmapFont(Gdx.files.internal("fonts/blackFont.fnt"));
		atlas = new TextureAtlas(Gdx.files.internal("skin/neutralizer-ui.atlas"));
		skin = new Skin(atlas);

		LabelStyle labelStyle = new LabelStyle();
		labelStyle.font = blackFont;
		label = new Label("Unkown", labelStyle);
		label.moveBy(100, 100);

		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.getDrawable("button");
		textButtonStyle.down = skin.getDrawable("button-pressed");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = blackFont;
		exit = new TextButton("Exit", textButtonStyle);

		exit.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {

				System.exit(0);

			}

		});

		textButtonStyle.up = skin.getDrawable("button");
		textButtonStyle.down = skin.getDrawable("button-pressed");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = blackFont;
		play = new TextButton("Play", textButtonStyle);

		
		play.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {

				((Game) Gdx.app.getApplicationListener()).setScreen(new TestScreen());

			}

		});
		
		


		Table table = new Table();
		table.setFillParent(true);
		table.debug();

		table.add(label);
		table.row();
		table.add(play);
		table.row();

		table.add(exit);

		stage.addActor(table);

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act(delta);
		stage.draw();

		System.out.println("MENU");
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
		stage.dispose();
		blackFont.dispose();
		atlas.dispose();
		skin.dispose();
	}

}
