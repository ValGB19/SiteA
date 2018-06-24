package javas.Implements.Personage;

import javas.Interfaces.Personage;
import javas.Interfaces.Planta;

public class Girasol implements Planta{
	
	private final int dano = 0;
	private final int costo = 50;
	private int vida = 60;
	private int sol = 25;
	private int capacSoles = 50;
	
//valu
	
	public Girasol(){
		
	}

	public Girasol(int pena){
		capacSoles += pena;
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
	
	public int getSol() {
		return sol;
	}
	
	/** 
	 * Metodo que setea el valor que tendra un sol producido por un girasol
	 * @param n nuevo valor de sol 
	 * @pre. true.
	 * @post. Sol tiene valor n
	 */
	public void setSol(int n) {
		sol = n;
	}
	
	/** 
	 * Metodo que retorna la capacidad actual que tiene el girasol.
	 * Utilizada generalmente para verificar si el girasol llego al umbral maximo de soles acumulados
	 * @pre. true.
	 * @post. retorna la capacidad que tiene el girasol de acumular soles
	 * @return return int que representa la capacidad que tiene el girasol de acumular soles
	 */
	public int getCapacSoles() {
		return capacSoles;
	}
	
	/** 
	 * Metodo que representa la produccion de un sol donde a la capacSoles se le resta el valor un sol
	 * Utilizada generalmente para verificar si el girasol llego al umbral maximo de soles acumulados
	 * @pre. true.
	 * @post. resta capacidad de acumular soles al girasol
	 */
	public void setCapacSoles() {
		capacSoles -= sol;
	}
	
	/** 
	 * Metodo que cuando el girasol llego a su umbral maximo de acumulamiento de soles,
	 * (capacSoles==0), luego de dar la energia acumulada al jugador, resetea la capacidad
	 * de acumulamiento del girasol en 50<p>
	 * @pre. true.
	 * @post. reset capacSoles en 50
	 */
	public void resetCapacSoles() {
		capacSoles=50;
	}
	
	/** 
	 * Verifica si el girasol llego a su umbral maximo de acumulamiento de soles
	 * y retorna la energia para entregar al jugador<p>
	 * @pre. true.
	 * @post. retorna la energia para entregar al jugador. 50 si se llego al umbral maximo.
	 * @return integer que representa la energia.
	 */
	public int TopCapacSoles() {
		return  (capacSoles==0 ? 50 : 0);
	}
	
	/** 
	 * Representa la accion de cuando el Girasol recibe dano de parte de un zombie
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
		return "PG";
	}
	
	public boolean equals(Personage other) {
		if (other instanceof Girasol) {
			return this.getVida() == other.getVida();
		}
		return false;
	}
}
