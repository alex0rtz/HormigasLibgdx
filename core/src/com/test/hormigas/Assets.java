package com.test.hormigas;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {


    public static Texture background;

    private static Texture textureVerde;
    private static Texture textureNaranja;
    private static Texture textureRoja;
    private static Texture textureAzul;
    private static Texture textureRosa;

    public static Texture texturePlanta;

    public static Animation animationVerde;
    public static Animation animationNaranja;
    public static Animation animationRoja;
    public static Animation animationAzul;
    public static Animation animationRosa;

    public static final float screenWidth = 1280;
    public static final float screenHeight = 720;

    public static void loadtexture(){


        //Textura fondo
        background = new Texture("arena.png");
        background.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        // Textura Planta
        texturePlanta = new Texture(Gdx.files.internal("plant.png"));

        texturePlanta.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        // Texturas Hormigas
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
