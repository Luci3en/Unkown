package game.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EntityManager {

	private ArrayList<Tile> liste;
	private HashMap<Integer, Entity> entities;
	private Player player;

	public EntityManager() {
		this.liste = new ArrayList<Tile>();
		this.entities = new HashMap<Integer, Entity>();
		this.player = new Player(10, 10);
	}

	public void findTiles(int x, int y, int width, int height, World world) {

		liste.clear();

		int left = x / Tile.TILE_PIXEL_WIDTH;
		int right = (x + width) / Tile.TILE_PIXEL_WIDTH;
		int buttom = y / Tile.TILE_PIXEL_HEIGHT;
		int top = (y + height) / Tile.TILE_PIXEL_HEIGHT;

		for (int i = left; i <= right; i++) {
			for (int j = buttom; j <= top; j++) {

				liste.add(world.getTile(i, j));

			}
		}

	}

	public void spawnEntity(World world) {

		for (Entry<Integer, Entity> iterable : world.getEntityManager().getEntities().entrySet()) {

			int left = (int) iterable.getValue().getX() / Tile.TILE_PIXEL_WIDTH;
			int right = (int) (iterable.getValue().getX() + iterable.getValue().getHitBox().getWidth())
					/ Tile.TILE_PIXEL_WIDTH;
			int buttom = (int) iterable.getValue().getY() / Tile.TILE_PIXEL_HEIGHT;
			int top = (int) (iterable.getValue().getY() + iterable.getValue().getHitBox().getHeight())
					/ Tile.TILE_PIXEL_HEIGHT;

			for (int i = left; i <= right; i++) {
				for (int j = buttom; j <= top; j++) {

					world.getTile(i, j).setEntityId(iterable.getKey());
				}
			}

		}

	}

	public void update(World world) {
		findTiles((int) player.getHitBox().getX(), (int) player.getHitBox().getY(), (int) player.getHitBox().getWidth(),
				(int) player.getHitBox().getHeight(), world);

		for (Tile tile : liste) {

			if (tile.getEntityId() != 0) {
				if (entities.containsKey(tile.getEntityId())) {
					
					player.collision(entities.get(tile.getEntityId()));

				}
			} else {
				continue;
			}

		}

	}

	public void render(SpriteBatch spriteBatch, World world) {

		update(world);
		player.render(spriteBatch);

		for (Entry<Integer, Entity> entity : entities.entrySet()) {
			entity.getValue().render(spriteBatch);
		}

	}

	public ArrayList<Tile> getListe() {
		return liste;
	}

	public void setListe(ArrayList<Tile> liste) {
		this.liste = liste;
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
