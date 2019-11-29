package com.instra.bojan.state;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
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

        defaultState = BojanStateFactory.getState(BojanStateType.USUAL, bojanCircle, null);
        bojanCircle.setState(this);



        bojanCircle.addListener(new InputListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                System.out.println("touch up");
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }


        });

        bojanCircle.addCaptureListener(new InputListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                System.out.println("touch up 2");
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }


        });

    }

    @Override
    public void act(float delta) {
        defaultState.act(delta);

        if(bojanCircle.isJustStoppedPlaying()){

            String result = "correct";
            if(note != bojanCircle.getNote()){
                result = "incorrect";
            }
            logger.log(Level.SEVERE,"Just stopped playing: "+bojanCircle.getNote()+" "+result);
            bojanCircle.setJustStoppedPlaying(false);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        defaultState.draw(batch, parentAlpha);


    }


}
