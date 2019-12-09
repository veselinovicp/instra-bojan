package com.instra.bojan.state;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.instra.bojan.elements.BojanCircle;
import com.instra.bojan.elements.BojanPosition;
import com.instra.bojan.theory.Duration;

public class StateUtils {

    private BojanCircle bojanCircle;
    private float red;
    private float green;
    private float blue;

    public StateUtils(BojanCircle bojanCircle) {


        this.bojanCircle = bojanCircle;
        red = bojanCircle.getColor().r;
        green = bojanCircle.getColor().g;
        blue = bojanCircle.getColor().b;




    }

    private static float eightLength = 0.3f;

    public float getDurationTime(Duration duration){
        if(duration == Duration.EIGHT){
            return eightLength;
        }
        if(duration == Duration.QUARTER){
            return eightLength*2;
        }
        if(duration == Duration.HALF){
            return eightLength*4;
        }
        if(duration == Duration.WHOLE){
            return eightLength*8;
        }
        throw new RuntimeException("No duration time is known for: "+duration);

    }

    public void draw(Batch batch, boolean playing){
        if(!batch.isDrawing()){
            batch.begin();
        }



        //public void draw (Texture texture, float x, float y, float width, float height);
        if(playing){
            batch.setColor(bojanCircle.getActiveColor());

        }else {
//            batch.setColor(bojanCircle.getColor());
            batch.setColor(red, green, blue, bojanCircle.getColor().a);

        }




        BojanPosition bojanPosition = bojanCircle.getBojanPosition();
        batch.draw(bojanCircle.getTexture(),bojanPosition.getLeftX(),bojanPosition.getLeftY(), bojanCircle.getWidth(), bojanCircle.getHeight());
    }

    public void setRed(float red) {
        this.red = red;
    }

    public void setGreen(float green) {
        this.green = green;
    }

    public void setBlue(float blue) {
        this.blue = blue;
    }

    public BojanCircle getBojanCircle() {
        return bojanCircle;
    }
}
