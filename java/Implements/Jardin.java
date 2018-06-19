package java.Implements;

import java.Interfaces.AdversaryFramework.AdversarySearchState;
import java.Interfaces.AdversaryFramework.State;

class Jardin implements AdversarySearchState{
	
	private int w;
	private int h;
	private Character[][] mapa;
	private int energiaZombie;
	private int energiaJugador;
	

	public Jardin(int j, int k){
		w = j;
		h = k;
		mapa = new Character[w][h];
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
    	boolean res = other instanceof Jardin && other.getSizeH() == this.h && other.getSizeW() == this.w;
    	Object[][] otherMap = other.getMapa();
    	for (int i = 0; i < w && res; i++) {
			for (int j = 0; j < h && res; j++) {
				res = otherMap[w][h].equals(this.mapa[w][h]);
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
		return false;
	}

	public boolean equals(AdversarySearchState other) {
		return false;
	}

	public Object ruleApplied() {
		return null;
	}
}