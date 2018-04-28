package game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import game.utility.Hitbox;

public class Weapon {

	private float x, y;
	private Hitbox hitbox;
	private Sprite sprite;

	public Weapon(float x, float y) {

		this.hitbox = new Hitbox(x, y, 0, 0, 50, 50);
	}

	public void attack() {

		Vector2 mouse = new Vector2(Gdx.input.getX(), Gdx.input.getY());

	//	System.out.println(MathUtils.atan2(hitbox.getX(), hitbox.getY()) - MathUtils.atan2(mouse.x, mouse.y));
		System.out.println(MathUtils.atan2(hitbox.getX() * mouse.y - hitbox.getY() * mouse.x,
				hitbox.getX() * mouse.x + hitbox.getY() * mouse.y));

		// direction = new Vector2((float) Math.cos(Math.toRadians(degrees)),
		// (float) Math.sin(Math.toRadians(degrees)));

		for (int i = 0; i < 40; i++) {
			if (i < 19)
				x += 2;
			else
				x -= 2;
		}
	}

	public float[] rotate(float[] _vertices, float rotation, Vector2 rotdot) {

		final float[] vertices = _vertices;
		final float cos = MathUtils.cosDeg(rotation);
		final float sin = MathUtils.sinDeg(rotation);

		for (int i = 0; i < vertices.length; i += 2) {
			float x = vertices[i];
			float y = vertices[i + 1];

			if (rotation != 0) {
				float oldX = x;

				x = rotdot.x + cos * (x - rotdot.x) - sin * (y - rotdot.y);
				y = rotdot.y + sin * (oldX - rotdot.y) + cos * (y - rotdot.y);
			}
			vertices[i] = x;
			vertices[i + 1] = y;
			// vertices[i] = positionX + x + originX;
			// vertices[i + 1] = positionY + y + originY;
		}
		return vertices;

	}

}
