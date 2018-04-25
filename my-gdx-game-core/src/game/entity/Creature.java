package game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import game.utility.Hitbox;

public class Creature extends Entity {

	private Animation<TextureRegion> animation, up_walking, down_walking, left_walking, right_walking;
	private float stateTime;

	public Creature(float x, float y) {
		super(x, y, new Hitbox(x, y, 0, 0, 32, 32));

		this.stateTime = 0;

		TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("img/player_walking.atlas"));

		animation = new Animation<TextureRegion>(1f / 8f, textureAtlas.findRegions("up_walking"));
		up_walking = animation;

		animation = new Animation<TextureRegion>(1f / 8f, textureAtlas.findRegions("down_walking"));
		down_walking = animation;

		animation = new Animation<TextureRegion>(1f / 10f, textureAtlas.findRegions("left_walking"));
		left_walking = animation;

		animation = new Animation<TextureRegion>(1f / 10f, textureAtlas.findRegions("right_walking"));
		right_walking = animation;

	}

	@Override
	public void render(SpriteBatch spriteBatch, EntityManager entityManager, Map map) {
		stateTime += Gdx.graphics.getDeltaTime();
		update(Gdx.graphics.getDeltaTime(), entityManager, map);

		if (animation != null) {

			TextureRegion currentFrame = (TextureRegion) animation.getKeyFrame(stateTime, true);

			if (super.getVelocity().x != 0 || super.getVelocity().y != 0) {
				spriteBatch.draw(currentFrame, super.getX(), super.getY());
			} else {
				
				TextureRegion[] currentFrames = animation.getKeyFrames();
				spriteBatch.draw(currentFrames[0], super.getX(), super.getY());

			}
		}
	}

	public void update(float delta, EntityManager entityManager, Map map) {

		if (super.getVelocity().x != 0 || super.getVelocity().y != 0) {
			
		
			super.move(entityManager, map );
			super.getVelocity().set(0, 0);
		}
	}

}
