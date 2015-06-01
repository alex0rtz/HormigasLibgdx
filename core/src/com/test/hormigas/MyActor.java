package com.test.hormigas;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class MyActor extends Actor {

    private float tamano;
    private float vision;
    private int tipo;

    private Rectangle bounds;
    private Polygon polygon;

    public MyActor(float x, float y, float tamano, float vision, int tipo) {
        bounds = new Rectangle(x, y, tamano, tamano);

        this.tamano = tamano;
        this.vision = vision;
        this.tipo = tipo;
        polygon = new Polygon(new float[]{0, 0, bounds.width, 0, bounds.width, bounds.height, 0, bounds.height});
        polygon.setOrigin(bounds.width / 2, bounds.height / 2);
    }

    @Override
    public boolean remove() {
        polygon = null;
        return super.remove();
    }

    public Polygon getPolygon() {
        return polygon;
    }

    public float getTamano() {
        return tamano;
    }

    public float getVision() {
        return vision;
    }

    public int getTipo() {
        return tipo;
    }
}
