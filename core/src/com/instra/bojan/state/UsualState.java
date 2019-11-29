package com.instra.bojan.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.utils.Pool;
import com.instra.bojan.elements.BojanCircle;
import com.instra.bojan.elements.BojanPosition;

import java.util.logging.Level;
import java.util.logging.Logger;

public class UsualState extends BojanState {


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
        logger.log(Level.SEVERE,"Created");



    }


    @Override
    public void start() {

    }

    @Override
    public void act(float delta) {

    }


    private VolumeDownAction volumeDownAction;

    private void stopPlaying() {
        if (playing) {
            /*soundEffect.stop(soundEffectId);
            playing = false;*/

            volumeDownAction = volumeDownActionPool.obtain();

            bojanCircle.addAction(volumeDownAction);




        }
    }



    Pool<VolumeDownAction> volumeDownActionPool = new Pool<VolumeDownAction>(){
        protected VolumeDownAction newObject(){
            return new VolumeDownAction();
        }
    };

    boolean volumeDownEffect= false;

    class VolumeDownAction extends TemporalAction {

        public VolumeDownAction() {


            setReverse(true);
            setDuration(0.2f);
            playing = false;
        }

        @Override
        protected void begin() {
            super.begin();
            volumeDownEffect = true;


        }

        @Override
        protected void update(float percent) {

            soundEffect.setVolume(soundEffectId, percent);
        }

        @Override
        protected void end() {
            super.end();
            soundEffect.stop(soundEffectId);
            volumeDownEffect= false;

            bojanCircle.setJustStoppedPlaying(true);


//            playing = false;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(Gdx.input.isTouched() && !volumeDownEffect){

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
