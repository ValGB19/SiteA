package javas.Implements;

import javas.Interfaces.*;
import javas.Implements.Personage.*;
import javas.Interfaces.AdversaryFramework.AdversarySearchProblem;

import java.util.ArrayList;
import java.util.List;

public class Problema implements AdversarySearchProblem<JardinExt>{
	
	JardinExt inicial;
	
	@SuppressWarnings("unused")
	private Problema() { //declarado asi para que no puedan crear un problema sin estado inicial
		inicial = null;
	}
	
	public Problema(JardinExt j) {
		inicial = j;
	}
	
	
	public JardinExt initialState() {
		return inicial;
	}
	
	//@pre state is not a maxState
	//i, j posiciones donde genera
	private List<JardinExt> generaTurnoJugador(int i, int j, JardinExt state){
		ArrayList<JardinExt> res = new ArrayList<JardinExt>();
		JardinExt aux = state;
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
	private List<JardinExt> generaTurnoZombie(int i, JardinExt state){
		ArrayList<JardinExt> res = new ArrayList<JardinExt>();
		JardinExt aux = state;
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

	public List<JardinExt> getSuccessors(JardinExt state){
		if (state.isMax())
			return new ArrayList<JardinExt>();
		else{
			List<JardinExt> suc = new ArrayList<JardinExt>();
			if(state.getTurno())
				if(state.getEnergiaJugador() < Math.min(Math.min(new Nuez().getCosto(), new Girasol().getCosto()), new Lanzaguisante().getCosto())){
					JardinExt aux = state.clone();
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
	
	public boolean end(JardinExt state){
		if(getSuccessors(state).size()==0)
			return true;
		return false;
	}

	public int value(JardinExt state) {
		Personage[][] jar = state.getMapa();
		int valor=0;
		int p=0;
		int z=0;
		Personage bicho,zom;
		for(int i=0;i<state.getSizeH();i++ ) {
			for(int j=0;j<state.getSizeW();j++ ){
				bicho=jar[i][j];
				if(bicho instanceof Nuez || bicho instanceof Lanzaguisante) {
					p++;
					zom=jar[i][j+1];
					if(zom instanceof Zombie){
						valor=valor-((zom.getVida()-bicho.getDano())-(bicho.getVida()-zom.getDano()));
					}
				}else if(bicho instanceof Zombie)
					z++;
			}
		}
		valor=valor+(p-z);
		return valor;
	}

	public int minValue() {
		return Integer.MIN_VALUE;
	}

	public int maxValue() {
		return Integer.MAX_VALUE;
	}
}