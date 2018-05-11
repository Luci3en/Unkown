package game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import game.utility.Hitbox;

public class Weapon extends Entity {

	public Weapon(float x, float y) {
		super(x, y);
		super.setHitbox(new Hitbox(x, y, 0, 0, 20, 20));
	}

	public void attack(Camera camera) {
		Vector3 mouse = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);

		camera.unproject(mouse);

		double angle = Math.atan2(mouse.y - super.getHitbox().getY(), mouse.x - super.getHitbox().getX()) * 180
				/ Math.PI;

getHitbox().getPolygon().setVertices(rotate(getHitbox().getPolygon().getTransformedVertices(),(float) angle, new Vector2(super.getX(), super.getY())));

	}

	//
	// Vector3 mouse = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
	// game.getStage().getCamera().unproject(mouse);
	// origin.set(position.getX() + offsetX, position.getY() + 15);
	//
	// degrees = ((float) ((Math.atan2(mouse.x - origin.getX(), -(mouse.y -
	// origin.getY())) * 180.0d
	// / Math.PI)) - 90);
	// if (degrees < 0)
	// degrees = 360 + degrees;
	//
	// direction = new Vector((float) Math.cos(Math.toRadians(degrees)),
	// (float) Math.sin(Math.toRadians(degrees)));
	//
	// hitbox.setVertices(rotate(hitbox.getVertices(), degrees - 90, new Vector(0,
	// 0)));
	// float[] or = rotate(new float[] { hitbox.getOriginX(), hitbox.getOriginY() },
	// degrees - 90,
	// new Vector(hitbox.getOriginX() + 1, hitbox.getOriginY() - 12));
	// hitbox.setOrigin(or[0], or[1]);
	// hitbox.setPosition(hitbox.getOriginX(), hitbox.getOriginY());
	//

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

	public Vector2 rotate(float originX, float originY, float rotationX, float rotationY, float angle) {
		float s = MathUtils.cosDeg(angle);
		float c = MathUtils.sinDeg(angle);

		// translate point back to origin:
		originX -= rotationX;
		originY -= rotationY;

		// rotate point
		float xnew = originX * c - originY * s;
		float ynew = originX * s + originY * c;

		// translate point back:
		originX = xnew + rotationX;
		originY = ynew + rotationY;

		return new Vector2(originX, originY);
	}

}
