package com.mygdx.frogger.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.frogger.Frogger;
import com.mygdx.frogger.com.mygdx.frogger.objects.Frog;

/**
 * Created by Caroll Madrona on 1/5/2015.
 */
public class GameScreen implements Screen {
    private Frog frog;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Frogger game;
    private BitmapFont font;

    public GameScreen(Frogger game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 720, 360);
        batch = new SpriteBatch();
        frog = new Frog();
        frog.setPosition(0,32);
        font = new BitmapFont();
        font.setScale(1.5f);
        font.setColor(Color.RED);
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        frog.draw(batch);
        font.draw(batch, "Game Screen", 150, 120);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
