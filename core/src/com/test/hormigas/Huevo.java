package com.test.hormigas;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Huevo extends Image {

    private final float TAMANOX = 9;
    private final float TAMANOY = 10.75f;

    public static final float TIEMPO_ECLOSION = 10;

    private Texture texture;

    public Huevo(int tipo) {
        super();

        darTipo(tipo);
    }

    private void darTipo(int tipo) {
        switch (tipo) {
            case Hormiga.VERDE:
                texture = Assets.textureHuevoVerde;
                break;
            case Hormiga.NARANJA:
                texture = Assets.textureHuevoNaranja;
                break;
            case Hormiga.ROJA:
                texture = Assets.textureHuevoRoja;
                break;
            case Hormiga.AZUL:
                texture = Assets.textureHuevoAzul;
                break;
            default:
                texture = Assets.textureHuevoRosa;
                break;
        }
    }

    /**
     * METODOS CLASE ACTOR
     */
    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(texture, getX(), getY(), TAMANOX, TAMANOY);
    }
}
