package game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import game.World;
import game.utility.Hitbox;

public class Tree extends Entity {

	public Tree(float x, float y, World world) {
		super(x, y, new Hitbox(x, y, 0, 0, 5, 10), world);

		TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("atlas/Trees.atlas"));

		int random = ((int) (Math.random() * 3) + 1);
		String treeType = "Trees" + random;

		float offsetX = 0;
		float offsetY = 0;

		switch (treeType) {
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

		super.getHitbox().translate(offsetX, offsetY);
		super.setTouchedTiles(world.getEntityManager().findTiles(super.getHitbox(), world.getMap()));
		super.setWorldTiles(world);

		Sprite temp = new Sprite(textureAtlas.findRegion(treeType));
		setSprite(temp);
		super.getSprite().setX(getX());
		super.getSprite().setY(getY());

	}

	@Override
	public String toString() {

		return "Tree [toString()=" + super.toString() + ", getX()=" + getX() + ", getY()=" + getY() + ", getId()="
				+ getId() + ", getHitbox()=" + getHitbox() + ", getHitBox()=" + getHitbox() + ", getSprite()="
				+ getSprite() + ", getVelocity()=" + getVelocity() + ", getSpeed()=" + getSpeed()
				+ ", getTouchedTiles()=" + getTouchedTiles() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + "]";
	}

}
