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
    private Sound soundEffect;

    float width;
    float height;

    public BojanCircle(BojanPosition bojanPosition, Color color, String sound) {
        this.bojanPosition = bojanPosition;
        this.color=color;

        width = bojanPosition.getRightX()-bojanPosition.getLeftX();
        height = bojanPosition.getRightY()-bojanPosition.getLeftY();


        setBounds(bojanPosition.getLeftX(),bojanPosition.getLeftY(),width,height);

        soundEffect = Gdx.audio.newSound(Gdx.files.internal(sound));
        addListener(new TouchListener(soundEffect));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        //public void draw (Texture texture, float x, float y, float width, float height);
        batch.setColor(color);
        batch.draw(texture,bojanPosition.getLeftX(),bojanPosition.getLeftY(), width, height);
    }


}
