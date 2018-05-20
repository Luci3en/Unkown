package game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import game.utility.BoundingPolygon;

public class Weapon {

	private BoundingPolygon polygon;
	private float x, y;

	public Weapon(float x, float y) {
		this.x = x;
		this.y = y;
		this.polygon = new BoundingPolygon(x, y, 5, 32);
		this.polygon.setRectangleBoundary();

	}

	public void update(float x, float y) {

		polygon.setPosition(x, y);
		polygon.setOrigin(0, 0);

		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {

			
					polygon.setRotation(0);

				polygon.rotate(-1.8f * 2);

				System.out.println(polygon.getRotation());
			}

		}


	public void render(ShapeRenderer shapeRenderer) {

		polygon.render(shapeRenderer);

	}

	public BoundingPolygon getPolygon() {
		return polygon;
	}

	public void setPolygon(BoundingPolygon polygon) {
		this.polygon = polygon;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

}
