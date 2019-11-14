package com.instra.bojan.engine.instrument;

public class FrequencyInstrument extends BojanInstrument {

    public FrequencyInstrument(int numOfOctaves, int circlesPerOctave) {
        super(numOfOctaves, circlesPerOctave);
    }

    @Override
    String getOctave(int i) {

        if(i==0){
            return "440";
        }
        if(i==1){
            return "880";
        }
        return "1760";
    }

    @Override
    String getSoundFile(String tone, String octave) {
        return octave+".wav";
    }
}
