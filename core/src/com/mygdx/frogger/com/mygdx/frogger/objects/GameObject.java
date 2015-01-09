package com.mygdx.frogger.com.mygdx.frogger.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Caroll Madrona on 1/5/2015.
 */
public abstract class GameObject {
    public abstract int hits(Rectangle r);
    public abstract void action(int type, float x, float y);
    public abstract void setPosition(float x, float y);
    public abstract void moveLeft(float delta);
    public abstract void moveRight(float delta);
    public abstract void draw(SpriteBatch batch);
    public abstract Rectangle getHitBox();
    public abstract int hitAction(int side);
    public abstract float getBottom();
    public abstract float getLeft();
    public abstract float getRight();
    public abstract float getTop();

}