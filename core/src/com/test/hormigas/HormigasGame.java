package com.test.hormigas;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.FPSLogger;

public class HormigasGame extends Game {

    FPSLogger logger;

    @Override
    public void create() {
        Assets.loadtexture();
        setScreen(new PantallaHormiga(this));

        logger = new FPSLogger();
    }


    @Override
    public void render() {
        super.render();
        logger.log();
    }
}

