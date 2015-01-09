package com.mygdx.frogger.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
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

    Rectangle waterRec = new Rectangle(0,224, 720, 96);

    public GameScreen(Frogger game){

        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 720, 360);
        batch = new SpriteBatch();
        frog = new Frog();
        frog.setPosition(0, 64);
        Gdx.input.setCatchBackKey(true);
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
        Rectangle top = new Rectangle(0, 320, 720, 32);
        if(frog.hits(top) != -1){
            game.setScreen(new YouWinScreen(game));
        }
        batch.end();

        //updates
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)){
            game.setScreen(new MainMenuScreen(game));
        }
        for(GameObject c: cars) {
            if(frog.hits(c.getHitBox()) != -1) {
                frog.action(1);
                if(c.hitAction(1) == 2) {
                   frog.goToStartPosition();
                    tries--;
                    if(tries<1) {
                        game.setScreen(new GameOverScreen(game));
                    }
                }
            }
        }
        for(GameObject t: truck) {
            if(frog.hits(t.getHitBox()) != -1) {
                frog.action(1);
                if(t.hitAction(1) == 2) {
                    frog.goToStartPosition();
                    tries--;
                    if(tries<1) {
                        game.setScreen(new GameOverScreen(game));
                    }
                }
            }
        }

        for(GameObject l: lily) {
            if(frog.hits(l.getHitBox()) != -1 && frog.hits(waterRec) != -1) {
                if(l.hitAction(1) == 2) {
                    if(l.getRight() > (frog.getRight()) && frog.getLeft() > l.getLeft()-16) {
                        frog.moveLeft(Gdx.graphics.getDeltaTime());
                    }
                    else {
                        tries--;
                        frog.goToStartPosition();
                        if (tries < 1) {
                            game.setScreen(new GameOverScreen(game));
                        }
                    }
                }
            }

        }
        for(GameObject lo: log) {
            if(frog.hits(lo.getHitBox()) != -1) {
                if(lo.hitAction(1) == 2) {
                    if(lo.getRight() > (frog.getRight()) && frog.getLeft() > lo.getLeft()-16)
                        frog.moveRight(Gdx.graphics.getDeltaTime());
                    else {
                        tries--;
                        frog.goToStartPosition();
                        if (tries < 1) {
                            game.setScreen(new GameOverScreen(game));
                        }
                    }
                }
            }
        }


    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        font = new BitmapFont();
        font.setScale(1.5f);
        font.setColor(Color.RED);
        grass.add(new Grass(0, 64));
        grass.add(new Grass(0, 192));
        grass.add(new Grass(0, 320));
        road = new Road(0, 96);
        water = new Water(0,224);

        //adding of cars
        for(int g=0; g<2; g++) {
            if(g==0){
                px=500;
                max = 96;
            }
            else {
                px=600;
                max = 160;
            }
            for(int i=0; i<99; i++) {
                cars.add(new Car(px, max));
                if(g==0) {
                    px -=170;
                }
                else
                    px -=150;
            }
        }

        //adding of truck
        px = 0;
        for(int i = 0; i < 99; i++) {
            truck.add(new Truck(px,128));
            px += 300;
        }

        //adding of lily
        for(int g=0; g<2; g++) {
            px=0;
            if(g==0){
                max = 224;
            }
            else {
                max = 288;
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
        px = 720;
        for(int i = 0; i < 99; i++) {
            log.add(new Log(px, 256 ));
            px -= 200;
        }

        //controls



        Gdx.input.setInputProcessor(new SimpleDirectionGestureDetector(new SimpleDirectionGestureDetector.DirectionListener() {
            @Override
            public void onUp() {
                if(frog.getTop() < 352) {
                        frog.moveUp();
                        if (flag == 0) {
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
                if(frog.getBottom() > 64) {
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
