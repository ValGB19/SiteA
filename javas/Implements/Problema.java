package javas.Implements;

import javas.Interfaces.Personage;
import javas.Interfaces.Planta;
import javas.Interfaces.Zombie;
import javas.Interfaces.AdversaryFramework.AdversarySearchProblem;
import javas.Interfaces.AdversaryFramework.AdversarySearchState;
import java.util.List;

public abstract class Problema implements AdversarySearchProblem<Jardin>{

	@Override
	public Jardin initialState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getSuccessors(Jardin state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean end(Jardin state) {
		if(getSuccessors(state).size()==0)
			return true;
		return false;
	}

	@Override
<<<<<<< HEAD
	public int value(AdversarySearchState state) {
		int carril=0;
		for(int i = 0; i<5; i++) {
			for(int j = 0; j<10; j++) {
				Personage jar= ((Jardin) state).getMapa()[i][j];
				if(jar instanceof Zombie){
					carril=carril+jar.getVida();
				}else {
					if(jar instanceof Planta) {
						if(jar instanceof Girasol) {
							carril=carril-(jar.getVida()/3);
						}else {
							carril=carril-jar.getVida();
						}
					}
				}
			}
		}
		return carril;
=======
	public int value(Jardin state) {
		// TODO Auto-generated method stub
		return 0;
>>>>>>> faa14b670d05bc624983799c92ebc8610516e9e3
	}

	@Override
	public int minValue() {
		return Integer.MIN_VALUE;
	}

	@Override
	public int maxValue() {
		return Integer.MAX_VALUE;
	}
	
}