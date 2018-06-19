package javas.Implements;

import javas.Interfaces.AdversaryFramework.AdversarySearchProblem;
import java.util.List;

public abstract class Problema implements AdversarySearchProblem<Jardin>{

	@Override
	public Jardin initialState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Jardin> getSuccessors(Jardin state) {
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
	public int value(Jardin state) {
		// TODO Auto-generated method stub
		return 0;
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