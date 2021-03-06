package com.instra.bojan;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import com.instra.bojan.communication.Commander;
import com.instra.bojan.communication.CommanderType;
import com.instra.bojan.data.Song;
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

	private Skin skin;


	
	@Override
	public void create () {

		Gdx.app.setLogLevel(LOG_INFO);
//		skin = new Skin(Gdx.files.internal("star-soldier-ui.json"));//sgx-ui.json
		skin = new Skin(Gdx.files.internal("Particle Park UI.json"));//sgx-ui.json



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

		keyboardGenerator = KeyboardGenerator.getKeyboardGenerator(Constants.KEYBOARD_TYPE_EXPONENTIAL_SPIRAL, Constants.INSTRUMENT_C4_LONG, width, height, skin, stage);
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

		final SelectBox<Song> songsDropDown=new SelectBox<Song>(skin);

		final TextButton playButton = new TextButton("Play",skin);
		playButton.setWidth(200);
		playButton.setHeight(50);

		playButton.setPosition(0,Gdx.graphics.getHeight()-50);

        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Commander commander = Commander.getCommander(CommanderType.PLAY, circles, songsDropDown.getSelected().getNotation());
                commander.execute();
            }
        });
		stage.addActor(playButton);

		final TextButton learnButton = new TextButton("Learn",skin);
		learnButton.setWidth(200);
		learnButton.setHeight(50);
		learnButton.setPosition(200,Gdx.graphics.getHeight()-50);




		learnButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {


				Commander commander = Commander.getCommander(CommanderType.LEARN, circles, songsDropDown.getSelected().getNotation());
				commander.execute();
			}
		});
		stage.addActor(learnButton);


		String[] markoSkaceSplit = Constants.MARKO_SKAČE_SONG.split("---");
		Song markoSkace = new Song(markoSkaceSplit[0], markoSkaceSplit[1]);

		String[] zajcekDolgouscekSplit = Constants.ZAJČEK_DOLGOUŠČEK_SONG.split("---");
		Song zajcekDolgouscek = new Song(zajcekDolgouscekSplit[0], zajcekDolgouscekSplit[1]);


		String[] kuzaPaziSplit = Constants.KUŽA_PAZI_SONG.split("---");
		Song kuzaPazi = new Song(kuzaPaziSplit[0], kuzaPaziSplit[1]);

		songsDropDown.setItems(markoSkace, zajcekDolgouscek, kuzaPazi);

		songsDropDown.setWidth(200);
		songsDropDown.setHeight(50);
		songsDropDown.setPosition(400,Gdx.graphics.getHeight()-50);


		stage.addActor(songsDropDown);

		//new

		final SelectBox<Integer> offsetDropDown=new SelectBox<Integer>(skin);

		offsetDropDown.setItems(0, 1, 2,3, -1, -2, -3);

		offsetDropDown.setWidth(100);
		offsetDropDown.setHeight(50);
		offsetDropDown.setPosition(600,Gdx.graphics.getHeight()-50);

		offsetDropDown.addListener(new EventListener() {
			@Override
			public boolean handle(Event event) {
				GlobalState.getInstance().setPitchOffset(offsetDropDown.getSelected());
				return true;
			}
		});


		stage.addActor(offsetDropDown);










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

	boolean commanderActive = false;

	int renderNum = 0;

	@Override
	public void render () {

		Gdx.gl.glClearColor(0, 0.1f, 0.2f, 1);

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		/*if(!commanderActive && renderNum > 1000){

			commanderActive = true;
			Commander commander = Commander.getCommander(CommanderType.PLAY, circles);
			commander.execute();
		}
*/


		stage.act();
		stage.draw();

		renderNum++;




	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);



		if(screenWidth!=width || screenHeight!=height) {
			disposeStage();
			fillStage(width, height);
			screenWidth=width;
			screenHeight=height;



				/*Commander commander = Commander.getCommander(CommanderType.PLAY, circles);
				commander.execute();*/


		}
	}

	@Override
	public void dispose () {
		disposeStage();

	}
}
