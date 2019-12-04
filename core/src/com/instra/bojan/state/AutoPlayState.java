package com.instra.bojan.state;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Pool;
import com.instra.bojan.elements.BojanCircle;
import com.instra.bojan.theory.Duration;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AutoPlayState extends BojanState {

    private Duration duration;



    private StateUtils stateUtils;

    private Logger logger = Logger.getLogger("AutoPlayState");





    boolean startPlaying = false;
    boolean stopPlaying = false;





    class AutoPlayAction extends TemporalAction{





//        private Sound sound;

        public AutoPlayAction(float duration) {
            super(duration);
            setDuration(duration);
//            this.sound = bojanCircle.getSoundEffect();

        }

        @Override
        protected void begin() {
            super.begin();



//            sound.loop(1, bojanCircle.getPitch(), 0);

            logger.log(Level.SEVERE,"Start auto play: "+bojanCircle.getNote());
            startPlaying = true;

        }

        @Override
        protected void update(float percent) {

        }

        @Override
        protected void end() {
            super.end();
//            sound.stop(soundId);
//            sound.stop();
//            sound.stop();


            logger.log(Level.SEVERE,"End auto play: "+bojanCircle.getNote());


//            sound.setVolume(soundId,0);
//            sound.dispose();




            stopPlaying = true;

          /*  BojanState state = BojanStateFactory.getState(BojanStateType.USUAL, bojanCircle);
            bojanCircle.setState(state);*/



//            startNextStates();



        }
    }

    public AutoPlayState(BojanCircle bojanCircle, Duration duration) {

        super(bojanCircle);


        this.bojanCircle = bojanCircle;

        this.duration = duration;

        stateUtils = new StateUtils(bojanCircle);






    }

    Pool<AutoPlayAction> autoPlayActionPool = new Pool<AutoPlayAction>(){
        protected AutoPlayAction newObject(){
            return new AutoPlayAction(stateUtils.getDurationTime(duration));
        }
    };




    @Override
    public void start() {


        bojanCircle.setState(this);


        bojanCircle.addAction(autoPlayActionPool.obtain());





    }

    @Override
    public void act(float delta) {



    }



    @Override
    public void draw(Batch batch, float parentAlpha) {
        stateUtils.draw(batch, true);

        if(startPlaying){
            startPlaying = false;
             bojanCircle.getSoundEffect().loop(1, bojanCircle.getPitch(), 0);
            logger.log(Level.SEVERE,"Start action: "+bojanCircle.getNote());
        }
        if(stopPlaying){
            stopPlaying = false;
            bojanCircle.getSoundEffect().stop();
            logger.log(Level.SEVERE,"Stop action: "+bojanCircle.getNote());
            BojanState state = BojanStateFactory.getState(BojanStateType.USUAL, bojanCircle);
            bojanCircle.setState(state);
            startNextStates();
        }
    }

}
