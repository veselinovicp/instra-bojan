package com.instra.bojan;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import com.instra.bojan.communication.Commander;
import com.instra.bojan.communication.CommanderType;
import com.instra.bojan.elements.BojanCircle;
import com.instra.bojan.elements.BojanPosition;
import com.instra.bojan.elements.InstrumentGrid;
import com.instra.bojan.engine.KeyboardGenerator;

import java.util.List;

import static com.badlogic.gdx.Application.LOG_INFO;

public class InstraBojan extends ApplicationAdapter {
	Stage stage;
	ScreenViewport viewport;

	int screenWidth;
	int screenHeight;
	Group spiralCircles;
	List<BojanCircle> circles;

	private KeyboardGenerator keyboardGenerator;
	private InstrumentGrid instrumentGrid;


	
	@Override
	public void create () {

		Gdx.app.setLogLevel(LOG_INFO);



//		BojanCircle circle = new BojanCircle(new BojanPosition(200f,200f,500,500));


		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		fillStage(screenWidth, screenHeight);

		Gdx.input.setInputProcessor(stage);






	}

	private void fillStage(int width, int height ) {



		viewport = new ScreenViewport();
		stage = new Stage(viewport);

		System.out.println("width: "+width+", height: "+height);

		keyboardGenerator = KeyboardGenerator.getKeyboardGenerator(Constants.KEYBOARD_TYPE_EXPONENTIAL_SPIRAL, Constants.INSTRUMENT_VIOLINS, width, height);
		circles = keyboardGenerator.getSpiralCircles();

		this.spiralCircles = keyboardGenerator.getSpiralCirclesGroup(circles);


		BojanCircle first = (BojanCircle) (this.spiralCircles.getChildren().first());
		BojanPosition firstCircle = first.getBojanPosition();
		float gridWidthStart = (firstCircle.getRightX()-firstCircle.getLeftX())/3f;
		instrumentGrid = new InstrumentGrid(keyboardGenerator.getGridLines(200), gridWidthStart);

		stage.addActor(instrumentGrid);



		/*for(BojanCircle circle : spiralCircles){
			stage.addActor(circle);


		}*/

		stage.addActor(this.spiralCircles);


	}

	private void disposeStage() {
		if(spiralCircles!=null){

			spiralCircles.getChildren().clear();

			/*for(BojanCircle circle : spiralCircles){
				circle.dispose();

			}*/
			keyboardGenerator.dispose();
			instrumentGrid.dispose();
		}
		if(stage!=null){
//			stage.clear();
			stage.clear();
			stage.dispose();





		}
	}


	@Override
	public void render () {

		Gdx.gl.glClearColor(0, 0, 0.2f, 1);

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



		stage.act();
		stage.draw();

		/*if(!commanderActive){
			commanderActive = true;
			Commander commander = Commander.getCommander(CommanderType.PLAY, circles);
			commander.execute();
		}*/


	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);



		if(screenWidth!=width || screenHeight!=height) {
			disposeStage();
			fillStage(width, height);
			screenWidth=width;
			screenHeight=height;



				Commander commander = Commander.getCommander(CommanderType.PLAY, circles);
				commander.execute();


		}
	}

	@Override
	public void dispose () {
		disposeStage();

	}
}
