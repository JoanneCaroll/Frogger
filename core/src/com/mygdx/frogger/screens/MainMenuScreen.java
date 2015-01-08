package com.mygdx.frogger.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.frogger.Frogger;

/**
 * Created by Caroll Madrona on 1/5/2015.
 */
public class MainMenuScreen implements Screen {

    private Frogger game;
//    private Stage stage;
    private OrthographicCamera camera;

    private SpriteBatch batch;
    private Stage stage; //** stage holds the Button **//
    private TextureAtlas buttonsAtlas; //** image of buttons **//
    private Skin buttonSkin; //** images are used as skins of the button **//
    private TextButton button; //** the button - the only actor in program **//

    public class MyActor extends Actor {
        Texture texture = new Texture(Gdx.files.internal("buttons/playGameButton.png"));
        float actorX = 0, actorY = 0;
        public boolean started = false;

        public MyActor(){
            setBounds(actorX, actorY, texture.getWidth(),texture.getHeight());
            addListener(new InputListener(){
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    ((MyActor)event.getTarget()).started = true;
                    return true;
                }
            });
        }

        @Override
        public void draw(Batch batch, float alpha){
            batch.draw(texture, actorX,actorY );
        }

        @Override
        public void act(float delta){
            if(started){
                actorX+=15;
                if(actorX > 600) {
                    goToGameScreen();
                }
            }
        }
    }

    public MainMenuScreen(Frogger game){
        this.game = game;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 720, 360);
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);



        MyActor myActor = new MyActor();
        myActor.setTouchable(Touchable.enabled);
        stage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                goToGameScreen();
            }
        });
        stage.addActor(myActor);
    }


//    @Override
//    public void dispose() {
//        batch.dispose();
//        buttonSkin.dispose();
//        buttonsAtlas.dispose();
//        stage.dispose();
//    }
//
//    @Override
//    public void show() {
//
//    }
//
//    @Override
//    public void hide() {
//
//    }
//
//    @Override
//    public void render(float delta) {
//        batch.begin();
//        Gdx.gl.glClearColor(1, 1, 1, 1);
//
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
////        stage.act();
//
//        batch.setProjectionMatrix(camera.combined);
//
//        stage.draw();
//        batch.end();
//    }
//
//    @Override
//    public void resize(int width, int height) {
//    }
//
//    @Override
//    public void pause() {
//    }
//
//    @Override
//    public void resume() {
//    }
//}

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
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
        stage.dispose();

    }
}
