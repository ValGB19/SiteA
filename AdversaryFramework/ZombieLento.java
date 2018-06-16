//import ;

public class ZombieLento extends Zombie{
	
	public ZombieLento(){
		setVida(0);
		dano = 5;
		costo = 20;
		vel = 1;
	}
	
	public ZombieLento(int vi){
		vida = vi;
		dano = 5;
		costo = 20;
		vel = 1;
	}
}