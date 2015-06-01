package com.test.hormigas;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.Random;

public class Obrera extends Hormiga {

    // https://github.com/JavadocMD/JackJaneRace/blob/master/actionTest/src/com/javadocmd/actionTest/actor/RunnerActor.java

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

    public static final float VISION = 70;
    public static final int TAMANO = 35;

    /**
     * CONSTRUCTOR
     */

    public Obrera(int tipo, float posX, float posY) {
        super(tipo, posX, posY, TAMANO, VISION);
        setBounds(posX, posY, TAMANO, TAMANO);

        this.tipo = tipo;
        energiaInicial();
        animation = getAnimation();
        setOrigin(TAMANO / 2, TAMANO / 2);
        setScale(0.1f);

        getPolygon().setPosition(getX(), getY());
        getPolygon().setScale(getScaleX(), getScaleY());

        moverInicial(getRandomAngle());

        crecer(TIEMPO_CRECIMIENTO);

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

        if (!esAdulta) {

            getPolygon().setScale(getScaleX(), getScaleY());

            tiempoCrecimiento += delta;
            if (tiempoCrecimiento >= TIEMPO_CRECIMIENTO)
                esAdulta = true;
        }

        getPolygon().setPosition(getX(), getY());
        getPolygon().setRotation(getRotation());

        // Comprueba si las hormigas están entre la pantalla y cuando llegan al extremos chocan y cambian de dirección.
        if (!isPeleando() && (getX() < 0 || getX() > Assets.screenWidth - TAMANO || getY() < 0 || getY() > Assets.screenHeight - TAMANO)) {
            clearActions();

            if (getX() <= 0)
                setX(0);
            else if (getX() >= Assets.screenWidth - TAMANO)
                setX(Assets.screenWidth - TAMANO);
            else if (getY() <= 0)
                setY(0);
            else if (getY() >= Assets.screenHeight - TAMANO)
                setY(Assets.screenHeight - TAMANO);

            if (!esAdulta)
                seguirCreciendo();

            mover(getRandomAngle());
        }

    }

    @Override
    public Actor hit(float x, float y, boolean touchable) {
        return super.hit(x, y, touchable);
    }

    /**
     * ACCIONES DE LA OBRERA
     */

    public Animation getAnimation() {
        switch (tipo) {
            case Assets.VERDE:
                return Assets.animationVerde;
            case Assets.NARANJA:
                return Assets.animationNaranja;
            case Assets.ROJA:
                return Assets.animationRoja;
            case Assets.AZUL:
                return Assets.animationAzul;
            default:
                return Assets.animationRosa;
        }
    }

    public void energiaInicial() {
        switch (tipo) {
            case Assets.VERDE:
                energia = 3;
                break;
            case Assets.NARANJA:
            case Assets.AZUL:
            case Assets.ROSA:
                energia = 3;
                break;
            case Assets.ROJA:
                energia = 3;
                break;

        }
    }

    public void ganarPelea(int cantidad) {
        energia += cantidad;
        victorias++;
    }

    public void perderPelea() {
        energia /= 2;
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

    public int getVictorias() {
        return victorias;
    }

    public int getEnergia() {
        return energia;
    }
}
