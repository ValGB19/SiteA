package javas.Implements;

import java.util.ArrayList;
import java.util.List;

import javas.Interfaces.AdversaryFramework.AdversarySearchEngine;

public class Juego extends AdversarySearchEngine<Problema,Jardin>{
	
int cota=0;//usa en el metodo comp sucesor
	
	public int computeValue(Jardin state) {
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
