package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

import aurelienribon.tweenengine.TweenAccessor;

public class SpriteAccessor implements TweenAccessor<Sprite> {

	public static final int APLHA = 0;

	@Override
	public int getValues(Sprite target, int tweenType, float[] returnValues) {

		switch (tweenType) {
		case APLHA:
			returnValues[0] = target.getColor().a;
			return 1;
		default:
			assert false;
			return -1;
		}

	}

	@Override
	public void setValues(Sprite target, int tweenType, float[] newValues) {
		switch (tweenType) {
		case APLHA:

			target.setColor(target.getColor().r, target.getColor().g, target.getColor().b, newValues[0]);
			break;

		default:
			assert false;
		}

	}
}
