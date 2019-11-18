package com.instra.bojan.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.logging.Level;
import java.util.logging.Logger;


public class BojanCircle extends Actor {

    private Texture texture;
    Logger logger = Logger.getLogger("BojanCircle");



    private BojanPosition bojanPosition;
    private Color color;
    private Color activeColor;
    private Sound soundEffect;
    private long soundEffectId;

    float width;
    float height;

    private float pitch;


    private Circle circle;
    private Circle nextCircle = new Circle(0,0,0);;

    private boolean playing = false;

    public BojanCircle(Texture texture, BojanPosition bojanPosition, Color color, Color activeColor, Sound sound, float pitch) {
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

        logger.log(Level.SEVERE,"Created");



    }

    private void stopPlaying() {
        if (playing) {
            soundEffect.stop(soundEffectId);
//            soundEffect.stop();
            playing = false;
        }
    }

    int inputX;
    int inputY;


    @Override
    public void draw(Batch batch, float parentAlpha) {




//        if(Gdx.input.isTouched()){
//        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
        if(Gdx.input.isTouched()){

            inputX = Gdx.input.getX();
            inputY = Gdx.graphics.getHeight() - Gdx.input.getY();
            if(circle.contains(inputX, inputY) && !nextCircle.contains(inputX, inputY) && Gdx.graphics.getHeight()>0){

                if(!playing) {
//                    soundEffect.setPitch(soundEffectId, pitch);
                    soundEffectId = soundEffect.loop(1, pitch, 0);



//                    soundEffectId = soundEffect.loop(1, pitch, 0);
                    playing = true;
//                    logger.log(Level.SEVERE,"Playing with pitch: "+pitch);

                }
            }else{
                stopPlaying();
            }
        }else {
            stopPlaying();
        }

        if(!batch.isDrawing()){
            batch.begin();
        }



        //public void draw (Texture texture, float x, float y, float width, float height);
        if(playing){
            batch.setColor(activeColor);
        }else {
            batch.setColor(color);
        }




        batch.draw(texture,bojanPosition.getLeftX(),bojanPosition.getLeftY(), width, height);


    /*if(nextCircle.radius==0){
        batch.end();
    }*/



//        batch.end();
    }

    public void dispose(){
//        soundEffect.dispose();
//        texture.dispose();


        /*setBounds(0,0,0,0);
        remove();*/
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
}
