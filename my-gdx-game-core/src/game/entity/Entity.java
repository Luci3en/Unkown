package game.entity;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;

import game.Map;
import game.Tile;
import game.World;
import game.utility.BoundingPolygon;
import game.utility.EntityManager;

public abstract class Entity {

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
		// this.touchedTiles = world.getEntityManager().findTiles(hitbox,
		// world.getMap());
		// addEntityInWorld(world);

	}

	public void render(SpriteBatch spriteBatch) {
		sprite.draw(spriteBatch);
	}

	public void update(EntityManager entityManager) {

	}

	public boolean collides(Entity entity) {

		if (Intersector.overlapConvexPolygons(getBoundingPolygon(), entity.getBoundingPolygon())) {

			return true;

		} else {

			return false;

		}

	}

	public void addEntityInWorld(World world) {

		for (Tile tile : touchedTiles) {
			world.getMap().getTile(tile.getX(), tile.getY()).getEntityIDs().add(id);
		}
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		if (x >= 0 && x <= Map.MAP_PIXEL_WIDTH - boundingPolygon.getWidth()) {
			this.x = x;
		} else {
			return;
		}

	}

	public float getY() {
		return y;
	}

	public void setY(float y) {

		if (y >= 0 && y <= Map.MAP_PIXEL_HEIGHT - boundingPolygon.getWidth()) {
			this.y = y;
		} else {
			return;
		}

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
