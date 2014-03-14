package org.jcn.fatbug.caracter;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

public class Enemigos extends Caracter{

	private boolean tranvia;
	
	public Enemigos(Texture texture, float x, float y, float velocidad) {
		super(texture, x, y, velocidad);
		
	}

	public boolean isTranvia() {
		return tranvia;
	}

	public void setTranvia(boolean tranvia) {
		this.tranvia = tranvia;
	}
	
}
