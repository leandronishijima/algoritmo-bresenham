package br.com.cg.algoritmos;

import java.util.List;

import br.com.cg.model.ComponenteCirculo;

public class AlgoritmoBresenham {
	
	public static void calcula(ComponenteCirculo ponto1, ComponenteCirculo ponto2, List<List<ComponenteCirculo>> matrizCirculos) {
		
		int x1 = ponto1.getLinha();
		int y1 = ponto1.getColuna();
		int x2 = ponto2.getLinha();
		int y2 = ponto2.getColuna();
		
		int dx = Math.abs(x2 - x1);
	    int dy = Math.abs(y2 - y1);
	    int rozdil = dx - dy;

	    int posun_x, posun_y;

	    if (x1 < x2) posun_x = 1; else posun_x = -1;
	    if (y1 < y2) posun_y = 1; else posun_y = -1;

	    while ((x1 != x2) || (y1 != y2)) {  

	        int p = 2 * rozdil;

	        if (p > -dy) {
	            rozdil = rozdil - dy;
	            x1 = x1 + posun_x;
	        }
	        if (p < dx) {
	            rozdil = rozdil + dx;
	            y1 = y1 + posun_y;
	        }
	        matrizCirculos.get(x1).get(y1).selecionaCirculo();
	    }
	}
	
	
}
