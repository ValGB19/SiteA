package javas.Implements; 

/*************************************
 * considerar la posibilidad de que jardin implemente Estado
 * una nueva clase implementara AdversarySearchState
*************************************/

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
	private boolean turno = false; //false zombie true jugador
	

	public Jardin(int j, int k){
		w = 10;
		h = 5;
		mapa = new Personage[h][w];
		energiaJugador = 100;
		energiaZombie = 1500;
	}
	
	public Jardin(int i, int k, Personage[][] p, int eJ, int ez){
		w = i;
		h = k;
		mapa = p;
		energiaJugador = eJ;
		energiaZombie = ez;
	}
	
	/******************************************
	 * Setters and getters
	 ******************************************/

	public void avanzar() {
		turno = !turno; 
	}
	
	public boolean getTurno() {
		return turno;
	}
	
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
	
	public Personage[][] getMapa() {
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
    				res = otherMap[i][j].equals(this.mapa[i][j]);
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
			res |= mapa[i][0] instanceof Zombie;
		}
		
		for (int i = 0; i < mapa.length && empty && !res; i++) {
			for (int j = 0; j < mapa[0].length && empty && !res; j++) {
				empty &= !(mapa[i][j] instanceof Zombie);
			}
		}
		
		res = (energiaZombie < new ZombieLento(5).getCosto()) && empty;
		return res;
	}
	
	public void place(int i, int j, Personage x) {
		mapa[i][j] = x;
	}
	
	int i1 = 0;
	int i2 = 0;
	int ac = 0;
	
	public Object ruleApplied() {
		Jardin res = this.clone();
		res.avanzar();
		if (i1 == h) {
			return null;
		}

		if (turno) {
			
			switch (ac) {
			case 0:
				res.place(i1, i2, new Girasol());
				ac++;
				break;
			case 1:
				res.place(i1, i2, new Lanzaguisante());
				ac++;
				break;
			default:
				res.place(i1, i2, new Nuez());
				ac = 0;
				if(i2 == w-1) {
					i2 = 0;
					i1++;
				}
				break;
			}
			
		}else{
			i2 = w; 
			if (res.getMapa()[i1][i2] == null) {
				if (ac == 0)
					res.place(i1,i2,new ZombieLento()); 
				else 
					res.place(i1,i2,new ZombieRapido());
				ac = (ac == 1)? 0: 1;
			}
			i1++;
		}
		return res;
	}

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
	
	protected Jardin clone(){
		return new Jardin(w,h,mapa,energiaJugador,energiaZombie);
	}
}