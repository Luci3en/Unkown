package game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import game.utility.BoundingPolygon;
import game.utility.EntityManager;

public class Creature extends Entity {

	private Animation<TextureRegion> currentAnimation, up_walking, down_walking, left_walking, right_walking;
	private float speed = 120;
	private float stateTime;
	private boolean moving;
	private Vector2 velocity;
	private Weapon weapon;

	public Creature(float x, float y) {
		super(x, y, new BoundingPolygon(x, y, 20, 15));
		this.weapon = new Weapon(200, 200);
		this.stateTime = 0;
		this.moving = false;
		this.velocity = new Vector2();

		TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("atlas/player_walking.atlas"));

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
	public void render(SpriteBatch spriteBatch) {

		if (currentAnimation != null) {

			TextureRegion currentFrame = (TextureRegion) currentAnimation.getKeyFrame(stateTime, true);

			if (isMoving()) {

				spriteBatch.draw(currentFrame, super.getX(), super.getY());

			}

			else {

				TextureRegion[] currentFrames = currentAnimation.getKeyFrames();
				spriteBatch.draw(currentFrames[0], super.getX(), super.getY());

			}
		}
	}

	@Override
	public void update(EntityManager entityManager) {
		weapon.update(super.getX() + 20, getY() + 30);

		if (getVelocity().x != 0 || getVelocity().y != 0) {
			stateTime += Gdx.graphics.getDeltaTime();
			setMoving(true);

			move(entityManager);

			if (getVelocity().x < 0) {
				currentAnimation = left_walking;
			}

			else if (getVelocity().x > 0) {
				currentAnimation = right_walking;
			}

			else if (getVelocity().y < 0) {
				currentAnimation = down_walking;
			}

			else if (getVelocity().y > 0) {
				currentAnimation = up_walking;
			}

			getVelocity().set(0, 0);
		}

		else {
			setMoving(false);
			return;
		}
	}

	public void move(EntityManager entityManager) {

		float old_tempX = super.getX();
		float old_tempY = super.getY();

		super.getBoundingPolygon().setPosition(super.getX() + getVelocity().x, super.getY() + getVelocity().y);

		super.setX(super.getX() + getVelocity().x);
		super.setY(getY() + getVelocity().y);

		if (getVelocity().x != 0) {
			super.setTouchedTiles(entityManager.findTiles(getBoundingPolygon()));

			if (entityManager.collidingWithEntity(this)) {

				super.getBoundingPolygon().setPosition(old_tempX, getY());
				super.setX(old_tempX);
			}

		}

		if (getVelocity().y != 0) {
			super.setTouchedTiles(entityManager.findTiles(getBoundingPolygon()));

			if (entityManager.collidingWithEntity(this)) {
				super.getBoundingPolygon().setPosition(super.getX(), old_tempY);
				super.setY(old_tempY);
			}
		}

	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}

}
