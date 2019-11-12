package com.instra.bojan.elements;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class TouchListener extends InputListener {

    private Sound sound;
    private long id;
    private boolean circlePressed = false;
    private float pitch;

    public TouchListener(Sound sound, float pitch) {
        this.sound = sound;
        this.pitch = pitch;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        id = sound.loop(1, pitch, 0);
        circlePressed = true;
        return true;
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        super.touchUp(event, x, y, pointer, button);
        sound.stop(id);
        circlePressed = false;

    }

    public boolean isCirclePressed(){
        return circlePressed;
    }
}
