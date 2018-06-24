package javas;

import javas.Implements.*;
import javas.Implements.Personage.ZombieLento;

public class asd{
	
	public static void main(String[] args) {
		Jardin jardin = new Jardin();
		System.out.println("******************");
		JardinExt je = new JardinExt(jardin);
	    Problema p = new Problema(je);
	    je.avanzar();
	    for(JardinExt x:p.getSuccessors(je)) {
	    	System.out.println(x);
	    }
	    System.out.println(p.getSuccessors(je).size());
	    System.out.println(new ZombieLento().getClass());
	}
}