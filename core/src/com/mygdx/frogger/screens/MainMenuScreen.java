package com.mygdx.frogger.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.frogger.Frogger;

/**
 * Created by Caroll Madrona on 1/5/2015.
 */
public class MainMenuScreen implements Screen {

    private Frogger game;
    private SpriteBatch batch;
    private BitmapFont font;

    public MainMenuScreen(Frogger game){
        this.game = game;
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setScale(2f);
        font.setColor(Color.RED);
    }

    @Override
    public void render(float delta) {
        batch.begin();
        font.draw(batch, "Main Menu", 360, 200);
        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
        }
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
    }
}
