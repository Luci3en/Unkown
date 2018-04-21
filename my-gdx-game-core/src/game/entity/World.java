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

	public World(int map_width, int map_height) {

		map = new TiledMap();

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

		
		

		entityManager.getEntities().put(1, new Tree(100, 100));
		entityManager.getEntities().put(2, new Tree(300, 100));
		entityManager.getEntities().put(3, new Tree(500, 100));
		
		
		entityManager.spawnEntity(this);
	
	}

	
	// check ob Entities über oder unter dem spieler liegen fals ja render erst die und dann dass
	
	public void render(SpriteBatch spriteBatch) {

		entityManager.render(spriteBatch, this);

	}

	public Tile getTile(int x, int y) {
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(0);
		return (Tile) layer.getCell(x, y).getTile();
	}

	public TiledMap getMap() {
		return map;
	}

	public void setMap(TiledMap map) {
		this.map = map;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
