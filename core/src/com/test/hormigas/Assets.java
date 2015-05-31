package com.test.hormigas;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {


    public static final int VERDE = 1;
    public static final int NARANJA = 2;
    public static final int ROJA = 3;
    public static final int AZUL = 4;
    public static final int ROSA = 5;

    public static Texture background;

    public static Texture textureHuevoVerde;
    public static Texture textureHuevoAzul;
    public static Texture textureHuevoRoja;
    public static Texture textureHuevoNaranja;
    public static Texture textureHuevoRosa;

    public static Texture textureHormigueroVerde;
    public static Texture textureHormigueroNaranja;
    public static Texture textureHormigueroRojo;
    public static Texture textureHormigueroAzul;
    public static Texture textureHormigueroRosa;

    public static Texture texturePlanta;

    private static Texture textureVerde;
    private static Texture textureNaranja;
    private static Texture textureRoja;
    private static Texture textureAzul;
    private static Texture textureRosa;

    public static Animation animationVerde;
    public static Animation animationNaranja;
    public static Animation animationRoja;
    public static Animation animationAzul;
    public static Animation animationRosa;

    private static Texture textureSoldadoVerde;
    private static Texture textureSoldadoNaranja;
    private static Texture textureSoldadoRoja;
    private static Texture textureSoldadoAzul;
    private static Texture textureSoldadoRosa;

    public static Animation animationSoldadoVerde;
    public static Animation animationSoldadoNaranja;
    public static Animation animationSoldadoRoja;
    public static Animation animationvSoldadoAzul;
    public static Animation animationSoldadoRosa;

    public static final float screenWidth = 1280;
    public static final float screenHeight = 720;

    public static void loadtexture(){
        // Textura hormiguero
        textureHormigueroVerde = new Texture("hormigueroVerde.png");
        textureHormigueroNaranja = new Texture("hormigueroNaranja.png");
        textureHormigueroRojo = new Texture("hormigueroRojo.png");
        textureHormigueroAzul = new Texture("hormigueroAzul.png");
        textureHormigueroRosa = new Texture("hormigueroRosa.png");

        textureHormigueroVerde.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        textureHormigueroNaranja.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        textureHormigueroRojo.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        textureHormigueroAzul.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        textureHormigueroRosa.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        // Textura huevo
        textureHuevoVerde = new Texture("huevoVerde.png");
        textureHuevoNaranja = new Texture("huevoNaranja.png");
        textureHuevoRoja = new Texture("huevoRojo.png");
        textureHuevoAzul = new Texture("huevoAzul.png");
        textureHuevoRosa = new Texture("huevoRosa.png");

        textureHuevoVerde.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        textureHuevoNaranja.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        textureHuevoRoja.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        textureHuevoAzul.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        textureHuevoRosa.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        //Textura fondo
        background = new Texture("arena.png");
        background.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        // Textura Planta
        texturePlanta = new Texture(Gdx.files.internal("plant.png"));

        texturePlanta.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        // Texturas Hormigas Obreras
        textureVerde = new Texture(Gdx.files.internal("spriteHormigaVerde.png"));
        textureNaranja = new Texture(Gdx.files.internal("spriteHormigaNaranja.png"));
        textureRoja = new Texture(Gdx.files.internal("spriteHormigaRoja.png"));
        textureAzul = new Texture(Gdx.files.internal("spriteHormigaAzul.png"));
        textureRosa = new Texture(Gdx.files.internal("spriteHormigaRosa.png"));

        textureVerde.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        textureNaranja.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        textureRoja.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        textureAzul.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        textureRosa.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        animationVerde = Assets.createAnimation(textureVerde, 3, 2, 0.04f);
        animationNaranja = Assets.createAnimation(textureNaranja, 3, 2, 0.04f);
        animationRoja = Assets.createAnimation(textureRoja, 3, 2, 0.04f);
        animationAzul = Assets.createAnimation(textureAzul, 3, 2, 0.04f);
        animationRosa = Assets.createAnimation(textureRosa, 3, 2, 0.04f);

        // Texturas Hormigas Soldado
        textureSoldadoVerde = new Texture(Gdx.files.internal("spriteHormigaSoldadoVerde.png"));
        textureSoldadoNaranja = new Texture(Gdx.files.internal("spriteHormigaSoldadoNaranja.png"));
        textureSoldadoRoja = new Texture(Gdx.files.internal("spriteHormigaSoldadoRojo.png"));
        textureSoldadoAzul = new Texture(Gdx.files.internal("spriteHormigaSoldadoAzul.png"));
        textureSoldadoRosa = new Texture(Gdx.files.internal("spriteHormigaSoldadoRosa.png"));

        textureSoldadoVerde.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        textureSoldadoNaranja.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        textureSoldadoRoja.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        textureSoldadoAzul.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        textureSoldadoRosa.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        animationSoldadoVerde = Assets.createAnimation(textureSoldadoVerde, 3, 2, 0.04f);
        animationSoldadoNaranja = Assets.createAnimation(textureSoldadoNaranja, 3, 2, 0.04f);
        animationSoldadoRoja = Assets.createAnimation(textureSoldadoRoja, 3, 2, 0.04f);
        animationvSoldadoAzul = Assets.createAnimation(textureSoldadoAzul, 3, 2, 0.04f);
        animationSoldadoRosa = Assets.createAnimation(textureSoldadoRosa, 3, 2, 0.04f);
    }

    public static Animation createAnimation(Texture texture, int columns, int rows, float animTime) {

        TextureRegion[][] temp = TextureRegion.split(texture, texture.getWidth() / columns, texture.getHeight() / rows);
        TextureRegion[] tempFrames = new TextureRegion[columns * rows];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                tempFrames[index++] = temp[i][j];
            }
        }

        Animation tempAnim = new Animation(animTime, tempFrames);

        return tempAnim;
    }
}
