package game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import game.Game;

public class MenuScreen extends AbstractScreen {

	public MenuScreen(AssetManager assetManager) {
		super(assetManager, 800f, 800f);
		this.buildStage();
	}

	@Override
	public void buildStage() {

		getAssetManager().load("skin/metal-ui.json", Skin.class);
		getAssetManager().finishLoading();

		Label header = new Label("Unkown", getAssetManager().get("skin/metal-ui.json", Skin.class));
		header.setColor(1, 1, 1, 1);
		header.setFontScale(2f);

		TextButton exit = new TextButton("Exit", getAssetManager().get("skin/metal-ui.json", Skin.class));

		exit.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {

				Gdx.app.exit();

			}

		});

		TextButton play = new TextButton("Play", getAssetManager().get("skin/metal-ui.json", Skin.class));
		play.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {

				((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen(getAssetManager()));
		
				// welcher screen genau wird disposed;
				//dispose();
		

			}

		});

		TextButton settings = new TextButton("Settings", getAssetManager().get("skin/metal-ui.json", Skin.class));
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
