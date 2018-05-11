package game.entity;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;

import game.Map;
import game.Tile;
import game.World;
import game.utility.EntityManager;
import game.utility.Hitbox;

public abstract class Entity {

	public static int ID = 1;

	private int id;
	private float speed = 120;
	private Vector2 velocity;
	private Sprite sprite;
	private Hitbox hitbox;
	private ArrayList<Tile> touchedTiles;
	private float x, y;

	public Entity(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public Entity(float x, float y, Hitbox hitbox) {
		this.id = Entity.ID;
		Entity.ID++;
		this.x = x;
		this.y = y;
		this.hitbox = hitbox;
		this.touchedTiles = new ArrayList<Tile>();
		this.velocity = new Vector2();

	}

	public Entity(float x, float y, Hitbox hitbox, World world) {
		this.id = Entity.ID;
		Entity.ID++;

		this.x = x;
		this.y = y;
		this.hitbox = hitbox;
		this.touchedTiles = new ArrayList<Tile>();
		this.velocity = new Vector2();
//		this.touchedTiles = world.getEntityManager().findTiles(hitbox, world.getMap());
//		setWorldTiles(world);

	}

	@Override
	public String toString() {
		return "Entity [id=" + id + ", speed=" + speed + ", velocity=" + velocity + ", sprite=" + sprite + ", hitbox="
				+ hitbox + ", touchedTiles=" + touchedTiles + ", x=" + x + ", y=" + y + "]";
	}

	public void render(SpriteBatch spriteBatch) {

		sprite.draw(spriteBatch);
	}

	public void render(SpriteBatch spriteBatch, EntityManager entityManager, Map map) {

		sprite.draw(spriteBatch);
	}

	public boolean collision(Entity entity) {

		if (Intersector.overlapConvexPolygons(hitbox.getPolygon(), entity.getHitbox().getPolygon())) {

			return true;

		} else {

			return false;

		}

	}

	public void setWorldTiles(World world) {

		for (Tile tile : touchedTiles) {
			world.getMap().getTile(tile.getX(), tile.getY()).getEntityIDs().add(id);
		}
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		if (x >= 0 && x <= Map.MAP_PIXEL_WIDTH - hitbox.getWidth()) {
			this.x = x;
		} else {
			return;
		}

	}

	public float getY() {
		return y;
	}

	public void setY(float y) {

		if (y >= 0 && y <= Map.MAP_PIXEL_HEIGHT - hitbox.getWidth()) {
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

	public Hitbox getHitbox() {
		return hitbox;
	}

	public void setHitbox(Hitbox hitbox) {
		this.hitbox = hitbox;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public ArrayList<Tile> getTouchedTiles() {
		return touchedTiles;
	}

	public void setTouchedTiles(ArrayList<Tile> touchedTiles) {
		this.touchedTiles = touchedTiles;
	}

}
