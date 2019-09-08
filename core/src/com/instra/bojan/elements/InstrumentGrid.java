package com.instra.bojan.elements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.List;

public class InstrumentGrid extends Actor {

    private List<BojanCircle> bojanCircles;

    private ShapeRenderer shapeRenderer;

    int numOfCircles;

    public InstrumentGrid(List<BojanCircle> bojanCircles) {
        this.bojanCircles = bojanCircles;
        numOfCircles = bojanCircles.size();

        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

//        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);


        for(int i=0; i<numOfCircles-1;i++){
            BojanCircle previous = bojanCircles.get(i);
            BojanCircle next = bojanCircles.get(i + 1);
            float x1 = (previous.getBojanPosition().getLeftX()+previous.getBojanPosition().getRightX())/2f;
            float y1 = (previous.getBojanPosition().getLeftY()+previous.getBojanPosition().getRightY())/2f;
            float x2 = (next.getBojanPosition().getLeftX()+next.getBojanPosition().getRightX())/2f;
            float y2 = (next.getBojanPosition().getLeftY()+next.getBojanPosition().getRightY())/2f;
            shapeRenderer.rectLine(x1,y1,x2,y2,20);
        }

        shapeRenderer.end();

        batch.end();

    }
}
