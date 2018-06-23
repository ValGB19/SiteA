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
		Scanner planta = new Scanner(System.in);
		int posicionX = 0;
		int posicionY = 0;
		boolean salir = false;
	    int opcion; //Guardaremos la opcion del usuario
	    Jardin posicion2 = new Jardin();
	      
	       while(!salir){
	    	   System.out.println(posicion2);
	           System.out.println("1. generador de energia");
	           System.out.println("2. ataque potenciado");
	           System.out.println("3. defensa - ataque");
	           System.out.println("4. no seleccionar mas");
	            
	           try {
		           System.out.println("la planta a usar");
		           opcion = planta.nextInt();
		            
			       switch(opcion){
		           case 1:
		               System.out.println("GIRASOL :)");
		               System.out.println("ingrese la posicion de la planta seleccionada");
			       	   System.out.println("eje X");
			       	   posicionX = sn.nextInt();
			       	   System.out.println("eje Y");
			       	   posicionY = sn.nextInt();  
		               posicion2.place(posicionX, posicionY,new Girasol());
		               break;
		           case 2:
		               System.out.println("LANZAGUISANTE >:3");
		               System.out.println("ingrese la posicion de la planta seleccionada");
		       		   System.out.println("eje X");
		       		   posicionX = sn.nextInt();
		       		   System.out.println("eje Y");
		       		   posicionY = sn.nextInt();
		       		   posicion2.place(posicionX, posicionY,new Lanzaguisante());
		               break;
		           case 3:
		               System.out.println("NUEZ :3");
		               System.out.println("ingrese la posicion de la planta seleccionada");
		       		   System.out.println("eje X");
		       		   posicionX = sn.nextInt();
		       		   System.out.println("eje Y");
		       		   posicionY = sn.nextInt();
		               posicion2.place(posicionX, posicionY,new Nuez());
		               break;
		           case 4:
		              salir=true;
		              break;
		           default:
		               System.out.println("elija su opcion");
		           }
	           }
	           catch (InputMismatchException e) {
		           System.out.println("Debes insertar un número");
		           planta.next();
	           }
	       }
	}	
}