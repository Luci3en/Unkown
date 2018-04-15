package game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

public class World {

	public static int MAP_WIDTH;
	public static int MAP_HEIGHT;
	public static int MAP_PIXEL_WIDTH;
	public static int MAP_PIXEL_HEIGHT;

	private TiledMap map;
	private EntityManager entityManager;
	private Player player;

	public World(int map_width, int map_height) {

		map = new TiledMap();
		player = new Player();

		
		MAP_WIDTH = map_width;
		MAP_HEIGHT = map_height;
		MAP_PIXEL_WIDTH = map_width * Tile.TILE_PIXEL_WIDTH;
		MAP_PIXEL_HEIGHT = map_height * Tile.TILE_PIXEL_HEIGHT;

		TiledMapTileLayer layer = new TiledMapTileLayer(MAP_PIXEL_WIDTH, MAP_PIXEL_HEIGHT, Tile.TILE_PIXEL_WIDTH,
				Tile.TILE_PIXEL_HEIGHT);

		Texture texture = new Texture(Gdx.files.internal("img/Tile.png"));
		TextureRegion textureRegion = new TextureRegion(texture);

		for (int i = 0; i < map_width; i++) {

			for (int j = 0; j < map_height; j++) {

				Cell cell = new Cell();

				cell.setTile(new Tile(textureRegion, i, j));

				layer.setCell(i, j, cell);
			}

		}

		entityManager = new EntityManager();

		map.getLayers().add(layer);

	}

	public void render(SpriteBatch batch) {
		player.render(batch);
		entityManager.findTiles((int) player.getX(), (int) player.getY(), (int) player.getHitBox().getWidth(), (int) player.getHitBox().getHeight());

	}

	public TiledMap getMap() {
		return map;
	}

	public void setMap(TiledMap map) {
		this.map = map;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
