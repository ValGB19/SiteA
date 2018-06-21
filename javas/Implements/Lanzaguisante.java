package javas.Implements;

import javas.Interfaces.Personage;
import javas.Interfaces.Planta;

public class Lanzaguisante implements Planta{
	
	private final int dano = 20;
	private final int costo = 100;
	private int vida = 60;
	
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
	
	public Personage recibeDano(int d){
		this.setVida(d);
		return this;
	}
	
	public boolean equals(Personage other){
		if (other instanceof Lanzaguisante) {
			return this.getVida() == other.getVida();
		}
		return false;
	}
}
