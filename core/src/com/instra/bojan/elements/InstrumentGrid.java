package com.instra.bojan.elements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.List;

public class InstrumentGrid extends Actor {



    private List<GridLine> gridLines;

    private ShapeRenderer shapeRenderer;


    public InstrumentGrid(List<GridLine> gridLines) {

        this.gridLines = gridLines;


        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);



       for(GridLine gridLine : gridLines){
           shapeRenderer.rectLine(gridLine.getX1(),gridLine.getY1(),gridLine.getX2(),gridLine.getY2(),20);
       }

        shapeRenderer.end();

        batch.end();

    }
}
