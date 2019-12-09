package com.instra.bojan.state.actions;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.instra.bojan.elements.BojanCircle;
import com.instra.bojan.state.StateUtils;

public class FadeOutAction extends TemporalAction {
    float deltaR;
    float deltaG;
    float deltaB;

    private BojanCircle bojanCircle;
    private StateUtils stateUtils;
    private Sound soundEffect;

    private FadeOutCallback callback;

    private long soundEffectId;

    public FadeOutAction(StateUtils stateUtils, FadeOutCallback callback, long soundEffectId) {
        this.stateUtils = stateUtils;
        this.callback = callback;
        this.soundEffectId = soundEffectId;
        bojanCircle = stateUtils.getBojanCircle();
        soundEffect = bojanCircle.getSoundEffect();
        Color color = bojanCircle.getColor();
        Color activeColor = bojanCircle.getActiveColor();
        deltaR = -activeColor.r+color.r;
        deltaB = -activeColor.b+color.b;
        deltaG = -activeColor.g+color.g;


        setReverse(true);
        setDuration(0.2f);


    }

    @Override
    protected void begin() {
        super.begin();

        callback.beginFadeOut();


    }

    @Override
    protected void update(float percent) {

        soundEffect.setVolume(soundEffectId, percent);
        stateUtils.setRed(bojanCircle.getActiveColor().r+(1-percent)*deltaR);
        stateUtils.setGreen(bojanCircle.getActiveColor().g+(1-percent)*deltaG);
        stateUtils.setBlue(bojanCircle.getActiveColor().b+(1-percent)*deltaB);
    }

    @Override
    protected void end() {
        super.end();
        soundEffect.stop(soundEffectId);
//        fadeOutEffect = false;
        callback.end();

        bojanCircle.setJustStoppedPlaying(true);
        stateUtils.setRed(bojanCircle.getColor().r);
        stateUtils.setGreen(bojanCircle.getColor().g);
        stateUtils.setBlue(bojanCircle.getColor().b);



    }
}
