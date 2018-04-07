package game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;

public class World {

	public static int MAP_WIDTH;
	public static int MAP_HEIGHT;
	public static int MAP_PIXEL_WIDTH;
	public static int MAP_PIXEL_HEIGHT;
	public static int TILE_PIXEL_WIDTH;
	public static int TILE_PIXEL_HEIGHT;

	private TiledMap map;
	private Item items[][];

	public World() {
		map = new TmxMapLoader().load("maps/map.tmx");

		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(0);

		MAP_WIDTH = layer.getWidth();
		MAP_HEIGHT = layer.getHeight();
		MAP_PIXEL_WIDTH = (int) layer.getTileWidth() * layer.getWidth();
		MAP_PIXEL_HEIGHT = (int) layer.getTileHeight() * layer.getHeight();
		TILE_PIXEL_WIDTH = (int) layer.getTileWidth();
		TILE_PIXEL_HEIGHT = (int) layer.getTileHeight();

		items = new Item[World.MAP_WIDTH][World.MAP_HEIGHT];

//		for (int i = 15; i < World.MAP_WIDTH; i++) {
//
//			for (int j = 15; j < World.MAP_HEIGHT; j++) {
//				items[i][j] = new Item(new Texture(Gdx.files.internal("img/Tile.png")), i*32, j*32);
//			}
//		}

	}

	public void renderItems(SpriteBatch batch) {
		for (int i = 1; i < World.MAP_WIDTH; i++) {

			for (int j = 1; j < World.MAP_HEIGHT; j++) {

				if (items[i][j] != null) {
					items[i][j].draw(batch);
				} else {
					continue;
				}
			}
		}
	}

	public TiledMap getMap() {
		return map;
	}

	public void setMap(TiledMap map) {
		this.map = map;
	}

}
