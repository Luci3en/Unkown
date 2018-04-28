package game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class SettingsScreen extends AbstractScreen {

	public SettingsScreen(AssetManager assetManager) {
		super(assetManager, 800f, 800f);
	}

	@Override
	public void buildStage() {

		Label header = new Label("Settings", getAssetManager().get("skin/metal-ui.json", Skin.class));
		header.setFontScale(1.5f);

		TextButton back = new TextButton("Back", getAssetManager().get("skin/metal-ui.json", Skin.class));
		back.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {

				((Game) Gdx.app.getApplicationListener()).setScreen(new MenuScreen(getAssetManager()));

			}

		});

		CheckBox fullscreen = new CheckBox(" Fullscreen", getAssetManager().get("skin/metal-ui.json", Skin.class));
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
