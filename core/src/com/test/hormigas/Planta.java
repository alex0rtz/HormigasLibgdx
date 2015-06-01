package com.test.hormigas;


import com.badlogic.gdx.graphics.g2d.Batch;

public class Planta extends MyActor {

    /**
     * Van apareciendo aleatoriamente. Tienen que ser regadas para poder ser comidas.
     */

    private int energia = 0;

    private boolean viva = true;
    private boolean comestible = false;

    private final int ENERGIA_MAX = 4;

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

    public void regar() {
        energia += 2;

        if (energia == ENERGIA_MAX)
            comestible = true;
    }

    public void comer() {
        energia--;

        if (energia <= 0)
            comestible = false;
    }

    public boolean isViva() {
        return viva;
    }

    public void matar() {
        viva = false;
    }

    public boolean isComestible() {
        return comestible;
    }
}
