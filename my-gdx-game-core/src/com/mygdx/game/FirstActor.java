package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class FirstActor {

	private Texture texture;
	private float x, y;

	public FirstActor() {
		super();
		texture = new Texture(Gdx.files.internal("img/player.png"));
		this.x = 0;
		this.y = 0;

	}

	public void draw(Batch batch, float parentAlpha) {

		batch.draw(texture, x, y);

	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	

}
