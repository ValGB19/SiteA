package java.Implements;

import java.Interfaces.Zombie;

public class ZombieRapido implements Zombie{
	
	private int vida;
	private static final int dano = 1;
	private static final int costo = 20; // ver consigna
	private static final int vel = 2;
	
	public ZombieRapido(int vi){
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
}