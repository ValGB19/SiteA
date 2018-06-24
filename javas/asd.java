package javas;

import javas.Implements.*;
import javas.Implements.Personage.ZombieLento;

public class asd{
	
	public static void main(String[] args) {
		Jardin jardin = new Jardin();
		System.out.println("******************");
		JardinExt je = new JardinExt(jardin);
	    Problema p = new Problema(je);
	    Juego j = new Juego(0,p);
	    p.getSuccessors(je);
	   // System.out.println(p.getSuccessors(je).get(3));
	    System.out.println(new ZombieLento().getClass());
	}
}