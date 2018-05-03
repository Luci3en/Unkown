package game.utility;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;

import game.Map;

public class Hitbox {

	private Polygon polygon;
	private float offsetX, offsetY;

	public Hitbox(float x, float y, float offsetX, float offsetY, float width, float height) {
		this.offsetX = offsetX;

		this.offsetY = offsetY;
		this.polygon = new Polygon(new float[] { 0, 0, 0, height, width, height, width, 0 });

		this.polygon.setPosition(x + offsetX, y + offsetY);
		this.polygon.setOrigin(x + offsetX, y + offsetY);

	}

	public void setX(float x) {
		if (x >= 0 && x <= Map.MAP_PIXEL_WIDTH - getWidth()) {
			polygon.setPosition(x, polygon.getY());
			polygon.setOrigin(polygon.getX(), polygon.getY());
			
			

		} else {
			return;
		}

	}

	public void setY(float y) {

		if (y >= 0 && y <= Map.MAP_PIXEL_HEIGHT - getHeight()) {
			polygon.setPosition(polygon.getX(), y);
			polygon.setOrigin(polygon.getX(), polygon.getY());
		} else {
			return;
		}

	}
	
	public void render(ShapeRenderer shapeRenderer) {
		shapeRenderer.polygon(polygon.getTransformedVertices());
	}

	

	public float getWidth() {
		return polygon.getBoundingRectangle().getWidth();
	}

	public float getHeight() {
		return polygon.getBoundingRectangle().getHeight();
	}

	public float getX() {
		return polygon.getOriginX();
	}

	public float getY() {
		return polygon.getOriginY();
	}

	public Polygon getPolygon() {
		return polygon;
	}

	public void Polygon(Polygon hitbox) {
		this.polygon = hitbox;
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
