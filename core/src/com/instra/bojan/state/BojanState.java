package com.instra.bojan.state;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.instra.bojan.elements.BojanCircle;

import java.util.List;

public abstract class BojanState {

    protected BojanCircle bojanCircle;



    protected BojanState nextState;

    public BojanState(BojanCircle bojanCircle) {
        this.bojanCircle = bojanCircle;
    }



    public abstract void start();


    public abstract void act(float delta);
    public abstract void draw(Batch batch, float parentAlpha);


    public void setNextState(BojanState nextState) {
        this.nextState = nextState;
    }
}
