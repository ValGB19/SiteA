package java.Implements;

import java.Interfaces.Planta;

public class Girasol implements Planta{
	
	//private final int dano = 0;
	private final int costo = 5;
	private int vida = 50;
	
	public int getVida(){
		return vida;
	}
	
	public void setVida(int e){
		vida = e;
	}
	
	public int getCosto(){
		return costo;
	}
	
	public int getDano(){
		return 0;
	}
	
	public void reciveDano(int d){
		vida = vida - d;
	}
}
