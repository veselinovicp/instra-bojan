package com.instra.bojan.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.utils.Pool;

import com.instra.bojan.state.BojanState;
import com.instra.bojan.state.BojanStateFactory;
import com.instra.bojan.state.BojanStateType;
import com.instra.bojan.theory.Note;

import java.util.logging.Level;
import java.util.logging.Logger;


public class BojanCircle extends Actor {

    private Texture texture;
    Logger logger = Logger.getLogger("BojanCircle");





    private BojanPosition bojanPosition;
    private Color color;
    private Color activeColor;
    private Sound soundEffect;


    float width;
    float height;

    private float pitch;

    private Note note;


    private Circle circle;
    private Circle nextCircle = new Circle(0,0,0);;



    private BojanState state;

    private boolean justStoppedPlaying = false;

    public BojanCircle(Texture texture, BojanPosition bojanPosition, Color color, Color activeColor, Sound sound, float pitch, Note note) {
        this.note = note;
        this.bojanPosition = bojanPosition;
        this.color=color;
        this.activeColor=activeColor;

        width = bojanPosition.getRightX()-bojanPosition.getLeftX();
        height = bojanPosition.getRightY()-bojanPosition.getLeftY();


        setBounds(bojanPosition.getLeftX(),bojanPosition.getLeftY(),width,height);

        soundEffect = sound;

        circle = new Circle(bojanPosition.getLeftX()+width/2, bojanPosition.getLeftY()+height/2, width/2);


        this.pitch=pitch;
        this.texture = texture;

        state = BojanStateFactory.getState(BojanStateType.USUAL, this);
        logger.log(Level.SEVERE,"Created");





    }


    @Override
    public void act(float delta) {
        super.act(delta);
        /*if(action != null){
            action.act(delta);
        }*/
        state.act(delta);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {




       state.draw(batch, parentAlpha);

    }





    public BojanPosition getBojanPosition() {
        return bojanPosition;
    }

    public Circle getCircle() {
        return circle;
    }

    public void setNextCircle(Circle nextCircle) {
        this.nextCircle = nextCircle;
    }



    public Texture getTexture() {
        return texture;
    }

    @Override
    public Color getColor() {
        return color;
    }

    public Color getActiveColor() {
        return activeColor;
    }

    public Sound getSoundEffect() {
        return soundEffect;
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getHeight() {
        return height;
    }

    public float getPitch() {
        return pitch;
    }

    public Circle getNextCircle() {
        return nextCircle;
    }

    public void setState(BojanState state) {
        this.state = state;
    }

    public boolean isJustStoppedPlaying() {
        return justStoppedPlaying;
    }

    public void setJustStoppedPlaying(boolean justStoppedPlaying) {
        this.justStoppedPlaying = justStoppedPlaying;
    }

    public Note getNote() {
        return note;
    }
}
