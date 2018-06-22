package javas.Interfaces;

/**
 * Title:       Zombie<p>
 * Description: Interface que define la estructura basica para representar 
 * para representar a los Zombies.
 * Facilita el filtrado con el instanceof.
 * <p>
 * Copyright:   None <p>
 * Company:     None<p>
 * @author Grupo:  Dalessandro Garcia, Saenz.
 * @version 0.1
 */

public interface Zombie extends Personage{
	
	/** 
	 * Obtiene la velocidad de movimiento del Zombie..
	 * @pre. true.
	 * @post. Retorna un entero representando la cantidad de casilleros
	 *  que avanza por turno (como maximo).
	 * @return Un integer
	 */	
	public int getVel();
}