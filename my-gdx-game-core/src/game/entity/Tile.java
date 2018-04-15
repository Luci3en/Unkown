package game.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;

public class Tile extends StaticTiledMapTile {

	public static int TILE_PIXEL_WIDTH = 32;
	public static int TILE_PIXEL_HEIGHT = 32;
	private int x;
	private int y;

	public Tile(TextureRegion textureRegion) {
		super(textureRegion);
		// TODO Auto-generated constructor stub
	}

	public Tile(TextureRegion textureRegion, int x, int y) {
		super(textureRegion);
		this.x = x;
		this.y = y;
	}

	



	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
