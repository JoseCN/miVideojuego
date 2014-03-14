package org.jcn.fatbug.manager;

import java.util.Iterator;

import org.jcn.fatbug.Juego;
import org.jcn.fatbug.caracter.Enemigos;
import org.jcn.fatbug.caracter.Objetos;
import org.jcn.fatbug.caracter.Personajes;
import org.jcn.fatbug.util.Util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.sun.corba.se.spi.orbutil.fsm.State;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

public class SpriteManager {

	Personajes personaje;
	Objetos objeto;
	Array<Enemigos> enemigos;
	
	private long unEnemigo;
	private long unMisil;
	private long unBonus;
	private long timeRuedas = 900;
	
	private float velocidad = 150f;
	private float velocidadPersonaje = 100f;
	
	private int cont = 0;
	
	private boolean bonus;
	
	Juego juego;
	private Texture fondo;
	Sound sonidoChirriante;
	Sound sonidoayuken;
	Sound sonidoBonus;
	Sound sonidoCruce;
	
	public SpriteManager(Juego juego){
		
		this.juego = juego;
		generarPersonaje();
		enemigos = new Array<Enemigos>();
		
		fondo = new Texture(Gdx.files.internal("background.png"));
		sonidoayuken = Gdx.audio.newSound(Gdx.files.internal("ayuken.mp3"));
		sonidoBonus = Gdx.audio.newSound(Gdx.files.internal("bonus.wav"));
		sonidoCruce = Gdx.audio.newSound(Gdx.files.internal("Wololo.wav"));
		
	}
	
