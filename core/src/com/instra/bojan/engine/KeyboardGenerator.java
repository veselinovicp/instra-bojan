package com.instra.bojan.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.instra.bojan.Constants;
import com.instra.bojan.elements.BojanCircle;
import com.instra.bojan.elements.BojanPosition;
import com.instra.bojan.elements.GridLine;
import com.instra.bojan.theory.Note;


import java.util.ArrayList;
import java.util.List;

public abstract class KeyboardGenerator {

    protected int numOfOctaves = 3;
    protected int circlesPerOctave = 12;

    protected float width;
    protected float height;
    protected float circleWidthStart;
    protected float circleHeightStart;

    protected float circleWidthEnd;
    protected float circleHeightEnd;

    private String instrument;

    private  Texture texture;

    private Sound sound;

    protected Skin skin;
    protected Stage stage;


    public KeyboardGenerator(String instrument, float width, float height, Skin skin, Stage stage) {
        this.width = width;
        this.height = height;
        this.skin = skin;
        this.stage = stage;
        circleHeightStart = Math.min(width, height)/6f;
        circleWidthStart = circleHeightStart;

        circleHeightEnd = Math.min(width, height)/24;
        circleWidthEnd = circleHeightEnd;

        this.instrument = instrument;

        texture = new Texture(Gdx.files.internal("circle.png"));


        sound = Gdx.audio.newSound(Gdx.files.internal(instrument));


    }

    public static KeyboardGenerator getKeyboardGenerator(String keyboardType, String instrument, float width, float height, Skin skin, Stage stage){
        if(keyboardType.equals(Constants.KEYBOARD_TYPE_EXPONENTIAL_SPIRAL)){
            return new ExponentialKeyboardGenerator(instrument, width, height, skin, stage);
        }
        throw new RuntimeException("No keyboard by type: "+keyboardType+" found");
    }



    protected abstract float getRadius(float angle);
    protected abstract float getScale();

    public Group getSpiralCirclesGroup(List<BojanCircle> bojanCircles) {
        Group result = new Group();
        for(BojanCircle bojanCircle : bojanCircles) {
            result.addActor(bojanCircle);
        }
        return result;
    }


    public List<BojanCircle> getSpiralCircles() {




        float angleDelta=(float) ( 2*Math.PI)/circlesPerOctave;

        List<BojanCircle> bojanCircles = new ArrayList<BojanCircle>();

        for(int i=0; i<numOfOctaves*circlesPerOctave;i++){
            //1,3,6,8,11
            if(i%12==1 || i%12==3 || i%12==6 || i%12==8 || i%12==11 ){
                continue;
            }

            Note note = null;
            if(i%12==0){
                note = Note.DO;
            }
            if(i%12==2){
                note = Note.RE;
            }
            if(i%12==4){
                note = Note.MI;
            }
            if(i%12==5){
                note = Note.FA;
            }
            if(i%12==7){
                note = Note.SO;
            }
            if(i%12==9){
                note = Note.LA;
            }
            if(i%12==10){
                note = Note.TI;
            }

            float angle = (float) i*angleDelta;

            BojanPosition bojanPosition = getBojanPosition(i, angle);

            Color color = getColor(i);
            Color activeColor = getActiveColor(color);



            BojanCircle bojanCircle = new BojanCircle(texture, bojanPosition, color, activeColor, sound, note, skin, stage, i);
            bojanCircles.add(bojanCircle);


        }
//        int numOfCircles = numOfOctaves * circlesPerOctave;
        int numOfCircles = bojanCircles.size();
        for(int i = 0; i< numOfCircles -1; i++){
            bojanCircles.get(i).setNextCircle(bojanCircles.get(i+1).getCircle());
        }


        return bojanCircles;
    }



    private Color getActiveColor(Color color){
        Color activeColor = new Color(color);
        activeColor.r=1-color.r;
        activeColor.g=1-color.g;
        activeColor.b=1-color.b;
        return activeColor;
    }

