package javas;

import javas.Implements.*;
import javas.Implements.Personage.*;
import javas.Interfaces.Personage;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Mein {

	static Scanner sn = new Scanner(System.in);

	public static void main(String[] args) {
		boolean salir = false;
		int opcion; // Guardaremos la opcion del usuario
		while (!salir) {
			System.out.println(" _____________________");
			System.out.println("|     BIENVENIDOS     |");
			System.out.println("| PLANTAS VS. ZOMBIES |");
			System.out.println("| 1. Jugar            |");
			System.out.println("| 2. Salir            |");
			System.out.println("|_____________________|");
			System.out.println("");

			try {
				System.out.println("Seleccione una opcion");
				opcion = sn.nextInt();
				switch (opcion) {
				case 1:
					System.out.println("Ha seleccionado la opcion 1");
					System.out.println("QUE COMIENCE EL JUEGO!");
					System.out.println("");
					jugar();
					break;
				case 2:
					salir = true;
					break;
				default:
					System.out.println("Elija su opcion");
				}
			} catch (InputMismatchException e) {
				System.out.println("Debe insertar un número");
				sn.next();
			}
		}
	}

	public static void jugar() {
		boolean salir = false;
		int opcion;
		Jardin jardin = new Jardin();
		while (!salir) {
			if (jardin.getTurno() && !jardin.endGame()) {
				System.out.println(jardin);
				System.out.println(" _________________________");
				System.out.println("| 1. Generador de energia |");
				System.out.println("| 2. Ataque potenciado    |");
				System.out.println("| 3. Defensa - ataque     |");
				System.out.println("|_________________________|");
				System.out.println("");
				System.out.print("La planta a usar:");
				opcion = sn.nextInt();
				System.out.println("-------------------------------");
				if (jardin.getEnergiaJugador() >= 50) {
					try {
						switch (opcion) {
						case 1:
							System.out.println(" _________________");
							System.out.println("|   GIRASOL :)    |");
							if (jardin.getEnergiaJugador() >= 50) {
								jardin.setEnergiaJugador(jardin.getEnergiaJugador() - 50);
								juegaJugador(new Girasol(), jardin);
							} else
								System.out.println("No tiene energia suficiente");
							break;
						case 2:
							System.out.println(" _________________");
							System.out.println("|LANZAGUISANTE >:3|");
							if (jardin.getEnergiaJugador() >= 100) {
								jardin.setEnergiaJugador(jardin.getEnergiaJugador() - 100);
								juegaJugador(new Lanzaguisante(), jardin);
							} else
								System.out.println("No tiene energia suficiente");
							break;
						case 3:
							System.out.println(" _________________");
							System.out.println("|     NUEZ :3     |");
							if (jardin.getEnergiaJugador() >= 75) {
								jardin.setEnergiaJugador(jardin.getEnergiaJugador() - 75);
								juegaJugador(new Lanzaguisante(), jardin);
							} else
								System.out.println("No tiene energia suficiente");
							break;
						default:
							System.out.println("Elija su opcion:");
						}
					} catch (InputMismatchException e) {
						System.out.println("ERROR! Debes insertar un número");
						sn.next();
					}
				} else {
					jardin.avanzar();
				}
			} else {
				JardinExt je = new JardinExt(jardin);
				Problema p = new Problema(je);
				Juego j = new Juego(300, p);
				je = j.computeSuccessor(je);
				if (je != null) {
					jardin.setMapa(je.getMapa());
					jardin.changeTurno();
					jardin.setEnergiaZombie(je.getEnergiaZombie());
					jardin.setEnergiaJugador(je.getEnergiaJugador());
				} else {
					salir = true;
				}
			}
		}
		System.out.println("Partida finalizada!!");
	}

	private static void juegaJugador(Personage x, Jardin jardin) {
		System.out.println(" ________________________________");
		System.out.println("|Ingrese la posicion de la planta|");
		System.out.println(" _________________");
		System.out.println("| Carril:         |");
		int posicionX = sn.nextInt();
		System.out.println(" _________________");
		System.out.println("| Cantero:        |");
		int posicionY = sn.nextInt();
		System.out.println("|_________________|");
		if (corroborar(posicionX, posicionY)) {
			if (jardin.getMapa()[posicionX][posicionY] == null) {

				jardin.place(posicionX, posicionY, x);
				jardin.avanzar();
			}
		}
	}

	public static boolean corroborar(int x, int y) {
		if ((x >= 0) || (x < 5) && (y >= 0) || (y < 9))
			return true;
		else {
			System.out.println("Ingrese valores validos, entre para X: [0..4] e  Y: [0..8]");
			return false;
		}
	}
}