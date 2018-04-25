package game.entity;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;

import game.utility.Hitbox;

public abstract class Entity {

	public static int ID = 1;

	private int id;
	private float speed = 100;
	private Vector2 velocity;
	private Sprite sprite;
	private Hitbox hitbox;
	private ArrayList<Tile> touchedTiles;
	private float x, y;

	public Entity(float x, float y, Hitbox hitbox) {
		this.id = Entity.ID;
		Entity.ID++;
		this.x = x;
		this.y = y;

		this.hitbox = hitbox;
		this.touchedTiles = new ArrayList<Tile>();
		this.velocity = new Vector2();

	}

	public void render(SpriteBatch spriteBatch) {
		sprite.setX(getX());
		sprite.setY(getY());
		sprite.draw(spriteBatch);
	}

	public void render(SpriteBatch spriteBatch, EntityManager entityManager, Map map) {
		sprite.setX(getX());
		sprite.setY(getY());
		sprite.draw(spriteBatch);
	}

	public void move(EntityManager entityManager, Map map) {

		float old_tempX = hitbox.getX();
		float old_tempY = hitbox.getY();

		hitbox.setX(hitbox.getX() + velocity.x);
		hitbox.setY(hitbox.getY() + velocity.y);
		setX(getX() + velocity.x);
		setY(getY() + velocity.y);

		if (velocity.x != 0 || velocity.y != 0) {

			touchedTiles = entityManager.findTiles(hitbox, map);

			if (entityManager.collision(this)) {
				hitbox.setX(old_tempX);
				setX(old_tempX);

			}

			if (entityManager.collision(this)) {
				velocity.y = 0;
				hitbox.setY(old_tempY);
				setY(old_tempY);
			}

		}

	}

	public boolean collision(Entity entity) {

		if (Intersector.overlaps(hitbox.getHitbox(), entity.getHitBox().getHitbox())) {

			return true;

		} else {

			return false;

		}

	}

	public void renderHitbox(ShapeRenderer shapeRenderer) {
		shapeRenderer.rect(hitbox.getX(), hitbox.getY(), hitbox.getHitbox().width, hitbox.getHitbox().height);
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

	public Hitbox getHitBox() {
		return hitbox;
	}

	public void setHitBox(Hitbox hitBox) {
		this.hitbox = hitBox;
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
