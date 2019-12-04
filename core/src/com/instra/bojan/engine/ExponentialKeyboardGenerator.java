package com.instra.bojan.engine;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class ExponentialKeyboardGenerator extends KeyboardGenerator {


    public ExponentialKeyboardGenerator(String instrument, float width, float height, Skin skin, Stage stage) {
        super(instrument, width, height, skin, stage);
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
