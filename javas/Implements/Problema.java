package javas.Implements;

import javas.Interfaces.*;
import javas.Implements.Personage.*;
import javas.Interfaces.AdversaryFramework.AdversarySearchProblem;

import java.util.ArrayList;
import java.util.List;

/**
 * Title:       Problema<p>
 * Description: Implementacion de la interface AdversarySearchProblem <p>
 * Copyright:   None <p>
 * Company:     None<p>
 * @author Grupo:  Dalessandro, Garcia, Saenz.
 * @version 0.1
 */

public class Problema implements AdversarySearchProblem<JardinExt>{
	
	JardinExt inicial;
	
	@SuppressWarnings("unused")
	private Problema() { 
		inicial = null;
	}
	
	public Problema(JardinExt j) {
		inicial = j;
	}
	
	/** 
	 * Returns the initial state corresponding to the problem. 
	 * Concrete implementations of AdversarySearchProblem must 
	 * implement this routine, to indicate the starting point for 
	 * the (adversary) search.
	 * @return the initial state for the problem.
	 * @pre. true.
	 * @post. the initial state for the problem is returned.  
	 */
	public JardinExt initialState() {
		return inicial;
	}
	
	/** 
	 * Genera una lista con todos los estados que podria tomar el juego
	 * dependiendo de que juega el jugador en la posicion (i,j).
	 * @return Retorna una lista con todas las posibilidades que tiene 
	 * el jugador para jugar en una posicion.
	 * @pre. state no es un estado final.
	 * @post. Una lista con todas las posibilidades que tiene el jugador para jugar es retornada.  
	 */
	private List<JardinExt> generaTurnoJugador(int i, int j, JardinExt state){
		ArrayList<JardinExt> res = new ArrayList<JardinExt>();
		JardinExt aux = state;
		if(state.getMapa()[i][j]==null) {
			if(state.getEnergiaJugador()>=50) {
				aux = state.clone();
				aux.setEnergiaJugador(state.getEnergiaJugador() - 50);
				aux.setPadre(this.inicial);
				aux.place(i, j, new Girasol());
				aux.avanzar();
				res.add(aux);
			}
			if(state.getEnergiaJugador()>=75) {
				aux = state.clone();
				aux.setPadre(this.inicial);
				aux.place(i, j, new Nuez());
				aux.setEnergiaJugador(state.getEnergiaJugador() - 75);
				aux.avanzar();
				res.add(aux);
			}
			if(state.getEnergiaJugador()>=100) {
				aux = state.clone();
				aux.setPadre(this.inicial);
				aux.setEnergiaJugador(state.getEnergiaJugador() - 100);
				aux.place(i, j, new Lanzaguisante());
				aux.avanzar();
				res.add(aux);
			}
			if (state.getEnergiaJugador()<50) {
				aux = state.clone();
				aux.setPadre(this.inicial);
				aux.avanzar();
				res.add(aux);
			}
		}
		return res;
	}
		
	/** 
	 * Genera una lista con todos los estados que podria tomar el juego
	 * dependiendo de que juega el zombie en la posicion (i,W).
	 * Siendo W "la columna que representa la calle", o mas bien, el 
	 * limite derecho de la matriz.
	 * @return Retorna una lista con todas las posibilidades que tiene 
	 * el zombie para jugar en una posicion.
	 * @pre. state no es un estado final.
	 * @post. Una lista con todas las posibilidades que tiene el jugador para jugar es retornada.  
	 */
	private List<JardinExt> generaTurnoZombie(int i, JardinExt state){
		ArrayList<JardinExt> res = new ArrayList<JardinExt>();
		JardinExt aux = state;
		if(state.getMapa()[i][9]==null) {
			if(state.getEnergiaZombie()>=75) {
				aux = state.clone();
				aux.setPadre(this.inicial);
				aux.setEnergiaZombie(state.getEnergiaZombie() - 75);
				aux.place(i, 9, new ZombieLento());
				aux.avanzar();
				res.add(aux);
			}
			if(state.getEnergiaZombie()>=100) {
				aux = state.clone();
				aux.setEnergiaZombie(state.getEnergiaZombie() - 100);
				aux.setPadre(this.inicial);
				aux.place(i, 9, new ZombieRapido());
				aux.avanzar();
				res.add(aux);
			}
		}
		return res;
	}

