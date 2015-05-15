package com.test.hormigas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.FillViewport;

import java.util.Random;
import java.util.Vector;

public class PantallaHormiga implements Screen {

    private static final int HORMIGAS_POR_CLIC = 100;
    private Stage stage;
    private FillViewport viewport;

    private SpriteBatch stageBatch;

    private static Vector<Hormiga> hormigas;
    private static int posHormigas = 0;

    private static Vector<Planta> plantas;
    private static int posPlantas = 0;

    public PantallaHormiga(HormigasGame game) {
        stageBatch = new SpriteBatch();
        viewport = new FillViewport(Assets.screenWidth, Assets.screenHeight);
        hormigas = new Vector<>();
        plantas = new Vector<>();

        stage = new Stage(viewport, stageBatch);
        Gdx.input.setInputProcessor(stage);

        Image background = new Image(Assets.background);
        background.setSize(Assets.screenWidth, Assets.screenHeight);
        background.setScaling(Scaling.fill);

        stage.addActor(background);


        crearHormiga(2);

        crearPlanta(5);
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

    public void crearHormiga(int numero) {
        for (int i = 0; i < numero; i++) {
            Random ran = new Random();
            hormigas.insertElementAt(new Hormiga(ran.nextInt(5) + 1, ran.nextInt(Assets.screenWidth - Hormiga.TAMANO), ran.nextInt(Assets.screenHeight - Hormiga.TAMANO)), posHormigas);
            stage.addActor(hormigas.get(posHormigas));
            hormigas.get(posHormigas).mover();
            posHormigas++;
        }
    }

    public void crearPlanta(int numero){
        for (int i = 0; i < numero; i++) {
            Random ran = new Random();
            plantas.insertElementAt(new Planta(ran.nextInt(Assets.screenWidth - Hormiga.TAMANO), ran.nextInt(Assets.screenHeight - Hormiga.TAMANO)), posPlantas);
            stage.addActor(plantas.get(posPlantas));
            posPlantas++;
        }
    }

    public void detectarColision() {
        for (int i = 0; i < PantallaHormiga.getPosHormigas(); i++) {
            for (int j = 0; j < PantallaHormiga.getPosHormigas(); j++) {

                if (hormigas.get(j).isChocada() || hormigas.get(i).isChocada())
                    continue;

                if (hormigas.get(j) != hormigas.get(i) && (hormigas.get(j).polygon.contains(hormigas.get(i).polygon.getX(), hormigas.get(i).polygon.getY())
                        || hormigas.get(j).polygon.contains(hormigas.get(i).polygon.getX() + hormigas.get(i).TAMANO, hormigas.get(i).polygon.getY())
                        || hormigas.get(j).polygon.contains(hormigas.get(i).polygon.getX() + hormigas.get(i).TAMANO, hormigas.get(i).polygon.getY() + hormigas.get(i).TAMANO)
                        || hormigas.get(j).polygon.contains(hormigas.get(i).polygon.getX(), hormigas.get(i).polygon.getY() + hormigas.get(i).TAMANO))) {
                    chocado(PantallaHormiga.getHormigas().get(i), PantallaHormiga.getHormigas().get(j));
                }
            }
        }
    }

    public void chocado(final Hormiga hormiga1, final Hormiga hormiga2) {
        hormiga1.setChocada(true);
        hormiga2.setChocada(true);
        hormiga1.clearActions();
        hormiga2.clearActions();
        hormiga1.mover();
        hormiga2.mover();

        hormiga1.addAction(Actions.delay(Hormiga.TIEMPO_CHOQUE, Actions.run(new Runnable() {
            @Override
            public void run() {
                hormiga1.setChocada(false);
            }
        })));
        hormiga2.addAction(Actions.delay(Hormiga.TIEMPO_CHOQUE, Actions.run(new Runnable() {
            @Override
            public void run() {
                hormiga2.setChocada(false);
            }
        })));
    }

    private void processInput() {

        // Se ejecuta cuando le das ESC (ordenador) y Atras (movil).
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK) || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
        //TODO cerrar aplicaciones
        }

        Vector3 touchPoint = new Vector3();

        stage.getViewport().unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

        if (Gdx.input.isKeyJustPressed(Input.Keys.A) || Gdx.input.justTouched()) {

            //TODO añadir hormiga
            for (int i = 0; i < HORMIGAS_POR_CLIC; i++) {
                Random ran = new Random();
                hormigas.insertElementAt(new Hormiga(ran.nextInt(5) + 1, touchPoint.x, touchPoint.y), posHormigas);
                stage.addActor(hormigas.get(posHormigas));
                hormigas.get(posHormigas).mover();
                posHormigas++;
            }
        }
    }

    /**
     * GETTERS AND SETTERS
     */

    public static int getPosHormigas() {
        return posHormigas;
    }

    public static Vector<Planta> getPlantas() {
        return plantas;
    }

    public static Vector<Hormiga> getHormigas() {
        return hormigas;
    }
}
