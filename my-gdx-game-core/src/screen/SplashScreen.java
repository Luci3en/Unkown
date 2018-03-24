package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Game;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;
import utility.SpriteAccessor;

public class SplashScreen implements Screen {

	private Sprite sprite;
	private SpriteBatch batch;
	private TweenManager manager;

	@Override
	public void show() {

		manager = new TweenManager();
		Tween.registerAccessor(Sprite.class, new SpriteAccessor());

		batch = new SpriteBatch();
		Texture texture = new Texture(Gdx.files.internal("img/Logo.jpg"));
		sprite = new Sprite(texture);
		sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		Tween.set(sprite, SpriteAccessor.APLHA).target(0).start(manager);
		Tween.to(sprite, SpriteAccessor.APLHA, 2).target(1).start(manager);
		Tween.to(sprite, SpriteAccessor.APLHA, 2).target(0).delay(2).start(manager).setCallback(new TweenCallback() {

			@Override
			public void onEvent(int arg0, BaseTween<?> arg1) {
				((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen());

			}
		});

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		manager.update(delta);

		batch.begin();

		sprite.draw(batch);

		batch.end();

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {

	}

}
