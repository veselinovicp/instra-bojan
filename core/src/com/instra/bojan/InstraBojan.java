package com.instra.bojan;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.instra.bojan.elements.BojanCircle;
import com.instra.bojan.elements.BojanPosition;
import com.instra.bojan.elements.InstrumentGrid;
import com.instra.bojan.engine.KeyboardGenerator;

import java.util.List;

public class InstraBojan extends ApplicationAdapter {
	Stage stage;
	ScreenViewport viewport;

	int screenWidth;
	int screenHeight;
	List<BojanCircle> spiralCircles;


	
	@Override
	public void create () {



//		BojanCircle circle = new BojanCircle(new BojanPosition(200f,200f,500,500));


		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		fillStage(screenWidth, screenHeight);





	}

	private void fillStage(int width, int height ) {



		viewport = new ScreenViewport();
		stage = new Stage(viewport);

		System.out.println("width: "+width+", height: "+height);

		KeyboardGenerator keyboardGenerator = KeyboardGenerator.getKeyboardGenerator(Constants.KEYBOARD_TYPE_EXPONENTIAL_SPIRAL, Constants.INSTRUMENT_VIOLIN, width, height);
		spiralCircles = keyboardGenerator.getSpiralCircles();


		BojanPosition firstCircle = spiralCircles.get(0).getBojanPosition();
		float gridWidthStart = (firstCircle.getRightX()-firstCircle.getLeftX())/3f;
		InstrumentGrid instrumentGrid = new InstrumentGrid(keyboardGenerator.getGridLines(200), gridWidthStart);

		stage.addActor(instrumentGrid);



		for(BojanCircle circle : spiralCircles){
			stage.addActor(circle);


		}

		Gdx.input.setInputProcessor(stage);
	}

	private void disposeStage() {
		if(spiralCircles!=null){
			for(BojanCircle circle : spiralCircles){
				circle.dispose();

			}
		}
		if(stage!=null){
//			stage.clear();
			stage.clear();
			stage.dispose();



		}
	}

	@Override
	public void render () {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);

		if(screenWidth!=width || screenHeight!=height) {
			disposeStage();
			fillStage(width, height);
			screenWidth=width;
			screenHeight=height;
		}
	}
}
