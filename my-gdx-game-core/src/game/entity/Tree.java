package game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import game.World;
import game.utility.BoundingPolygon;

public class Tree extends Entity {

	public Tree(float x, float y, World world) {
		super(x, y, true, new BoundingPolygon(x, y, 5, 10), world);

		TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("atlas/Trees.atlas"));

		int random = ((int) (Math.random() * 3) + 1);
		String treeTexture = "Trees" + random;

		float offsetX = 0;
		float offsetY = 0;

		switch (treeTexture) {
		case "Trees1":
			offsetX = 28;

			break;

		case "Trees2":
			offsetX = 24;

			break;

		case "Trees3":
			offsetX = 22;

			break;

		default:
			break;
		}

		super.getBoundingPolygon().setPosition(getX() + offsetX, getY() + offsetY);
		super.setTouchedTiles(world.getEntityManager().findTiles(super.getBoundingPolygon()));
		super.addEntityInWorld(world);

		Sprite sprite = new Sprite(textureAtlas.findRegion(treeTexture));
		setSprite(sprite);
		super.getSprite().setX(getX());
		super.getSprite().setY(getY());

	}

}
