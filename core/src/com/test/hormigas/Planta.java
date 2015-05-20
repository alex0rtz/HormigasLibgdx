package com.test.hormigas;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Planta extends MyActor {

    /**
     * Van apareciendo aleatoriamente. Tienen que ser regadas para poder ser comidas.
     */

    private Texture texture;

    public static final float TAMANO = Hormiga.TAMANO * 1.5f;

    public Planta(float posX, float posY) {
        super(posX, posY, TAMANO);

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
