package org.jcn.fatbug.caracter;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Caracter {

	public Vector2 posicion;
	public float speed;
	
	public Animation animacion;
	public TextureRegion currentFrame;
	public float stateTime;
	public Rectangle rect;
	
	
	public Caracter(Animation animacion, float x, float y, float velocidad){
		
		posicion = new Vector2(x, y);
		this.animacion = animacion;
		speed = velocidad;
		
	}
	
	public Caracter(Texture texture, float x, float y, float velocidad) {
		
		currentFrame = new TextureRegion(texture);
		posicion = new Vector2(x, y);
		speed = velocidad;
		
		rect = new Rectangle(posicion.x+2, posicion.y+2, currentFrame.getRegionWidth()-4, currentFrame.getRegionHeight()-4);
	}
	
	

	public void render(SpriteBatch batch){
		
		batch.draw(currentFrame, posicion.x, posicion.y);
	}
	
	public void update(float dt){
		
		stateTime += dt;
		if(animacion != null){
			currentFrame = animacion.getKeyFrame(stateTime, true);
		}
		
		if(rect == null){
			rect = new Rectangle(posicion.x, posicion.y, currentFrame.getRegionWidth(), currentFrame.getRegionHeight());
		}
		rect.x = posicion.x;
		rect.y = posicion.y;
		
	}

	
	
}
