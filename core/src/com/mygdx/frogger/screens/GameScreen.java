package com.mygdx.frogger.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.frogger.Frogger;
import com.mygdx.frogger.SimpleDirectionGestureDetector;
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
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        frog.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new SimpleDirectionGestureDetector(new SimpleDirectionGestureDetector.DirectionListener() {
            @Override
            public void onUp() {
                if(frog.getTop() < 320)
                    frog.moveUp();
            }
            @Override
            public void onRight() {
                if(frog.getRight() < 704)
                    frog.moveRight();
            }
            @Override
            public void onLeft() {
                if(frog.getLeft() > 0)
                    frog.moveLeft();
            }
            @Override
            public void onDown() {
                if(frog.getBottom() > 32)
                    frog.moveDown();
            }
        }));
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
