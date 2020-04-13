package com.instra.bojan;

public class GlobalState {

    private int pitchOffset;

    private static GlobalState instance;
    private GlobalState(){}

    public static GlobalState getInstance(){
        if(instance == null){
            instance = new GlobalState();
        }
        return instance;
    }

    public int getPitchOffset() {
        return pitchOffset;
    }

    public void setPitchOffset(int pitchOffset) {
        this.pitchOffset = pitchOffset;
    }
}
