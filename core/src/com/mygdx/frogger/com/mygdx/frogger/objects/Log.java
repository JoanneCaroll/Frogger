package com.mygdx.frogger.com.mygdx.frogger.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Caroll Madrona on 1/6/2015.
 */
public class Log extends GameObject{

    Rectangle hitBox;
    Sprite sprite;
    Texture texture;

    public Log(int x, int y) {
        hitBox = new Rectangle(x, y, 32, 32);
        texture = new Texture(Gdx.files.internal("sprite/log.png"));
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
    }

    @Override
    public void moveRight(float delta) {
        hitBox.x += (70*delta);
        sprite.setPosition(hitBox.x, hitBox.y);
    }

    @Override
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    @Override
    public Rectangle getHitBox() {
        return null;
    }
}
