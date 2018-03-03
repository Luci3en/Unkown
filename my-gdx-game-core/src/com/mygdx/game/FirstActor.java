package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;

public class FirstActor extends Actor {

	private Texture texture;

	public FirstActor() {
		super();
		texture = new Texture(Gdx.files.internal("img/player.png"));

		MoveToAction action = new MoveToAction();
		action.setPosition(750, 250);
		action.setDuration(10f);

		ScaleToAction scaleToAction = new ScaleToAction();

		scaleToAction.setScale(4, 17);
		scaleToAction.setDuration(10f);

		RotateToAction rotateToAction = new RotateToAction();
		rotateToAction.setDuration(10f);
		rotateToAction.setRotation(100f);

		addAction(action);
		addAction(rotateToAction);
		addAction(scaleToAction);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(texture, this.getX(), getY(), this.getOriginX(), this.getOriginY(), texture.getHeight(),
				texture.getWidth() , this.getScaleX(), this.getScaleY(), this.getRotation(), 0, 0, texture.getWidth(),
				texture.getHeight(), false, false);
		
		
		
	}

	@Override
	public void act(float delta) {
		super.act(delta);

		for (int i = 0; i < this.getActions().size; i++) {
			getActions().get(i).act(delta);
		}

	}

}
