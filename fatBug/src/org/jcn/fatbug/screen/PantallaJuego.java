package org.jcn.fatbug.screen;

import org.jcn.fatbug.Juego;
import org.jcn.fatbug.manager.ResourceManager;
import org.jcn.fatbug.manager.SpriteManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;

public class PantallaJuego implements Screen{

	Juego juego;
	SpriteManager spriteManager;
	
	Texture fondo;
	Music musica;
	// Indica las opciones del menú
	public enum MenuOption {
		HELP, PLAY
	}
	
	
	public PantallaJuego(Juego juego, MenuOption option){		
		this.juego = juego;		
		fondo = new Texture(Gdx.files.internal("background.png"));
		musica = Gdx.audio.newMusic(Gdx.files.internal("Orquesta.mp3"));
		ResourceManager.cargarTodosRecursos();		
		spriteManager = new SpriteManager(juego);		
	}

	@Override
	public void render(float dt) {		
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);		
		spriteManager.update(dt);
		spriteManager.render(juego.batch);		
	}
	
	@Override
	public void dispose() {		
	}
	
	@Override
	public void show() {			
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
	
}
