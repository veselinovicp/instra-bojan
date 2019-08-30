package com.instra.bojan;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.instra.bojan.elements.BojanCircle;
import com.instra.bojan.engine.KeyboardGenerator;

import java.util.List;

public class InstraBojan extends ApplicationAdapter {
	Stage stage;


	
	@Override
	public void create () {
		stage = new Stage(new ScreenViewport());


//		BojanCircle circle = new BojanCircle(new BojanPosition(200f,200f,500,500));


		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		KeyboardGenerator keyboardGenerator = KeyboardGenerator.getKeyboardGenerator(Constants.KEYBOARD_TYPE_EXPONENTIAL_SPIRAL, Constants.INSTRUMENT_VIOLIN, width, height);
		List<BojanCircle> spiralCircles = keyboardGenerator.getSpiralCircles();
		for(BojanCircle circle : spiralCircles){
			stage.addActor(circle);
		}


		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render () {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();
	}
}
