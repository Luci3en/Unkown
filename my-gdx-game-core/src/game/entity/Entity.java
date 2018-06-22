package game.entity;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Disposable;

import game.Map;
import game.Tile;
import game.World;
import game.utility.BoundingPolygon;

public abstract class Entity implements Disposable {

	public static int ID = 1;

	private int id;
	private Sprite sprite;
	private BoundingPolygon boundingPolygon;
	private ArrayList<Tile> touchedTiles;
	private float x, y;
	private boolean solid;

	public Entity() {
		// TODO Auto-generated constructor stub
	}

	public Entity(float x, float y, BoundingPolygon boundingPolygon) {
		this.id = Entity.ID;
		Entity.ID++;

		this.x = x;
		this.y = y;
		this.boundingPolygon = boundingPolygon;
		this.boundingPolygon.setRectangleBoundary();
		this.touchedTiles = new ArrayList<Tile>();

	}

	public Entity(float x, float y, boolean solid, BoundingPolygon boundingPolygon, World world) {
		this.id = Entity.ID;
		Entity.ID++;

		this.x = x;
		this.y = y;
		this.boundingPolygon = boundingPolygon;
		this.boundingPolygon.setRectangleBoundary();
		this.solid = solid;
		this.touchedTiles = new ArrayList<Tile>();

	}

	public void debugRender(ShapeRenderer shapeRenderer) {
		getBoundingPolygon().render(shapeRenderer);

		if (this instanceof Creature) {
			Creature temp = (Creature) this;
			temp.getWeapon().render(shapeRenderer);

		}

		for (int i = 0; i < getTouchedTiles().size(); i++) {
			shapeRenderer.rect(getTouchedTiles().get(i).getX() * 32, getTouchedTiles().get(i).getY() * 32, 32, 32);

		}

	}

	public void render(SpriteBatch spriteBatch) {
		sprite.draw(spriteBatch);
	}

	public void update(World world) {

	}

	public boolean colliding(Entity entity) {

		if (Intersector.overlapConvexPolygons(getBoundingPolygon(), entity.getBoundingPolygon())) {

			return true;

		} else {

			return false;

		}

	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		x = MathUtils.clamp(x, 0, Map.MAP_PIXEL_WIDTH - boundingPolygon.getWidth());
		this.x = x;

	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		y = MathUtils.clamp(y, 0, Map.MAP_PIXEL_HEIGHT - boundingPolygon.getHeight());
		this.y = y;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BoundingPolygon getBoundingPolygon() {
		return boundingPolygon;
	}

	public void setBoundingPolygon(BoundingPolygon boundingPolygon) {
		this.boundingPolygon = boundingPolygon;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public ArrayList<Tile> getTouchedTiles() {
		return touchedTiles;
	}

	public void setTouchedTiles(ArrayList<Tile> touchedTiles) {
		this.touchedTiles = touchedTiles;
	}

	public boolean isSolid() {
		return solid;
	}

	public void setSolid(boolean solid) {
		this.solid = solid;
	}

}
