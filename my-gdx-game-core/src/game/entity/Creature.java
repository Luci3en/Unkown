package game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import game.Map;
import game.utility.EntityManager;
import game.utility.Hitbox;

public class Creature extends Entity {

	private Animation<TextureRegion> currentAnimation, up_walking, down_walking, left_walking, right_walking;
	private float stateTime;
	private boolean moving;

	public Creature(float x, float y) {
		super(x, y, new Hitbox(x, y, 0, 0, 20, 15));

		this.stateTime = 0;
		this.moving = false;

		TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("img/player_walking.atlas"));

		currentAnimation = new Animation<TextureRegion>(1f / 8f, textureAtlas.findRegions("up_walking"));
		up_walking = currentAnimation;

		currentAnimation = new Animation<TextureRegion>(1f / 8f, textureAtlas.findRegions("down_walking"));
		down_walking = currentAnimation;

		currentAnimation = new Animation<TextureRegion>(1f / 10f, textureAtlas.findRegions("left_walking"));
		left_walking = currentAnimation;

		currentAnimation = new Animation<TextureRegion>(1f / 10f, textureAtlas.findRegions("right_walking"));
		right_walking = currentAnimation;

	}

	@Override
	public void render(SpriteBatch spriteBatch, EntityManager entityManager, Map map) {
		stateTime += Gdx.graphics.getDeltaTime();

		update(Gdx.graphics.getDeltaTime(), entityManager, map);

		if (currentAnimation != null) {

			TextureRegion currentFrame = (TextureRegion) currentAnimation.getKeyFrame(stateTime, true);

			if (isMoving()) {

				spriteBatch.draw(currentFrame, super.getX(), super.getY());
			} else {

				TextureRegion[] currentFrames = currentAnimation.getKeyFrames();
				spriteBatch.draw(currentFrames[0], super.getX(), super.getY());

			}
		}
	}

	public void update(float delta, EntityManager entityManager, Map map) {

		if (super.getVelocity().x != 0 || super.getVelocity().y != 0) {

			moving = true;
			move(entityManager, map);

			if (super.getVelocity().x < 0) {
				currentAnimation = left_walking;
			}

			if (super.getVelocity().x > 0) {
				currentAnimation = right_walking;
			}

			if (super.getVelocity().y < 0) {
				currentAnimation = down_walking;
			}

			if (super.getVelocity().y > 0) {
				currentAnimation = up_walking;
			}

			super.getVelocity().set(0, 0);
		} else {
			moving = false;
		}
	}

	public void move(EntityManager entityManager, Map map) {

		float old_tempX = super.getHitbox().getX();
		float old_tempY = super.getHitbox().getY();

		super.getHitbox().setX(super.getHitbox().getX() + super.getVelocity().x);
		super.getHitbox().setY(super.getHitbox().getY() + super.getVelocity().y);
		super.setX(getX() + super.getVelocity().x);
		super.setY(getY() + super.getVelocity().y);

		if (super.getVelocity().x != 0) {
			super.setTouchedTiles(entityManager.findTiles(getHitbox(), map));

			if (entityManager.collidingWithEntity(this)) {

				System.out.println("reset x");
				super.getHitbox().setX(old_tempX);
				super.setX(old_tempX);
			}

		}

		if (super.getVelocity().y != 0) {
			super.setTouchedTiles(entityManager.findTiles(getHitbox(), map));
			if (entityManager.collidingWithEntity(this)) {

				System.out.println("reset y");
				super.getHitbox().setY(old_tempY);
				super.setY(old_tempY);
			}
		}

	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

}
