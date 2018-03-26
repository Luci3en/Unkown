package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;

public class TileMap {

	public static int MAP_WIDTH;
	public static int MAP_HEIGHT;
	public static int MAP_PIXEL_WIDTH;
	public static int MAP_PIXEL_HEIGHT;
	public static int TILE_PIXEL_WIDTH;
	public static int TILE_PIXEL_HEIGHT;

	private TiledMap map;

	public TileMap() {
		map = new TmxMapLoader().load("maps/map.tmx");
		
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get("Kachelebene 3");

		MAP_WIDTH = layer.getWidth();
		MAP_HEIGHT = layer.getHeight();
		MAP_PIXEL_WIDTH = (int) layer.getTileWidth() * layer.getWidth();
		MAP_PIXEL_HEIGHT = (int) layer.getTileHeight() * layer.getHeight();
		TILE_PIXEL_WIDTH = (int) layer.getTileWidth();
		TILE_PIXEL_HEIGHT = (int) layer.getTileHeight();

		for (int i = 1; i < 3; i++) {

			for (int j = 1; j < 3; j++) {
				Cell cell = new Cell();
//				cell.setTile(
//						new StaticTiledMapTile(new TextureRegion(new Texture(Gdx.files.internal("img/Tile.png")))));
				
				
			
				layer.setCell(i, j, cell);
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