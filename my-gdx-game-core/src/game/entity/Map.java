package game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class Map {

	public static int MAP_WIDTH;
	public static int MAP_HEIGHT;
	public static int MAP_PIXEL_WIDTH;
	public static int MAP_PIXEL_HEIGHT;

	private TiledMap map;
	private OrthogonalTiledMapRenderer tiledMapRenderer;

	public Map(int map_width, int map_height) {

		this.map = new TiledMap();

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

		map.getLayers().add(layer);
		this.tiledMapRenderer = new OrthogonalTiledMapRenderer(map);

	}

	public void render(OrthographicCamera camera) {
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();
	}

	public Tile getTile(int x, int y) {
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(0);
		return (Tile) layer.getCell(x, y).getTile();
	}

}
