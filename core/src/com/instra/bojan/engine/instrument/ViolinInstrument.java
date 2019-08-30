package com.instra.bojan.engine.instrument;

import com.badlogic.gdx.graphics.Color;

public class ViolinInstrument extends BojanInstrument {

    @Override
    String getOctave(int i) {
        if(i<7){
            return "4";
        }
        if(i<14){
            return "5";
        }
        return "6";
    }

    @Override
    String getSoundFile(String tone, String octave) {
        return "violin_"+tone+octave+"_025_piano_arco-normal.mp3";
    }
}
