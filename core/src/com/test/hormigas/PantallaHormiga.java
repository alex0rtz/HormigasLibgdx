package com.test.hormigas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.FillViewport;

import java.util.Random;
import java.util.Vector;

public class PantallaHormiga implements Screen {

    /**
     * VARIABLES PANTALLA
     */

    private static final int HORMIGAS_POR_CLIC = 25;
    private final float TIEMPO_PLANTAS = 5;
    private float tiempo_nueva_planta = 0;
    private final int MARGEN_PLANTAS = 70;

    final int SECTORES = 6;

    /**
     * VARIABLES LIBGDX
     */
    private Stage stage;
    private FillViewport viewport;

    private SpriteBatch stageBatch;

    private static Vector<MyActor> sector1, sector2, sector3, sector4, sector5, sector6;
    private static Vector<MyActor> sector7, sector8, sector9, sector10, sector11, sector12;
    private static Vector<MyActor> sector13, sector14, sector15, sector16, sector17, sector18;
    private static Vector<MyActor> sector19, sector20, sector21, sector22, sector23, sector24;
    private static Vector<MyActor> sector25, sector26, sector27, sector28, sector29, sector30;
    private static Vector<MyActor> sector31, sector32, sector33, sector34, sector35, sector36;

    private static Vector3 sec1, sec2, sec3, sec4, sec5, sec6;
    private static Vector3 sec7, sec8, sec9, sec10, sec11, sec12;
    private static Vector3 sec13, sec14, sec15, sec16, sec17, sec18;
    private static Vector3 sec19, sec20, sec21, sec22, sec23, sec24;
    private static Vector3 sec25, sec26, sec27, sec28, sec29, sec30;
    private static Vector3 sec31, sec32, sec33, sec34, sec35, sec36;

    private static Vector<Vector3> coordSectores;
    private static Vector<Vector> sectores;

    private static Vector<MyActor> pendientesEliminar;

    private Random ran = new Random();

