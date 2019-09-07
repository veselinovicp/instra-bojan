package com.instra.bojan.engine;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.instra.bojan.Constants;
import com.instra.bojan.elements.BojanCircle;
import com.instra.bojan.elements.BojanPosition;
import com.instra.bojan.engine.instrument.BojanInstrument;

import java.util.ArrayList;
import java.util.List;

public abstract class KeyboardGenerator {

    protected float width;
    protected float height;
    protected float circleWidthStart;
    protected float circleHeightStart;

    protected float circleWidthEnd;
    protected float circleHeightEnd;
    private BojanInstrument bojanInstrument;


    public KeyboardGenerator(String instrument, float width, float height) {
        this.width = width;
        this.height = height;
        circleHeightStart = Math.min(width, height)/4.5f;
        circleWidthStart = circleHeightStart;

        circleHeightEnd = Math.min(width, height)/24;
        circleWidthEnd = circleHeightEnd;

        bojanInstrument = BojanInstrument.getBojanInstrument(instrument);




    }

    public static KeyboardGenerator getKeyboardGenerator(String keyboardType, String instrument, float width, float height){
        if(keyboardType.equals(Constants.KEYBOARD_TYPE_EXPONENTIAL_SPIRAL)){
            return new ExponentialKeyboardGenerator(instrument, width, height);
        }
        throw new RuntimeException("No keyboard by type: "+keyboardType+" found");
    }



    protected abstract float getRadius(float angle);
    protected abstract float getScale();


    public List<BojanCircle> getSpiralCircles() {
        List<BojanCircle> bojanCircles = new ArrayList<BojanCircle>();
        for(int i=0; i<21;i++){

            float angle = (float) (i*2*Math.PI)/7f;
            float radius = getRadius(angle);
            float cartesionX = getCartesionX(angle, radius);
            float cartesionY = getCartesionY(angle, radius);
            float circleWidth = (((21-i)*circleWidthStart)+(i*circleWidthEnd))/21;
            float circleHeight = (((21-i)*circleHeightStart)+(i*circleHeightEnd))/21;
            float leftX = cartesionX - (circleWidth /2);
            float leftY = cartesionY - (circleHeight /2) ;
            float rightX = cartesionX + (circleWidth /2);
            float rightY = cartesionY + (circleHeight /2);
            BojanPosition bojanPosition = new BojanPosition(leftX, leftY, rightX, rightY);

            Color color=null;
            Color activeColor=null;
            if(i%7==0){
                color = Color.valueOf("FF0000");
                activeColor = Color.valueOf("ff6666");
            }
            if(i%7==1){
                color = Color.valueOf("FF7F00");
                activeColor = Color.valueOf("ffb870");
            }
            if(i%7==2){
                color = Color.valueOf("FFFF00");
                activeColor = Color.valueOf("ffff8a");
            }
            if(i%7==3){
                color = Color.valueOf("00FF00");
                activeColor = Color.valueOf("85ff85");
            }
            if(i%7==4){
                color = Color.valueOf("0000FF");
                activeColor = Color.valueOf("8080ff");
            }
            if(i%7==5){
                color = Color.valueOf("4B0082");
                activeColor = Color.valueOf("b042ff");
            }
            if(i%7==6){
                color = Color.valueOf("9400D3");
                activeColor = Color.valueOf("d980ff");
            }


            String sound = bojanInstrument.getSound(i);

            BojanCircle bojanCircle = new BojanCircle(bojanPosition, color, activeColor, sound);
            bojanCircles.add(bojanCircle);
        }
        return bojanCircles;
    }

    protected float getCartesionX(float angle, float radius){
        radius = radius * getScale() * Math.min(width, height) / width;
        return ((MathUtils.cos(angle)*radius*width)+width/2);
    }

    protected float getCartesionY(float angle, float radius){
        radius = radius * getScale()* Math.min(width, height) / height;
        return ((MathUtils.sin(angle)*radius*height)+height/2);
    }



}
