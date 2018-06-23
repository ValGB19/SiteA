package javas.Implements;

import java.util.ArrayList;
import java.util.List;

import javas.Interfaces.AdversaryFramework.AdversarySearchEngine;

public class Juego extends AdversarySearchEngine<Problema,JardinExt>{
	
int cota=0;//usa en el metodo comp sucesor
	
	public Juego(Problema problem, int maxDepth) {
		super(problem, maxDepth);
	}


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


	public JardinExt computeSuccessor(JardinExt state) {//El método ruleApplied () en el resultado indica qué regla condujo al estado. 
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
	
	/*Funcion minMaxAB(n, alfa, beta) --> Valor!  
	 *   Si n es hoja o esta a nivel maximo!    
	 *       retornar valor(n)!
	 *           Sino!
	 *         Para cada hijo n_k de n y mientras alfa<beta hacer! 
	 *                    Si n es Max!                alfa:= max(alfa, minMaxAB(n_k, alfa, beta))!            Sino!                beta:= min(beta, minMaxAB(n_k, alfa, beta))!            Fsi  !        Fpara!        Si n es Max!            retornar alfa!        Sino!            retornar beta!        Fsi!    Fsi ! Ffuncion
*/

	public void report() {
		
	}
}
