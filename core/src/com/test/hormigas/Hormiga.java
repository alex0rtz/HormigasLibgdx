package com.test.hormigas;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import java.util.Random;

public class Hormiga extends MyActor {

    // https://github.com/JavadocMD/JackJaneRace/blob/master/actionTest/src/com/javadocmd/actionTest/actor/RunnerActor.java

    /**
     * ATRIBUTOS LIBGDX
     */

    private Animation animation;
    private float stateTime;

    /**
     * ATRIBUTOS HORMIGA
     */

    private int tipo;

    private boolean chocada = false;

    public static final int VERDE = 1;
    public static final int NARANJA = 2;
    public static final int ROJA = 3;
    public static final int AZUL = 4;
    public static final int ROSA = 5;

    public static final int TAMANO = 30;
    private static final float VELOCIDAD = 200;
    private static final float TIEMPO_GIRO = 0.1f;
    public static final float TIEMPO_CHOQUE = 0.2f;

    /**
     * CONSTRUCTOR
     */

    public Hormiga(int tipo, float posX, float posY) {
        super(posX, posY, TAMANO);
        setBounds(posX, posY, TAMANO, TAMANO);

        this.tipo = tipo;
        animation = getAnimation();
        setOrigin(TAMANO / 2, TAMANO / 2);
    }

    /**
     * METODOS DEL LIBGDX
     */

    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(animation.getKeyFrame(stateTime, true), getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), 1, 1, getRotation());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        stateTime += delta;

        getPolygon().setPosition(getX(), getY());
        getPolygon().setRotation(getRotation());

        // Comprueba si las hormigas están entre la pantalla y cuando llegan al extremos chocan y cambian de dirección.
        if (getX() < 0 || getX() > Assets.screenWidth - Hormiga.TAMANO || getY() < 0 || getY() > Assets.screenHeight - Hormiga.TAMANO) {
            clearActions();

            if (getX() <= 0)
                setX(0);
            else if (getX() >= Assets.screenWidth - Hormiga.TAMANO)
                setX(Assets.screenWidth - Hormiga.TAMANO);
            else if (getY() <= 0)
                setY(0);
            else if (getY() >= Assets.screenHeight - Hormiga.TAMANO)
                setY(Assets.screenHeight - Hormiga.TAMANO);

            mover();
        }

    }

    @Override
    public Actor hit(float x, float y, boolean touchable) {
        return super.hit(x, y, touchable);
    }

    /**
     * ACCIONES DE LA HORMIGA
     */

    public Animation getAnimation() {
        switch (tipo) {
            case VERDE:
                return Assets.animationVerde;
            case NARANJA:
                return Assets.animationNaranja;
            case ROJA:
                return Assets.animationRoja;
            case AZUL:
                return Assets.animationAzul;
            default:
                return Assets.animationRosa;
        }
    }

    public void mover() {
        Random ran = new Random();

        int x = ran.nextInt(1000) - 499;
        int y = ran.nextInt(1000) - 499;

        float p = (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        float tiempo = p / VELOCIDAD;

        addAction(Actions.forever(Actions.parallel(
                        Actions.moveBy(x, y, tiempo),
                        Actions.rotateTo((float) getAngle(getX(), getY(), x, y), TIEMPO_GIRO)
                )
        ));
    }

    public double getAngle(float x, float y, float targetX, float targetY) {
        float angle = (float) Math.toDegrees(Math.atan2((y + targetY) - y, (x + targetX) - x)) - 90;

        if (angle < 0) {
            angle += 360;
        }

        return angle;
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
}
