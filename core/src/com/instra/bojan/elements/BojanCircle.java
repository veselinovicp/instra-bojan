package com.instra.bojan.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BojanCircle extends Actor {

    Texture texture = new Texture(Gdx.files.internal("circle.png"));

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

    public BojanCircle(BojanPosition bojanPosition, Color color, Color activeColor, String sound, float pitch) {
        this.bojanPosition = bojanPosition;
        this.color=color;
        this.activeColor=activeColor;

        width = bojanPosition.getRightX()-bojanPosition.getLeftX();
        height = bojanPosition.getRightY()-bojanPosition.getLeftY();


        setBounds(bojanPosition.getLeftX(),bojanPosition.getLeftY(),width,height);

        soundEffect = Gdx.audio.newSound(Gdx.files.internal(sound));

        circle = new Circle(bojanPosition.getLeftX()+width/2, bojanPosition.getLeftY()+height/2, width/2);


        this.pitch=pitch;



    }

    private void stopPlaying() {
        if (playing) {
            soundEffect.stop(soundEffectId);
            playing = false;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        if(Gdx.input.isTouched()){
            int inputX = Gdx.input.getX();
            int inputY = Gdx.graphics.getHeight() - Gdx.input.getY();
            if(circle.contains(inputX, inputY) && !nextCircle.contains(inputX, inputY)){

                if(!playing) {
                    soundEffectId = soundEffect.loop(1, pitch, 0);
                    playing = true;
//                    System.out.println("play pitch: "+pitch);
                }
            }else{
                stopPlaying();
            }
        }else {
            stopPlaying();
        }


        //public void draw (Texture texture, float x, float y, float width, float height);
        if(playing){
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

    public void dispose(){
        soundEffect.dispose();
        texture.dispose();
        setBounds(0,0,0,0);
        remove();
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
