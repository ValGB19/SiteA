package javas.Implements;

import javas.Interfaces.AdversaryFramework.State;
import javas.Interfaces.Zombie;
import javas.Interfaces.Personage;
import javas.Interfaces.AdversaryFramework.AdversarySearchState;

class Jardin implements AdversarySearchState{
	
	private int w;
	private int h;
	private Personage[][] mapa;
	private int energiaZombie;
	private int energiaJugador;
	

	public Jardin(int j, int k){
		w = 10;
		h = 5;
		mapa = new Personage[w][h];
		energiaJugador = 500;
		energiaZombie = 500;
	}
	
	/******************************************
	 * Setters and getters
	 ******************************************/

	public int getEnergiaZombie(){
		return energiaZombie;
	}
	
	public int getEnergiaJugador(){
		return energiaJugador;
	}
	
	public void setEnergiaZombie(int e){
		energiaZombie = e;
	}
	
	public void setEnergiaJugador(int e){
		energiaJugador = e;
	}
	
	public Object[][] getMapa() {
		return mapa;
	}
	
	public int getSizeW() {
		return w;
	}
	
	public int getSizeH() {
		return h;
	}

	/** 
	 * Checks whether 'this' is equal to another state. This must be implemented
	 * by every concrete class implementing State.
	 * @param other is the state to compare 'this' to.
	 * @return true iff 'this' is equal, as a state, to 'other'.
	 * @pre. true.
	 * @post. true is returned iff 'this' is equal, as a state, to 'other'.
	 */	
    public boolean equals(State other){
    	if(other == null) return false;
    	boolean res = false;
    	if(other instanceof Jardin) {
    		Object[][] otherMap = ((Jardin) other).getMapa();
        	for (int i = 0; i < w && res; i++) {
    			for (int j = 0; j < h && res; j++) {
    				res = otherMap[w][h].equals(this.mapa[w][h]);
    			}
    		}
    	}
    	return res;
    }
  
	/** 
	 * Returns a representation as a string of the current state. This method
	 * must be implemented by all concrete classes implementing State.
	 * @return a string representing the current state.
	 * @pre. true.
	 * @post. A text representation of the current state is returned.
	 */	
	public String toString(){
		String res = "";
		for (int i = 0; i< mapa.length;i++ ) {
			res += "|";	
			for (int k = 0; k< mapa.length;k++ ) {
				if (mapa[i][k] == null) {
					res += "NN"+"|";
				}else{
					res +=  mapa[i][k].toString()+"|";
				}
			}
			res += "\n";
		}
		return res;
	}

	public boolean isMax() {
		
		
		boolean res = false;
		
		boolean empty = true;
		
		for (int i = 0; i < mapa[0].length && !res; i++) {
			res |= mapa[0][i] instanceof Zombie;
		}
		
		for (int i = 0; i < mapa.length && empty && !res; i++) {
			for (int j = 0; j < mapa[0].length && empty && !res; j++) {
				empty &= !(mapa[i][j] instanceof Zombie);
			}
		}
		
		res = (energiaZombie < new ZombieLento(5).getCosto()) && empty;
		return res;
	}

	
	public Object ruleApplied() {
		return null;
	}

	@Override
	public boolean equals(AdversarySearchState other) {
		if(other == null) return false;
    	boolean res = false;
    	if(other instanceof Jardin) {
    		Object[][] otherMap = ((Jardin) other).getMapa();
        	for (int i = 0; i < w && res; i++) {
    			for (int j = 0; j < h && res; j++) {
    				res = otherMap[w][h].equals(this.mapa[w][h]);
    			}
    		}
    	}
    	return res;
	}
}