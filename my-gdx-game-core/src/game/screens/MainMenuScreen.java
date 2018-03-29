package game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import game.Game;

public class MainMenuScreen extends AbstractScreen {

	public MainMenuScreen(AssetManager assetManager) {
		super(assetManager, 800f, 800f);
		this.buildStage();
	}

	@Override
	public void buildStage() {

		BitmapFont blackFont = new BitmapFont(Gdx.files.internal("fonts/blackFont.fnt"));
		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("skin/neutralizer-ui.atlas"));
		Skin skin = new Skin(atlas);

		LabelStyle labelStyle = new LabelStyle();
		labelStyle.font = blackFont;
		Label header = new Label("Unkown", labelStyle);
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

		TextButton exit = new TextButton("Exit", textButtonStyle);

		exit.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {

				Gdx.app.exit();

			}

		});

		TextButton play = new TextButton("Play", textButtonStyle);
		play.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {

				((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen(getAssetManager()));

			}

		});

		TextButton settings = new TextButton("Settings", textButtonStyle);
		settings.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {

				((Game) Gdx.app.getApplicationListener()).setScreen(new SettingsScreen(getAssetManager()));

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
		addActor(table);

	}

}
