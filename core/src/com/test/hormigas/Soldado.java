package com.test.hormigas;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.Random;

public class Soldado extends Hormiga {

    private int energia;
    private int tipo;
    private int victorias = 0;

    /**
     * ATRIBUTOS LIBGDX
     */

    private Animation animation;
    private float stateTime;

    private Random random = new Random();

    /**
     * ATRIBUTOS HORMIGA
     */

    private boolean chocada = true;
    private boolean peleando = false;
    private boolean viva = true;

    public static final int TAMANO = Obrera.TAMANO;

    public Soldado(int tipo, float posX, float posY, int sector) {
        super(tipo, posX, posY, TAMANO, 0, sector);
        setBounds(posX, posY, TAMANO, TAMANO);

        this.tipo = tipo;
        energiaInicial();
        animation = getAnimation();
        setOrigin(TAMANO / 2, TAMANO / 2);

    }

    /**
     * METODOS DEL LIBGDX
     */

    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(animation.getKeyFrame(stateTime, true), getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        stateTime += delta;

        getPolygon().setPosition(getX(), getY());
        getPolygon().setRotation(getRotation());

        // Comprueba si las hormigas están entre la pantalla y cuando llegan al extremos chocan y cambian de dirección.
        if (!peleando && (getX() < 0 || getX() > Assets.screenWidth - TAMANO || getY() < 0 || getY() > Assets.screenHeight - TAMANO)) {
            clearActions();

            if (getX() <= 0)
                setX(0);
            else if (getX() >= Assets.screenWidth - TAMANO)
                setX(Assets.screenWidth - TAMANO);
            else if (getY() <= 0)
                setY(0);
            else if (getY() >= Assets.screenHeight - TAMANO)
                setY(Assets.screenHeight - TAMANO);
        }

    }

    @Override
    public Actor hit(float x, float y, boolean touchable) {
        return super.hit(x, y, touchable);
    }

    /**
     * METODOS DEL SOLDADO
     */

    public void comprobarIntrusos() {
        switch (tipo) {
            case Assets.VERDE:

        }
    }

    public Animation getAnimation() {
        switch (tipo) {
            case Assets.VERDE:
                return Assets.animationSoldadoVerde;
            case Assets.NARANJA:
                return Assets.animationSoldadoNaranja;
            case Assets.ROJA:
                return Assets.animationSoldadoRoja;
            case Assets.AZUL:
                return Assets.animationvSoldadoAzul;
            default:
                return Assets.animationSoldadoRosa;
        }
    }

    @Override
    public void energiaInicial() {
        energia = 10;
    }

    // METODOS DE ACCIONES
    public void ganarPelea(int cantidad) {
        energia += cantidad;
        victorias++;
    }

    public void perderPelea() {
        energia = 0;
    }

    public boolean viva() {
        return energia >= 1;
    }

    /**
     * GETTERS AND SETTERS
     */
    public int getTipo() {
        return tipo;
    }

    public boolean isChocada() {
        return chocada;
    }

    public void setChocada(boolean chocada) {
        this.chocada = chocada;
    }


    public void setPeleando(boolean peleando) {
        this.peleando = peleando;
    }

    public boolean isPeleando() {
        return peleando;
    }

    public int getVictorias() {
        return victorias;
    }

    public int getEnergia() {
        return energia;
    }
}
