/**
 * Title:        State<p>
 * Description:  Interface which defines the basic requirements on
 * states, needed when characterising problems as search problems. 
 * State definitions for particular search problems should extend this class,
 * so the general search strategies can be used.<p>
 * Copyright:    Copyright (c) Nazareno Aguirre 2003,2010<p>
 * Company:      None<p>
 * @author Nazareno Aguirre
 * @version 0.3
 */
 
public interface State  {

	/** 
	 * Checks whether 'this' is equal to another state. This must be implemented
	 * by every concrete class implementing State.
	 * @param other is the state to compare 'this' to.
	 * @return true iff 'this' is equal, as a state, to 'other'.
	 * @pre. other!=null.
	 * @post. true is returned iff 'this' is equal, as a state, to 'other'.
	 */	
    abstract public boolean equals(State other);
  
	/** 
	 * Returns a representation as a string of the current state. This method
	 * must be implemented by all concrete classes implementing State.
	 * @return a string representing the current state.
	 * @pre. true.
	 * @post. A text representation of the current state is returned.
	 */	
    abstract public String toString();


} 