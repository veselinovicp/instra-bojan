package com.instra.bojan.state;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.instra.bojan.elements.BojanCircle;
import com.instra.bojan.theory.Note;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ListenState extends BojanState{

    private BojanState defaultState;

    private Note note;

    private Logger logger = Logger.getLogger("ListenState");


    public ListenState(BojanCircle bojanCircle, Note note) {
        super(bojanCircle);
        this.note = note;
    }

    @Override
    public void start() {


        bojanCircle.setJustStoppedPlaying(false);
        defaultState = BojanStateFactory.getState(BojanStateType.USUAL, bojanCircle);
        bojanCircle.setState(this);

    }

    @Override
    public void act(float delta) {
        defaultState.act(delta);

        if(bojanCircle.isJustStoppedPlaying()){
            bojanCircle.setJustStoppedPlaying(false);
            bojanCircle.setState(defaultState);
            String result = "correct";
            if(note != bojanCircle.getNote()){
                result = "incorrect";

            }
            logger.log(Level.SEVERE,"Just stopped playing: "+bojanCircle.getNote()+" "+result);


            startNextStates();

        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        defaultState.draw(batch, parentAlpha);


    }


}
