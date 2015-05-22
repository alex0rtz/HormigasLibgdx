package com.test.hormigas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.FillViewport;

import java.util.Random;
import java.util.Vector;

public class PantallaHormiga implements Screen {

    private static final int HORMIGAS_POR_CLIC = 25;
    private final int MARGEN_PLANTAS = 70;

    private Stage stage;
    private FillViewport viewport;

    private SpriteBatch stageBatch;

    private static Vector<MyActor> actores;
    private static Vector<MyActor> pendientesEliminar;

    private Random ran = new Random();

    public PantallaHormiga(HormigasGame game) {
        stageBatch = new SpriteBatch();
        viewport = new FillViewport(Assets.screenWidth, Assets.screenHeight);
        actores = new Vector<>();
        pendientesEliminar = new Vector<>();

        stage = new Stage(viewport, stageBatch);
        Gdx.input.setInputProcessor(stage);

        Image background = new Image(Assets.background);
        background.setSize(Assets.screenWidth, Assets.screenHeight);
        background.setScaling(Scaling.fill);

        stage.addActor(background);


        crearHormigas(0);

        crearPlantas(0);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(1, 1, 1, 1);

        act(delta);
        draw();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) Gdx.app.exit();
    }


    public void act(float delta) {
        stage.act(delta);
        processInput();
        detectarColision();
    }

    public void draw() {
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }

    /**
     * ACCIONES EN PANTALLA
     */

    public void crearHormiga(Vector2 pos) {
        Hormiga h = new Hormiga(ran.nextInt(5) + 1, pos.x, pos.y);
        actores.add(h);
        stage.addActor(h);
        h.getPolygon().setPosition(h.getX(), h.getY());
    }

    public void crearPlanta(Vector2 pos) {
        Planta p = new Planta(pos.x, pos.y);
        actores.add(p);
        stage.addActor(p);
        p.getPolygon().setPosition(p.getX(), p.getY());
    }

    public void crearHormigas(int numero) {
        for (int i = 0; i < numero; i++) {
            crearHormiga(new Vector2(ran.nextFloat() * (Assets.screenWidth - Planta.TAMANO), ran.nextFloat() * (Assets.screenHeight - Planta.TAMANO)));
        }
    }

    public void crearPlantas(int numero) {
        for (int i = 0; i < numero; i++) {
            crearPlanta(new Vector2(ran.nextFloat() * (Assets.screenWidth - MARGEN_PLANTAS * 2 - Planta.TAMANO) + MARGEN_PLANTAS, ran.nextFloat() * (Assets.screenHeight - MARGEN_PLANTAS * 2 - Planta.TAMANO) + MARGEN_PLANTAS));
        }
    }

    public void detectarColision() {

        for (MyActor act : pendientesEliminar) {
            actores.remove(act);
            act.remove();
        }

        pendientesEliminar.clear();

        for (MyActor act1 : actores) {
            if (act1 instanceof Hormiga && ((Hormiga) act1).isChocada())
                continue;
            for (MyActor act2 : actores) {
                if (act2 instanceof Hormiga && ((Hormiga) act2).isChocada())
                    continue;
                if (act1 != act2 && (act2.getPolygon().contains(act1.getPolygon().getX(), act1.getPolygon().getY())
                        || act2.getPolygon().contains(act1.getPolygon().getX() + act1.getTamano(), act1.getPolygon().getY())
                        || act2.getPolygon().contains(act1.getPolygon().getX() + act1.getTamano(), act1.getPolygon().getY() + act1.getTamano())
                        || act2.getPolygon().contains(act1.getPolygon().getX(), act1.getPolygon().getY() + act1.getTamano()))) {
                    choque(act1, act2);
                }
            }
        }
    }

    public void choque(final MyActor actor1, final MyActor actor2) {
        if (actor1 instanceof Hormiga && actor2 instanceof Hormiga)
            choqueEntreHormigas((Hormiga) actor1, (Hormiga) actor2);
        else if (actor1 instanceof Hormiga && actor2 instanceof Planta)
            choqueHormigaPlanta((Hormiga) actor1, (Planta) actor2);
        else if (actor1 instanceof Planta && actor2 instanceof Hormiga)
            choqueHormigaPlanta((Hormiga) actor2, (Planta) actor1);
    }

    private void choqueHormigaPlanta(final Hormiga hormiga, final Planta planta) {
        if (!hormiga.isChocada()) {
            hormiga.setChocada(true);
            hormiga.clearActions();
            hormiga.mirar(planta);


            //TODO Hacer que no puedan comer cuando ya no hay comida
            if (hormiga.getTipo() != Hormiga.ROJA || planta.getComestible()) {
                hormiga.pelear();

                if (!planta.getComestible()) {
                    planta.regar();
                    hormiga.regar();
                } else {
                    planta.comer();
                    hormiga.comer();
                    if (!planta.estaViva()) {
                        stage.addAction(Actions.delay(Hormiga.TIEMPO_PELEA * Hormiga.IMPACTOS_PELEA * 2,
                                        Actions.run(new Runnable() {
                                            @Override
                                            public void run() {
                                                pendientesEliminar.add(planta);
                                            }
                                        })
                                )
                        );
                    }
                }
            } else {
                hormiga.invertDireccion();
            }
        }
    }

    private void choqueEntreHormigas(final Hormiga h1, final Hormiga h2) {
        if (!h1.isChocada()) {
            h1.setChocada(true);
            h1.clearActions();
            h1.mirar(h2);
            h1.pelear();

            switch (h1.getTipo()) {
                case Hormiga.VERDE:
                case Hormiga.NARANJA:
                case Hormiga.AZUL:
                case Hormiga.ROSA:
                    break;
            }
        }

        if (!h2.isChocada()) {
            h2.setChocada(true);
            h2.clearActions();
            h2.mirar(h1);
            h2.pelear();
        }
    }

    private void processInput() {

        // Se ejecuta cuando le das ESC (ordenador) y Atras (movil).
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK) || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            //TODO cerrar aplicaciones
        }

        Vector2 touchPoint = new Vector2();

        stage.getViewport().unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY()));

        if (Gdx.input.isKeyJustPressed(Input.Keys.H) || (Gdx.input.justTouched() && Gdx.input.isButtonPressed(Input.Buttons.LEFT))) {

            //TODO añadir hormiga
            for (int i = 0; i < HORMIGAS_POR_CLIC; i++) {
                Random ran = new Random();
                Hormiga h = new Hormiga(ran.nextInt(5) + 1, touchPoint.x, touchPoint.y);
                actores.add(h);
                stage.addActor(h);
                h.getPolygon().setPosition(h.getX(), h.getY());
            }
        }


        if (Gdx.input.isKeyJustPressed(Input.Keys.P) || (Gdx.input.justTouched() && Gdx.input.isButtonPressed(Input.Buttons.RIGHT))) {

            //TODO añadir Planta
            Planta p = new Planta(touchPoint.x - Planta.TAMANO / 2, touchPoint.y - Planta.TAMANO / 2);
            actores.add(p);
            stage.addActor(p);
            p.getPolygon().setPosition(p.getX(), p.getY());
        }
    }

    /**
     * GETTERS AND SETTERS
     */

    public static Vector<MyActor> getActores() {
        return actores;
    }
}
