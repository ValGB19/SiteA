package javas.Implements;

import javas.Interfaces.Personage;
import javas.Interfaces.Planta;

public class Nuez implements Planta{
	
	private final int dano = 10;
	private final int costo = 15;
	private int vida = 120;
	
	public int getVida(){
		return vida;
	}
	
	public void setVida(int e){
		vida = e;
	}
	
	public int getCosto(){
		return costo;
	}
	
	public int getDano(){
		return dano;
	}
	
	public void recibeDano(int d){
		vida = vida - d;
	}

	public boolean equals(Personage other) {
		if (other instanceof Nuez) {
			return this.getVida() == other.getVida();
		}
		return false;
	}
}
