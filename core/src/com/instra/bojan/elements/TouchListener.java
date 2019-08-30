package com.instra.bojan.elements;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class TouchListener extends InputListener {

    private Sound sound;

    public TouchListener(Sound sound) {
        this.sound = sound;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        sound.play();
        return true;
    }

}
