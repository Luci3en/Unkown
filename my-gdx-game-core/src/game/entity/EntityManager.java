package game.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import game.utility.Hitbox;

public class EntityManager {

	private ArrayList<Tile> touchedTiles;
	private HashMap<Integer, Entity> entities;
	private Player player;
	private ShapeRenderer shapeRenderer;

	public EntityManager() {

		this.touchedTiles = new ArrayList<Tile>();
		this.entities = new HashMap<Integer, Entity>();
		this.player = new Player(10, 10);
		this.shapeRenderer = new ShapeRenderer();
		this.shapeRenderer.setAutoShapeType(true);
	}

	public ArrayList<Tile> findTiles(Hitbox hitbox, Map map) {

		touchedTiles.clear();

		int left = (int) (hitbox.getX() / Tile.TILE_PIXEL_WIDTH);
		int right = (int) ((hitbox.getX() + hitbox.getHitbox().width) / Tile.TILE_PIXEL_WIDTH);
		int buttom = (int) (hitbox.getY() / Tile.TILE_PIXEL_HEIGHT);
		int top = (int) ((hitbox.getY() + hitbox.getHitbox().height) / Tile.TILE_PIXEL_HEIGHT);

		for (int i = left; i <= right; i++) {
			for (int j = buttom; j <= top; j++) {

				touchedTiles.add(map.getTile(i, j));

			}
		}
		return touchedTiles;
	}

	public void spawnEntity(Map map) {

		for (Entry<Integer, Entity> iterable : entities.entrySet()) {

			int left = (int) iterable.getValue().getX() / Tile.TILE_PIXEL_WIDTH;
			int right = (int) (iterable.getValue().getX() + iterable.getValue().getHitBox().getHitbox().getWidth())
					/ Tile.TILE_PIXEL_WIDTH;
			int buttom = (int) iterable.getValue().getY() / Tile.TILE_PIXEL_HEIGHT;
			int top = (int) (iterable.getValue().getY() + iterable.getValue().getHitBox().getHitbox().getHeight())
					/ Tile.TILE_PIXEL_HEIGHT;

			for (int i = left; i <= right; i++) {
				for (int j = buttom; j <= top; j++) {

					map.getTile(i, j).setEntityId(iterable.getKey());
				}
			}

		}

	}

	public boolean collision(Entity entity) {

		boolean collided = false;

		for (Tile tile : entity.getTouchedTiles()) {

			if (tile.getEntityId() != 0) {
				if (entities.containsKey(tile.getEntityId())) {

					if (entity.collision(entities.get(tile.getEntityId()))) {
						collided = true;
					}
				}
			} else {
				continue;
			}

		}

		return collided;
	}

	public void render(SpriteBatch spriteBatch, World world) {

		player.render(spriteBatch, world);

		for (Entry<Integer, Entity> entity : entities.entrySet()) {
			entity.getValue().render(spriteBatch);
			
		}

	}

	public void debugRender(OrthographicCamera camera) {
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin();

		// for (Tile iterable_element : player.getTouchedTiles()) {
		//
		// shapeRenderer.rect(iterable_element.getX() * 32, iterable_element.getY() *
		// 32, 32, 32);
		// }

		for (Entry<Integer, Entity> iterable : getEntities().entrySet()) {

			iterable.getValue().renderHitbox(shapeRenderer);

		}

		getPlayer().renderHitbox(shapeRenderer);

		shapeRenderer.end();
	}

	public ArrayList<Tile> getListe() {
		return touchedTiles;
	}

	public void setListe(ArrayList<Tile> liste) {
		this.touchedTiles = liste;
	}

	public HashMap<Integer, Entity> getEntities() {
		return entities;
	}

	public void setEntities(HashMap<Integer, Entity> entities) {
		this.entities = entities;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

}
