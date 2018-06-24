package javas.Implements.Personage;

import javas.Interfaces.Personage;
import javas.Interfaces.Planta;

/**
 * Title:       Lanzaguisante<p>
 * Description: implementacion de la interface Planta <p>
 * Copyright:   None <p>
 * Company:     None<p>
 * @author Grupo:  Dalessandro, Garcia, Saenz.
 * @version 0.1
 */

public class Lanzaguisante implements Planta{
	
	private final int dano = 20;
	private final int costo = 100;
	private int vida = 60;
	
	public Lanzaguisante(int n) {
		vida=n;
	}
	
	public Lanzaguisante() {

	}

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
	
	/** 
	 * Representa la accion de cuando el lanzaguisante recibe dano de parte de un zombie
	 * @pre. true.
	 * @post. Retorna el mismo Personage con menos vida en caso que la vida > 0
	 * luego de descontar el dano, de lo contrario retorna null.
	 * @return Retorna el mismo Personage o null respectivamente.
	 */
	public Personage recibeDano(int d){
		vida -= d;
		if (vida > 0) {
			return this;
		}
		return null;
	}
	
	public String toString() {
		return "PL";
	}
	
	public boolean equals(Personage other){
		if (other instanceof Lanzaguisante) {
			return this.getVida() == other.getVida();
		}
		return false;
	}
	
	public Lanzaguisante clone() {
		return new Lanzaguisante(vida);
	} 
}
