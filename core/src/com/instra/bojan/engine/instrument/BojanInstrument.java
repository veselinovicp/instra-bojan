package com.instra.bojan.engine.instrument;

import com.instra.bojan.Constants;

public abstract class BojanInstrument {

     abstract String getOctave(int i);
     abstract String getSoundFile(String tone, String octave);

    public static BojanInstrument getBojanInstrument(String instrument){
        if(instrument.equals(Constants.INSTRUMENT_VIOLIN)){
            return new ViolinInstrument();
        }
        throw new RuntimeException("No instrument: "+instrument+" found");
    }

    public String getSound(int i) {
        String tone ="";
        if(i%7==0){
            tone="C";
        }
        if(i%7==1){
            tone="D";
        }
        if(i%7==2){
            tone="E";
        }
        if(i%7==3){
            tone="F";
        }
        if(i%7==4){
            tone="G";
        }
        if(i%7==5){
            tone="A";
        }
        if(i%7==6){
            tone="B";
        }
        String octave=getOctave(i);

        return getSoundFile(tone, octave);
    }
}
