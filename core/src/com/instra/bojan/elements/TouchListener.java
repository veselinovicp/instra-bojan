package com.instra.bojan.elements;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class TouchListener extends InputListener {

    private Sound sound;
    private long id;
    private boolean circlePressed = false;

    public TouchListener(Sound sound) {
        this.sound = sound;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        id = sound.loop();
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
