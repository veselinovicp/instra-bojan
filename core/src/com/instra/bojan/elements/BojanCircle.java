package com.instra.bojan.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BojanCircle extends Actor {

    Texture texture = new Texture(Gdx.files.internal("circle.png"));

    private BojanPosition bojanPosition;
    private Color color;
    private Color activeColor;
    private Sound soundEffect;

    float width;
    float height;

    private TouchListener touchListener;

    public BojanCircle(BojanPosition bojanPosition, Color color, Color activeColor, String sound) {
        this.bojanPosition = bojanPosition;
        this.color=color;
        this.activeColor=activeColor;

        width = bojanPosition.getRightX()-bojanPosition.getLeftX();
        height = bojanPosition.getRightY()-bojanPosition.getLeftY();


        setBounds(bojanPosition.getLeftX(),bojanPosition.getLeftY(),width,height);

        soundEffect = Gdx.audio.newSound(Gdx.files.internal(sound));
        touchListener = new TouchListener(soundEffect);
        addListener(touchListener);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        //public void draw (Texture texture, float x, float y, float width, float height);
        if(touchListener.isCirclePressed()){
            batch.setColor(activeColor);
        }else {
            batch.setColor(color);
        }

        if(!batch.isDrawing()){
            batch.begin();
        }
        batch.draw(texture,bojanPosition.getLeftX(),bojanPosition.getLeftY(), width, height);
//        batch.end();
    }

    public BojanPosition getBojanPosition() {
        return bojanPosition;
    }
}
