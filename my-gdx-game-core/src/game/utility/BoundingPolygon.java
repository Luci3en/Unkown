package game.utility;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;

public class BoundingPolygon extends Polygon {

	private float width, height;
	private float offsetX, offsetY;

	public BoundingPolygon(float x, float y, float width, float height) {
		this.width = width;
		this.height = height;
		this.offsetX = 0;
		this.offsetY = 0;
		setOrigin(width / 2, height / 2);
		setPosition(x, y);

	}

	public void setRectangleBoundary() {
		float[] vertices = { 0, 0, getWidth(), 0, getWidth(), getHeight(), 0, getHeight() };
		setVertices(vertices);
		setOrigin(getOriginX(), getOriginY());
	}

	@Override
	public void setPosition(float x, float y) {
		super.setPosition(x + offsetX, y + offsetY);
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
