package com.mygdx.frogger.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.frogger.Frogger;
import com.mygdx.frogger.SimpleDirectionGestureDetector;
import com.mygdx.frogger.com.mygdx.frogger.objects.Car;
import com.mygdx.frogger.com.mygdx.frogger.objects.Frog;
import com.mygdx.frogger.com.mygdx.frogger.objects.GameObject;
import com.mygdx.frogger.com.mygdx.frogger.objects.Grass;
import com.mygdx.frogger.com.mygdx.frogger.objects.Road;
import com.mygdx.frogger.com.mygdx.frogger.objects.Water;

import java.util.ArrayList;

/**
 * Created by Caroll Madrona on 1/5/2015.
 */
public class GameScreen implements Screen {
    private Frog frog;
    private ArrayList<GameObject> grass = new ArrayList<GameObject>();
    private ArrayList<GameObject> road = new ArrayList<GameObject>();
    private ArrayList<GameObject> water = new ArrayList<GameObject>();
    private ArrayList<GameObject> cars = new ArrayList<GameObject>();
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Frogger game;
    private BitmapFont font;
    private int max, px;
    public GameScreen(Frogger game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 720, 360);
        batch = new SpriteBatch();
        frog = new Frog();
        frog.setPosition(0, 32);
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        for (GameObject g : grass) {
            g.draw(batch);
        }
        for (GameObject r : road) {
            r.draw(batch);
        }
        for (GameObject w : water) {
            w.draw(batch);
        }
        for(GameObject c: cars) {
            c.draw(batch);
            c.moveRight(Gdx.graphics.getDeltaTime());
        }
        frog.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {

        //adding of grass
        for(int g=0; g<3; g++) {
            if(g==0){
                px=0;
                max = 32;
            }
            else if(g==1){
                px=0;
                max = 160;
            }
            else if(g==2){
                px=0;
                max = 288;
            }
            for(int i=0; i<23; i++) {
                grass.add(new Grass(px, max));
                px += 32;
            }
        }

        //adding of roads
        for(int g=0; g<3; g++) {
            if(g==0){
                px=0;
                max = 64;
            }
            else if(g==1){
                px=0;
                max = 96;
            }
            else if(g==2){
                px=0;
                max = 128;
            }
            for(int i=0; i<23; i++) {
                road.add(new Road(px, max));
                px += 32;
            }
        }

        //adding of water
        for(int g=0; g<3; g++) {
            if(g==0){
                px=0;
                max = 192;
            }
            else if(g==1){
                px=0;
                max = 224;
            }
            else if(g==2){
                px=0;
                max = 256;
            }
            for(int i=0; i<23; i++) {
                water.add(new Water(px, max));
                px += 32;
            }
        }

        //adding of cars
        for(int g=0; g<2; g++) {
            if(g==0){
                px=0;
                max = 64;
            }
            else {
                px=-45;
                max = 128;
            }
            for(int i=0; i<99; i++) {
                cars.add(new Car(px, max));
                if(g==0) {
                    px -=150;
                }
                else
                    px -=130;
            }
        }


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
