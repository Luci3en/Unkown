package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class SettingsScreen implements Screen {

	private Stage stage;
	private TextureAtlas atlas;
	private Skin skin;
	private Label header;
	private CheckBox fullscreen;
	private TextButton back;
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
		header = new Label("Settings", labelStyle);
		header.setFontScale(1.5f);

		TextButtonStyle textButtonStyle = new TextButtonStyle();

		textButtonStyle.up = skin.getDrawable("menu-button");
		textButtonStyle.down = skin.getDrawable("menu-button-down");
		textButtonStyle.over = skin.getDrawable("menu-button-over");
		textButtonStyle.up.setMinWidth(200);
		textButtonStyle.down.setMinWidth(200);
		textButtonStyle.over.setMinWidth(200);

		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = blackFont;

		back = new TextButton("Back", textButtonStyle);

		back.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {

				((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen());

			}

		});

		CheckBoxStyle checkBoxStyle = new CheckBoxStyle();
		checkBoxStyle.font = blackFont;
		checkBoxStyle.up = skin.getDrawable("checkbox");
		checkBoxStyle.checked = skin.getDrawable("checkbox-checked");

		fullscreen = new CheckBox("        ", checkBoxStyle);
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

		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		table.add(header).pad(100, 300, 100, 300);
		table.row();
		table.add(fullscreen).padBottom(30);
		table.row();
		table.add(back);
		stage.addActor(table);

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
	public void render(float delta) {
		Gdx.gl.glClearColor(0.1f, 0.3f, 0.4f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();

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
	public void dispose() {
		stage.dispose();
		blackFont.dispose();
		atlas.dispose();
		skin.dispose();

	}

}
