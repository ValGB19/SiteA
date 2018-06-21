package javas;

import javas.Implements.ZombieLento;
import javas.Interfaces.Personage;

public class Mein{
	
	public static void main(String[] args) {
		Personage l = new ZombieLento(12);
		
		System.out.println(l instanceof ZombieLento);
	}
}