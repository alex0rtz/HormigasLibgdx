package com.test.hormigas;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Planta extends MyActor {

    /**
     * Van apareciendo aleatoriamente. Tienen que ser regadas para poder ser comidas.
     */

    private Texture texture;
    private int energia = 0;

    private boolean puede_comerse = false;

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
            puede_comerse = true;
    }

    public void comer() {
        energia--;
    }

    public boolean estaViva() {
        return energia > 0;
    }

    /**
     * GETTERS AND SETTERS
     */

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public boolean getComestible() {
        return puede_comerse;
    }
}
