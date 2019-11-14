package com.instra.bojan.elements;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class TouchListener extends InputListener {

    private Sound sound;
    private long id;
    private boolean circlePressed = false;
    private float pitch;
    private Circle circle;

    public TouchListener(Sound sound, float pitch, Circle circle) {
        this.sound = sound;
        this.pitch = pitch;
        this.circle = circle;
    }


    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if (circle.contains(x,y)) {
            id = sound.loop(1, pitch, 0);
            circlePressed = true;
            return true;
        }
        return false;
    }

    @Override
    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
        super.exit(event, x, y, pointer, toActor);
        System.out.println("exit");
    }

    /* @Override
    public boolean mouseMoved(InputEvent event, float x, float y) {
        id = sound.loop(1, pitch, 0);
        circlePressed = true;
        return true;
    }*/

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
