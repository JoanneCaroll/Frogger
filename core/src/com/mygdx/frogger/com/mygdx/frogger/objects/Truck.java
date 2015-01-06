package com.mygdx.frogger.com.mygdx.frogger.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Caroll Madrona on 1/5/2015.
 */
public class Truck extends GameObject {
    Rectangle hitBox;
    Sprite sprite;
    Texture texture;

    public Truck(int x, int y) {
        hitBox = new Rectangle(x, y, 96, 32);
        texture = new Texture(Gdx.files.internal("sprite/truck.png"));
        sprite = new Sprite(texture);
        setPosition(x, y);
    }
    @Override
    public int hits(Rectangle r) {
        return -1;
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
        hitBox.x -= (70*delta);
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
}
