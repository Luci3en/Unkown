package game.utility;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;

public class BoundingPolygon extends Polygon {

	private float width, height;

	public BoundingPolygon(float x, float y, float width, float height) {
		this.width = width;
		this.height = height;
		setOrigin(0, 0);
		setPosition(x, y);

	}

	public void setRectangleBoundary() {
		float[] vertices = { 0, 0, getWidth(), 0, getWidth(), getHeight(), 0, getHeight() };
		setVertices(vertices);
		setOrigin(getOriginX(), getOriginY());
	}

	public void render(ShapeRenderer shapeRenderer) {
		shapeRenderer.polygon(getTransformedVertices());
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
