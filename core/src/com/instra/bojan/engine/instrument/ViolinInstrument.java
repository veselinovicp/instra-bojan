package com.instra.bojan.engine.instrument;

import com.badlogic.gdx.graphics.Color;

public class ViolinInstrument extends BojanInstrument {

    public ViolinInstrument(int numOfOctaves, int circlesPerOctave) {
        super(numOfOctaves, circlesPerOctave);
    }

    @Override
    String getOctave(int i) {

        int octave = (i / circlesPerOctave) + 4;
        return "" + octave;
    }

    @Override
    String getSoundFile(String tone, String octave) {
        return "violin_"+tone+octave+"_025_piano_arco-normal.mp3";
    }
}
