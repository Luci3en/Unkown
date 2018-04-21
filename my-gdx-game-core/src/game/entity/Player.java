package game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player extends Entity implements InputProcessor {

	private boolean pressed_up, pressed_down, pressed_left, pressed_right;
	private Animation<TextureRegion> animation, up_walking, down_walking, left_walking, right_walking;
	private float stateTime;

	public Player(float x, float y) {
		super(x, y);

		this.pressed_up = false;
		this.pressed_down = false;
		this.pressed_left = false;
		this.pressed_right = false;
		super.setHitBox(new Rectangle(getX() + 8, getY(), 16, 20));

		this.stateTime = 0;

		TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("img/player_walking.atlas"));

		animation = new Animation<TextureRegion>(1f / 8f, textureAtlas.findRegions("up_walking"));
		up_walking = animation;

		animation = new Animation<TextureRegion>(1f / 8f, textureAtlas.findRegions("down_walking"));
		down_walking = animation;

		animation = new Animation<TextureRegion>(1f / 10f, textureAtlas.findRegions("left_walking"));
		left_walking = animation;

		animation = new Animation<TextureRegion>(1f / 10f, textureAtlas.findRegions("right_walking"));
		right_walking = animation;

		// Texture texture = new Texture(Gdx.files.internal("img/Alina 2.0
		// bewegt.png"));
		// TextureRegion[][] textureRegion = TextureRegion.split(texture, 32, 32);
		//
		// TextureRegion[] walkFrames = new TextureRegion[5 * 1];
		//
		// int index = 0;
		// for (int i = 3; i < 4; i++) {
		// for (int j = 0; j < 5; j++) {
		// walkFrames[index++] = textureRegion[i][j];
		// }
		// }
		//
		//
		// animation = new Animation<TextureRegion>(1f / 10f, walkFrames);
		// left_walking = animation;

	}

	@Override
	public void render(SpriteBatch spriteBatch) {
		stateTime += Gdx.graphics.getDeltaTime();
		update(Gdx.graphics.getDeltaTime());

		if (animation != null) {
			TextureRegion currentFrame = (TextureRegion) animation.getKeyFrame(stateTime, true);

			if (super.move()) {
				spriteBatch.draw(currentFrame, super.getX(), super.getY());
			} else {
				TextureRegion[] currentFrames = animation.getKeyFrames();
				spriteBatch.draw(currentFrames[0], super.getX(), super.getY());

			}
		}
	}

	public void update(float delta) {
		

		
		super.getVelocity().x = 0;
		super.getVelocity().y = 0;

		if (pressed_left) {
			super.getVelocity().x = -super.getSpeed() * delta;
		}

		if (pressed_right) {
			super.getVelocity().x = super.getSpeed() * delta;

		}

		if (pressed_up) {
			super.getVelocity().y = super.getSpeed() * delta;

		}

		if (pressed_down) {
			super.getVelocity().y = -super.getSpeed() * delta;

		}

		move();

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

	public boolean isUp() {
		return pressed_up;
	}

	public void setUp(boolean up) {
		this.pressed_up = up;
	}

	public boolean isDown() {
		return pressed_down;
	}

	public void setDown(boolean down) {
		this.pressed_down = down;
	}

	public boolean isLeft() {
		return pressed_left;
	}

	public void setLeft(boolean left) {
		this.pressed_left = left;
	}

	public boolean isRight() {
		return pressed_right;
	}

	public void setRight(boolean right) {
		this.pressed_right = right;
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

}
