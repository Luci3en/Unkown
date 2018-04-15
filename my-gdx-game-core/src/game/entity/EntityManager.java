package game.entity;

import java.util.ArrayList;

public class EntityManager {

	private ArrayList<Tile> liste;

	
	public EntityManager() {
		liste = new ArrayList<Tile>();
	}

	public void findTiles(int x, int y, int width, int height) {

		liste.clear();
		
		int left = x / Tile.TILE_PIXEL_WIDTH;
		int right = (x + width) / Tile.TILE_PIXEL_WIDTH;
		int buttom = y / Tile.TILE_PIXEL_HEIGHT;
		int top = (y + height) / Tile.TILE_PIXEL_HEIGHT;

		for (int i = left; i <= right; i++) {
			for (int j = buttom; j <= top; j++) {

				System.out.println(i + "  " + j);
				liste.add(new Tile(null, i, j));
				
				
			}
		}

	}

	public ArrayList<Tile> getListe() {
		return liste;
	}

	public void setListe(ArrayList<Tile> liste) {
		this.liste = liste;
	}

}
