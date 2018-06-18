package java;

public class Lanzaguisante extends Plantas{
	
	private final int dano = 50;
	private final int costo = 10;
	private final int vida = 100;
	
	public int getVida(){
		return vida;
	}
	
	public void setVida(int e){
		vida = e;
	}
	
	public int getCosto(){
		return costo;
	}
	
	public void setCosto(int e){
		costo = e;
	}
	
	public int getDano(){
		return dano;
	}
	
	public void setDano(int e){
		dano = e;
	}
	
	public void reciveDano(int d){
		vida = vida - d;
	}
}
