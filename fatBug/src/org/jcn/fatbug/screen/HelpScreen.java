package org.jcn.fatbug.screen;



import org.jcn.fatbug.Juego;
import org.jcn.fatbug.util.Constantes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.backends.openal.Mp3.Music;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;


/**
 * 
 * @author Jose
 * Pantalla de ayuda del juego.
 */
public class HelpScreen implements Screen {
	Juego game;
	Stage stage;
	Music musica;
	public HelpScreen(Juego game) {
		this.game = game;
	}
	
	private void loadScreen() {
		
		// Grafo de escena que contiene la pantalla de ayuda
		stage = new Stage();
		
	
		Table table = new Table();
		table.setPosition(Constantes.SCREEN_WIDTH / 3.8f, Constantes.SCREEN_HEIGHT / 1.5f);		
	    table.setFillParent(true);
	    table.setHeight(500);
	    stage.addActor(table);
		
	    // Etiqueta de texto
		Label label = new Label("FATBUG, MENU DE AYUDA: \n\n"
							+ 	"Mueve a FatBug por la pantalla \n"
							+	"usando las flechas del teclado y \n"
							+ 	"tratando de conseguir llegar \n"
							+ 	"hasta el final mientras esquivas \n"
							+ 	"los obstaculos. Las tartas, \n"
							+ 	"cervezas y croissants te suman \n"
							+ 	"puntos!"
							+ 	"", game.getSkin());
		table.addActor(label);
		
		TextButton buttonMainMenu = new TextButton("Volver", game.getSkin());
		buttonMainMenu.setPosition(label.getOriginX(), label.getOriginY() - 100);
		buttonMainMenu.setWidth(200);
		buttonMainMenu.setHeight(40);
		buttonMainMenu.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;	
			}
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {				
				
				dispose();
				game.setScreen(new MainMenuScreen(game));
			}
		});
		table.addActor(buttonMainMenu);
		
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
