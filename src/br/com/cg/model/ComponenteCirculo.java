package br.com.cg.model;

import static java.lang.String.format;
import static javafx.scene.paint.Color.BLUE;
import static javafx.scene.paint.Color.GREY;
import javafx.scene.shape.Circle;

public class ComponenteCirculo extends Circle {

	private Integer coluna;
	private Integer linha;

	public ComponenteCirculo(Integer coluna, Integer linha) {
		this.coluna = coluna;
		this.linha = linha;
		resetaCor();
	}
	
	public Integer getLinha() {
		return linha;
	}
	
	public Integer getColuna() {
		return coluna;
	}
	
	public void selecionaCirculo() {
		setFill(BLUE);
	}
	
	public void resetaCor() {
		setFill(GREY);
	}
	
	@Override
	public String toString() {
		return format("[ %d, %d ]", linha, coluna);
	}
	
}
