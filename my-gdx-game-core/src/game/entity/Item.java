package game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Item extends Sprite {

	private Rectangle hitbox;

	public Item(Texture texture, float x, float y) {
		super(texture);
		super.setX(x);
		super.setY(y);
		hitbox = new Rectangle(super.getX(), super.getY(), texture.getWidth(), texture.getHeight());

	}

	public Rectangle getHitbox() {
		return hitbox;
	}

	public void setHitbox(Rectangle hitbox) {
		this.hitbox = hitbox;
	}

}
