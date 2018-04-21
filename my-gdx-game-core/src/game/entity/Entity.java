package game.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {

	private float x, y;
	private float speed = 100;
	private Vector2 velocity;
	private Sprite sprite;
	private Rectangle hitBox;
	private boolean collides;

	public Entity(float x, float y) {
		this.velocity = new Vector2();
		this.x = x;
		this.y = y;
	}

	public void render(SpriteBatch spriteBatch) {
		sprite.setX(getX());
		sprite.setY(getY());
		sprite.draw(spriteBatch);
	}

	public boolean move() {

		float tempx = hitBox.getX() - 1;
		float tempy = hitBox.getY();
		float deltax = 0;
		float deltay = 0;

		hitBox.setX(hitBox.getX() + velocity.x);
		hitBox.setY(hitBox.getY() + velocity.y);

		if (collides) {

			if (velocity.x > 0) {
				deltax = hitBox.getX() - tempx;
				hitBox.setX(tempx);
				velocity.x = 0;
			}
			if (velocity.y > 0) {
				deltay = hitBox.getY() - tempy;
				hitBox.setY(tempy);
				velocity.y = 0;
			}

			return false;
		} else {
			return true;
		}

	}

	public boolean move2() {

		if (!collides) {
			if (velocity.x != 0 || velocity.y != 0) {
				return true;
			} else {
				return false;
			}
		} else {
			velocity.x = -velocity.x;
			velocity.y = -velocity.y;
			setX(getX() + velocity.x * 2);
			setY(getY() + velocity.y * 2);
			return false;
		}

	}
	
	public void collide() {
		
		
		
	}

	public boolean collision(Entity entity) {

		if (move()) {
			if (Intersector.overlaps(hitBox, entity.getHitBox())) {
				collides = true;
				return true;

			} else {
				collides = false;
				return false;

			}
		} else {
			return false;
		}

	}

	public void debugHitbox(ShapeRenderer shapeRenderer) {
		shapeRenderer.rect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		if (x >= 0 && x <= World.MAP_PIXEL_WIDTH - getHitBox().width) {
			this.x = x;
		} else {
			return;
		}

	}

	public float getY() {
		return y;
	}

	public void setY(float y) {

		if (y >= 0 && y <= World.MAP_PIXEL_HEIGHT - getHitBox().height) {
			this.y = y;
		} else {
			return;
		}

	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public Rectangle getHitBox() {
		return hitBox;
	}

	public void setHitBox(Rectangle hitbox) {
		this.hitBox = hitbox;
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

}
