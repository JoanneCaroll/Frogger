package com.mygdx.frogger.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
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
    private Rectangle left, right, jump, back;
    private Sprite spriteLeft, spriteRight, spriteJump, spriteBack;
    private Texture buttonTexture;

    public GameScreen(Frogger game){

        this.game = game;
        camera = new OrthographicCamera();
        buttonTexture = new Texture(Gdx.files.internal("buttons/arrows.png"));
        spriteLeft = new Sprite(buttonTexture, 0, 0, 64, 64);
        spriteRight = new Sprite(buttonTexture, 64, 0, 64, 64);
        spriteBack = new Sprite(buttonTexture, 64, 64, 64, 64);
        spriteJump = new Sprite(buttonTexture, 0, 64, 64, 64);


        spriteLeft.setPosition(384, 0);
        spriteRight.setPosition(468, 0);
        spriteJump.setPosition(552, 0);
        spriteBack.setPosition(636, 0);

        left = new Rectangle(384, 0, 64, 64);
        right = new Rectangle(468, 0, 64, 64);
        jump = new Rectangle(552, 0, 64, 64);
        back = new Rectangle(636, 0, 64, 64);

        camera.setToOrtho(false, 720, 360);
        batch = new SpriteBatch();
        frog = new Frog();
        frog.setPosition(0, 64);
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
        spriteBack.draw(batch);
        spriteRight.draw(batch);
        spriteLeft.draw(batch);
        spriteJump.draw(batch);
        batch.end();

        //updates
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
            if(frog.hits(l.getHitBox()) != -1) {
                if(l.hitAction(1) == 2) {
                    Gdx.app.log("yoy", (frog.getRight()+"")+ ","+(l.getRight()+""));
                    if(l.getRight() > (frog.getRight()-10) && frog.getLeft() > l.getLeft())
                        frog.moveLeft(Gdx.graphics.getDeltaTime());
                    else
                        tries--;
                        frog.goToStartPosition();
                }
            }
        }
        //controls
        for(int i =0; i<5; i++) {
            if(Gdx.input.isTouched(i)){
                Vector3 touchPos = new Vector3(Gdx.input.getX(i), Gdx.input.getY(i), 0);
                camera.unproject(touchPos);
                Gdx.app.log("yoy", touchPos.x+"");
                Rectangle touch = new Rectangle(touchPos.x, touchPos.y, 64, 64);
                if(touch.overlaps(left)){
                    if(frog.getLeft() > 0)
                        frog.moveLeft();
                }
                else if(touch.overlaps(right)){
                    if(frog.getRight() < 704)
                        frog.moveRight();
                }
                else if(touch.overlaps(jump)){
                    if(frog.getTop() < 352) {
                        frog.moveUp();
                        if(flag == 0) {
                            score++;
                        }
                        flag = 0;
                    }
                }
                else if(touch.overlaps(back)){
                    if(frog.getBottom() > 64) {
                        flag = 1;
                        frog.moveDown();
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
                px=0;
                max = 96;
            }
            else {
                px=-45;
                max = 160;
            }
            for(int i=0; i<99; i++) {
                cars.add(new Car(px, max));
                if(g==0) {
                    px -=250;
                }
                else
                    px -=210;
            }
        }

        //adding of truck
        px = 704;
        for(int i = 0; i < 99; i++) {
            truck.add(new Truck(px,128));
            px += 300;
        }

        //adding of lily
        for(int g=0; g<2; g++) {
            if(g==0){
                px=720;
                max = 224;
            }
            else {
                px=700;
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
        px = -85;
        for(int i = 0; i < 99; i++) {
            log.add(new Log(px, 256 ));
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
