package game.utility;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;

import game.Map;

public class BoundingPolygon extends Polygon {

	private float width, height;

	public BoundingPolygon(float x, float y, float width, float height) {
		this.width = width;
		this.height = height;
		setOrigin(width / 2, height / 2);
		setPosition(x, y);
	}

	public void setRectangleBoundary() {
		float[] vertices = { 0, 0, getWidth(), 0, getWidth(), getHeight(), 0, getHeight() };
		setVertices(vertices);
		setOrigin(getOriginX(), getOriginY());
	}

	public void render(ShapeRenderer shapeRenderer) {
		shapeRenderer.polygon(getTransformedVertices());
		shapeRenderer.circle(getX(), getY(), 1.4f);
		shapeRenderer.circle(getOriginX(), getOriginY(), 1.2f);
	}

	public void update() {

		setOrigin(0, 0);
		rotate(-1.8f);

	}

	@Override
	public void setPosition(float x, float y) {
		// HIER MATH.CLAMP einbauen !

		if (y >= 0 && y <= Map.MAP_PIXEL_HEIGHT - getHeight() && x >= 0 && x <= Map.MAP_PIXEL_WIDTH - getWidth()) {
			super.setPosition(x, y);
		} else {
			return;
		}

	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

}
