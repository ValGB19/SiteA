package javas.Implements; 

import javas.Interfaces.AdversaryFramework.State;
import javas.Implements.Personage.*;
import javas.Interfaces.*;

/**
 * Title:       Jardin<p>
 * Description: implementacion de la interface State <p>
 * Copyright:   None <p>
 * Company:     None<p>
 * @author Grupo:  Dalessandro, Garcia, Saenz.
 * @version 0.1
 */

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
	
	public Jardin(){
		w = 10;
		h = 5;
		mapa = new Personage[h][w];
		energiaJugador = 100;
		energiaZombie = 1500;
	}
	
	/** 
	 * Constructor de la clase Jardin
	 * @param i es la cantidad de carriles del jardin
	 * @param j es la cantidad de canteros por carril
	 * @param p es la matriz para representar el jardin
	 * @param eJ es la cantidad de energia con la que el jugador inicia una partida
	 * @param ez es la can
	 * @pre. true.
	 * @post. inicializa w con el valor de i, h con el valor de j, mapa con la matriz p, energiaJugador con eJ y energiaZombie con ez
	 */
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

	/** 
	 * Determina si el estado representa el final de una partida
	 * @pre. true.
	 * @post. Retorna true si es un estado final
	 * @return Retorna un boolean representando si es un estado de final de partida
	 */
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
	
	/** 
	 * Se encarga de avanzar un estado al siguiente moviendo los zombies o accionando 
	 * a las plantas respectivamente del turno y cambia la variable turno al finalizar,
	 * solo si no es un estado de final de partida<p>
	 * @pre. true.
	 * @post. ejecuta el turno del jugador o el maestro zombie segun corresponda
	 */
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
  

    /** 
	 * Ubica una planta o zombie en la matriz en la posicion [i][j]
	 * @param i es el carril donde se ubicara el personaje
	 * @param j es el cantero del carril donde se ubicara el personaje
	 * @param x es el personaje a ubicar
	 * @pre. true.
	 * @post. ubica x en la posicion mapa[i][j] 
	 */
	public void place(int i, int j, Personage x) {
		mapa[i][j] = x;
	}
	
	/** 
	 * Returns a representation as a string of the current state. This method
	 * must be implemented by all concrete classes implementing State.
	 * @return a string representing the current state.
	 * @pre. true.
	 * @post. A text representation of the current state is returned.
	 */	
	public String toString(){
		String res = "energia Jugador: "+energiaJugador+"\n"+"Energia Zombie: "+energiaZombie+"\n";
		for (int i = 0; i< mapa.length;i++ ) {
			res += "|";	
			for (int k = 0; k< mapa[0].length;k++ ) {
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
	
	protected Jardin clone(){
		return new Jardin(w,h,mapa,energiaJugador,energiaZombie);
	}
}