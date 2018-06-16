public class Zombie implements Character{
	
	private int vida;
	private int dano;
	private int costo;
	private int vel;
	
	public Zombie(){
		vida = 0;
		dano = 0;
		costo = 0;
		vel = 1;
	}
	
	
	public Zombie(int vi){
		vida = vi;
		dano = 0;
		costo = 0;
		vel = 1;
	}
	
	public int getVida() {
		return vida;
	}
	public void setVida(int e) {
		vida = e;
	}
	
	public int getCosto() {
		return costo;
	}
	
	public int getDano() {
		return dano;
	}
	
	public void setDano(int e) {
		 dano = e;
	}

	public int getVel() {
		return vel;
	}
	
	public int setVel() {
		return vel;
	}
	
	public void reciveDano(int d){
		vida -= d;
	}
}