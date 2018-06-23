package javas.Implements;

/**
 * Title:       Juego<p>
 * Description: Clase utilizada para que la eleccion de donde poner un zombie sea la mejor<p>
 * Copyright:   None <p>
 * Company:     None<p>
 * @author Grupo:  Dalessandro, Garcia, Saenz.
 * @version 0.1
 */
import java.util.List;

import javas.Interfaces.AdversaryFramework.AdversarySearchEngine;

public class Juego extends AdversarySearchEngine<Problema,JardinExt>{
	
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
			return minMaxAB(state, maxDepth, problem.minValue(), problem.maxValue());
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
	public JardinExt computeSuccessor(JardinExt state) {//El método ruleApplied () en el resultado indica qué regla condujo al estado.
		JardinExt mejor = null;
		int im = (state.getTurno()) ? problem.minValue(): problem.maxValue();
		int r;
		for (JardinExt item : problem.getSuccessors(state)){
			r = computeValue(item);
			if(state.getTurno()) {
				if (r > im) {
					mejor = item;
					im = r;
				}
			}else {
				if (r < im) {
					mejor = item;
					im = r;
				}
			}
		}
		return mejor;
	}
	
	/** 
	 * Metodo para recorrer el arbol de estados, generados a partir de 
	 * uno en particular y retorna la valoracion del mejor estado siguiente (aproximado)
	 * @param raiz es el estado actual del que se quiere obtener el siguiente
	 * @param profundida es el numero de niveles maximo del arbol de estados a generar
	 * @param alfa es el minimo valor actual
	 * @param beta es el maximo valor actual
	 * @return int con la valoracion del mejor estado siguiente (aproximado)
	 * @pre. problem!=null
	 * @post. retornar la valoracion del mejor estado siguiente
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
	
	/** 
	 * Reports information regarding a previously executed search.   
	 * @pre. computeSuccessor(s) or computeValue(s) have been 
	 * executed and finished.
	 * @post. A report regarding the last performed search is printed 
	 * to standard output.
	 */
	public void report() {
		System.out.println("Max depth: "+maxDepth);
		System.out.println("isMax? "+problem.initialState().isMax());
	}
}
