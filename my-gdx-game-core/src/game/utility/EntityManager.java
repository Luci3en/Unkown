package game.utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import game.Map;
import game.Tile;
import game.entity.Creature;
import game.entity.Entity;

public class EntityManager {

	private HashMap<Integer, Entity> entities;
	private ArrayList<Entity> renderOrder;
	private Comparator<Entity> yOrder;

	public EntityManager() {
		this.entities = new HashMap<Integer, Entity>();
		this.renderOrder = new ArrayList<Entity>();
		this.yOrder = new Comparator<Entity>() {

			@Override
			public int compare(Entity o1, Entity o2) {
				return Float.compare(o2.getY(), o1.getY());
			}
		};

	}

	public ArrayList<Tile> findTiles(Hitbox hitbox, Map map) {

		ArrayList<Tile> touchedTiles = new ArrayList<Tile>();

		int left = (int) (hitbox.getX() / Tile.TILE_PIXEL_WIDTH);
		int right = (int) ((hitbox.getX() + hitbox.getWidth()) / Tile.TILE_PIXEL_WIDTH);
		int buttom = (int) (hitbox.getY() / Tile.TILE_PIXEL_HEIGHT);
		int top = (int) ((hitbox.getY() + hitbox.getHeight()) / Tile.TILE_PIXEL_HEIGHT);

		for (int i = left; i <= right; i++) {
			for (int j = buttom; j <= top; j++) {

				touchedTiles.add(map.getTile(i, j));

			}
		}

		return touchedTiles;
	}

	public boolean collidingWithEntity(Entity entity) {

		System.out.println("....");
		boolean collided = false;

		for (Tile tile : entity.getTouchedTiles()) {

			System.out.println(tile.getEntityIDs().toString());

			for (int id : tile.getEntityIDs()) {
				if (id != 0 && id != entity.getId()) {

					if (entity.collision(entities.get(id))) {
						System.out.println("Colliding with: " + id);
						collided = true;
					}

				} else {
					System.out.println("continue;");
					continue;
				}
			}

		}

		return collided;
	}

	public void render(SpriteBatch spriteBatch, Map map) {

		renderOrder.clear();
		renderOrder.addAll(entities.values());
		Collections.sort(renderOrder, yOrder);

		for (Entity entity : renderOrder) {

			if (entity instanceof Creature) {

				entity.render(spriteBatch, this, map);
			} else {

				entity.render(spriteBatch);
			}

		}

	}

	public void debugRender(ShapeRenderer shapeRenderer) {

		for (Entry<Integer, Entity> entity : entities.entrySet()) {

			if (entity.getValue() instanceof Creature) {

				Creature creature = (Creature) entity.getValue();
				creature.getWeapon().getHitbox().render(shapeRenderer);

			}

			entity.getValue().getHitbox().render(shapeRenderer);

			for (int i = 0; i < entity.getValue().getTouchedTiles().size(); i++) {

				shapeRenderer.rect(entity.getValue().getTouchedTiles().get(i).getX() * 32,
						entity.getValue().getTouchedTiles().get(i).getY() * 32, 32, 32);

			}

		}

	}

	public HashMap<Integer, Entity> getEntities() {
		return entities;
	}

	public void setEntities(HashMap<Integer, Entity> entities) {
		this.entities = entities;
	}

}
