package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import game.Application;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = Application.TITEL + " " + Application.VERSION;
		config.foregroundFPS = 60;
		config.backgroundFPS = 60;
		config.width = Application.WIDTH;
		config.height = Application.HEIGHT;
		new LwjglApplication(new Application(), config);
	}
}
