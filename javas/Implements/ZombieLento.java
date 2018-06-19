package javas.Implements;

import javas.Interfaces.Personage;
import javas.Interfaces.Zombie;

public class ZombieLento implements Zombie{
	
	private int vida;
	private static final int dano = 2;
	private static final int costo = 20;
	private static final int vel = 1;
	
	public ZombieLento(int vi){
		vida = vi;
	}

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

	public void reciveDano(int d) {
		vida -= d;
	}
	
	public String toString(){
		return "ZR";
	}
	
	public boolean equals(Personage other) {
		if (other instanceof ZombieLento) {
			return this.getVida() == other.getVida();
		}
		return false;
	}
}