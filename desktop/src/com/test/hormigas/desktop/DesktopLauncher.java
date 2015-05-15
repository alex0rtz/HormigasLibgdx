package com.test.hormigas.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.test.hormigas.HormigasGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.height = 720;
        config.width = 1280;
		config.vSyncEnabled  = false;
		config.title = "Ant simulator";
		new LwjglApplication(new HormigasGame(), config);
	}
}