    private Color getColor(int i){
        Color result= null;

        float specter = i % circlesPerOctave / (float) circlesPerOctave;
        if(specter<1/7f){
            float percent = calculatePercent(0, specter);
            result = calculate(255,255,0,127,0,0,percent);
        }else if(1/7f<=specter && specter<2/7f){
            float percent = calculatePercent(1/7f, specter);
            result = calculate(255,255,127,255,0,0,percent);
        }else if(2/7f<=specter && specter<3/7f){
            float percent = calculatePercent(2/7f, specter);
            result = calculate(255,0,255,255,0,0,percent);

        }else if(3/7f<=specter && specter<4/7f){
            float percent = calculatePercent(3/7f, specter);
            result = calculate(0,0,255,0,0,255,percent);

        }else if(4/7f<=specter && specter<5/7f){
            float percent = calculatePercent(4/7f, specter);
            result = calculate(0,75,0,0,255,130,percent);

        }else if(5/7f<=specter && specter<6/7f){
            float percent = calculatePercent(5/7f, specter);
            result = calculate(75,143,0,0,130,255,percent);

        }else if(6/7f<=specter && specter<=1){
            float percent = calculatePercent(6/7f, specter);
            result = calculate(143,255,0,0,255,0,percent);
        }
        return result;
    }

    private float calculatePercent(float lowerLimit, float specter){
        return (specter-lowerLimit)*7;

    }

    private Color calculate(int rStart, int rStop, int gStart, int gStop, int bStart, int bStop, float percent){
        float r =calculateColorComponent(rStart, rStop, percent);
        float g =calculateColorComponent(gStart, gStop, percent);
        float b =calculateColorComponent(bStart, bStop, percent);
        return new Color(r, g, b, 1f);

    }

    private float calculateColorComponent(int colorStart, int colorStop, float percent){
        return (((1-percent)*colorStart)+(percent*colorStop))/255f;
    }




    private BojanPosition getBojanPosition(int i, float angle) {
        float radius = getRadius(angle);
        float cartesionX = getCartesionX(angle, radius);
        float cartesionY = getCartesionY(angle, radius);
        float total = numOfOctaves * circlesPerOctave;
        float circleWidth = (((total-i)*circleWidthStart)+(i*circleWidthEnd))/total;
        float circleHeight = (((total-i)*circleHeightStart)+(i*circleHeightEnd))/total;
        float leftX = cartesionX - (circleWidth /2);
        float leftY = cartesionY - (circleHeight /2) ;
        float rightX = cartesionX + (circleWidth /2);
        float rightY = cartesionY + (circleHeight /2);
        return new BojanPosition(leftX, leftY, rightX, rightY);
    }

    public List<GridLine> getGridLines(int number){
        List<GridLine> result = new ArrayList<GridLine>();

        float angleStep = (float)(6*Math.PI/(float)number);
        for(int i=0; i<number-1;i++){
            float previousAngle = angleStep*i;
            float previousRadius = getRadius(previousAngle);
            float previousCartesionX = getCartesionX(previousAngle, previousRadius);
            float previousCartesionY = getCartesionY(previousAngle, previousRadius);

            float nextAngle = angleStep*(i+1);
            float nextRadius = getRadius(nextAngle);
            float nextCartesionX = getCartesionX(nextAngle, nextRadius);
            float nextCartesionY = getCartesionY(nextAngle, nextRadius);

            GridLine gridLine = new GridLine(previousCartesionX, previousCartesionY, nextCartesionX, nextCartesionY);
            result.add(gridLine);



        }
        return result;
    }

    protected float getCartesionX(float angle, float radius){
        radius = radius * getScale() * Math.min(width, height) / width;
        return ((MathUtils.cos(angle)*radius*width)+width/2);
    }

    protected float getCartesionY(float angle, float radius){
        radius = radius * getScale()* Math.min(width, height) / height;
        return ((MathUtils.sin(angle)*radius*height)+height/2);
    }

    public void dispose(){
        System.out.println("disposing");
        texture.dispose();
        sound.dispose();
    }



}
