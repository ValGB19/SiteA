package javas.Implements;

import java.util.ArrayList;
/**
 * Title:       Juego<p>
 * Description: Clase utilizada para que la eleccion de donde poner un zombie sea la mejor<p>
 * <p>
 * Copyright:   None <p>
 * Company:     None<p>
 * @author Grupo:  Dalessandro, Garcia, Saenz.
 * @version 0.1
 */
import java.util.List;

import javas.Interfaces.AdversaryFramework.AdversarySearchEngine;

public class Juego extends AdversarySearchEngine<Problema,JardinExt>{
	
int cota=0;//usa en el metodo comp sucesor
	
	public Juego(Problema problem, int maxDepth) {
		super(problem, maxDepth);
	}

	/** 
	 * Starts the search in order to compute a value for a state. The
	 * computation is performed by exploring the game tree corresponding
	 * to the problem being analysed, considering state as the root,
	 * and with maximum depth maxDepth.
	 * @param state is the state for which its value is being computed.
	 * @return the value computed for the state.
	 * @pre. problem!=null and state!=null.
	 * @post. the value for the state is computed, via a search in the
	 * game tree for state as the root, and maxDepth as the maximum 
	 * depth. 
	 */
	public int computeValue(JardinExt state) {
		if (state.isMax() || maxDepth == 0) {
			return problem.value(state);
		}
		Integer res = (state.getTurno()) ? problem.maxValue() : problem.minValue();
		Juego aux = new Juego(problem, maxDepth-1);
		for (JardinExt j :problem.getSuccessors(state)) {
			res = (state.getTurno()) ? Math.min(aux.computeValue(j), res) : Math.max(aux.computeValue(j), res);
		}
		return 0;
	}

	/** 
	 * Starts the search in order to compute a most promising successor
	 * for a state. The computation is performed by exploring the game 
	 * tree corresponding to the problem being analysed, considering 
	 * state as the root, and with maximum depth maxDepth.
	 * @param state is the state for which its most promising successor
	 * is being computed.
	 * @return the most promising successor for state. The method
	 * ruleApplied() in the result indicates which rule led to the 
	 * state. 
	 * @pre. problem!=null and state!=null.
	 * @post. the most promising successor for the state is computed, 
	 * via a search in the game tree for state as the root, and 
	 * maxDepth as the maximum depth. 
	 */
	public JardinExt computeSuccessor(JardinExt state) {
			List<JardinExt> nuevo = new ArrayList<JardinExt>();
			nuevo = problem.getSuccessors(state);
			JardinExt mejor = null, masMejor = null;
			if(cota <= getMaxDepth()){
				for (JardinExt item : nuevo){
					if((problem.value(state) <= problem.value(item))&& (state.getTurno() == true))
						mejor = item;
					
					if((problem.value(state) > problem.value(item))&&(state.getTurno() == false))
						mejor = state;
				}
				cota++;
				masMejor = computeSuccessor(mejor);
			}
			return masMejor;
	}
	
	/** 
	 * Metodo para recorrer el arbol de estados, generados a partir de 
	 * uno en particular y retorna la valoracion del mejor estado siguiente (aproximado)
	 * @param raiz es el estado actual del que se quiere obtener el siguiente
	 * @param profundida es el numero de niveles maximo del arbol de estados a generar
	 * @param alfa es el maxi
	 * @return the most promising successor for state. The method
	 * ruleApplied() in the result indicates which rule led to the 
	 * state. 
	 * @pre. problem!=null and state!=null.
	 * @post. the most promising successor for the state is computed, 
	 * via a search in the game tree for state as the root, and 
	 * maxDepth as the maximum depth. 
	 */
	public int minMaxAB(JardinExt raiz, int profundidad, int alfa, int beta){
		if(raiz.isMax() || profundidad <= 0 ){
			return problem.value(raiz);
		}else{
			List<JardinExt> hijo = problem.getSuccessors(raiz);
			for (int i = 0; i < hijo.size() && alfa < beta; i++){
				if(!raiz.getTurno()){
					alfa = Math.max(alfa, minMaxAB(hijo.get(i), profundidad-1, alfa, beta));
				}else{
					beta = Math.min(beta, minMaxAB(hijo.get(i), profundidad-1, alfa, beta));
				}
			}
			if(raiz.getTurno()){
				return beta;
			}else{
				return alfa;
			}
		}
	}
	
	public void report() {
		
	}
}
