package javas.Implements;

import javas.Interfaces.Personage;
import javas.Interfaces.AdversaryFramework.AdversarySearchState;
import javas.Interfaces.AdversaryFramework.State;

public class JardinExt  extends Jardin implements AdversarySearchState{
	
	public JardinExt(int i, int k, Personage[][] p, int eJ, int ez) {
		super(i, k, p, eJ, ez);
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
		return endGame();
	}

	public Jardin ruleApplied() {
		return null;
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

	@Override
	public boolean equals(State other) {
		// TODO Auto-generated method stub
		return false;
	}
}
