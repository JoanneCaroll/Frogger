package com.mygdx.frogger.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.frogger.Frogger;
import com.mygdx.frogger.SimpleDirectionGestureDetector;
import com.mygdx.frogger.com.mygdx.frogger.objects.Car;
import com.mygdx.frogger.com.mygdx.frogger.objects.Frog;
import com.mygdx.frogger.com.mygdx.frogger.objects.GameObject;
import com.mygdx.frogger.com.mygdx.frogger.objects.Grass;
import com.mygdx.frogger.com.mygdx.frogger.objects.Lily;
import com.mygdx.frogger.com.mygdx.frogger.objects.Log;
import com.mygdx.frogger.com.mygdx.frogger.objects.Road;
import com.mygdx.frogger.com.mygdx.frogger.objects.Truck;
import com.mygdx.frogger.com.mygdx.frogger.objects.Water;

import java.util.ArrayList;

/**
 * Created by Caroll Madrona on 1/5/2015.
 */
public class GameScreen implements Screen {
    private Frog frog;
    private ArrayList<GameObject> grass = new ArrayList<GameObject>();
    private Road road;
    private Water water;
    private ArrayList<GameObject> truck = new ArrayList<GameObject>();
    private ArrayList<GameObject> cars = new ArrayList<GameObject>();
    private ArrayList<GameObject> lily = new ArrayList<GameObject>();
    private ArrayList<GameObject> log = new ArrayList<GameObject>();
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Frogger game;
    private BitmapFont font;
    private int max, px, score, tries=3, flag;

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
        road.draw(batch);
        water.draw(batch);
        for(GameObject c: cars) {
            c.draw(batch);
            c.moveRight(Gdx.graphics.getDeltaTime());
        }
        for(GameObject t: truck) {
            t.draw(batch);
            t.moveLeft(Gdx.graphics.getDeltaTime());
        }
        for(GameObject l: lily) {
            l.draw(batch);
            l.moveLeft(Gdx.graphics.getDeltaTime());
        }
        for(GameObject lg: log) {
            lg.draw(batch);
            lg.moveRight(Gdx.graphics.getDeltaTime());
        }
        frog.draw(batch);
        font.draw(batch, "Score: " + score, 0, 20);
        font.draw(batch, "Lives Left " + tries, 200, 20);
        batch.end();

        //updates
        for(GameObject c: cars) {
            if(frog.hits(c.getHitBox()) != -1) {
                frog.action(1);
                if(c.hitAction(1) == 2) {
                   frog.goToStartPosition();
                    tries--;
                }

            }
        }
        for(GameObject t: truck) {
            if(frog.hits(t.getHitBox()) != -1) {
                frog.action(1);
                if(t.hitAction(1) == 2) {
                    frog.goToStartPosition();
                    tries--;
                }

            }
        }
        if(frog.hits(water.getHitBox()) != -1) {
            frog.action(1);
            if(water.hitAction(1) == 2) {
                frog.setPosition(0,32);
            }
        }
        //controls

    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        font = new BitmapFont();
        font.setScale(1.5f);
        font.setColor(Color.RED);
        grass.add(new Grass(0, 32));
        grass.add(new Grass(0, 160));
        grass.add(new Grass(0, 288));
        road = new Road(0, 64);
        water = new Water(0,192);

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
                    px -=170;
                }
                else
                    px -=200;
            }
        }

        //adding of truck
        px = 704;
        for(int i = 0; i < 99; i++) {
            truck.add(new Truck(px,96));
            px += 250;
        }

        //adding of lily
        for(int g=0; g<2; g++) {
            if(g==0){
                px=720;
                max = 192;
            }
            else {
                px=700;
                max = 256;
            }
            for(int i=0; i<99; i++) {
                lily.add(new Lily(px, max));
                if(g==0) {
                    px +=200;
                }
                else
                    px +=170;
            }
        }

        //adding of log
        px = -85;
        for(int i = 0; i < 99; i++) {
            log.add(new Log(px, 224 ));
            px -= 200;
        }

        //controls
        Gdx.input.setInputProcessor(new SimpleDirectionGestureDetector(new SimpleDirectionGestureDetector.DirectionListener() {
            @Override
            public void onUp() {
                if(frog.getTop() < 320) {
                    frog.moveUp();
                    if(flag == 0) {
                        score++;
                    }
                    flag = 0;
                }
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
                if(frog.getBottom() > 32) {
                    flag = 1;
                    frog.moveDown();
                }
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
