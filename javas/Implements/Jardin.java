package javas.Implements; 

/*************************************
 * considerar la posibilidad de que jardin implemente Estado
 * una nueva clase implementara AdversarySearchState
*************************************/

import javas.Interfaces.AdversaryFramework.State;
import javas.Interfaces.Planta;
import javas.Interfaces.Zombie;
import javas.Interfaces.Personage;

public class Jardin implements State{
	
	private int w;
	private int h;
	protected Personage[][] mapa;
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

	public boolean endGame() {
		boolean res = false;
		boolean empty = true;
		
		for (int i = 0; i < mapa[0].length && !res; i++) { //algun zombie al final
			res |= mapa[i][0] instanceof Zombie;
		}
		if(res)
			return res;
		
		for (int i = 0; i < mapa.length && empty; i++) { //si quedan zombies en el mapa
			for (int j = 0; j < mapa[0].length && empty ; j++) {
				empty &= !(mapa[i][j] instanceof Zombie);
			}
		}
		
		res = (getEnergiaZombie() < new ZombieLento(5).getCosto()) && empty; 
		return res;
	}
	

	public void avanzar() {
		if(!endGame()){
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w-1; j++) {
					if (turno) {
						if(mapa[i][j] instanceof Planta) {
							if (mapa[i][j] instanceof Girasol) {
								if(((Girasol) mapa[i][j]).TopCapacSoles()==0){
									energiaJugador += ((Girasol) mapa[i][j]).TopCapacSoles();
									((Girasol) mapa[i][j]).resetCapacSoles();
								}
								((Girasol) mapa[i][j]).setCapacSoles();
								energiaJugador += ((Girasol) mapa[i][j]).TopCapacSoles();
							}else if (mapa[i][j+1] instanceof Zombie) {
								mapa[i][j+1] = ((Zombie) mapa[i][j+1]).recibeDano(mapa[i][j].getDano()); 
							}
						}
					} else {
						if(mapa[i][j] instanceof Zombie) {
							if (mapa[i][j-1] instanceof Planta) {
								mapa[i][j-1] = ((Planta) mapa[i][j-1]).recibeDano(mapa[i][j].getDano()); 
							}else{
								for (int j2 = 0; j2 < ((Zombie) mapa[i][j]).getVel() && mapa[i][j-1] == null && j > 0; j2++) {
									mapa[i][j-1] = mapa[i][j];
									mapa[i][j] = null;
								}
							}
						}
					}
				}
			}
			turno = !turno;
		}
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
  

	
	public void place(int i, int j, Personage x) {
		mapa[i][j] = x;
	}
	
	protected Jardin clone(){
		return new Jardin(w,h,mapa,energiaJugador,energiaZombie);
	}
}