package game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Tree extends Entity {

	private int hitboxOffsetX = 8;
	private int hitboxOffsetY = 0;
	private int width = 70;
	private int height = 50;

	public Tree(float x, float y) {
		super(x, y);

		super.setSprite(new Sprite(new Texture(Gdx.files.internal("img/baum.png"))));

		super.setHitBox(new Rectangle(x + hitboxOffsetX, y + hitboxOffsetY, width, height));
	}

}
