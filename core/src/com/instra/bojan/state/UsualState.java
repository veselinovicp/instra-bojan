package com.instra.bojan.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.utils.Pool;
import com.instra.bojan.elements.BojanCircle;
import com.instra.bojan.elements.BojanPosition;
import com.instra.bojan.state.actions.FadeOutAction;
import com.instra.bojan.state.actions.FadeOutCallback;

import java.util.logging.Logger;

public class UsualState extends BojanState implements FadeOutCallback {


    Logger logger = Logger.getLogger("UsualState");

    private long soundEffectId;



    private boolean playing = false;



    private Sound soundEffect;

    private BojanPosition bojanPosition;

    private StateUtils stateUtils;

    public UsualState(BojanCircle bojanCircle) {

        super(bojanCircle);


        this.bojanCircle = bojanCircle;
        soundEffect = bojanCircle.getSoundEffect();
        bojanPosition = bojanCircle.getBojanPosition();
        stateUtils = new StateUtils(bojanCircle);
//        logger.log(Level.SEVERE,"Created");



    }


    @Override
    public void start() {
        bojanCircle.setState(this);

    }

    @Override
    public void act(float delta) {

    }



    private void stopPlaying() {
        if (playing) {


//            fadeOutAction = fadeOutActionPool.obtain();

            bojanCircle.addAction(fadeOutActionPool.obtain());


//            bojanCircle.addAction(new com.instra.bojan.state.actions.FadeOutAction(stateUtils, this, soundEffectId));



        }
    }



    Pool<FadeOutAction> fadeOutActionPool = new Pool<FadeOutAction>(){
        protected FadeOutAction newObject(){
            return new FadeOutAction(stateUtils, UsualState.this, soundEffectId);
        }
    };

    boolean fadeOutEffect = false;

    @Override
    public void beginFadeOut() {
        playing = false;
        fadeOutEffect = true;
    }

    @Override
    public void end() {
        fadeOutEffect = false;
    }



    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(Gdx.input.isTouched() && !fadeOutEffect){

            int inputX = Gdx.input.getX();
            int inputY = Gdx.graphics.getHeight() - Gdx.input.getY();
            if(bojanCircle.getCircle().contains(inputX, inputY) && !bojanCircle.getNextCircle().contains(inputX, inputY) && Gdx.graphics.getHeight()>0){

                if(!playing) {
                    playing = true;

                    soundEffectId = soundEffect.loop(1, bojanCircle.getPitch(), 0);

                }
            }else{
                stopPlaying();
            }
        }else {
            stopPlaying();
        }


       stateUtils.draw(batch, playing);



    }

}
