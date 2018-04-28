package game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import game.Map;
import game.utility.Hitbox;

public class Creature extends Entity {

	private Animation<TextureRegion> animation, up_walking, down_walking, left_walking, right_walking;
	private float stateTime;
	private boolean moving;
	private Inventory inventory;

	public Creature(float x, float y) {
		super(x, y, new Hitbox(x, y, 0, 0, 20, 15));

		this.stateTime = 0;
		this.moving = false;
		this.inventory = new Inventory();

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
	public String toString() {
		return "Creature [animation=" + animation + ", up_walking=" + up_walking + ", down_walking=" + down_walking
				+ ", left_walking=" + left_walking + ", right_walking=" + right_walking + ", stateTime=" + stateTime
				+ ", moving=" + moving + ", toString()=" + super.toString() + ", getX()=" + getX() + ", getY()="
				+ getY() + ", getId()=" + getId() + ", getHitbox()=" + getHitbox() + ", getHitBox()=" + getHitBox()
				+ ", getSprite()=" + getSprite() + ", getVelocity()=" + getVelocity() + ", getSpeed()=" + getSpeed()
				+ ", getTouchedTiles()=" + getTouchedTiles() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + "]";
	}

	@Override
	public void render(SpriteBatch spriteBatch, EntityManager entityManager, Map map) {
		stateTime += Gdx.graphics.getDeltaTime();

		update(Gdx.graphics.getDeltaTime(), entityManager, map);

		if (animation != null) {

			TextureRegion currentFrame = (TextureRegion) animation.getKeyFrame(stateTime, true);

			if (isMoving()) {

				spriteBatch.draw(currentFrame, super.getX(), super.getY());
			} else {

				TextureRegion[] currentFrames = animation.getKeyFrames();
				spriteBatch.draw(currentFrames[0], super.getX(), super.getY());

			}
		}
	}

	public void update(float delta, EntityManager entityManager, Map map) {

		if (super.getVelocity().x != 0 || super.getVelocity().y != 0) {

			moving = true;
			move(entityManager, map);

			if (super.getVelocity().x < 0) {
				animation = left_walking;
			}

			if (super.getVelocity().x > 0) {
				animation = right_walking;
			}

			if (super.getVelocity().y < 0) {
				animation = down_walking;
			}

			if (super.getVelocity().y > 0) {
				animation = up_walking;
			}

			super.getVelocity().set(0, 0);
		} else {
			moving = false;
		}
	}
	
	
	

	public void move(EntityManager entityManager, Map map) {

		float old_tempX = super.getHitBox().getX();
		float old_tempY = super.getHitBox().getY();

		super.getHitBox().setX(super.getHitBox().getX() + super.getVelocity().x);
		super.getHitBox().setY(super.getHitBox().getY() + super.getVelocity().y);
		super.setX(getX() + super.getVelocity().x);
		super.setY(getY() + super.getVelocity().y);

		if (super.getVelocity().x != 0) {
			super.setTouchedTiles(entityManager.findTiles(getHitBox(), map));

			if (entityManager.collidingWithEntity(this)) {

				System.out.println("reset x");
				super.getHitBox().setX(old_tempX);
				super.setX(old_tempX);
			}

		}

		if (super.getVelocity().y != 0) {
			super.setTouchedTiles(entityManager.findTiles(getHitBox(), map));
			if (entityManager.collidingWithEntity(this)) {

				System.out.println("reset y");
				super.getHitBox().setY(old_tempY);
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
