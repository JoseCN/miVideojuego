package org.jcn.fatbug.caracter;

import org.jcn.fatbug.util.Util;

import com.badlogic.gdx.graphics.Texture;


public class Objetos extends Caracter{
	
	private Util.Tipo tipo;
	
	public Objetos(Texture texture, float x, float y, float velocidad) {
		super(texture, x, y, velocidad);
	}

	public Util.Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Util.Tipo tipo) {
		this.tipo = tipo;
	}
}
