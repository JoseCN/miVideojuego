package org.jcn.fatbug.screen;

import org.jcn.fatbug.Juego;
import org.jcn.fatbug.screen.PantallaJuego.MenuOption;
import org.jcn.fatbug.util.Constantes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * Pantalla principal del juego, donde se muestra el menu principal
 * @author Jose
 * @version 1.0
 *
 */
public class MainMenuScreen implements Screen {

	Juego game;
	Stage stage;
	Music musica;
	
	public MainMenuScreen(Juego game) {
		this.game = game;
		
	}
	
	private void loadScreen() {
		musica = Gdx.audio.newMusic(Gdx.files.internal("Orquesta.mp3"));
		// Grafo de escena que contendrá todo el menú
		stage = new Stage();
					
		// Crea una tabla, donde añadiremos los elementos de menú
		Table table = new Table();
		table.setPosition(Constantes.SCREEN_WIDTH / 3.3f, Constantes.SCREEN_HEIGHT);
		// La tabla ocupa toda la pantalla
	    table.setFillParent(true);
	    table.setHeight(500);
	    stage.addActor(table);
		
	    // Etiqueta de texto
		Label label = new Label("Bienvenido a FatBugger", game.getSkin());
		table.addActor(label);
		
		// Boton de ayuda
		TextButton buttonPlay = new TextButton("Ayuda", game.getSkin());
		buttonPlay.setPosition(label.getOriginX() - 60, label.getOriginY() - 70);
		buttonPlay.setWidth(300);
		buttonPlay.setHeight(40);
		buttonPlay.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				
				dispose();
				game.setScreen(new HelpScreen(game));
			}
		});
		table.addActor(buttonPlay);
		
		// Boton de jugar
		TextButton buttonHistory = new TextButton("Jugar", game.getSkin());
		buttonHistory.setPosition(label.getOriginX() - 60, label.getOriginY() - 120);
		buttonHistory.setWidth(300);
		buttonHistory.setHeight(40);
		buttonHistory.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				
				dispose();				
				game.setScreen(new PantallaJuego(game, MenuOption.PLAY));
			}
		});
		table.addActor(buttonHistory);		
		
		
		// Botón
		TextButton buttonQuit = new TextButton("Salir", game.getSkin());
		buttonQuit.setPosition(label.getOriginX() - 60, label.getOriginY() - 170);
		buttonQuit.setWidth(300);
		buttonQuit.setHeight(40);
		buttonQuit.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;	
			}
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				
				game.dispose();
				System.exit(0);
			}
		});
		table.addActor(buttonQuit);
		
		Gdx.input.setInputProcessor(stage);
	}
	
	@Override
	public void show() {

		loadScreen();
	}
	
	@Override
	public void render(float dt) {

		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        
		// Pinta el menu
		stage.act(dt);
		stage.draw();
	}
	
	@Override
	public void dispose() {

		stage.dispose();
	}

	@Override
	public void hide() {

		
	}

	@Override
	public void pause() {

		
	}

	

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
}
