package com.instra.bojan.state;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.instra.bojan.elements.BojanCircle;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public abstract class BojanState {

    protected BojanCircle bojanCircle;





    protected List<BojanState> nextStates = new ArrayList<BojanState>();



    public BojanState(BojanCircle bojanCircle) {
        this.bojanCircle = bojanCircle;

    }



    public abstract void start();


    public abstract void act(float delta);
    public abstract void draw(Batch batch, float parentAlpha);

    protected void startNextStates(){
        for(BojanState nextState : nextStates) {
            nextState.start();
        }
    }


    public void setNextStates(List<BojanState> nextStates) {
        this.nextStates = nextStates;
    }

    public List<BojanState> getNextStates() {
        return nextStates;
    }

    public BojanCircle getBojanCircle() {
        return bojanCircle;
    }
}
