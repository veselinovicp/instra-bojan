package com.instra.bojan.state;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.instra.bojan.elements.BojanCircle;
import com.instra.bojan.elements.BojanPosition;

public class StateUtils {

    private BojanCircle bojanCircle;

    public StateUtils(BojanCircle bojanCircle) {


        this.bojanCircle = bojanCircle;




    }

    public void draw(Batch batch, boolean playing){
        if(!batch.isDrawing()){
            batch.begin();
        }



        //public void draw (Texture texture, float x, float y, float width, float height);
        if(playing){
            batch.setColor(bojanCircle.getActiveColor());
        }else {
            batch.setColor(bojanCircle.getColor());
        }


        BojanPosition bojanPosition = bojanCircle.getBojanPosition();
        batch.draw(bojanCircle.getTexture(),bojanPosition.getLeftX(),bojanPosition.getLeftY(), bojanCircle.getWidth(), bojanCircle.getHeight());
    }
}
