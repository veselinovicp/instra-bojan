package com.instra.bojan.state;

import com.instra.bojan.elements.BojanCircle;
import com.instra.bojan.theory.Duration;
import com.instra.bojan.theory.Note;

public class BojanStateFactory {

    public static BojanState getState(BojanStateType type, BojanCircle bojanCircle){
        return getState(type, bojanCircle, null, null);
    }

    public static BojanState getState(BojanStateType type, BojanCircle bojanCircle, Note note){
        return getState(type, bojanCircle, note, null);
    }

    public static BojanState getState(BojanStateType type, BojanCircle bojanCircle, Duration duration){
        return getState(type, bojanCircle, null, duration);
    }

    public static BojanState getState(BojanStateType type, BojanCircle bojanCircle, Note note, Duration duration){
        if (type == BojanStateType.USUAL){
            return new UsualState(bojanCircle);
        }
        if (type == BojanStateType.AUTO_PLAY){
            return new AutoPlayState(bojanCircle, duration);
        }
        if (type == BojanStateType.LISTEN){
            return new ListenState(bojanCircle, note);
        }
        throw new RuntimeException("No bojan state: "+type+" found");
    }
}
