/*función alfa-beta(nodo //en nuestro caso el tablero, profundidad, α, β, jugador)
    si nodo es un nodo terminal o profundidad = 0
        devolver el valor heurístico del nodo
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

package java;

import java.util.ArrayList;
import java.util.List;

public class minMax{
	public int minMaxAB(Tree<Jardin> raiz, boolean jugador) {
		List<Tree<Jardin>> childs = raiz.getChilds();
		if(childs.size()==0)
			return raiz.getRoot().valEstado();
		else {
			for(int i )
		}
	}
}