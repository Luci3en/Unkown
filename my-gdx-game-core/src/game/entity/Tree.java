package game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import game.World;
import game.utility.Hitbox;

public class Tree extends Entity {

	public Tree(float x, float y, World world) {
		super(x, y, new Hitbox(x, y, 5, 0, 10, 5), new Sprite(new Texture(Gdx.files.internal("img/baum.png"))), world);

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
