package game.entity;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;

import game.utility.Hitbox;

public abstract class Entity {

	private float speed = 100;
	private Vector2 velocity;
	private Sprite sprite;
	private Hitbox hitbox;
	private ArrayList<Tile> touchedTiles;

	public Entity(float x, float y, Hitbox hitbox) {
		this.hitbox = hitbox;
		this.touchedTiles = new ArrayList<Tile>();
		this.velocity = new Vector2();

	}

	public void render(SpriteBatch spriteBatch) {
		sprite.setX(getX());
		sprite.setY(getY());
		sprite.draw(spriteBatch);
	}

	public void render(SpriteBatch spriteBatch, World world) {
		sprite.setX(getX());
		sprite.setY(getY());
		sprite.draw(spriteBatch);
	}

	public boolean move(World world) {

		float old_tempX = hitbox.getX();
		float old_tempY = hitbox.getY();

		hitbox.setX(hitbox.getX() + velocity.x);
		hitbox.setY(hitbox.getY() + velocity.y);

		if (velocity.x != 0 || velocity.y != 0) {

			touchedTiles = world.getEntityManager().findTiles(hitbox, world.getMap());

			if (world.getEntityManager().collision(this)) {

				hitbox.setX(old_tempX);

			}

			if (world.getEntityManager().collision(this)) {
				velocity.y = 0;
				hitbox.setY(old_tempY);
			}

			return true;
		} else {

			return false;
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
		return hitbox.getX();
	}

	public void setX(float x) {
		if (x >= 0 && x <= Map.MAP_PIXEL_WIDTH - hitbox.getHitbox().width) {
			this.hitbox.setX(x);
		} else {
			return;
		}

	}

	public float getY() {
		return hitbox.getY();
	}

	public void setY(float y) {

		if (y >= 0 && y <= Map.MAP_PIXEL_HEIGHT - hitbox.getHitbox().height) {
			this.hitbox.setY(y);
		} else {
			return;
		}

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
