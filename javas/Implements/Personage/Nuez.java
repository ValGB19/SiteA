package javas.Implements.Personage;

import javas.Interfaces.Personage;
import javas.Interfaces.Planta;

public class Nuez implements Planta{
	
	private final int dano = 10;
	private final int costo = 75;
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
	
	/** 
	 * Representa la accion de cuando la nuez recibe dano de parte de un zombie
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
		return "PN";
	}

	public boolean equals(Personage other) {
		if (other instanceof Nuez) {
			return this.getVida() == other.getVida();
		}
		return false;
	}
}
