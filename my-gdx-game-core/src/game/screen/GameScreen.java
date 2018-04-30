package game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import game.Application;
import game.Player;
import game.World;

public class GameScreen extends AbstractScreen {

	private World world;
	private Player player;

	public GameScreen(Application app) {
		super(app);
		this.player = new Player();

	}

	@Override
	public void show() {

		world = new World(player);

		InputMultiplexer inputMultiplexer = new InputMultiplexer();
		inputMultiplexer.addProcessor(player);
		inputMultiplexer.addProcessor(stage);
		Gdx.input.setInputProcessor(inputMultiplexer);

		Label header = new Label("FPS: ", app.getAssetManager().get("skin/metal-ui.json", Skin.class));
		header.setName("label");
		header.setColor(0, 0, 0, 1);
		header.setFontScale(2f);
		header.setPosition(4, stage.getViewport().getWorldHeight() - 20);
		stage.addActor(header);

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		Label temp = (Label) stage.getActors().get(0);
		temp.setText("Fps: " + Gdx.graphics.getFramesPerSecond());

		player.update(delta, world.getEntityManager());
		world.update(player);
		stage.act(delta);

		world.render(app.getSpriteBatch());
		stage.draw();
	}

	@Override
	public void dispose() {
		super.dispose();
	}

}
