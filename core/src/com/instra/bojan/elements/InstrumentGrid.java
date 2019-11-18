package com.instra.bojan.elements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.List;

public class InstrumentGrid extends Actor {



    private List<GridLine> gridLines;

    private ShapeRenderer shapeRenderer;

    float initialSize;


    public InstrumentGrid(List<GridLine> gridLines, float initialSize) {

        this.gridLines = gridLines;
        this.initialSize = initialSize;


        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);


        int numOfLines = gridLines.size();
        int i=0;
        for(GridLine gridLine : gridLines){
           float width = (((numOfLines-i)*initialSize)+(i*1))/numOfLines;
           shapeRenderer.rectLine(gridLine.getX1(),gridLine.getY1(),gridLine.getX2(),gridLine.getY2(),width);
           i++;
       }

        shapeRenderer.end();

        batch.end();

    }

    public void dispose(){
        shapeRenderer.dispose();
    }
}
