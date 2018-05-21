package game.utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;

import game.Tile;
import game.World;
import game.entity.Creature;
import game.entity.Entity;

public class EntityManager implements Disposable {

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

	public void render(SpriteBatch spriteBatch, World world) {

		renderOrder.clear();
		renderOrder.addAll(entities.values());
		Collections.sort(renderOrder, yOrder);

		for (Entity entity : renderOrder) {

			if (entity instanceof Creature) {
				entity.update(world);
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

	public boolean collidingWithEntity(Entity entity) {

		for (Tile tile : entity.getTouchedTiles()) {

			if (tile.getEntityIDs().size() == 0) {
				continue;
			} else {

				for (int id : tile.getEntityIDs()) {
					if (id != 0 && id != entity.getId()) {

						if (entity.colliding(entities.get(id))) {
							return true;
						}

					} else {
						continue;
					}
				}

			}

		}
		return false;
	}

	@Override
	public void dispose() {
		for (Entry<Integer, Entity> entity : entities.entrySet()) {
			entity.getValue().dispose();
		}

		for (Entity entity : renderOrder) {
			entity.dispose();
		}

	}

	public HashMap<Integer, Entity> getEntities() {
		return entities;
	}

	public void setEntities(HashMap<Integer, Entity> entities) {
		this.entities = entities;
	}

}
