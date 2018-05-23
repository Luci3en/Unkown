package game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import game.World;
import game.utility.BoundingPolygon;

public class Tree extends Entity {

	public Tree(float x, float y, World world) {
		super(x, y, true, new BoundingPolygon(x, y, 5, 10), world);

		TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("atlas/Trees.atlas"));

		int random = ((int) (Math.random() * 4) + 1);
		String treeTexture = "Trees" + random;

		switch (treeTexture) {
		case "Trees1":
			getBoundingPolygon().setOffsetX(28f);
			Sprite sprite = new Sprite(textureAtlas.findRegion(treeTexture));
			setSprite(sprite);

			break;

		case "Trees2":
			getBoundingPolygon().setOffsetX(24f);
			Sprite sprite2 = new Sprite(textureAtlas.findRegion(treeTexture));
			setSprite(sprite2);

			break;

		case "Trees3":
			getBoundingPolygon().setOffsetX(22f);
			Sprite sprite3 = new Sprite(textureAtlas.findRegion(treeTexture));
			setSprite(sprite3);

			break;

		case "Trees4":
			getBoundingPolygon().setOffsetX(15f);
			getBoundingPolygon().setOffsetY(8f);
			Sprite sprite4 = new Sprite(new Texture(Gdx.files.internal("img/apple.png")));
			setSolid(false);
			sprite4.setSize(32, 32);
			setSprite(sprite4);
			

			break;

		default:
			break;
		}

		super.getBoundingPolygon().setPosition(getX(), getY());
		super.setTouchedTiles(world.getMap().findTiles(getBoundingPolygon()));

		super.getSprite().setX(getX());
		super.getSprite().setY(getY());

	}

	@Override
	public void dispose() {
		getSprite().getTexture().dispose();
	}

	@Override
	public String toString() {
		return "Tree [getX()=" + getX() + ", getY()=" + getY() + ", getId()=" + getId() + ", getBoundingPolygon()="
				+ getBoundingPolygon() + ", getSprite()=" + getSprite() + ", getTouchedTiles()=" + getTouchedTiles()
				+ ", isSolid()=" + isSolid() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

}
