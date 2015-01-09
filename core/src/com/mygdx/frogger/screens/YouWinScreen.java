package com.mygdx.frogger.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.frogger.Frogger;

/**
 * Created by Caroll Madrona on 1/9/2015.
 */
public class YouWinScreen  implements Screen {

    private Frogger game;
    private SpriteBatch batch;
    private Texture background;
    private OrthographicCamera camera;

    public YouWinScreen(Frogger game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 720, 360);
        batch = new SpriteBatch();
        background = new Texture(Gdx.files.internal("backgrounds/winBg.png"));
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        batch.draw(background, 0, 0);
        if (Gdx.input.isTouched()) {
            game.setScreen(new MainMenuScreen(game));
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
        batch.dispose();
    }
}