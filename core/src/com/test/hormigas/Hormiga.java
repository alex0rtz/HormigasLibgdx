package com.test.hormigas;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import java.util.Random;

public class Hormiga extends Actor {

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

    public static final int TAMANO = 80;

    Rectangle bounds;
    Polygon polygon;

    /**
     * CONSTRUCTOR
     */

    public Hormiga(int tipo, float posX, float posY) {
        setBounds(posX, posY, TAMANO, TAMANO);

        this.tipo = tipo;
        animation = getAnimation();
        setOrigin(TAMANO / 2, TAMANO / 2);

        bounds = new Rectangle(getX(), getY(), TAMANO, TAMANO);

        polygon = new Polygon(new float[] { 0, 0, bounds.width, 0, bounds.width, bounds.height, 0, bounds.height });
        polygon.setOrigin(bounds.width / 2, bounds.height / 2);
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

        polygon.setPosition(getX(), getY());
        polygon.setRotation(getRotation());

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

            moverHormigas();
        }

        detectarColision();

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

    public void moverHormigas() {
        Random ran = new Random();

        float x = ran.nextInt(1000) - 499;
        float y = ran.nextInt(1000) - 499;

        float p = (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        float tiempo = p / 200;

        addAction(Actions.forever(Actions.parallel(
                        Actions.moveBy(x, y, tiempo),
                        Actions.rotateTo(getAngle(getX(), getY(), x, y), 0.1f)
                )
        ));
    }


    public float getAngle(float x, float y, float x2, float y2) {
        float angle = (float) Math.toDegrees(Math.atan2((y + y2) - y, (x + x2) - x)) - 90;

        if (angle < 0) {
            angle += 360;
        }

        return angle;
    }


    public void detectarColision() {
        for (int i = 0; i < PantallaHormiga.getPosHormigas(); i++) {

            Polygon polygon2 = PantallaHormiga.getHormigas().get(i).getPolygon();

            if (chocada)
                return;

            if (polygon2.contains(polygon.getX(), polygon.getY())) {
                chocado(PantallaHormiga.getHormigas().get(i), 1);
            } else if (polygon2.contains(polygon.getX() + TAMANO, polygon.getY())) {
                chocado(PantallaHormiga.getHormigas().get(i), 2);
            } else if (polygon2.contains(polygon.getX() + TAMANO, polygon.getY() + TAMANO)) {
                chocado(PantallaHormiga.getHormigas().get(i), 3);
            } else if (polygon2.contains(polygon.getX(), polygon.getY() + TAMANO)) {
                chocado(PantallaHormiga.getHormigas().get(i), 4);

            }
        }
    }

    public void chocado(final Hormiga hormiga, int num) {
        chocada = true;
        clearActions();
        hormiga.clearActions();

        float hormigaX = hormiga.getOriginX();
        float hormigaY = hormiga.getOriginY();


        setRotation(getAngle(hormigaX, hormigaY, getOriginX(), getOriginY()));
        hormiga.setRotation(getAngle(getOriginX(), getOriginY(), hormigaX, hormigaY));


        //moverHormigas();
        //hormiga.moverHormigas();

        addAction(Actions.delay(0.1f, Actions.run(new Runnable() {
            @Override
            public void run() {
                chocada = false;
            }
        })));
        hormiga.addAction(Actions.delay(0.1f, Actions.run(new Runnable() {
            @Override
            public void run() {
                hormiga.chocada = false;
            }
        })));
        /*
        System.out.println("Choque en IF: " + num);
        System.out.println("Hormiga 1: X = " + getX() + " Y = " + getY());
        System.out.println("Hormiga 2: X = " + hormiga.getX() + " Y = " + hormiga.getY());
        System.out.println("Poligono 1: X = " + polygon.getX() + " Y = " + polygon.getY());
        System.out.println("Poligono 2: X = " + hormiga.getPolygon().getX() + " Y = " + hormiga.getPolygon().getY());
        */
    }


    /**
     * GETTERS AND SETTERS
     */
    public int getTipo() {
        return tipo;
    }

    public Polygon getPolygon() {
        return polygon;
    }
}