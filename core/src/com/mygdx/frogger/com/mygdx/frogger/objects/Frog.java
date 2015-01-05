package com.mygdx.frogger.com.mygdx.frogger.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Caroll Madrona on 1/5/2015.
 */
public class Frog {

    private Sprite sprite;
    private Texture texture;

    //Entry point
    public Frog() {
        texture = new Texture(Gdx.files.internal("sprite/frog.png"));
        sprite = new Sprite(texture, 0, 0, 32, 32);
    }

    public int hits(Rectangle r) {
        return -1;
    }

    public void action(int type, float x, float y) {
    }

    public void setPosition(float x, float y) {
        sprite.setPosition(x, y);
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

}
