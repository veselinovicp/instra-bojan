package com.instra.bojan.engine.instrument;

public class PianoInstrument extends BojanInstrument {

    public PianoInstrument(int numOfOctaves, int circlesPerOctave) {
        super(numOfOctaves, circlesPerOctave);
    }

    @Override
    String getOctave(int i) {

        int octave = (i / circlesPerOctave) + 4;
        return "" + octave;
    }

    @Override
    String getSoundFile(String tone, String octave) {
        return "piano-"+tone+octave+".wav";
    }
}
