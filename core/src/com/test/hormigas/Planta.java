package com.test.hormigas;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Planta extends Actor {

    private Texture texture;

    public static final int TAMANO = 40;

    public Planta (int posX, int posY){
        setBounds(posX, posY, TAMANO, TAMANO);
        setOrigin(TAMANO / 2, TAMANO / 2);
    }

    /**
     * METODOS CLASE ACTOR
     */
    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(Assets.texturePlanta, getX(), getY(), TAMANO, TAMANO);
    }
}
