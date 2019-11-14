package com.instra.bojan.engine.instrument;

import com.instra.bojan.Constants;

public abstract class BojanInstrument {

    protected int numOfOctaves;
    protected int circlesPerOctave;


     abstract String getOctave(int i);
     abstract String getSoundFile(String tone, String octave);

    public BojanInstrument(int numOfOctaves, int circlesPerOctave) {
        this.numOfOctaves = numOfOctaves;
        this.circlesPerOctave = circlesPerOctave;
    }

    public static BojanInstrument getBojanInstrument(String instrument, int numOfOctaves, int circlesPerOctave){
        if(instrument.equals(Constants.INSTRUMENT_VIOLIN)){
            return new ViolinInstrument(numOfOctaves, circlesPerOctave);
        }
        if(instrument.equals(Constants.INSTRUMENT_TRUMPET)){
            return new TrumpetInstrument(numOfOctaves, circlesPerOctave);
        }
        if(instrument.equals(Constants.INSTRUMENT_PIANO)){
            return new PianoInstrument(numOfOctaves, circlesPerOctave);
        }
        if(instrument.equals(Constants.INSTRUMENT_FREQUENCY)){
            return new FrequencyInstrument(numOfOctaves, circlesPerOctave);
        }
        throw new RuntimeException("No instrument: "+instrument+" found");
    }

    public float getPitch(int i){
        return 1 + ((i%circlesPerOctave)/(float)circlesPerOctave);
        /*if(i%3==0){
            return 2;
        }else if(i%3==1){
            return 3;
        }
        return 4;*/
    }

    public String getSound(int i) {
        String tone ="C";

        /*String tone ="";
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
        }*/
        String octave=getOctave(i);

        return getSoundFile(tone, octave);
    }
}
