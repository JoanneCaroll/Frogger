package com.mygdx.frogger.com.mygdx.frogger.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Caroll Madrona on 1/5/2015.
 */
public class Grass extends GameObject {

    Rectangle hitBox;
    Sprite sprite;
    Texture texture;

    public Grass(int x, int y) {
        hitBox = new Rectangle(x, y, 32, 32);
        texture = new Texture(Gdx.files.internal("sprite/grass.png"));
        sprite = new Sprite(texture);
        setPosition(x, y);
    }
    public  int hits(Rectangle r) {
        return 0;
    }

    public  void action(int type, float x, float y) {
    }

    public  void setPosition(float x, float y) {
        hitBox.x = x;
        hitBox.y = y;
        sprite.setPosition(x,y);
    }

    public  void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public  Rectangle getHitBox() {
        return hitBox;
    }

    @Override
    public void moveRight(float delta) {
    }

    @Override
    public void moveLeft(float delta) {

    }

    @Override
    public int hitAction(int side) {
        return 0;
    }

    @Override
    public float getBottom() {
        return 0;
    }

    @Override
    public float getLeft() {
        return 0;
    }

    @Override
    public float getRight() {
        return 0;
    }

    @Override
    public float getTop() {
        return 0;
    }
}
