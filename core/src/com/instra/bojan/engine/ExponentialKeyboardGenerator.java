package com.instra.bojan.engine;

public class ExponentialKeyboardGenerator extends KeyboardGenerator {


    public ExponentialKeyboardGenerator(String instrument, float width, float height) {
        super(instrument, width, height);
    }

    @Override
    protected float getRadius(float angle) {
        return (float) Math.pow(Math.E , ((float)(-0.1*angle)));
    }

    @Override
    protected float getScale() {
        return 0.5f;
    }
}
