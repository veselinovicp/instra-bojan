package com.instra.bojan.state;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Pool;
import com.instra.bojan.elements.BojanCircle;
import com.instra.bojan.state.actions.FadeOutAction;
import com.instra.bojan.state.actions.FadeOutCallback;
import com.instra.bojan.theory.Duration;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AutoPlayState extends BojanState implements FadeOutCallback {

    private Duration duration;



    private StateUtils stateUtils;

    private Logger logger = Logger.getLogger("AutoPlayState");






    private long soundEffectId;





    class AutoPlayAction extends TemporalAction{



        public AutoPlayAction(float duration) {
            super(duration);
            setDuration(duration);


        }

        @Override
        protected void begin() {
            super.begin();



            logger.log(Level.SEVERE,"Start auto play: "+bojanCircle.getNote());


            soundEffectId = bojanCircle.getSoundEffect().loop(1, bojanCircle.getPitch(), 0);
            logger.log(Level.SEVERE,"Start action: "+bojanCircle.getNote());

        }

        @Override
        protected void update(float percent) {

        }

        @Override
        protected void end() {
            super.end();



            logger.log(Level.SEVERE,"End auto play: "+bojanCircle.getNote());
            bojanCircle.addAction(fadeOutActionPool.obtain());




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

    Pool<FadeOutAction> fadeOutActionPool = new Pool<FadeOutAction>(){
        protected FadeOutAction newObject(){
            return new FadeOutAction(stateUtils, AutoPlayState.this, soundEffectId);
        }
    };

    @Override
    public void beginFadeOut() {
        playing = false;
    }

    @Override
    public void end() {


        logger.log(Level.SEVERE,"Stop action: "+bojanCircle.getNote());
        BojanState state = BojanStateFactory.getState(BojanStateType.USUAL, bojanCircle);
        bojanCircle.setState(state);
        startNextStates();
    }

    boolean playing = true;

    @Override
    public void draw(Batch batch, float parentAlpha) {
        stateUtils.draw(batch, playing);


    }

}
