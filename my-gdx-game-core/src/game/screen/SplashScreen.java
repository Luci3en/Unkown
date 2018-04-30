package game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;
import game.Application;
import game.utility.SpriteAccessor;

public class SplashScreen extends AbstractScreen {

	private Sprite sprite;
	private TweenManager tweenManager;

	public SplashScreen(Application app) {
		super(app);

		tweenManager = new TweenManager();
		Tween.registerAccessor(Sprite.class, new SpriteAccessor());

		Texture texture = new Texture(Gdx.files.internal("img/Logo.jpg"));
		sprite = new Sprite(texture);
		sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		Tween.set(sprite, SpriteAccessor.APLHA).target(0).start(tweenManager);
		Tween.to(sprite, SpriteAccessor.APLHA, 2).target(1).start(tweenManager);
		Tween.to(sprite, SpriteAccessor.APLHA, 2).target(0).delay(2).start(tweenManager)
				.setCallback(new TweenCallback() {

					@Override
					public void onEvent(int arg0, BaseTween<?> arg1) {
						dispose();
						getApp().setScreen(getApp().getMenuScreen());

					}
				});

	}



	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		tweenManager.update(delta);

		getApp().getSpriteBatch().begin();

		sprite.draw(getApp().getSpriteBatch());

		getApp().getSpriteBatch().end();

	}
	
	@Override
	public void dispose() {
		sprite.getTexture().dispose();

	}

}
