package br.com.cg.algoritmos;

import java.util.ArrayList;
import java.util.List;

import br.com.cg.model.ComponenteCirculo;

public class AlgoritmoBresenham {
	
	public static List<ComponenteCirculo> getCaminho(ComponenteCirculo ponto1, ComponenteCirculo ponto2, List<List<ComponenteCirculo>> matrizCirculos) {
		
		List<ComponenteCirculo> caminho = new ArrayList<ComponenteCirculo>();
		
		int x1 = ponto1.getLinha();
		int y1 = ponto1.getColuna();
		int x2 = ponto2.getLinha();
		int y2 = ponto2.getColuna();
		
		int dx = Math.abs(x2 - x1);
	    int dy = Math.abs(y2 - y1);
	    int abs = dx - dy;

	    int posX, posY;

	    posX = (x1 < x2) ? 1 : -1;
	    posY = (y1 < y2) ? 1 : -1;

	    while ((x1 != x2) || (y1 != y2)) {  
	        int p = 2 * abs;

	        if (p > -dy) {
	            abs = abs - dy;
	            x1 = x1 + posX;
	        }
	        if (p < dx) {
	            abs = abs + dx;
	            y1 = y1 + posY;
	        }
	        caminho.add(matrizCirculos.get(x1).get(y1));
	    }

	    return caminho;
	}
}
