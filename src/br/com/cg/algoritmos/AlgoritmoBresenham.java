package br.com.cg.algoritmos;

import java.util.List;

import br.com.cg.model.ComponenteCirculo;

public class AlgoritmoBresenham {
	
	public static void calcula(ComponenteCirculo ponto1, ComponenteCirculo ponto2, List<List<ComponenteCirculo>> matrizCirculos) {
		
		int x;
		int x1 = ponto1.getColuna();
		int x2 = ponto1.getLinha();
		int y1 = ponto2.getColuna();
		int y2 = ponto2.getLinha();
		int y = y1;
		int erro = 0;
		
		int numerador = y2 - y1;
		int denominador = x2 - x1;
		int threshold = denominador/2;
		
		for (x = x1; x < x2; x++) {
			matrizCirculos.get(x).get(y).selecionaCirculo();
			erro -= numerador;
			
			if(erro > threshold) {
				erro -= denominador;
				y++;
			}
		}
		
		matrizCirculos.get(x).get(y).selecionaCirculo();
	}
	
	
}
