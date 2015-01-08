package com.mygdx.frogger.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.frogger.Frogger;

/**
 * Created by Caroll Madrona on 1/5/2015.
 */
public class MainMenuScreen implements Screen {

    private Frogger game;
    private OrthographicCamera camera;
    private Texture background, playBtn;
    private SpriteBatch batch;
    private Sprite play;
    private Rectangle btn;
    public MainMenuScreen(Frogger game){
        this.game = game;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 720, 360);
        background = new Texture(Gdx.files.internal("backgrounds/menubg.png"));
        playBtn = new Texture(Gdx.files.internal("buttons/playBtn.png"));
        play = new Sprite(playBtn);
        play.setPosition(430, 100);
        btn = new Rectangle(430,100, 143, 45);
    }


    @Override
    public void render(float delta) {
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        batch.draw(background, 0, 0);
        play.draw(batch);

        for(int i =0; i<1; i++) {
            if(Gdx.input.isTouched(i)){
                Vector3 touchPos = new Vector3(Gdx.input.getX(i), Gdx.input.getY(i), 0);
                camera.unproject(touchPos);
                Rectangle touch = new Rectangle(touchPos.x, touchPos.y, 143, 45);
                if(touch.overlaps(btn)){
                    goToGameScreen();
                }
            }
        }
        batch.end();
    }

    private void goToGameScreen() {
        game.setScreen(new GameScreen(game));
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
