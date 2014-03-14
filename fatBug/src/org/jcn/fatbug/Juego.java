package org.jcn.fatbug;

import org.jcn.fatbug.screen.MainMenuScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Juego extends Game {
	
	public SpriteBatch batch;
	public BitmapFont font;
	OrthographicCamera camera;
	
	private Skin skin;	
	
	public int salvados = 0;
	public int perdidos = 0;
	public float tiempo = 100;
	public int puntos = 0;
	
	@Override
	public void create() {		
		
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.GREEN);
		camera = new OrthographicCamera();
		camera.setToOrtho(true, 600, 720);
		camera.update();
		
		Texture.setEnforcePotImages(false);
		
		setScreen(new MainMenuScreen(this));
	}

	@Override
	public void dispose() {
		
		batch.dispose();
		font.dispose();
	}
	
	public Skin getSkin() {
        if(skin == null ) {
            skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        }
        return skin;
    }	

	@Override
	public void render() {		
		super.render();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
