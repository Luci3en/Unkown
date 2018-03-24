package screen;

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
import com.mygdx.game.Game;

public class MainMenuScreen implements Screen {

	private Stage stage;
	private TextureAtlas atlas;
	private Skin skin;
	private Label header;
	private TextButton exit, play, settings;
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
		header = new Label("Unkown", labelStyle);
		header.setFontScale(2f);

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

		exit = new TextButton("Exit", textButtonStyle);

		exit.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {

				System.exit(0);

			}

		});

		play = new TextButton("Play", textButtonStyle);
		play.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {

				((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen());

			}

		});

		settings = new TextButton("Settings", textButtonStyle);
		settings.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {

				((Game) Gdx.app.getApplicationListener()).setScreen(new SettingsScreen());

			}

		});

		Table table = new Table();
		table.setFillParent(true);
		// table.debug();

		table.add(header).pad(100, 300, 100, 300);
		table.row();
		table.add(play).padBottom(30);
		table.row();
		table.add(settings).padBottom(30);
		table.row();
		table.add(exit);
		stage.addActor(table);

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
