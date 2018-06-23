package javas.Implements;

import javas.Interfaces.Personage;
import javas.Interfaces.AdversaryFramework.AdversarySearchState;

public class JardinExt extends Jardin implements AdversarySearchState{
	
	public JardinExt(int i, int k, Personage[][] p, int eJ, int ez) {
		super(i, k, p, eJ, ez);
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

	protected JardinExt clone(){
		return new JardinExt(getSizeW(),getSizeH(),mapa,getEnergiaJugador(),getEnergiaZombie());
	}
}