    public PantallaHormiga(HormigasGame game) {
        stageBatch = new SpriteBatch();
        viewport = new FillViewport(Assets.screenWidth, Assets.screenHeight);

        sector1 = new Vector<>();
        sector2 = new Vector<>();
        sector3 = new Vector<>();
        sector4 = new Vector<>();
        sector5 = new Vector<>();
        sector6 = new Vector<>();

        sector7 = new Vector<>();
        sector8 = new Vector<>();
        sector9 = new Vector<>();
        sector10 = new Vector<>();
        sector11 = new Vector<>();
        sector12 = new Vector<>();

        sector13 = new Vector<>();
        sector14 = new Vector<>();
        sector15 = new Vector<>();
        sector16 = new Vector<>();
        sector17 = new Vector<>();
        sector18 = new Vector<>();

        sector19 = new Vector<>();
        sector20 = new Vector<>();
        sector21 = new Vector<>();
        sector22 = new Vector<>();
        sector23 = new Vector<>();
        sector24 = new Vector<>();

        sector25 = new Vector<>();
        sector26 = new Vector<>();
        sector27 = new Vector<>();
        sector28 = new Vector<>();
        sector29 = new Vector<>();
        sector30 = new Vector<>();

        sector31 = new Vector<>();
        sector32 = new Vector<>();
        sector33 = new Vector<>();
        sector34 = new Vector<>();
        sector35 = new Vector<>();
        sector36 = new Vector<>();

        sectores = new Vector<>();

        sectores.add(sector1);
        sectores.add(sector2);
        sectores.add(sector3);
        sectores.add(sector4);
        sectores.add(sector5);
        sectores.add(sector6);

        sectores.add(sector7);
        sectores.add(sector8);
        sectores.add(sector9);
        sectores.add(sector10);
        sectores.add(sector11);
        sectores.add(sector12);

        sectores.add(sector13);
        sectores.add(sector14);
        sectores.add(sector15);
        sectores.add(sector16);
        sectores.add(sector17);
        sectores.add(sector18);

        sectores.add(sector19);
        sectores.add(sector20);
        sectores.add(sector21);
        sectores.add(sector22);
        sectores.add(sector23);
        sectores.add(sector24);

        sectores.add(sector25);
        sectores.add(sector26);
        sectores.add(sector27);
        sectores.add(sector28);
        sectores.add(sector29);
        sectores.add(sector30);

        sectores.add(sector31);
        sectores.add(sector32);
        sectores.add(sector33);
        sectores.add(sector34);
        sectores.add(sector35);
        sectores.add(sector36);

        coordSectores = new Vector<>();

        sec1 = new Vector3(0, 0, 1);
        sec2 = new Vector3((Assets.screenWidth / 3), 0, 2);
        sec3 = new Vector3((Assets.screenWidth / 3) * 2, 0, 3);
        sec4 = new Vector3((Assets.screenWidth / 3) * 3, 0, 4);
        sec5 = new Vector3((Assets.screenWidth / 3) * 4, 0, 5);
        sec6 = new Vector3((Assets.screenWidth / 3) * 5, 0, 6);

        sec7 = new Vector3(0, (Assets.screenWidth / 3), 7);
        sec8 = new Vector3((Assets.screenWidth / 3), (Assets.screenHeight / 3), 8);
        sec9 = new Vector3((Assets.screenWidth / 3) * 2, (Assets.screenWidth / 3), 9);
        sec10 = new Vector3((Assets.screenWidth / 3) * 3, (Assets.screenWidth / 3), 10);
        sec11 = new Vector3((Assets.screenWidth / 3) * 4, (Assets.screenWidth / 3), 11);
        sec12 = new Vector3((Assets.screenWidth / 3) * 5, (Assets.screenWidth / 3), 12);

        sec13 = new Vector3(0, (Assets.screenHeight / 3) * 2, 13);
        sec14 = new Vector3((Assets.screenWidth / 3), (Assets.screenHeight / 3) * 2, 14);
        sec15 = new Vector3((Assets.screenWidth / 3) * 2, (Assets.screenHeight / 3) * 2, 15);
        sec16 = new Vector3((Assets.screenWidth / 3) * 3, (Assets.screenHeight / 3) * 2, 16);
        sec17 = new Vector3((Assets.screenWidth / 3) * 4, (Assets.screenHeight / 3) * 2, 17);
        sec18 = new Vector3((Assets.screenWidth / 3) * 5, (Assets.screenHeight / 3) * 2, 18);

        sec19 = new Vector3(0, (Assets.screenHeight / 3) * 3, 19);
        sec20 = new Vector3((Assets.screenWidth / 3), (Assets.screenHeight / 3) * 3, 20);
        sec21 = new Vector3((Assets.screenWidth / 3) * 2, (Assets.screenHeight / 3) * 3, 21);
        sec22 = new Vector3((Assets.screenWidth / 3) * 3, (Assets.screenHeight / 3) * 3, 22);
        sec23 = new Vector3((Assets.screenWidth / 3) * 4, (Assets.screenHeight / 3) * 3, 23);
        sec24 = new Vector3((Assets.screenWidth / 3) * 5, (Assets.screenHeight / 3) * 3, 24);

        sec25 = new Vector3(0, (Assets.screenHeight / 3) * 4, 25);
        sec26 = new Vector3((Assets.screenWidth / 3), (Assets.screenHeight / 3) * 4, 26);
        sec27 = new Vector3((Assets.screenWidth / 3) * 2, (Assets.screenHeight / 3) * 4, 27);
        sec28 = new Vector3((Assets.screenWidth / 3) * 3, (Assets.screenHeight / 3) * 4, 28);
        sec29 = new Vector3((Assets.screenWidth / 3) * 4, (Assets.screenHeight / 3) * 4, 29);
        sec30 = new Vector3((Assets.screenWidth / 3) * 5, (Assets.screenHeight / 3) * 4, 30);

        sec31 = new Vector3(0, (Assets.screenHeight / 3) * 5, 31);
        sec32 = new Vector3((Assets.screenWidth / 3), (Assets.screenHeight / 3) * 5, 32);
        sec33 = new Vector3((Assets.screenWidth / 3) * 2, (Assets.screenHeight / 3) * 5, 33);
        sec34 = new Vector3((Assets.screenWidth / 3) * 3, (Assets.screenHeight / 3) * 5, 34);
        sec35 = new Vector3((Assets.screenWidth / 3) * 4, (Assets.screenHeight / 3) * 5, 35);
        sec36 = new Vector3((Assets.screenWidth / 3) * 5, (Assets.screenHeight / 3) * 5, 36);

        coordSectores.add(sec1);
        coordSectores.add(sec2);
        coordSectores.add(sec3);
        coordSectores.add(sec4);
        coordSectores.add(sec5);
        coordSectores.add(sec6);

        coordSectores.add(sec7);
        coordSectores.add(sec8);
        coordSectores.add(sec9);
        coordSectores.add(sec10);
        coordSectores.add(sec11);
        coordSectores.add(sec12);

        coordSectores.add(sec13);
        coordSectores.add(sec14);
        coordSectores.add(sec15);
        coordSectores.add(sec16);
        coordSectores.add(sec17);
        coordSectores.add(sec18);

        coordSectores.add(sec19);
        coordSectores.add(sec20);
        coordSectores.add(sec21);
        coordSectores.add(sec22);
        coordSectores.add(sec23);
        coordSectores.add(sec24);

        coordSectores.add(sec25);
        coordSectores.add(sec26);
        coordSectores.add(sec27);
        coordSectores.add(sec28);
        coordSectores.add(sec29);
        coordSectores.add(sec30);

        coordSectores.add(sec31);
        coordSectores.add(sec32);
        coordSectores.add(sec33);
        coordSectores.add(sec34);
        coordSectores.add(sec35);
        coordSectores.add(sec36);

        pendientesEliminar = new Vector<>();

        stage = new Stage(viewport, stageBatch);
        Gdx.input.setInputProcessor(stage);

        Image background = new Image(Assets.background);
        background.setSize(Assets.screenWidth, Assets.screenHeight);
        background.setScaling(Scaling.fill);

        stage.addActor(background);

        crearHormigueros();
        crearHormigasObreras(1, 2);
        crearHormigasObreras(2, 2);
        crearHormigasObreras(3, 2);
        crearHormigasObreras(4, 2);
        crearHormigasObreras(5, 2);
        crearPlantas(10);

        crearHormigasSoldados(1, 1);

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

        tiempo_nueva_planta += delta;

        if (tiempo_nueva_planta >= TIEMPO_PLANTAS) {
            crearPlantas(1);
            tiempo_nueva_planta = 0;
        }

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

    public void crearHormiguero(final int tipo, Vector2 pos, int sector) {
        Hormiguero h = new Hormiguero(tipo, pos.x, pos.y);
        h.setZIndex(20);
        stage.addActor(h);
        h.getPolygon().setPosition(h.getX(), h.getY());
    }

    public void crearHormigueros() {
        Gdx.app.log("TAMANO", Assets.screenWidth + " - " + Assets.screenHeight);
        Gdx.app.log("LOCALITATION", "1 de X entre 1 y " + (Assets.screenWidth / SECTORES + Hormiguero.TAMANO));
        Gdx.app.log("LOCALITATION", "1 de Y entre 1 y " + (Assets.screenHeight / SECTORES + Hormiguero.TAMANO));
        float x1 = ran.nextFloat() * (Assets.screenWidth / SECTORES) + Hormiguero.TAMANO;
        float y1 = ran.nextFloat() * (Assets.screenHeight / SECTORES) + Hormiguero.TAMANO;
        Gdx.app.log("LOCALITATION", "1: " + x1 + " - " + y1);

        Gdx.app.log("LOCALITATION", "2 de X entre " + (((Assets.screenWidth / SECTORES) * (SECTORES - 1)) - Hormiguero.TAMANO) + " y " + (Assets.screenWidth));
        Gdx.app.log("LOCALITATION", "2 de Y entre 1 y " + ((Assets.screenHeight / SECTORES) + Hormiguero.TAMANO));
        float x2 = ran.nextFloat() * (Assets.screenWidth / SECTORES - Hormiguero.TAMANO) + (Assets.screenWidth / SECTORES) * (SECTORES - 1);
        float y2 = ran.nextFloat() * Assets.screenHeight / SECTORES + Hormiguero.TAMANO;
        Gdx.app.log("LOCALITATION", "2: " + x2 + " - " + y2);

        Gdx.app.log("LOCALITATION", "3 de X entre " + (((Assets.screenWidth / SECTORES) * (SECTORES - 1)) - Hormiguero.TAMANO) + " y " + (Assets.screenWidth));
        Gdx.app.log("LOCALITATION", "3 de Y entre " + (((Assets.screenHeight / SECTORES) * (SECTORES - 1)) - Hormiguero.TAMANO) + " y " + (Assets.screenHeight));
        float x3 = ran.nextFloat() * (Assets.screenWidth / SECTORES - Hormiguero.TAMANO) + (Assets.screenWidth / SECTORES) * (SECTORES - 1);
        float y3 = ran.nextFloat() * (Assets.screenHeight / SECTORES - Hormiguero.TAMANO) + (Assets.screenHeight / SECTORES) * (SECTORES - 1);
        Gdx.app.log("LOCALITATION", "3: " + x3 + " - " + y3);

        Gdx.app.log("LOCALITATION", "4 de X entre " + 1 + " y " + (Assets.screenWidth / SECTORES + Hormiguero.TAMANO));
        Gdx.app.log("LOCALITATION", "4 de Y entre " + (((Assets.screenHeight / SECTORES) * (SECTORES - 1)) - Hormiguero.TAMANO) + " y " + (Assets.screenHeight));
        float x4 = ran.nextFloat() * Assets.screenWidth / SECTORES + Hormiguero.TAMANO;
        float y4 = ran.nextFloat() * (Assets.screenHeight / SECTORES - Hormiguero.TAMANO) + (Assets.screenHeight / SECTORES) * (SECTORES - 1);
        Gdx.app.log("LOCALITATION", "4: " + x4 + " - " + y4);

        Gdx.app.log("LOCALITATION", "5 de X entre " + (Assets.screenWidth / SECTORES) + " y " + (Assets.screenWidth / SECTORES - Hormiguero.TAMANO + (Assets.screenWidth / SECTORES)));
        Gdx.app.log("LOCALITATION", "5 de Y entre " + (Assets.screenHeight / SECTORES) + " y " + (Assets.screenHeight / SECTORES - Hormiguero.TAMANO + (Assets.screenHeight / SECTORES)));
        float x5 = ran.nextFloat() * (Assets.screenWidth / SECTORES - Hormiguero.TAMANO) + (Assets.screenWidth / SECTORES) * (SECTORES / 2);
        float y5 = ran.nextFloat() * (Assets.screenHeight / SECTORES - Hormiguero.TAMANO) + (Assets.screenHeight / SECTORES) * (SECTORES / 2);
        int s5 = 0;
        Gdx.app.log("LOCALITATION", "5: " + x5 + " - " + y5);

        for (Vector3 vec : coordSectores) {
            if (x5 >= vec.x && x5 <= vec.x + (Assets.screenWidth / SECTORES) && y5 >= vec.y && y5 <= vec.y + (Assets.screenHeight)) {
                s5 = ((int) vec.z);
            }
        }

        for (int i = 1; i < 6; i++) {
            switch (i) {
                case 1:
                    crearHormiguero(1, new Vector2(x1, y1), 1);
                    break;
                case 2:
                    crearHormiguero(2, new Vector2(x2, y2), 2);
                    break;
                case 3:
                    crearHormiguero(3, new Vector2(x3, y3), 3);
                    break;
                case 4:
                    crearHormiguero(4, new Vector2(x4, y4), 4);
                    break;
                case 5:
                    crearHormiguero(5, new Vector2(x5, y5), s5);
                    break;
            }
        }
    }

    public void crearHormigaSoldado(int tipo, Vector2 pos, int sector) {
        Soldado hs = new Soldado(tipo, pos.x, pos.y, sector);
        hs.setZIndex(10);
        introducirSector(sector, hs);
        stage.addActor(hs);
        hs.getPolygon().setPosition(hs.getX(), hs.getY());
    }

    public void crearHormigaObrera(int tipo, Vector2 pos, int sector) {
        Obrera ho = new Obrera(tipo, pos.x, pos.y, sector);
        ho.setZIndex(10);
        introducirSector(sector, ho);
        stage.addActor(ho);
        ho.getPolygon().setPosition(ho.getX(), ho.getY());
    }

    public void crearHuevo(final int tipo, Vector2 pos, int sector) {
        final Huevo huevo = new Huevo(tipo);
        huevo.setPosition(pos.x, pos.y);
        stage.addActor(huevo);
        huevo.setZIndex(1);
        stage.addAction(Actions.delay(Huevo.TIEMPO_ECLOSION,
                        Actions.run(new Runnable() {
                            @Override
                            public void run() {
                                float x = ran.nextFloat() * (Assets.screenWidth - Planta.TAMANO);
                                float y = ran.nextFloat() * (Assets.screenHeight - Planta.TAMANO);

                                for (Vector3 vec : coordSectores)
                                    if (x >= vec.x && x <= vec.x + (Assets.screenWidth / SECTORES) && y >= vec.y && y <= vec.y + (Assets.screenHeight))
                                        crearHormigaObrera(tipo, new Vector2(x, y), ((int) vec.z));
                                huevo.remove();
                            }
                        })
                )
        );
    }

    public void crearPlanta(Vector2 pos, int sector) {
        Planta p = new Planta(pos.x, pos.y, sector);
        introducirSector(sector, p);
        stage.addActor(p);
        p.getPolygon().setPosition(p.getX(), p.getY());
    }

    public static void introducirSector(int sector, MyActor actor) {
        switch (sector) {
            case 1:
                sector1.add(actor);
                break;
            case 2:
                sector2.add(actor);
                break;
            case 3:
                sector3.add(actor);
                break;
            case 4:
                sector4.add(actor);
                break;
            case 5:
                sector5.add(actor);
                break;
            case 6:
                sector6.add(actor);
                break;
            case 7:
                sector7.add(actor);
                break;
            case 8:
                sector8.add(actor);
                break;
            case 9:
                sector9.add(actor);
                break;
            case 10:
                sector10.add(actor);
                break;
            case 11:
                sector11.add(actor);
                break;
            case 12:
                sector12.add(actor);
                break;
            case 13:
                sector13.add(actor);
                break;
            case 14:
                sector14.add(actor);
                break;
            case 15:
                sector15.add(actor);
                break;
            case 16:
                sector16.add(actor);
                break;
            case 17:
                sector17.add(actor);
                break;
            case 18:
                sector18.add(actor);
                break;
            case 19:
                sector19.add(actor);
                break;
            case 20:
                sector20.add(actor);
                break;
            case 21:
                sector21.add(actor);
                break;
            case 22:
                sector22.add(actor);
                break;
            case 23:
                sector23.add(actor);
                break;
            case 24:
                sector24.add(actor);
                break;
            case 25:
                sector25.add(actor);
                break;
            case 26:
                sector26.add(actor);
                break;
            case 27:
                sector27.add(actor);
                break;
            case 28:
                sector28.add(actor);
                break;
            case 29:
                sector29.add(actor);
                break;
            case 30:
                sector30.add(actor);
                break;
            case 31:
                sector31.add(actor);
                break;
            case 32:
                sector32.add(actor);
                break;
            case 33:
                sector33.add(actor);
                break;
            case 34:
                sector34.add(actor);
                break;
            case 35:
                sector35.add(actor);
                break;
            default:
                sector36.add(actor);
                break;
        }
    }

    public static void eliminarSector(int sector, MyActor actor) {
        switch (sector) {
            case 1:
                sector1.remove(actor);
                break;
            case 2:
                sector2.remove(actor);
                break;
            case 3:
                sector3.remove(actor);
                break;
            case 4:
                sector4.remove(actor);
                break;
            case 5:
                sector5.remove(actor);
                break;
            case 6:
                sector6.remove(actor);
                break;
            case 7:
                sector7.remove(actor);
                break;
            case 8:
                sector8.remove(actor);
                break;
            case 9:
                sector9.remove(actor);
                break;
            case 10:
                sector10.remove(actor);
                break;
            case 11:
                sector11.remove(actor);
                break;
            case 12:
                sector12.remove(actor);
                break;
            case 13:
                sector13.remove(actor);
                break;
            case 14:
                sector14.remove(actor);
                break;
            case 15:
                sector15.remove(actor);
                break;
            case 16:
                sector16.remove(actor);
                break;
            case 17:
                sector17.remove(actor);
                break;
            case 18:
                sector18.remove(actor);
                break;
            case 19:
                sector19.remove(actor);
                break;
            case 20:
                sector20.remove(actor);
                break;
            case 21:
                sector21.remove(actor);
                break;
            case 22:
                sector22.remove(actor);
                break;
            case 23:
                sector23.remove(actor);
                break;
            case 24:
                sector24.remove(actor);
                break;
            case 25:
                sector25.remove(actor);
                break;
            case 26:
                sector26.remove(actor);
                break;
            case 27:
                sector27.remove(actor);
                break;
            case 28:
                sector28.remove(actor);
                break;
            case 29:
                sector29.remove(actor);
                break;
            case 30:
                sector30.remove(actor);
                break;
            case 31:
                sector31.remove(actor);
                break;
            case 32:
                sector32.remove(actor);
                break;
            case 33:
                sector33.remove(actor);
                break;
            case 34:
                sector34.remove(actor);
                break;
            case 35:
                sector35.remove(actor);
                break;
            default:
                sector36.remove(actor);
                break;
        }
    }

    public void crearHormigasObreras(int tipo, int numero) {
        float x, y;
        for (int i = 0; i < numero; i++) {
            x = ran.nextFloat() * (Assets.screenWidth - Planta.TAMANO);
            y = ran.nextFloat() * (Assets.screenHeight - Planta.TAMANO);

            for (Vector3 vec : coordSectores) {
                if (x >= vec.x && x <= vec.x + (Assets.screenWidth / SECTORES) && y >= vec.y && y <= vec.y + (Assets.screenHeight)) {
                    crearHormigaObrera(tipo, new Vector2(x, y), ((int) vec.z));
                }
            }

        }
    }

    public void crearHormigasSoldados(int tipo, int numero) {
        float x, y;
        for (int i = 0; i < numero; i++) {
            x = ran.nextFloat() * (Assets.screenWidth - Planta.TAMANO);
            y = ran.nextFloat() * (Assets.screenHeight - Planta.TAMANO);

            for (Vector3 vec : coordSectores) {
                if (x >= vec.x && x <= vec.x + (Assets.screenWidth / SECTORES) && y >= vec.y && y <= vec.y + (Assets.screenHeight)) {
                    crearHormigaSoldado(tipo, new Vector2(x, y), ((int) vec.z));
                }
            }

        }
    }

    public void crearPlantas(int numero) {
        float xa = Assets.screenWidth - MARGEN_PLANTAS * 2 - Planta.TAMANO + MARGEN_PLANTAS;
        float ya = Assets.screenHeight - MARGEN_PLANTAS * 2 - Planta.TAMANO + MARGEN_PLANTAS;
        float x, y;

        for (int i = 0; i < numero; i++) {
            x = ran.nextFloat() * xa;
            y = ran.nextFloat() * ya;

            for (Vector3 vec : coordSectores) {
                if (x >= vec.x && x <= vec.x + (Assets.screenWidth / SECTORES) && y >= vec.y && y <= vec.y + (Assets.screenHeight)) {
                    crearPlanta(new Vector2(x, y), ((int) vec.z));
                }
            }

        }
    }

    public void detectarColision() {

        for (MyActor act : pendientesEliminar) {
            eliminarSector(act.getSector(), act);
            act.remove();
        }

        pendientesEliminar.clear();

        if (!sector1.isEmpty())
            detectarColisionesSector(sector1);
        if (!sector2.isEmpty())
            detectarColisionesSector(sector2);
        if (!sector3.isEmpty())
            detectarColisionesSector(sector3);
        if (!sector4.isEmpty())
            detectarColisionesSector(sector4);
        if (!sector5.isEmpty())
            detectarColisionesSector(sector5);
        if (!sector6.isEmpty())
            detectarColisionesSector(sector6);
        if (!sector7.isEmpty())
            detectarColisionesSector(sector7);
        if (!sector8.isEmpty())
            detectarColisionesSector(sector8);
        if (!sector9.isEmpty())
            detectarColisionesSector(sector9);
        if (!sector10.isEmpty())
            detectarColisionesSector(sector10);
        if (!sector11.isEmpty())
            detectarColisionesSector(sector11);
        if (!sector12.isEmpty())
            detectarColisionesSector(sector12);
        if (!sector13.isEmpty())
            detectarColisionesSector(sector13);
        if (!sector14.isEmpty())
            detectarColisionesSector(sector14);
        if (!sector15.isEmpty())
            detectarColisionesSector(sector15);
        if (!sector16.isEmpty())
            detectarColisionesSector(sector16);
        if (!sector17.isEmpty())
            detectarColisionesSector(sector17);
        if (!sector18.isEmpty())
            detectarColisionesSector(sector18);
        if (!sector19.isEmpty())
            detectarColisionesSector(sector19);
        if (!sector20.isEmpty())
            detectarColisionesSector(sector20);
        if (!sector21.isEmpty())
            detectarColisionesSector(sector21);
        if (!sector22.isEmpty())
            detectarColisionesSector(sector22);
        if (!sector23.isEmpty())
            detectarColisionesSector(sector23);
        if (!sector24.isEmpty())
            detectarColisionesSector(sector24);
        if (!sector25.isEmpty())
            detectarColisionesSector(sector25);
        if (!sector26.isEmpty())
            detectarColisionesSector(sector26);
        if (!sector27.isEmpty())
            detectarColisionesSector(sector27);
        if (!sector28.isEmpty())
            detectarColisionesSector(sector28);
        if (!sector29.isEmpty())
            detectarColisionesSector(sector29);
        if (!sector30.isEmpty())
            detectarColisionesSector(sector30);
        if (!sector31.isEmpty())
            detectarColisionesSector(sector31);
        if (!sector32.isEmpty())
            detectarColisionesSector(sector32);
        if (!sector33.isEmpty())
            detectarColisionesSector(sector33);
        if (!sector34.isEmpty())
            detectarColisionesSector(sector34);
        if (!sector35.isEmpty())
            detectarColisionesSector(sector35);
        if (!sector36.isEmpty())
            detectarColisionesSector(sector36);
    }

    private void detectarColisionesSector(Vector<MyActor> sector) {
        for (MyActor act1 : sector) {
            if (act1 instanceof Hormiga && ((Hormiga) act1).isChocada())
                continue;
            for (MyActor act2 : sector) {
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
        if (!((Obrera) hormiga).isEsAdulta()) {
            ((Obrera) hormiga).seguirCreciendo();
            return;
        }

        int pro = ran.nextInt(100) + 1;

        if (!hormiga.isChocada()) {
            hormiga.setChocada(true);
            hormiga.clearActions();
            hormiga.mirar(planta);

            // Regar planta
            if (planta.isViva() && !planta.isComestible() && hormiga.getTipo() != Assets.ROJA) {
                if (hormiga.getTipo() == Assets.VERDE) {
                    planta.regar();
                    hormiga.regar();
                    comprobarMuerte(hormiga);
                } else if (hormiga.getTipo() == Assets.AZUL && pro >= 1 && pro <= 75) {
                    planta.regar();
                    hormiga.regar();
                    comprobarMuerte(hormiga);
                } else if (hormiga.getTipo() == Assets.NARANJA && pro >= 1 && pro <= 50) {
                    planta.regar();
                    hormiga.regar();
                    comprobarMuerte(hormiga);
                } else if (hormiga.getTipo() == Assets.ROSA && pro >= 1 && pro <= 25) {
                    planta.regar();
                    hormiga.regar();
                    comprobarMuerte(hormiga);
                } else {
                    hormiga.rebotar();
                }

                // Comer planta
            } else if (planta.isComestible() && planta.isViva()) {
                planta.comer();
                hormiga.comer();
                // Matar planta
                if (!planta.isComestible()) {
                    planta.matar();
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

            } else {
                hormiga.rebotar();
            }
        }
    }

    private void comprobarMuerte(final Hormiga hormiga) {
        if (!hormiga.viva())
            stage.addAction(Actions.delay(Hormiga.TIEMPO_PELEA * Hormiga.IMPACTOS_PELEA * 2,
                            Actions.run(new Runnable() {
                                @Override
                                public void run() {
                                    pendientesEliminar.add(hormiga);
                                }
                            })
                    )
            );
    }

    private void choqueEntreHormigas(final Hormiga h1, final Hormiga h2) {


        if (h1.isChocada() || h2.isChocada())
            return;
        else {
            h1.setChocada(true);
            h2.setChocada(true);
            h1.clearActions();
            h2.clearActions();
        }

        if (h1 instanceof Obrera || h2 instanceof Obrera) {
            if (!(((Obrera) h1).isEsAdulta() && ((Obrera) h2).isEsAdulta())) {
                if (((Obrera) h1).isEsAdulta()) {
                    h1.rebotar();
                    ((Obrera) h2).seguirCreciendo();
                    h2.mover(h2.getRandomAngle());
                } else if (((Obrera) h2).isEsAdulta()) {
                    h2.rebotar();
                    ((Obrera) h1).seguirCreciendo();
                    h1.mover(h1.getRandomAngle());
                } else {
                    ((Obrera) h1).seguirCreciendo();
                    h1.rebotar();
                    ((Obrera) h2).seguirCreciendo();
                    h2.mover(h2.getRandomAngle());
                }
                return;
            }
        }

        // Reproducirse
        int pro1 = ran.nextInt(100) + 1;
        int pro2 = ran.nextInt(100) + 1;

        if (pro1 >= 1 && pro1 <= h1.probabilidadObreras().x || pro2 >= 1 && pro2 <= h2.probabilidadObreras().x) {
            h1.mirar(h2);
            h2.mirar(h1);
            h1.pelear();
            h2.pelear();
            pelearse(h1, h2);
            comprobarMuerte(h1);
            comprobarMuerte(h2);
        } else if (pro1 >= h1.probabilidadObreras().x + 1 && pro1 <= h1.probabilidadObreras().x + h1.probabilidadObreras().y || pro2 >= h2.probabilidadObreras().x + 1 && pro2 <= h2.probabilidadObreras().x + h2.probabilidadObreras().y) {
            h1.mirar(h2);
            h2.girar(h1);
            h1.pelear();
            h2.pelear();
            comprobarMuerte(h1);
            comprobarMuerte(h2);

            Vector2 centroH1 = h1.localToStageCoordinates(new Vector2(h1.getOriginX(), h1.getOriginY()));
            Vector2 centroH2 = h2.localToStageCoordinates(new Vector2(h2.getOriginX(), h2.getOriginY()));

            final Vector2 centroHuevo = new Vector2((centroH1.x + centroH2.x) / 2, (centroH1.y + centroH2.y) / 2);
            stage.addAction(Actions.delay(Hormiga.TIEMPO_PELEA * Hormiga.IMPACTOS_PELEA * 2,
                            Actions.run(new Runnable() {
                                @Override
                                public void run() {
                                    float x = ran.nextFloat() * (Assets.screenWidth - Planta.TAMANO);
                                    float y = ran.nextFloat() * (Assets.screenHeight - Planta.TAMANO);

                                    for (Vector3 vec : coordSectores) {
                                        if (x >= vec.x && x <= vec.x + (Assets.screenWidth / SECTORES) && y >= vec.y && y <= vec.y + (Assets.screenHeight)) {
                                            crearHuevo(reproducirse(h1, h2), centroHuevo, ((int) vec.z));
                                        }
                                    }
                                }
                            })
                    )
            );

            return;
        } else {
            h1.rebotar();
            h2.rebotar();
        }
    }

    private int reproducirse(Hormiga h1, Hormiga h2) {
        h1.reproducir();
        h2.reproducir();

        float h1verde, h1naranja, h1roja, h1azul, h1rosa;
        float h2verde, h2naranja, h2roja, h2azul, h2rosa;
        float totalVerde, totalNaranja, totalRoja, totalAzul, totalRosa;

        switch (h1.getTipo()) {
            case Assets.VERDE:
                h1verde = 25;
                h1azul = 10;
                h1naranja = 7;
                h1rosa = 7;
                h1roja = 1;
                break;
            case Assets.NARANJA:
                h1verde = 4;
                h1azul = 8;
                h1naranja = 25;
                h1rosa = 9;
                h1roja = 4;
                break;
            case Assets.ROJA:
                h1verde = 1;
                h1azul = 7;
                h1naranja = 7;
                h1rosa = 10;
                h1roja = 25;
                break;
            case Assets.AZUL:
                h1verde = 7;
                h1azul = 25;
                h1naranja = 10;
                h1rosa = 7;
                h1roja = 1;
                break;
            default:
                h1verde = 4;
                h1azul = 8;
                h1naranja = 9;
                h1rosa = 25;
                h1roja = 4;
                break;
        }

        switch (h2.getTipo()) {
            case Assets.VERDE:
                h2verde = 25;
                h2azul = 10;
                h2naranja = 7;
                h2rosa = 7;
                h2roja = 1;
                break;
            case Assets.NARANJA:
                h2verde = 4;
                h2azul = 8;
                h2naranja = 25;
                h2rosa = 9;
                h2roja = 4;
                break;
            case Assets.ROJA:
                h2verde = 1;
                h2azul = 7;
                h2naranja = 7;
                h2rosa = 10;
                h2roja = 25;
                break;
            case Assets.AZUL:
                h2verde = 7;
                h2azul = 25;
                h2naranja = 10;
                h2rosa = 7;
                h2roja = 1;
                break;
            default:
                h2verde = 4;
                h2azul = 8;
                h2naranja = 9;
                h2rosa = 25;
                h2roja = 4;
                break;
        }

        totalAzul = h1azul + h2azul;
        totalRosa = h1rosa + h2rosa;
        totalNaranja = h1naranja + h2naranja;
        totalVerde = h1verde + h2verde;
        totalRoja = h1roja + h2roja;

        float pro = ran.nextFloat() * 100 + 1;

        if (pro >= 1 && pro <= totalAzul) {
            return Assets.AZUL;
        } else if (pro >= totalAzul + 1 && pro <= totalAzul + totalRosa) {
            return Assets.ROSA;
        } else if (pro >= totalAzul + totalRosa + 1 && pro <= totalAzul + totalRosa + totalNaranja) {
            return Assets.NARANJA;
        } else if (pro >= totalAzul + totalRosa + totalNaranja + 1 && pro <= totalAzul + totalRosa + totalNaranja + totalVerde) {
            return Assets.VERDE;
        } else {
            return Assets.ROJA;
        }

    }

    public void pelearse(Hormiga h1, Hormiga h2) {
        float pro;

        int h1e = h1.getEnergia();
        int h1v = h1.getVictorias();
        int h2e = h2.getEnergia();
        int h2v = h2.getVictorias();

        float porEne1, porEne2;
        float porVic1, porVic2;
        float porRan = 10;
        float porTotal1, porTotal2;
        float porTotal;

        /**
         * Comprueba cual de las dos hormigas tiene más energia.
         * La que tiene más energia es el 100% y la otra sera el porcentaje respecto a la otra.
         * La energia vale un 50%.
         */
        porEne1 = (h1e / 50) * 100;
        porEne2 = (h2e / 50) * 100;

        /**
         * Comprueba cual de las dos hormigas tiene más energia.
         * La que tiene más victorias es el 100% y la otra sera el porcentaje respecto a la otra.
         * La victorias vale un 40%.
         */
        porVic1 = (h1v / 40) * 100;
        porVic2 = (h2v / 40) * 100;

        /**
         * El azar representa el 10%, para que una hormiga inferior tengo alguna posibilidad de ganar a
         * una hormiga que tenga más posibilidades
         */
        porTotal1 = porEne1 + porVic1 + porRan;
        porTotal2 = porEne2 + porVic2 + porRan;

        /**
         * Se suman ambos resultados. De ambas hormigas.
         */
        porTotal = porTotal1 + porTotal2;

        /**
         * La que tenga mas puntuación que la otra ganará.
         * Y por lo tanto se hace con la mitad de la energia de la otra.
         */
        pro = ran.nextFloat() * porTotal + 1;

        if (pro >= 1 && pro <= porTotal1) {
            h1.ganarPelea(h2.getEnergia() / 2);
            h2.perderPelea();
        } else if (pro >= porTotal1 + 1 && pro <= porTotal) {
            h2.ganarPelea(h1.getEnergia() / 2);
            h1.perderPelea();
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

                float x = touchPoint.x;
                float y = touchPoint.y;

                for (Vector3 vec : coordSectores) {
                    if (x >= vec.x && x <= vec.x + (Assets.screenWidth / SECTORES) && y >= vec.y && y <= vec.y + (Assets.screenHeight)) {
                        Obrera ho = new Obrera(ran.nextInt(5) + 1, x, y, ((int) vec.z));
                        introducirSector(((int) vec.z), ho);
                        stage.addActor(ho);
                        ho.getPolygon().setPosition(ho.getX(), ho.getY());
                    }
                }

            }
        }


        if (Gdx.input.isKeyJustPressed(Input.Keys.P) || (Gdx.input.justTouched() && Gdx.input.isButtonPressed(Input.Buttons.RIGHT))) {

            //TODO añadir Planta
            float x = touchPoint.x;
            float y = touchPoint.y;

            for (Vector3 vec : coordSectores) {
                if (x >= vec.x && x <= vec.x + (Assets.screenWidth / SECTORES) && y >= vec.y && y <= vec.y + (Assets.screenHeight)) {
                    Planta p = new Planta(x - Planta.TAMANO / 2, y - Planta.TAMANO / 2, ((int) vec.z));
                    introducirSector(((int) vec.z), p);
                    stage.addActor(p);
                    p.getPolygon().setPosition(p.getX(), p.getY());
                }
            }
        }
    }

    /**
     * GETTERS AND SETTERS
     */

    public static Vector<Vector3> getCoordSectores() {
        return coordSectores;
    }
}
