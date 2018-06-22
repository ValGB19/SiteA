package javas.Implements;

import javas.Interfaces.Personage;
import javas.Interfaces.Planta;
import javas.Interfaces.Zombie;
import javas.Interfaces.AdversaryFramework.AdversarySearchProblem;

import java.util.ArrayList;
import java.util.List;

public class Problema implements AdversarySearchProblem<Jardin>{
	
	Jardin inicial;
	
	@SuppressWarnings("unused")
	private Problema() { //declarado asi para que no puedan crear un problema sin estado inicial
		inicial = null;
	}
	
	public Problema(Jardin j) {
		inicial = j;
	}
	
	
	public Jardin initialState() {
		return inicial;
	}
	
	//@pre state is not a maxState
	//i, j posiciones donde genera
	private List<Jardin> generaTurnoJugador(int i, int j, Jardin state){
		ArrayList<Jardin> res = new ArrayList<Jardin>();
		Jardin aux = state;
		if(state.getMapa()[i][j]==null) {
			if(state.getEnergiaJugador()>=50) {
				aux = state.clone();
				aux.setEnergiaJugador(state.getEnergiaJugador()-50);
				//aux.setPadre(this.inicial);
				aux.place(i, j, new Girasol());
				aux.avanzar();
				res.add(aux);
			}
			if(state.getEnergiaJugador()>=75) {
				aux = state.clone();
				//aux.setPadre(this.inicial);
				aux.place(i, j, new Nuez());
				aux.setEnergiaJugador(state.getEnergiaJugador()-75);
				aux.avanzar();
				res.add(aux);
			}
			if(state.getEnergiaJugador()>=100) {
				aux = state.clone();
				//aux.setPadre(this.inicial);
				aux.setEnergiaJugador(state.getEnergiaJugador()-100);
				aux.place(i, j, new Lanzaguisante());
				aux.avanzar();
				res.add(aux);
			}
			if (state.getEnergiaJugador()<50) {
				aux = state.clone();
				//aux.setPadre(this.inicial);
				aux.avanzar();
				res.add(aux);
			}
		}
		return res;
	}
		
	//i fila donde genera
	private List<Jardin> generaTurnoZombie(int i, Jardin state){
		ArrayList<Jardin> res = new ArrayList<Jardin>();
		Jardin aux = state;
		if(state.getMapa()[i][state.getSizeW()-1]==null) {
			if(state.getEnergiaZombie()>=75) {
				aux = state.clone();
				//aux.setPadre(this.inicial);
				aux.place(i, state.getSizeW()-1, new ZombieLento());
				aux.avanzar();
				res.add(aux);
			}
			if(state.getEnergiaZombie()>=100) {
				aux = state.clone();
				//aux.setPadre(this.inicial);
				aux.place(i, state.getSizeW()-1, new ZombieRapido());
				aux.avanzar();
				res.add(aux);
			}
		}
		return res;
	}

	public List<Jardin> getSuccessors(Jardin state){
		if (state.isMax())
			return new ArrayList<Jardin>();
		else{
			List<Jardin> suc = new ArrayList<Jardin>();
			if(state.getTurno())
				if(state.getEnergiaJugador() < Math.min(Math.min(new Nuez().getCosto(), new Girasol().getCosto()), new Lanzaguisante().getCosto())){
					Jardin aux = state.clone();
					aux.avanzar();
					suc.add(aux);
				}else{
					for(int i = 0; i < state.getSizeH(); i++) 
						for(int j = 0; j < state.getSizeW(); j++) 
							suc.addAll(generaTurnoJugador(i,j,state));
				}
			else
				for(int k = 0; k < 5; k++)
					suc.addAll(generaTurnoZombie(k,state));
			return suc;
		}
	}
	
	public boolean end(Jardin state){
		if(getSuccessors(state).size()==0)
			return true;
		return false;
	}

	public int value(Jardin state) {
		int carril=0;
		Personage[][] jar = state.getMapa();
		for(int i = 0; i<5; i++) {
			for(int j = 0; j<10; j++) {
				if(jar[i][j] instanceof Zombie){
					carril=carril+jar[i][j].getVida();
				}else {
					if(jar[i][j] instanceof Planta) {
						if(jar[i][j] instanceof Girasol) {
							carril=carril-(jar[i][j].getVida()/3);
						}else {
							carril=carril-jar[i][j].getVida();
						}
					}
				}
			}
		}
		return carril;
	}

	public int minValue() {
		return Integer.MIN_VALUE;
	}

	public int maxValue() {
		return Integer.MAX_VALUE;
	}
}