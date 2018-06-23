/*funcion alfa-beta(nodo //en nuestro caso el tablero, profundidad, α, β, jugador)
    si nodo es un nodo terminal o profundidad = 0
        devolver el valor heuristico del nodo
    si jugador1
        para cada hijo de nodo
            α := max(α, alfa-beta(hijo, profundidad-1, α, β, jugador2))
            si β≤α
                romper (* poda β *)
        devolver α
    si no
        para cada hijo de nodo
            β := min(β, alfa-beta(hijo, profundidad-1, α, β, jugador1))
            si β≤α
                romper (* poda α *)
        devolver β*/

package javas;

import java.util.ArrayList;
import java.util.List;

import javas.Implements.Jardin;
import javas.Implements.Problema;

public class minMax{
	public int minMaxAB(Jardin raiz, int profundidad, int alfa, int beta, int jugador){

		if(new Problema(new Jardin(1,2)).end(raiz) || profundidad == -1 ){
			return new Problema(new Jardin(1,2)).value(raiz);
		}
		else{
			jugador = -jugador;
			List<Jardin> hijos = new ArrayList<Jardin>();
			hijos = new Problema(new Jardin(1,2)).getSuccessors(raiz);
			for (Jardin item : hijos){
				if(item.isMax()){
					alfa = Math.max(alfa, minMaxAB(item, profundidad-1, alfa, beta, jugador));
				}
				else{
					beta = Math.min(beta, minMaxAB(item, profundidad-1, alfa, beta, jugador));
				}
			}
			if(jugador > 0){
				return alfa;
			}
			else{
				return beta;
			}
		}
	}
}