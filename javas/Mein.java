package javas;

import javas.Implements.*;
import javas.Implements.Personage.*;
import javas.Interfaces.Personage;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Mein{
	
	static Scanner sn = new Scanner(System.in);
	
	public static void main(String[] args) {
	       boolean salir = false;
	       int opcion; //Guardaremos la opcion del usuario
	       while(!salir){
	            
	           System.out.println("1. Jugar");
	           System.out.println("2. Salir");
	            
	           try {
	        	   
		           System.out.println("Escribe una de las opciones");
		           opcion = sn.nextInt();
		            
			       switch(opcion){
		           case 1:
		               System.out.println("Has seleccionado la opcion 1");
		               jugar();
		               break;
		            case 2:
		               salir=true;
		               break;
		            default:
		               System.out.println("elija su opcion");
		           }
	           }
	           catch (InputMismatchException e) {
		           System.out.println("Debes insertar un número");
		           sn.next();
	           }
	       }
	}
	
	public static void jugar(){
		
			boolean salir = false;
		    int opcion;
		    Jardin jardin = new Jardin();
		    
		    while(!salir){
		    	if(jardin.getTurno() && !jardin.endGame()){
		    	   System.out.println(jardin);
		           System.out.println("1. Generador de energia");
		           System.out.println("2. Ataque potenciado");
		           System.out.println("3. Defensa - ataque");
		            
		           try {
			           System.out.println("La planta a usar");
			           opcion = sn.nextInt();
			            
				       switch(opcion){
			           case 1:
			               System.out.println("GIRASOL :)");
			               juegaJugador(new Girasol(), jardin);
			               break;
			           case 2:
			               System.out.println("LANZAGUISANTE >:3");
			               juegaJugador(new Lanzaguisante(), jardin);
			               break;
			           case 3:
			               System.out.println("NUEZ :3");
			               juegaJugador(new Nuez(), jardin);
			       		   break;
			           default:
			               System.out.println("elija su opcion");
			           }
		           }
		           catch (InputMismatchException e){
			           System.out.println("Debes insertar un número");
			           sn.next();
		           }
		        }
		    	else{
		    		JardinExt je = new JardinExt(jardin);
				    Problema p = new Problema(je);
				    Juego j = new Juego(0,p);
				    jardin.setMapa(j.computeSuccessor(je).getMapa());
				}
		    }   	
	}
	
	
	private static void juegaJugador(Personage x, Jardin jardin) {
       System.out.println("ingrese la posicion de la planta seleccionada");
   	   System.out.println("Carril");
   	   int posicionX = sn.nextInt();
   	   System.out.println("Cantero");
   	   int posicionY = sn.nextInt();
   	   if(corroborar(posicionX, posicionY)){
   		   jardin.place(posicionX, posicionY, x);
   		   jardin.avanzar();
    	}
	}

	public static boolean corroborar(int x, int y){
		if(((x>=0)||(x<5))&&((y>=0)||(y<9)))
			return true;
		else{
			System.out.println("ingrese valores validos, entre para X: [0..4] e  Y: [0..8]");
			return false;
			}
	}
}