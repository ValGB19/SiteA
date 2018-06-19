package java.Implements;

import java.Interfaces.AdversaryFramework.AdversarySearchProblem;
import java.Interfaces.AdversaryFramework.AdversarySearchState;
import java.util.List;

public abstract class Problema implements AdversarySearchProblem{

	@Override
	public AdversarySearchState initialState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getSuccessors(AdversarySearchState state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean end(AdversarySearchState state) {
		if(getSuccessors(state).size()==0)
			return true;
		return false;
	}

	@Override
	public int value(AdversarySearchState state) {
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