package com.mygdx.frogger.com.mygdx.frogger.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Caroll Madrona on 1/6/2015.
 */
public class Lily extends GameObject{

    Rectangle hitBox;
    Sprite sprite;
    Texture texture;

    public Lily(int x, int y) {
        hitBox = new Rectangle(x, y, 128, 32);
        texture = new Texture(Gdx.files.internal("sprite/lily.png"));
        sprite = new Sprite(texture);
        setPosition(x, y);
    }
    @Override
    public int hits(Rectangle r) {
        return 0;
    }

    @Override
    public void action(int type, float x, float y) {
    }

    @Override
    public void setPosition(float x, float y) {
        hitBox.x = x;
        hitBox.y = y;
        sprite.setPosition(x,y);
    }

    @Override
    public void moveLeft(float delta) {
        hitBox.x -= (40*delta);
        sprite.setPosition(hitBox.x, hitBox.y);
    }

    @Override
    public void moveRight(float delta) {
    }

    @Override
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    @Override
    public Rectangle getHitBox() {
        return hitBox;
    }

    @Override
    public int hitAction(int side) {
        if(side==1) return 2;
        return 0;
    }

    @Override
    public float getBottom() {
        return sprite.getY();
    }

    @Override
    public float getLeft() {
        return sprite.getX();
    }

    @Override
    public float getRight() {
        return sprite.getX()+sprite.getWidth();
    }

    @Override
    public float getTop() {
        return sprite.getY()+sprite.getHeight();
    }
}
