package javas.Implements;

import javas.Interfaces.Personage;
import javas.Interfaces.AdversaryFramework.AdversarySearchState;

/**
 * Title:       JardinExt<p>
 * Description: Implementacion de la interface State <p>
 * Copyright:   None <p>
 * Company:     None<p>
 * @author Grupo:  Dalessandro, Garcia, Saenz.
 * @version 0.1
 */

public class JardinExt extends Jardin implements AdversarySearchState{
	
	Jardin padre;
	
	public JardinExt() {
		padre = null;
	}
	
	public JardinExt(Jardin p) {
		padre = p;
	}
	
	public void setPadre(Jardin p){
		padre = p;
	}
	
	/** 
	 * Constructor utilizado para que clonar sea mas facil
	 * @pre. true.
	 * @post. true
	 */
	private JardinExt(Personage[][] ma, int ej, int ez) {
		mapa = ma;
		energiaJugador = ej;
		energiaZombie = ez;
		padre = null;
	}

	/** 
	 * Retorna true si el estado es un estado final.
	 * @pre. true.
	 * @post. Retorna true si el estado es un estado final, sino false.
	 * @return Retorna true o false dependiendo si es un estado final.
	 */
	public boolean isMax() {
		return endGame();
	}
	
	/** 
	 * Returns an object representing the rule applied, leading to the
	 * current state. 
	 * @return an object representing the rule applied, leading to the
	 * current state. If the state is the initial state, then null is 
	 * returned.
	 * @pre. true.
	 * @post. An object representing the rule applied, leading to the
	 * current state, is returned. If the state is the initial state, 
	 * then null is returned.
	 */
	public Jardin ruleApplied() {
		return padre;
	}

	public boolean equals(AdversarySearchState other) {
		if(other == null) return false;
    	boolean res = false;
    	if(other instanceof Jardin) {
    		Object[][] otherMap = ((Jardin) other).getMapa();
        	for (int i = 0; i < getSizeW() && res; i++) {
    			for (int j = 0; j < getSizeH() && res; j++) {
    				res = otherMap[getSizeW()][getSizeH()].equals(this.mapa[getSizeW()][getSizeH()]);
    			}
    		}
    	}
    	return res;
	}

	protected JardinExt clone(){
		return new JardinExt(mapa.clone(),getEnergiaJugador(),getEnergiaZombie());
	}
}
