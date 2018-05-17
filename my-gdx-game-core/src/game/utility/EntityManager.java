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

	private Map map;
	private HashMap<Integer, Entity> entities;
	private ArrayList<Entity> renderOrder;
	private Comparator<Entity> yOrder;

	public EntityManager(Map map) {
		this.map = map;
		this.entities = new HashMap<Integer, Entity>();
		this.renderOrder = new ArrayList<Entity>();
		this.yOrder = new Comparator<Entity>() {

			@Override
			public int compare(Entity o1, Entity o2) {
				return Float.compare(o2.getY(), o1.getY());
			}
		};

	}

	public void render(SpriteBatch spriteBatch, Map map) {

		renderOrder.clear();
		renderOrder.addAll(entities.values());
		Collections.sort(renderOrder, yOrder);

		for (Entity entity : renderOrder) {

			if (entity instanceof Creature) {
				entity.update(this);
				entity.render(spriteBatch);
			} else {

				entity.render(spriteBatch);
			}

		}

	}

	public void debugRender(ShapeRenderer shapeRenderer) {

		for (Entry<Integer, Entity> entity : entities.entrySet()) {

			entity.getValue().getBoundingPolygon().render(shapeRenderer);

			if (entity.getValue() instanceof Creature) {
				Creature temp = (Creature) entity.getValue();
				temp.getWeapon().render(shapeRenderer);

			}

			for (int i = 0; i < entity.getValue().getTouchedTiles().size(); i++) {

				shapeRenderer.rect(entity.getValue().getTouchedTiles().get(i).getX() * 32,
						entity.getValue().getTouchedTiles().get(i).getY() * 32, 32, 32);

			}

		}

	}

	public ArrayList<Tile> findTiles(BoundingPolygon boundingPolygon) {

		ArrayList<Tile> touchedTiles = new ArrayList<Tile>();

		int left = (int) (boundingPolygon.getX() / Tile.TILE_PIXEL_WIDTH);
		int right = (int) ((boundingPolygon.getX() + boundingPolygon.getWidth()) / Tile.TILE_PIXEL_WIDTH);
		int buttom = (int) (boundingPolygon.getY() / Tile.TILE_PIXEL_HEIGHT);
		int top = (int) ((boundingPolygon.getY() + boundingPolygon.getHeight()) / Tile.TILE_PIXEL_HEIGHT);

		for (int i = left; i <= right; i++) {
			for (int j = buttom; j <= top; j++) {
				
				if(i >= 0 && i < Map.MAP_WIDTH && j >= 0 && j < Map.MAP_HEIGHT) {
					touchedTiles.add(map.getTile(i, j));
				}
				
			

			}
		}

		return touchedTiles;
	}

	public boolean collidingWithEntity(Entity entity) {

		boolean collided = false;

		for (Tile tile : entity.getTouchedTiles()) {

			for (int id : tile.getEntityIDs()) {
				if (id != 0 && id != entity.getId()) {

					if (entity.collides(entities.get(id))) {
						collided = true;
					}

				} else {
					continue;
				}
			}

		}

		return collided;
	}

	public HashMap<Integer, Entity> getEntities() {
		return entities;
	}

	public void setEntities(HashMap<Integer, Entity> entities) {
		this.entities = entities;
	}

}
