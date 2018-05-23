package game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;

import game.entity.Creature;
import game.entity.Entity;
import game.screen.GameScreen;
import game.utility.CameraStyles;

public class Controller implements InputProcessor {

	private boolean pressed_W, pressed_S, pressed_A, pressed_D, pressed_LeftMouseButton, pressed_RightMouseButton;
	private int currentEntityID;
	private Creature creature;
	private GameScreen gameScreen;

	public Controller(GameScreen gameScreen) {
		this.gameScreen = gameScreen;
		this.pressed_LeftMouseButton = false;
		this.pressed_RightMouseButton = false;
		this.pressed_W = false;
		this.pressed_S = false;
		this.pressed_A = false;
		this.pressed_D = false;

		gameScreen.getWorld().getEntityManager().getEntities().put(Entity.ID,
				new Creature(200, 200, gameScreen.getWorld()));
		setCurrentEntityID(Entity.ID - 1);
		setCreature((Creature) gameScreen.getWorld().getEntityManager().getEntities().get(getCurrentEntityID()));

	}

	public void update(float delta) {

		CameraStyles.lockOnEntity(gameScreen.getWorld().getCamera(), getCreature());

		if (pressed_LeftMouseButton) {
			creature.getWeapon().attack();
		}

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

		case Input.Keys.ESCAPE:
			if (gameScreen.getStage().getActors().size > 0) {
				gameScreen.getStage().clear();
			} else {
				gameScreen.showGameMenu();
			}

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
	public boolean scrolled(int amount) {

		((OrthographicCamera) gameScreen.getWorld().getCamera()).zoom += amount * 0.2f;

		return false;

	}

	@Override
	public boolean touchDown(int arg0, int arg1, int arg2, int keyCode) {

		switch (keyCode) {
		case Input.Buttons.LEFT:
			setPressed_LeftMouseButton(true);
			break;

		case Input.Buttons.RIGHT:
			setPressed_RightMouseButton(true);
			break;

		default:
			break;
		}

		return false;
	}

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int keyCode) {

		switch (keyCode) {
		case Input.Buttons.LEFT:
			setPressed_LeftMouseButton(false);
			break;

		case Input.Buttons.RIGHT:
			setPressed_RightMouseButton(false);
			break;

		default:
			break;

		}

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

	public boolean isPressed_LeftMouseButton() {
		return pressed_LeftMouseButton;
	}

	public void setPressed_LeftMouseButton(boolean pressed_LeftMouseButton) {
		this.pressed_LeftMouseButton = pressed_LeftMouseButton;
	}

	public boolean isPressed_RightMouseButton() {
		return pressed_RightMouseButton;
	}

	public void setPressed_RightMouseButton(boolean pressed_RightMouseButton) {
		this.pressed_RightMouseButton = pressed_RightMouseButton;
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
