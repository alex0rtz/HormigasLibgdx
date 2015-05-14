package com.test.hormigas;


import com.badlogic.gdx.Game;

public class HormigasGame extends Game {


    @Override
    public void create() {
        Assets.loadtexture();
        setScreen(new PantallaHormiga(this));
    }
}

