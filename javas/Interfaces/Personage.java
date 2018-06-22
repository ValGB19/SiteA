package javas.Interfaces;

/**
 * Title:       Personage<p>
 * Description: Interface que define la estructura basica de los elementos
 * del jardin. 
 * Los elementos que esten en el jardin deben implementar esta interface.
 * <p>
 * Copyright:   None <p>
 * Company:     None<p>
 * @author Grupo:  Dalessandro Garcia, Saenz.
 * @version 0.2
 */
 

public interface Personage{
	
	abstract int getVida();

	abstract void setVida(int e);
	
	abstract int getCosto();
	
	abstract int getDano();
	
	abstract boolean equals(Personage other);
	
	public Personage recibeDano(int d);
}