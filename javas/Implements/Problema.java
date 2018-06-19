package javas.Implements;

import javas.Interfaces.Personage;
import javas.Interfaces.Planta;
import javas.Interfaces.Zombie;
import javas.Interfaces.AdversaryFramework.AdversarySearchProblem;
import java.util.List;

public abstract class Problema implements AdversarySearchProblem<Jardin>{
	
	Jardin inicial;
	
	private Problema() {
		inicial = null;
	}
	
	public Problema(Jardin j) {
		inicial = j;
	}
	
	
	public Jardin initialState() {
		return inicial;
	}

	public List<Jardin> getSuccessors(Jardin state) {
		return null;
	}

	public boolean end(Jardin state) {
		if(getSuccessors(state).size()==0)
			return true;
		return false;
	}

	public int value(Jardin state) {
		int carril=0;
		Personage[][] jar = state.getMapa();
		for(int i = 0; i<5; i++) {
			for(int j = 0; j<10; j++) {
				if(jar[i][j] instanceof Zombie){
					carril=carril+jar[i][j].getVida();
				}else {
					if(jar[i][j] instanceof Planta) {
						if(jar[i][j] instanceof Girasol) {
							carril=carril-(jar[i][j].getVida()/3);
						}else {
							carril=carril-jar[i][j].getVida();
						}
					}
				}
			}
		}
		return carril;
	}

	public int minValue() {
		return Integer.MIN_VALUE;
	}

	public int maxValue() {
		return Integer.MAX_VALUE;
	}
}