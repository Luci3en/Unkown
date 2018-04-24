package game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import game.utility.Hitbox;

public class Tree extends Entity {

	public Tree(float x, float y) {
		super(x, y, new Hitbox(x, y, 5, 7, 20, 20));

		super.setSprite(new Sprite(new Texture(Gdx.files.internal("img/baum.png"))));

	}

}
