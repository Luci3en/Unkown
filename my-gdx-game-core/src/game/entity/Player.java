package game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player extends Entity implements InputProcessor {

	private Vector2 velocity;
	private TiledMapTileLayer collisionLayer;
	private float speed = 120;
	private boolean pressed_up, pressed_down, pressed_left, pressed_right;
	private Rectangle hitBox;
	private Animation<TextureRegion> animation, up_walking, down_walking, left_walking, right_walking;
	private float stateTime;

	public Player(TiledMapTileLayer tiledMapTileLayer) {
		this.collisionLayer = tiledMapTileLayer;
		this.velocity = new Vector2();
		this.pressed_up = false;
		this.pressed_down = false;
		this.pressed_left = false;
		this.pressed_right = false;
		this.hitBox = new Rectangle(getX() + (32 / 2) - 16 / 2, getY(), 16, 32);
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

	}

	public void draw(Batch batch) {
		stateTime += Gdx.graphics.getDeltaTime();
		update(Gdx.graphics.getDeltaTime());

		if (animation != null) {
			TextureRegion currentFrame = (TextureRegion) animation.getKeyFrame(stateTime, true);

			if (move()) {
				batch.draw(currentFrame, super.getX(), super.getY());
			} else {
				TextureRegion[] currentFrames = animation.getKeyFrames();
				batch.draw(currentFrames[0], super.getX(), super.getY());

			}
		}

	}

	public void update(float delta) {

		velocity.x = 0;
		velocity.y = 0;

		if (pressed_left) {
			velocity.x = -speed * delta;

		}

		if (pressed_right) {
			velocity.x = speed * delta;

		}

		if (pressed_up) {
			velocity.y = speed * delta;

		}

		if (pressed_down) {
			velocity.y = -speed * delta;

		}

		move();

	}

	public boolean move() {

		if (velocity.x > 0) { // Moving right
			int tempX = (int) (hitBox.x + velocity.x + hitBox.width) / 32;
			animation = right_walking;

			if (!(collisionWithTile(tempX, ((int) hitBox.y / 32)))
					&& !(collisionWithTile(tempX, (int) (hitBox.y + hitBox.getHeight()) / 32))) {

				setX(getX() + velocity.x);
				return true;

			} else {
				velocity.x = 0;
				return false;
			}

		} else if (velocity.x < 0) { // Moving left
			int tempX = (int) (hitBox.x + velocity.x) / 32;
			animation = left_walking;

			if (!(collisionWithTile(tempX, ((int) hitBox.y / 32)))
					&& !(collisionWithTile(tempX, (int) (hitBox.y + hitBox.getHeight()) / 32))) {

				setX(getX() + velocity.x);

				return true;

			} else {
				velocity.x = 0;
				return false;
			}

		}

		if (velocity.y < 0) { // Moving down
			int tempY = (int) (hitBox.y + velocity.y) / 32;
			animation = down_walking;

			if (!(collisionWithTile(((int) hitBox.x / 32), tempY))
					&& !(collisionWithTile((int) ((hitBox.x + hitBox.width) / 32), tempY))) {

				setY(getY() + velocity.y);

				return true;

			} else {
				velocity.y = 0;
				return false;

			}

		} else if (velocity.y > 0) { // Moving up
			int tempY = (int) (hitBox.y + velocity.y + hitBox.height) / 32;
			animation = up_walking;

			if (!(collisionWithTile((int) hitBox.x / 32, tempY))
					&& !(collisionWithTile((int) (hitBox.x + hitBox.width) / 32, tempY))) {

				setY(getY() + velocity.y);

				return true;
			} else {
				velocity.y = 0;
				return false;
			}

		}
		return false;

	}

	public boolean collisionWithTile(int x, int y) {
		Cell cell = collisionLayer.getCell(x, y);

		if (cell != null) {
			return (Boolean) cell.getTile().getProperties().get("blocked");

		} else {

			return false;
		}

	}

	@Override
	public void setX(float x) {
		if (x >= 0 && x <= World.MAP_PIXEL_WIDTH - hitBox.width) {
			super.setX(x);
			hitBox.setX(x + (32 / 2) - 16 / 2);
		} else {
			return;
		}

	}

	@Override
	public void setY(float y) {

		if (y >= 0 && y <= World.MAP_PIXEL_HEIGHT - hitBox.height) {
			super.setY(y);
			hitBox.setY(y);
		} else {
			return;
		}

	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
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

	public Rectangle getHitBox() {
		return hitBox;
	}

	public void setHitBox(Rectangle hitBox) {
		this.hitBox = hitBox;
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
