package javas.Implements;

import javas.Interfaces.Personage;
import javas.Interfaces.Planta;

public class Girasol implements Planta{
	
	private final int dano = 0;
	private final int costo = 50;
	private int vida = 60;
	private int sol = 25;
	private int capacSoles = 50;
	
//valu
	
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
	
	public int getSol() {
		return sol;
	}
	
	public void setSol(int n) {
		sol = n;
	}
	
	public int getCapacSoles() {
		return capacSoles;
	}
	
	public void setCapacSoles(int n) {
		capacSoles = n;
	}
	
	public void resetCapacSoles() {
		capacSoles=50;
	}
	
	public int TopCapacSoles() {
		return  (capacSoles==0 ? 50 : 0);
	}
	
	public void restCapacSoles() {
		capacSoles=capacSoles-sol;
	}
	
	public Personage recibeDano(int d){
		this.setVida(d);
		return this;
	}
	
	public boolean equals(Personage other) {
		if (other instanceof Girasol) {
			return this.getVida() == other.getVida();
		}
		return false;
	}
}
