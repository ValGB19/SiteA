package javas.Implements;

import java.util.ArrayList;
import java.util.List;

import javas.Interfaces.AdversaryFramework.AdversarySearchEngine;

public class Juego extends AdversarySearchEngine<Problema,Jardin>{
	
int cota=0;//usa en el metodo comp sucesor
	
	public Juego(Problema problem, int maxDepth) {
		super(problem, maxDepth);
	}


	public int computeValue(Jardin state) {
		if (state.isMax() || maxDepth == 0) {
			return problem.value(state);
		}
		Integer res = (state.getTurno()) ? problem.maxValue() : problem.minValue();
		Juego aux = new Juego(problem, maxDepth-1);
		for (Jardin j :problem.getSuccessors(state)) {
			res = (state.getTurno()) ? Math.min(aux.computeValue(j), res) : Math.max(aux.computeValue(j), res);
		}
		
		return 0;
	}


	public Jardin computeSuccessor(Jardin state) {//El método ruleApplied () en el resultado indica qué regla condujo al estado. 
			List<Jardin> nuevo = new ArrayList<Jardin>();
			nuevo = problem.getSuccessors(state);
			Jardin mejor = null, masMejor = null;
			if(cota <= getMaxDepth()){
				for (Jardin item : nuevo){
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

	public void report() {
		
	}
}
