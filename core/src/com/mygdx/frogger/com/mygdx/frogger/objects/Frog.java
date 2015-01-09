package com.mygdx.frogger.com.mygdx.frogger.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Caroll Madrona on 1/5/2015.
 */
public class Frog extends GameObject {

    private Rectangle bottom;
    private Sprite sprite;
    private Texture texture;

    //Entry point
    public Frog() {
        bottom = new Rectangle(0, 64, 32, 32);
        texture = new Texture(Gdx.files.internal("sprite/frog.png"));
        sprite = new Sprite(texture, 0, 0, 32, 32);
    }

    public int hits(Rectangle r) {
        if(bottom.overlaps(r)) {
            return 1;
        }
        return -1;
    }

    public void action(int type, float x, float y) {
        if(type == 1) {
            setPosition(0,64);
        }
    }

    public void action(int type) {
        if(type == 1) {
            setPosition(0,32);
        }
    }

    public void setPosition(float x, float y) {
        sprite.setPosition(x, y);
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    @Override
    public void moveLeft(float delta) {
        bottom.x -= (40*delta);
        sprite.setPosition(bottom.x, bottom.y);
    }

    @Override
    public void moveRight(float delta) {
        bottom.x += (70*delta);
        sprite.setPosition(bottom.x, bottom.y);
    }

    //Controls for frog
    public void moveRight(){
        bottom.x += 8;
        sprite.setPosition(bottom.x, bottom.y);
    }

    public void moveLeft(){
        bottom.x -= 8;
        sprite.setPosition(bottom.x, bottom.y);
    }

    public void moveUp(){
        bottom.y += 32;
        sprite.setPosition(bottom.x, bottom.y);
    }

    public void moveDown(){
        bottom.y -= 32;
        sprite.setPosition(bottom.x, bottom.y);
    }
    
    public float getBottom() {
        return sprite.getY();
    }

    public float getLeft() {
        return sprite.getX();
    }

    public float getRight() {
        return sprite.getX()+sprite.getWidth();
    }

    public float getTop() {
        return sprite.getY()+sprite.getHeight();
    }

    public void goToStartPosition(){
        bottom.x = 0;
        bottom.y = 64;
        sprite.setPosition(bottom.x, bottom.y);
    }

    @Override
    public Rectangle getHitBox() {
        return null;
    }

    @Override
    public int hitAction(int side) {
        return 0;
    }
}