	/** 
	 * Returns the list of successor states for a given state, in the 
	 * context of the current problem. Concrete implementations of 
	 * AdversarySearchProblem must implement this routine, to indicate
	 * the 'advance' rules (or game rules) for the search.
	 * @param state is the state for which its successors are being 
	 * computed.
	 * @return the list of successor states of state.
	 * @pre. state!=null.
	 * @post. the list of successor states of state is returned.  
	 */
	public List<JardinExt> getSuccessors(JardinExt state){
		if (state.isMax())
			return new ArrayList<JardinExt>();
		else{
			List<JardinExt> suc = new ArrayList<JardinExt>();
			if(state.getTurno())
				if(state.getEnergiaJugador() < 50){
					JardinExt aux = state.clone();
					aux.avanzar();
					suc.add(aux);
				}else{
					for(int i = 0; i < state.getSizeF(); i++) 
						for(int j = 0; j < state.getSizeC()-1; j++) 
							suc.addAll(generaTurnoJugador(i,j,state));
				}
			else
				for(int k = 0; k < 5; k++)
					suc.addAll(generaTurnoZombie(k,state));
			return suc;
		}
	}
	
	/** 
	 * Indicates whether a given state is an end state, i.e., a 
	 * state with no successors. 
	 * @param state is the state being checked to be an end state.
	 * @return true iff state is an end state.
	 * @pre. state!=null.
	 * @post. true is returned iff state is an end state.  
	 */
	public boolean end(JardinExt state){
		if(getSuccessors(state).size()==0)
			return true;
		return false;
	}
	
	/** 
	 * Computes the value of a given state. If the state is a leaf
	 * (end state), then this value is an exact value, and indicates
	 * the outcome of the game. If the state is not an end state, then
	 * this value is an approximate value. Its estimation plays a
	 * crucial role in the performance of search. 
	 * @param state is the state for which its value is being computed.
	 * @return an integer value, representing the value of the state.
	 * This value must be greater than minValue(), and smaller than
	 * maxValue().
	 * @pre. state!=null.
	 * @post. an integer value, representing the value of the state.   
	 */
	public int value(JardinExt state) {
		Personage[][] jar = state.getMapa();
		int valor=0;
		int p=0;
		int z=0;
		Personage bicho,zom;
		for(int i=0;i<state.getSizeF();i++ ) {
			for(int j=0;j<state.getSizeC()-1;j++ ){
				bicho=jar[i][j];
				if(bicho instanceof Nuez || bicho instanceof Lanzaguisante) {
					p++;
					zom=jar[i][j+1];
					if(zom instanceof Zombie){
						valor=valor-((zom.getVida()-bicho.getDano())-(bicho.getVida()-zom.getDano()));
					}
				}else if(bicho instanceof Zombie)
					z++;
			}
		}
		valor=valor+(p-z);
		return valor;
	}
	
	 /** 
	 * Indicates the least possible value for a state in the problem.
	 * Together with maxValue(), it determines an interval in which 
	 * values for states must range.
	 * @return an integer value, representing the least possible value
	 * for the problem. 
	 * This value must be strictly smaller than maxValue().
	 * @pre. true.
	 * @post. an integer value, representing the least possible value
	 * for states, is returned. 
	 */
	public int minValue() {
		return Integer.MIN_VALUE;
	}

    /** 
	 * Indicates the greatest possible value for a state in the problem.
	 * Together with minValue(), it determines an interval in which 
	 * values for states must range.
	 * @return an integer value, representing the greatest possible value
	 * for the problem. 
	 * This value must be strictly greater than minValue().
	 * @pre. true.
	 * @post. an integer value, representing the greatest possible value
	 * for states, is returned. 
	 */
	public int maxValue() {
		return Integer.MAX_VALUE;
	}
}