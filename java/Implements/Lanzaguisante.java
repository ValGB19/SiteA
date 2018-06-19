package java.Implements;

import java.Interfaces.Planta;

public class Lanzaguisante implements Planta{
	
	private final int dano = 20;
	private final int costo = 10;
	private int vida = 60;
	
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
		return dano;
	}
	
	public void reciveDano(int d){
		vida = vida - d;
	}
}
