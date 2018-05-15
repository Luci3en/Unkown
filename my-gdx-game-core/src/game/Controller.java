package game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;

import game.entity.Creature;
import game.utility.EntityManager;

public class Controller implements InputProcessor {

	private boolean pressed_W, pressed_S, pressed_A, pressed_D;
	private int currentEntityID;
	private Creature creature;

	public Controller() {
		this.pressed_W = false;
		this.pressed_S = false;
		this.pressed_A = false;
		this.pressed_D = false;

	}

	public void update(float delta, EntityManager entityManager, Camera camera) {

		if (pressed_A) {
			creature.getVelocity().x = -creature.getSpeed() * delta;
		}

		if (pressed_D) {
			creature.getVelocity().x = creature.getSpeed() * delta;
		}

		if (pressed_S) {
			creature.getVelocity().y = -creature.getSpeed() * delta;
		}

		if (pressed_W) {
			creature.getVelocity().y = creature.getSpeed() * delta;

		}

	}

	@Override
	public boolean keyDown(int keyCode) {

		switch (keyCode) {
		case Input.Keys.W:
			setPressed_W(true);
			break;

		case Input.Keys.S:
			setPressed_S(true);
			break;

		case Input.Keys.A:
			setPressed_A(true);
			break;

		case Input.Keys.D:
			setPressed_D(true);
			break;

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
		case Input.Keys.W:
			setPressed_W(false);
			break;

		case Input.Keys.S:
			setPressed_S(false);
			break;

		case Input.Keys.A:
			setPressed_A(false);
			break;

		case Input.Keys.D:
			setPressed_D(false);
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

	public boolean isPressed_W() {
		return pressed_W;
	}

	public void setPressed_W(boolean pressed_W) {
		this.pressed_W = pressed_W;
	}

	public boolean isPressed_S() {
		return pressed_S;
	}

	public void setPressed_S(boolean pressed_S) {
		this.pressed_S = pressed_S;
	}

	public boolean isPressed_A() {
		return pressed_A;
	}

	public void setPressed_A(boolean pressed_A) {
		this.pressed_A = pressed_A;
	}

	public boolean isPressed_D() {
		return pressed_D;
	}

	public void setPressed_D(boolean pressed_D) {
		this.pressed_D = pressed_D;
	}

	public int getCurrentEntityID() {
		return currentEntityID;
	}

	public void setCurrentEntityID(int currentEntityID) {
		this.currentEntityID = currentEntityID;
	}

	public Creature getCreature() {
		return creature;
	}

	public void setCreature(Creature creature) {
		this.creature = creature;
	}

}
