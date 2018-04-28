package game.utility;

import com.badlogic.gdx.math.Rectangle;

import game.Map;

public class Hitbox {

	private Rectangle hitbox;
	private float offsetX, offsetY;

	public Hitbox(float x, float y, float offsetX, float offsetY, float width, float height) {
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.hitbox = new Rectangle(x + offsetX, y + offsetY, width, height);
	

	}

	public void setX(float x) {
		if (x >= 0 && x <= Map.MAP_PIXEL_WIDTH - hitbox.width) {
			hitbox.x = x;
		} else {
			return;
		}

	}

	public void setY(float y) {

		if (y >= 0 && y <= Map.MAP_PIXEL_HEIGHT - hitbox.height) {
			hitbox.y = y;
		} else {
			return;
		}

	}

	public float getWidth() {
		return hitbox.getWidth();
	}

	public float getHeight() {
		return hitbox.getHeight();
	}

	public float getX() {
		return hitbox.getX();
	}

	public float getY() {
		return hitbox.getY();
	}

	public Rectangle getHitbox() {
		return hitbox;
	}

	public void setHitbox(Rectangle hitbox) {
		this.hitbox = hitbox;
	}

	public float getOffsetX() {
		return offsetX;
	}

	public void setOffsetX(float offsetX) {
		this.offsetX = offsetX;
	}

	public float getOffsetY() {
		return offsetY;
	}

	public void setOffsetY(float offsetY) {
		this.offsetY = offsetY;
	}

}
