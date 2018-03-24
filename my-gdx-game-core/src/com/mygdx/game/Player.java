package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player extends Sprite implements InputProcessor {

	private Vector2 velocity;
	private TiledMapTileLayer collisionLayer;
	private float speed = 240;
	private boolean pressed_up, pressed_down, pressed_left, pressed_right;
	private Rectangle hitBox;

	public Player(Sprite sprite, TiledMapTileLayer tiledMapTileLayer) {
		super(sprite);
		this.collisionLayer = tiledMapTileLayer;
		this.velocity = new Vector2();
		this.pressed_up = false;
		this.pressed_down = false;
		this.pressed_left = false;
		this.pressed_right = false;
		this.hitBox = new Rectangle(getX(), getY(), getWidth(), getHeight());

	}

	@Override
	public void draw(Batch batch) {
		update(Gdx.graphics.getDeltaTime());
		super.draw(batch);
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

	public void move() {

		moveY();
		moveX();

	}

	public void moveX() {

		if (velocity.x > 0) { // Moving right
			int tempX = (int) (getX() + velocity.x + hitBox.width) / 32;

			if (!(collisionWithTile(tempX, ((int) getY() / 32)))
					&& !(collisionWithTile(tempX, (int) (getY() + hitBox.getHeight()) / 32))) {

				setX(getX() + velocity.x);
				hitBox.setX(getX());

			}

		} else if (velocity.x < 0) { // Moving left
			int tempX = (int) (getX() + velocity.x) / 32;

			if (!(collisionWithTile(tempX, ((int) getY() / 32)))
					&& !(collisionWithTile(tempX, (int) (getY() + hitBox.getHeight()) / 32))) {

				setX(getX() + velocity.x);
				hitBox.setX(getX());

			}

		}

	}

	public void moveY() {

		if (velocity.y < 0) { // Moving up
			int tempY = (int) (getY() + velocity.y) / 32;

			if (!(collisionWithTile(((int) getX() / 32), tempY))
					&& !(collisionWithTile((int) ((getX() + hitBox.width) / 32), tempY))) {

				setY(getY() + velocity.y);
				hitBox.setY(getY());

			}

		} else if (velocity.y > 0) { // Moving down
			int tempY = (int) (getY() + velocity.y + hitBox.height) / 32;

			if (!(collisionWithTile((int) getX() / 32, tempY))
					&& !(collisionWithTile((int) (getX() + hitBox.width) / 32, tempY))) {

				setY(getY() + velocity.y);
				hitBox.setY(getY());

			}

		}

	}

	public boolean collisionWithTile(int x, int y) {

		if (x <= TileMap.MAP_WIDTH && y <= TileMap.MAP_HEIGHT && x >= 0 && y >= 0) {
			Cell cell = collisionLayer.getCell(x, y);

			if (cell != null) {
				return (Boolean) cell.getTile().getProperties().get("blocked");

			}

		} else {
			return true;
		}
		return false;

	}

	@Override
	public void setY(float y) {

		if (y >= 0 && y <= TileMap.MAP_PIXEL_HEIGHT - hitBox.height) {
			super.setY(y);
		} else {
			return;
		}

	}

	@Override
	public void setX(float x) {
		if (x >= 0 && x <= TileMap.MAP_PIXEL_WIDTH - hitBox.width) {
			super.setX(x);
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
