package com.mygdx.game;

import com.badlogic.gdx.Gdx;

public class Game extends com.badlogic.gdx.Game {

	public final static String TITEL = "Unknown";

	@Override
	public void create() {
		setScreen(new SplashScreen());
		Gdx.app.log(TITEL, "create()");
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		Gdx.app.log(TITEL, "resize()");

	}

	@Override
	public void render() {
		super.render();
		Gdx.app.log(TITEL, "render()");

	}

	@Override
	public void pause() {
		super.pause();
		Gdx.app.log(TITEL, "pause()");

	}

	@Override
	public void resume() {
		super.resume();
		Gdx.app.log(TITEL, "resume()");

	}

	@Override
	public void dispose() {
		super.dispose();
		Gdx.app.log(TITEL, "dispose()");

	}

}
