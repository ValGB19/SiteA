package javas.Implements.Personage;

import javas.Interfaces.Personage;
import javas.Interfaces.Zombie;

/**
 * Title:       ZombieLento<p>
 * Description: implementacion de la interface Zombie <p>
 * Copyright:   None <p>
 * Company:     None<p>
 * @author Grupo:  Dalessandro, Garcia, Saenz.
 * @version 0.1
 */


public class ZombieLento implements Zombie{
	
	private int vida=100;
	private static final int dano = 30;
	private static final int costo = 75;
	private static final int vel = 1;
	
	public ZombieLento(){
	}
	
	public ZombieLento(int vi){
		vida = vi;
	}

	/******************************************
	 * Setters and getters
	 ******************************************/
	public int getVida() {
		return vida;
	}

	public void setVida(int e) {
		vida = e;
	}

	public int getCosto() {
		return costo;
	}

	public int getDano() {
		return dano;
	}

	public int getVel() {
		return vel;
	}

	/** 
	 * Representa la accion de cuando el zombie recibe dano de parte de una planta
	 * @pre. true.
	 * @post. Retorna el mismo Personage con menos vida en caso que la vida > 0
	 * luego de descontar el dano, de lo contrario retorna null.
	 * @return Retorna el mismo Personage o null respectivamente.
	 */
	public Personage recibeDano(int d) {
		vida -= d;
		if (vida > 0) {
			return this;
		}
		return null;
	}
	
	public String toString(){
		return "ZL";
	}
	
	public boolean equals(Personage other) {
		if (other instanceof ZombieLento) {
			return this.getVida() == other.getVida();
		}
		return false;
	}
	
	public ZombieLento clone(){
		return new ZombieLento(vida);
	}
}