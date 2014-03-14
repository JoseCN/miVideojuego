package org.jcn.fatbug.manager;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class ResourceManager {

	private static Map<String, Animation> animations = new HashMap<String, Animation>();
	private static Map<String, TextureAtlas> atlas = new HashMap<String, TextureAtlas>();
	
	public static void cargarTodosRecursos(){
		
		atlas.put("objetos", new TextureAtlas(Gdx.files.internal("objetos.atlas")));
		
		animations.put("arriba", new Animation(0.25f, 
				atlas.get("objetos").findRegion("arriba1"),
				atlas.get("objetos").findRegion("arriba2")));
		
		animations.put("abajo", new Animation(0.25f, 
				atlas.get("objetos").findRegion("abajo1"),
				atlas.get("objetos").findRegion("abajo2")));
		
		animations.put("derecha", new Animation(0.25f, 
				atlas.get("objetos").findRegion("derecha1"),
				atlas.get("objetos").findRegion("derecha2")));
		
		animations.put("izquierda", new Animation(0.25f, 
				atlas.get("objetos").findRegion("izquierda1"),
				atlas.get("objetos").findRegion("izquierda2")));
		
		
	}
	
	public static Animation getAnimation(String name) {
		
		return animations.get(name);
	}
	
	public static TextureAtlas getAtlas(String name) {
		
		return atlas.get(name);
	}
	
}
