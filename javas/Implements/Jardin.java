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
	
	private int c;
	private int f;
	private Personage[][] mapa;
	private int energiaZombie;
	private int energiaJugador;
	private boolean turno = false; //false zombie true jugador
	
	public Jardin(){
		c = 10;
		f = 5;
		mapa = new Personage[f][c];
		energiaJugador = 100;
		energiaZombie = 1500;
	}
	
	/** 
	 * Constructor de la clase Jardin
	 * @param p es la matriz para representar el jardin
	 * @param eJ es la cantidad de energia con la que el jugador inicia una partida
	 * @param ez es la can
	 * @pre. true.
	 * @post. inicializa el mapa con la matriz p, energiaJugador con eJ y energiaZombie con ez
	 */
	public Jardin(Personage[][] p, int eJ, int ez){
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
	
	public void changeTurno() {
		turno = !turno;
	}
	
	public void setEnergiaJugador(int e){
		energiaJugador = e;
	}
	
	public void setMapa(Personage[][] j) {
		mapa = j;
	}
	
	public int getSizeF() {
		return f;
	}
	
	public int getSizeC() {
		return c;
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
		
		for (int i = 0; i < mapa.length && !res; i++) { //algun zombie al final
			res = mapa[i][0] instanceof Zombie;
		}
		if(res)
			return res;
		
		for (int i = 0; i < mapa.length && empty; i++) //si quedan zombies en el mapa
			for (int j = 0; j < mapa[0].length && empty ; j++)
				empty = !(mapa[i][j] instanceof Zombie);
		
		res = (getEnergiaZombie() < new ZombieLento().getCosto()) && empty; 
		return res;
	}

	public Personage[][] getMapa(){
		Personage[][] res = new Personage[5][10];
		for (int i = 0; i < res.length; i++) 
			for (int j = 0; j < res[0].length; j++) 
				if (mapa[i][j] != null) 
					res[i][j] = mapa[i][j].clone();
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
			for (int i = 0; i < f; i++) {
				for (int j = 0; j < c; j++) {
					if (turno)
						if(mapa[i][j] instanceof Planta)
							if (mapa[i][j] instanceof Girasol) 
								energiaJugador = ((Girasol) mapa[i][j]).energia() + energiaJugador;
							else if (j < c-1 && mapa[i][j+1] instanceof Zombie) 
								mapa[i][j+1] = ((Zombie) mapa[i][j+1]).recibeDano(mapa[i][j].getDano()); 
					else {
						if(j > 0 && mapa[i][j] instanceof Zombie) {
							if (mapa[i][j-1] instanceof Planta) {
								mapa[i][j-1] = ((Planta) mapa[i][j-1]).recibeDano(mapa[i][j].getDano()); 
							}else{
								mapa[i][j-1] = mapa[i][j];
								mapa[i][j] = null;
								if (mapa[i][j-1] instanceof ZombieRapido && 0 < j-1 && mapa[i][j-2] == null) {
									mapa[i][j-2] = mapa[i][j-1];
									mapa[i][j-1] = null;
								}
							}
						}
					}
				}
			}
			changeTurno();
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
        	for (int i = 0; i < f && res; i++) {
    			for (int j = 0; j < c && res; j++) {
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
		return new Jardin(mapa.clone(),energiaJugador,energiaZombie);
	}
}