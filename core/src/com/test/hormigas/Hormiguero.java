package com.test.hormigas;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Hormiguero extends MyActor {

    private int tipo;
    private int energia = 20;

    public static final float TAMANO = Obrera.TAMANO * 2f;

    public Hormiguero(int tipo, float posX, float posY) {
        super(posX, posY, TAMANO, 0, 0);

        this.tipo = tipo;
        setBounds(posX, posY, TAMANO, TAMANO);
        setOrigin(TAMANO / 2, TAMANO / 2);
    }


    /**
     * METODOS LIBGDX
     */
    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(pintarTextura(), getX(), getY(), TAMANO, TAMANO);
    }

    /**
     * METODOS HORMIGUERO
     */
    public Texture pintarTextura() {
        switch (tipo) {
            case Assets.VERDE:
                return Assets.textureHormigueroVerde;
            case Assets.NARANJA:
                return Assets.textureHormigueroNaranja;
            case Assets.ROJA:
                return Assets.textureHormigueroRojo;
            case Assets.AZUL:
                return Assets.textureHormigueroAzul;
            default:
                return Assets.textureHormigueroRosa;
        }
    }

    /**
     * GETTERS AND SETTERS
     */
    public int getTipo() {
        return tipo;
    }


}
