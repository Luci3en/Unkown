package game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

import game.utility.BoundingPolygon;

public class Map {

	public static int MAP_WIDTH;
	public static int MAP_HEIGHT;
	public static int MAP_PIXEL_WIDTH;
	public static int MAP_PIXEL_HEIGHT;

	private TiledMap tiledMap;

	public Map(int map_width, int map_height) {

		this.tiledMap = new TiledMap();

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

		tiledMap.getLayers().add(layer);

	}

	public Tile getTile(int x, int y) {
		TiledMapTileLayer layer = (TiledMapTileLayer) tiledMap.getLayers().get(0);
		return (Tile) layer.getCell(x, y).getTile();
	}

	public ArrayList<Tile> findTiles(BoundingPolygon boundingPolygon) {

		ArrayList<Tile> touchedTiles = new ArrayList<Tile>();

		int left = (int) (boundingPolygon.getX() / Tile.TILE_PIXEL_WIDTH);
		int right = (int) ((boundingPolygon.getX() + boundingPolygon.getWidth()) / Tile.TILE_PIXEL_WIDTH);
		int buttom = (int) (boundingPolygon.getY() / Tile.TILE_PIXEL_HEIGHT);
		int top = (int) ((boundingPolygon.getY() + boundingPolygon.getHeight()) / Tile.TILE_PIXEL_HEIGHT);

		for (int i = left; i <= right; i++) {
			for (int j = buttom; j <= top; j++) {

				if (i >= 0 && i < Map.MAP_WIDTH && j >= 0 && j < Map.MAP_HEIGHT) {
					touchedTiles.add(getTile(i, j));
				}

			}
		}

		return touchedTiles;
	}

	public TiledMap getTiledMap() {
		return tiledMap;
	}

	public void setTiledMap(TiledMap tiledMap) {
		this.tiledMap = tiledMap;
	}

}
