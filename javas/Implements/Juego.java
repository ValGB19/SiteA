package javas.Implements;

import java.util.List;

import javas.Interfaces.AdversaryFramework.AdversarySearchEngine;

public class Juego extends AdversarySearchEngine<Problema,JardinExt>{
	
	public int computeValue(JardinExt state) {	
		return minMaxAB(state, maxDepth, problem.minValue(), problem.maxValue());
	}


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
		System.out.println("Max depth: "+maxDepth);
		System.out.println("isMax? "+problem.initialState().isMax());
	}
}
