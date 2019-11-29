package com.instra.bojan.state;

import com.instra.bojan.elements.BojanCircle;
import com.instra.bojan.theory.Note;

public class BojanStateFactory {

    public static BojanState getState(BojanStateType type, BojanCircle bojanCircle, Note note){
        if (type == BojanStateType.USUAL){
            return new UsualState(bojanCircle);
        }
        if (type == BojanStateType.AUTO_PLAY){
            return new AutoPlayState(bojanCircle);
        }
        if (type == BojanStateType.LISTEN){
            return new ListenState(bojanCircle, note);
        }
        throw new RuntimeException("No bojan state: "+type+" found");
    }
}