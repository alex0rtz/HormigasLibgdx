package com.test.hormigas;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import java.util.Random;

public class Hormiga extends MyActor {

    // https://github.com/JavadocMD/JackJaneRace/blob/master/actionTest/src/com/javadocmd/actionTest/actor/RunnerActor.java

    /**
     * Las hormigas pueden comer plantas, moverse, regar plantas, pelearse y reproducirse.
     *
     * Ganan energia cada vez que comen una planta. Cuando una planta ha sido suficientemente regada,
     * por la primera hormiga que pase junto a ella.
     * No hay limite en la cantidad de plantas que pueden comer.
     *
     * Pierden energia cada vez que se mueven, riegan, se pelean o se reproducen.
     * Si pierde toda la energia muere.
     */
    private int energia;

    /**
     * Las hormigas pueden ser rojas (agresivas, no riegan las plantas), naranjas (equilibradas, que tienden a atacar), verdes (riegan las plantas y no pelean),
     * azules (equilibradas, que tienden a reproducirse) y rosas (equilibradas que tienden a regar).
     */

    private int tipo;

    /**
     * En un combate la hormiga ganadora se quedará con la mitad de la energía acumulada por la perdedora.
     * Cuantas más veces salga vencedora una hormiga, más probabilidades de salir victoriosa de la siguiente.
     */
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

    public static final int VERDE = 1;
    public static final int NARANJA = 2;
    public static final int ROJA = 3;
    public static final int AZUL = 4;
    public static final int ROSA = 5;

    public static final int TAMANO = 35;
    private static final float VELOCIDAD = 200;
    public static final float TIEMPO_GIRO = 0.15f;
    public static final float TIEMPO_CHOQUE = 0.2f;
    public static final float TIEMPO_PELEA = 0.125f;
    public static final int IMPACTOS_PELEA = 6;

    /**
     * CONSTRUCTOR
     */

    public Hormiga(int tipo, float posX, float posY) {
        super(posX, posY, TAMANO);
        setBounds(posX, posY, TAMANO, TAMANO);

        this.tipo = tipo;
        energiaInicial();
        animation = getAnimation();
        setOrigin(TAMANO / 2, TAMANO / 2);

        getPolygon().setPosition(getX(), getY());

        moverInicial(getRandomAngle());

        addAction(Actions.delay(Hormiga.TIEMPO_CHOQUE * 10, Actions.run(new Runnable() {
            @Override
            public void run() {
                setChocada(false);
            }
        })));
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
        if (!peleando && (getX() < 0 || getX() > Assets.screenWidth - Hormiga.TAMANO || getY() < 0 || getY() > Assets.screenHeight - Hormiga.TAMANO)) {
            clearActions();

            if (getX() <= 0)
                setX(0);
            else if (getX() >= Assets.screenWidth - Hormiga.TAMANO)
                setX(Assets.screenWidth - Hormiga.TAMANO);
            else if (getY() <= 0)
                setY(0);
            else if (getY() >= Assets.screenHeight - Hormiga.TAMANO)
                setY(Assets.screenHeight - Hormiga.TAMANO);

            mover(getRandomAngle());
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

    public double getAngle(Vector2 coordsTarget) {

        Vector2 coordsThis = localToStageCoordinates(new Vector2(getOriginX(), getOriginY()));

        float angle = (float) ((Math.atan2(coordsThis.x - coordsTarget.x, -(coordsThis.y - coordsTarget.y)) * 180.0d / Math.PI) + 90.0f);

        if (angle < 0)
            angle += 360;

        return angle;
    }

    public float getRandomAngle() {
        return random.nextInt(360);
    }

    public void invertDireccion() {
        float angle = getRotation();
        angle -= random.nextInt(50) + 166 - 90;

        if (angle < 0)
            angle += 360;

        mover(angle % 360);
    }

    public void mirar(Actor actor) {

        Vector2 coordsActor = actor.localToStageCoordinates(new Vector2(actor.getOriginX(), actor.getOriginY()));

        addAction(Actions.rotateTo((float) getAngle(coordsActor) - 90, TIEMPO_GIRO));

    }

    public void pelear() {

        setPeleando(true);


        addAction(Actions.delay(Hormiga.TIEMPO_GIRO, Actions.run(new Runnable() {
            @Override
            public void run() {

                float x = (float) (7 * Math.cos((getRotation() - 90) * Math.PI / 180));
                float y = (float) (7 * Math.sin((getRotation() - 90) * Math.PI / 180));

                addAction(Actions.sequence(
                                Actions.repeat(IMPACTOS_PELEA,
                                        Actions.sequence(
                                                Actions.moveBy(x, y, TIEMPO_PELEA),
                                                Actions.moveBy(-x, -y, TIEMPO_PELEA)

                                        )
                                ),
                                Actions.run(new Runnable() {
                                    @Override
                                    public void run() {
                                        peleando = false;
                                        invertDireccion();
                                    }
                                })
                        )
                );
            }
        })));


    }

    public void mover(float angulo) {

        moverInicial(angulo);

        addAction(Actions.delay(Hormiga.TIEMPO_CHOQUE, Actions.run(new Runnable() {
            @Override
            public void run() {
                setChocada(false);
            }
        })));
    }


    public void moverInicial(float angulo) {

        float x = (float) (100 * Math.cos(angulo * Math.PI / 180));
        float y = (float) (100 * Math.sin(angulo * Math.PI / 180));

        float p = (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        float tiempo = p / VELOCIDAD;

        addAction(Actions.forever(
                Actions.moveBy(x, y, tiempo)
        ));

        addAction(
                Actions.rotateTo(angulo - 90, TIEMPO_GIRO)
        );
    }

    public void energiaInicial() {
        switch (tipo) {
            case VERDE:
                energia = 10;
                break;
            case NARANJA:
            case AZUL:
            case ROSA:
                energia = 7;
                break;
            case ROJA:
                energia = 5;
                break;

        }
    }

    public void regar() {
        energia--;
    }

    public void comer() {
        energia += 2;
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
}
