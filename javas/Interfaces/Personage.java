package javas.Interfaces;

public interface Personage{
	
	abstract int getVida();

	abstract void setVida(int e);
	
	abstract int getCosto();
	
	abstract int getDano();
	
	abstract void recibeDano(int d);
	
	abstract boolean equals(Personage other);
}