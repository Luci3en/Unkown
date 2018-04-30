package game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import game.entity.Entity;
import game.utility.EntityManager;

public class Player implements InputProcessor {

	private boolean pressed_up, pressed_down, pressed_left, pressed_right;
	private int currentEntityID;
	private Entity entity;

	public Player() {
		this.pressed_up = false;
		this.pressed_down = false;
		this.pressed_left = false;
		this.pressed_right = false;

	}

	public void update(float delta, EntityManager entityManager) {

		if (entity == null) {
			entity = entityManager.getEntities().get(currentEntityID);
		}

		if (pressed_left) {
			entity.getVelocity().x = -entity.getSpeed() * delta;
		}

		if (pressed_right) {
			entity.getVelocity().x = entity.getSpeed() * delta;
		}

		if (pressed_down) {
			entity.getVelocity().y = -entity.getSpeed() * delta;
		}

		if (pressed_up) {
			entity.getVelocity().y = entity.getSpeed() * delta;

		}

	}

	@Override
	public boolean keyDown(int keyCode) {

		switch (keyCode) {
		case Input.Keys.UP:

			pressed_up = true;
			break;

		case Input.Keys.DOWN:
			pressed_down = true;
			break;

		case Input.Keys.LEFT:
			pressed_left = true;
			break;

		case Input.Keys.RIGHT:
			pressed_right = true;
			break;

		case Input.Keys.I:

		default:

			return false;
		}
		return false;

	}

	@Override
	public boolean keyTyped(char keyCode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keyCode) {
		switch (keyCode) {
		case Input.Keys.UP:
			pressed_up = false;
			break;

		case Input.Keys.DOWN:
			pressed_down = false;
			break;

		case Input.Keys.LEFT:
			pressed_left = false;
			break;

		case Input.Keys.RIGHT:
			pressed_right = false;
			break;

		default:

			return false;
		}
		return false;

	}

	@Override
	public boolean mouseMoved(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isPressed_up() {
		return pressed_up;
	}

	public void setPressed_up(boolean pressed_up) {
		this.pressed_up = pressed_up;
	}

	public boolean isPressed_down() {
		return pressed_down;
	}

	public void setPressed_down(boolean pressed_down) {
		this.pressed_down = pressed_down;
	}

	public boolean isPressed_left() {
		return pressed_left;
	}

	public void setPressed_left(boolean pressed_left) {
		this.pressed_left = pressed_left;
	}

	public boolean isPressed_right() {
		return pressed_right;
	}

	public void setPressed_right(boolean pressed_right) {
		this.pressed_right = pressed_right;
	}

	public int getCurrentEntityID() {
		return currentEntityID;
	}

	public void setCurrentEntityID(int currentEntityID) {
		this.currentEntityID = currentEntityID;
	}

}