	public void render(SpriteBatch batch) {
		
		batch.begin();
		
		juego.batch.draw(fondo, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		//juego.font.draw(juego.batch, "Salvados: " + juego.salvados, 10, 710);
		//juego.font.draw(juego.batch, "Perdidos: " + juego.perdidos, 10, 690);
		juego.font.draw(juego.batch, "Puntos: " + juego.puntos, 260, 700);
		juego.font.draw(juego.batch, "Tiempo: " + juego.tiempo, 500, 700);
		
		if(bonus){
			objeto.render(batch);
		}
		
		personaje.render(batch);
		for (Enemigos enemy : enemigos)
			enemy.render(batch);
		batch.end();
	}
	
	public void update(float dt) {
		juego.tiempo -= Gdx.graphics.getDeltaTime();
		
		/*if(juego.tiempo <= 0){
			juego.setScreen(new GameOver(juego));
		}*/
		
		if(TimeUtils.millis() - unEnemigo > timeRuedas){
			generarEnemigoAbajo();
			//generarEnemigoArriba();
			
			unEnemigo = TimeUtils.millis();
		}
		
		if(TimeUtils.millis() - unMisil > 1200){
			generarMisil();
		}
		
		if(TimeUtils.millis() - unBonus > 15000 && !bonus){
			generarObjetos();
		}
		
		if(personaje.posicion.y > 690){
			sonidoCruce.play();
			juego.salvados += 1;
			juego.puntos += 50;
			generarPersonaje();
		}
		
		personaje.update(dt);
		
		for (int i = enemigos.size - 1; i >= 0; i--){
			enemigos.get(i).posicion.x += enemigos.get(i).speed * Gdx.graphics.getDeltaTime();
			enemigos.get(i).update(dt);
			
			if(enemigos.get(i).posicion.x < 0 - enemigos.get(i).currentFrame.getRegionWidth() || enemigos.get(i).posicion.x > 600){
				enemigos.removeIndex(i);
			}
			
		}
		
		teclado(dt);
		colisiones();
		
	}
	
	private void teclado(float dt){
		
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			personaje.state = personaje.state.LEFT;
			personaje.posicion.x += personaje.speed * -dt;
		}
		else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			personaje.state = personaje.state.RIGHT;
			personaje.posicion.x += personaje.speed * dt;
		}
		else if (Gdx.input.isKeyPressed(Keys.UP)) {
			personaje.state = personaje.state.UP;
			personaje.posicion.y += personaje.speed * dt;
		}
		else if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			personaje.state = personaje.state.DOWN;
			personaje.posicion.y += personaje.speed * -dt;
		}
		else 
			personaje.state = personaje.state.IDLE;
		
	}
	
	private void colisiones(){
		
		for(Enemigos enemigo: enemigos){
			if(enemigo.rect.overlaps(personaje.rect)){
				juego.perdidos += 1;
				juego.puntos -= 20;
				generarPersonaje();
				cont += 1;
				if(enemigo.isTranvia()){
					sonidoayuken.play();
				}
				else{
					sonidoChirriante.play();
				}
			}
		}
		
		if(personaje.rect.overlaps(objeto.rect) && bonus){
			sonidoBonus.play();
			juego.puntos += 10;
			switch(objeto.getTipo()){
			case RAYO:
				personaje.setTipo(objeto.getTipo());
				personaje.setSpeed(personaje.getSpeed() + 20);
				velocidadPersonaje += 20;
				break;
			case RELOJ:
				personaje.setTipo(objeto.getTipo());
				juego.tiempo += 15;
				break;
			case SEÑAL50:
				personaje.setTipo(objeto.getTipo());
				timeRuedas += 80*cont;
				for(Enemigos vehiculo: enemigos){
					vehiculo.speed -= 25;
				}
				velocidad -= 25;
				break;
			case SEÑAL60:
				personaje.setTipo(objeto.getTipo());
				timeRuedas += 60*cont;
				for(Enemigos vehiculo: enemigos){
					vehiculo.speed -= 20;
				}
				velocidad -= 20;
				break;
			case SEÑAL80:
				personaje.setTipo(objeto.getTipo());
				timeRuedas += 30*cont;
				for(Enemigos enemigo: enemigos){
					enemigo.speed -= 10;
				}
				velocidad -= 10;
				break;
				default:
				break;
			}
			bonus = false;
			unBonus = TimeUtils.millis();
		}
		
	}
	
	private void generarPersonaje(){
		
		
			personaje = new Personajes(new Texture("fatSpritesD.png"), 300 - 16, 10, velocidadPersonaje);
		
		
		
		
	}
	
	private void generarObjetos(){
		
		int numbonus = (int) (Math.random()*3+1);
		
		float posx = (float) (Math.random()*460+50);
		float posy = (float) (Math.random()*480+130);
		
		switch(numbonus){
		case 1:
			objeto = new Objetos(new Texture("croissant.png"), posx, posy, 0f);
			objeto.setTipo(Util.Tipo.RAYO);
			break;
		case 2:
			objeto = new Objetos(new Texture("cerveza.png"), posx, posy, 0f);
			objeto.setTipo(Util.Tipo.RELOJ);
			break;
		case 3:
			cont += 1;
			int num = (int) (Math.random()*3+1);
			
			switch(num){
			case 1:
				objeto = new Objetos(new Texture("pastel.png"), posx, posy, 0f);
				objeto.setTipo(Util.Tipo.SEÑAL50);
				break;
			case 2:
				objeto = new Objetos(new Texture("pastel.png"), posx, posy, 0f);
				objeto.setTipo(Util.Tipo.SEÑAL60);
				break;
			case 3:
				objeto = new Objetos(new Texture("pastel.png"), posx, posy, 0f);
				objeto.setTipo(Util.Tipo.SEÑAL80);
				break;
				default:
					break;
			}
			break;
			default:
				break;
		}
		bonus = true;
	}
	
	private void generarEnemigoAbajo(){
		
		int carril = (int) (Math.random()*3+1);
		int num = (int) (Math.random()*100+1);
		
		Enemigos enemigo = null;
		
		switch(carril){
		case 1:
			enemigo = rellenarAbajo(num, 120f);
			break;
		case 2:
			enemigo = rellenarAbajo(num, 180f);
			break;	
		case 3:
			enemigo = rellenarAbajo(num, 240f);
			break;
		}
		enemigo.setTranvia(false);
		enemigos.add(enemigo);
	}
	
	private Enemigos rellenarAbajo(int num, float y){
		Enemigos enemigo = null;
		
		if(num < 15){
			enemigo = new Enemigos(new Texture("rueda.png"), (0f - 64), y, velocidad);
			return enemigo;
		}
		
		if(num < 75){
			enemigo = new Enemigos(new Texture("sumo.png"), (0f - 64), y, velocidad);
			return enemigo;
		}
		if(num < 101){
			enemigo = new Enemigos(new Texture("pirana.png"), (0f - 64), y, velocidad);
			return enemigo;
		}
		return enemigo;
	}
	
	private void generarEnemigoArriba(){
		
		int carril = (int) (Math.random()*3+1);
		int num = (int) (Math.random()*100+1);
		
		Enemigos enemigo = null;
		
		switch(carril){
		case 1:
			enemigo = rellenarArriba(num, 490f);
			break;
		case 2:
			enemigo = rellenarArriba(num, 550f);
			break;
		case 3:
			enemigo = rellenarArriba(num, 610f);
			break;
		}
		enemigo.setTranvia(false);
		enemigos.add(enemigo);
		
	}
	
	private Enemigos rellenarArriba(int num, float y){
		Enemigos enemigo = null;
		
		if(num < 15){
			enemigo = new Enemigos(new Texture("sumo.png"), 600, y, -velocidad);
			return enemigo;
		}
		if(num < 50){
			enemigo = new Enemigos(new Texture("rueda.png"), 600, y, -velocidad);
			return enemigo;
		}
		if(num < 75){
			enemigo = new Enemigos(new Texture("misil.png"), 600, y, -velocidad);
			return enemigo;
		}
		if(num < 101){
			enemigo = new Enemigos(new Texture("rueda.png"), 600, y, -velocidad);
			return enemigo;
		}
		return null;
	}

	private void generarMisil(){
		
		int carril = (int) (Math.random()*2+1);
		
		Enemigos enemigo = null;
		
		switch(carril){
		case 1:
			enemigo = new Enemigos(new Texture("misil2.png"), 0 - 256, 300f, 250f);
			break;
			
		case 2:
			enemigo = new Enemigos(new Texture("misil.png"), 600, 430f, -250f);
			break;
		}
		enemigo.setTranvia(true);
		enemigos.add(enemigo);
	
		unMisil = TimeUtils.millis();
	}

}
