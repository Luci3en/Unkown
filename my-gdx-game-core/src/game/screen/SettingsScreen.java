package game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class SettingsScreen extends AbstractScreen {

	public SettingsScreen(AssetManager assetManager) {
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
		Label header = new Label("Settings", labelStyle);
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

		TextButton back = new TextButton("Back", textButtonStyle);
		back.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {

				((Game) Gdx.app.getApplicationListener()).setScreen(new MenuScreen(getAssetManager()));

			}

		});

		CheckBoxStyle checkBoxStyle = new CheckBoxStyle();
		checkBoxStyle.font = blackFont;
		checkBoxStyle.up = skin.getDrawable("checkbox");
		checkBoxStyle.checked = skin.getDrawable("checkbox-checked");

		CheckBox fullscreen = new CheckBox("        ", checkBoxStyle);
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

		table.add(header).pad(100, 300, 100, 300);
		table.row();
		table.add(fullscreen).padBottom(30);
		table.row();
		table.add(back);
		addActor(table);

	}

}
