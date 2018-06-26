package javas.Implements.Personage;

import javas.Interfaces.Personage;
import javas.Interfaces.Planta;

/**
 * Title:      	Girasol<p>
 * Description: implementacion de la interface Planta <p>
 * Copyright:   None <p>
 * Company:     None<p>
 * @author Grupo:  Dalessandro, Garcia, Saenz.
 * @version 0.1
 */

public class Girasol implements Planta{
	
	private final int dano = 0;
	private final int costo = 50;
	private int vida = 60;
	
	private final int capMax = 50;
	private final int sol = 25;
	private int almacen = 0;
	
	public Girasol(){
	}
	
	public Girasol(int e, int q) {
		vida = e;
		almacen = q;
	}
	
	/*public Girasol(int pena){
		capacSoles += pena;
	}*/
	
	/******************************************
	 * Setters and getters
	 ******************************************/
	
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
		vida -= d;
		if (vida > 0) 
			return this;
		return null;
	}
	
	public int energia() {
		almacen +=sol;
		if (almacen >= capMax) {
			almacen = 0;
			return capMax;
		}
		return 0;
	}
	
	public String toString() {
		return "PG";
	}
	
	public boolean equals(Personage other) {
		if (other instanceof Girasol)
			return this.getVida() == other.getVida();
		return false;
	}
	
	public Girasol clone() {
		return new Girasol(vida, almacen);
	} 
}
