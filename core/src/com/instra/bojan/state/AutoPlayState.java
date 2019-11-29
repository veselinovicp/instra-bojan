package com.instra.bojan.state;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.instra.bojan.elements.BojanCircle;

public class AutoPlayState extends BojanState {



    private StateUtils stateUtils;

    class AutoPlayAction extends TemporalAction{

        private long soundId;

        private Sound sound;

        public AutoPlayAction(float duration) {
            super(duration);
            this.sound = bojanCircle.getSoundEffect();

        }

        @Override
        protected void begin() {
            super.begin();
            soundId = sound.loop(1, bojanCircle.getPitch(), 0);

        }

        @Override
        protected void update(float percent) {

        }

        @Override
        protected void end() {
            super.end();
            sound.stop(soundId);

            BojanState state = BojanStateFactory.getState(BojanStateType.USUAL, bojanCircle, null);
            bojanCircle.setState(state);


            startNextStates();


        }
    }

    public AutoPlayState(BojanCircle bojanCircle) {

        super(bojanCircle);


        this.bojanCircle = bojanCircle;

        stateUtils = new StateUtils(bojanCircle);






    }




    @Override
    public void start() {
        bojanCircle.setState(this);
        bojanCircle.addAction(new AutoPlayAction(3));
    }

    @Override
    public void act(float delta) {

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        stateUtils.draw(batch, true);
    }

}
