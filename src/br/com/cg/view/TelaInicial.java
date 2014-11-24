package br.com.cg.view;

import static java.lang.Integer.parseInt;
import static javafx.geometry.Pos.CENTER;
import static javafx.geometry.Pos.TOP_LEFT;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import br.com.cg.model.ComponenteCirculo;

public class TelaInicial extends Application {

	private AnchorPane pane;

	private VBox vLayout;
	private HBox hLayout;
	private VBox vLayoutCirculos;
	private GridPane paneCirculos;

	private Label lblDigiteTamanho;
	private TextField txtSizeMatriz;
	private Button btnGerar;
	private Button btnLimparMatriz;

	private ComponenteCirculo pontoInicial;
	private ComponenteCirculo pontoFinal;
	
	private List<List<ComponenteCirculo>> matrizCirculos;

	private int tamanhoMatriz;

	public TelaInicial() {
		tamanhoMatriz = 0;
		matrizCirculos = new ArrayList<List<ComponenteCirculo>>();
	}

	public void show(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		inicializaLayouts();
		inicializaComponentes();
		inicializaConfiguracoesPane();

		configuraStage(stage);
	}

	private void configuraStage(Stage stage) {
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.setTitle("Algoritmo de Bresenham");
		stage.setResizable(true);
		stage.show();
	}

	private void inicializaComponentes() {
		lblDigiteTamanho = new Label("Digite um número de linhas/colunas: ");

		txtSizeMatriz = new TextField();

		btnGerar = new Button("Gerar!");
		btnGerar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				btnGerarAction();
			}
		});
		
		btnLimparMatriz = new Button("Limpar seleções");
		btnLimparMatriz.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if(paneCirculos.getChildren().isEmpty())
					return;
				
				limpaDadosMatriz();
			}
		});

		addHLayout(lblDigiteTamanho);
		addHLayout(txtSizeMatriz);
		addHLayout(btnGerar);
		addHLayout(btnLimparMatriz);
		addVLayout(hLayout);
		addVLayout(vLayoutCirculos);
	}

	private void btnGerarAction() {
		if (txtSizeMatriz.getText().matches("\\d+")) {
			tamanhoMatriz = parseInt(txtSizeMatriz.getText());
			geraMatrizDeCirculos();
		} else {
			txtSizeMatriz.setText("");
			showMessageDialog(null, "Somente números são permitidos!", "Atenção!", ERROR_MESSAGE);
		}
	}

	private void geraMatrizDeCirculos() {
		if(!matrizCirculos.isEmpty())
			limpaMatrizAtual();

		for (int i = 0; i < tamanhoMatriz; i++) {
			matrizCirculos.add(i, new ArrayList<ComponenteCirculo>());
			
			for (int j = 0; j < tamanhoMatriz; j++) {
				ComponenteCirculo circulo = criaCirculo(i, j);
				matrizCirculos.get(i).add(j, circulo);
				paneCirculos.addRow(i, circulo);
			}
		}

	}

	private void limpaMatrizAtual() {
		matrizCirculos.clear();
		paneCirculos.getChildren().clear();
	}

	private ComponenteCirculo criaCirculo(int linha, int coluna) {
		final ComponenteCirculo circulo = new ComponenteCirculo(coluna, linha);
		circulo.setCenterX(100);
		circulo.setCenterY(100);
		circulo.setRadius(50);

		circulo.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				setPontoInicialOuFinal(circulo);
			}
		});

		return circulo;
	}

	private void addHLayout(Node node) {
		hLayout.getChildren().add(node);
	}

	private void addVLayout(Node node) {
		vLayout.getChildren().add(node);
	}

	private void inicializaLayouts() {
		vLayout = new VBox();
		vLayout.setSpacing(5);
		vLayout.setAlignment(TOP_LEFT);

		hLayout = new HBox();
		hLayout.setSpacing(5);
		hLayout.setAlignment(TOP_LEFT);

		paneCirculos = new GridPane();
		paneCirculos.setAlignment(CENTER);
		paneCirculos.setHgap(10);
		paneCirculos.setVgap(10);
		paneCirculos.setStyle("-fx-background-color: black;");

		vLayoutCirculos = new VBox();
		vLayoutCirculos.setSpacing(5);
		vLayoutCirculos.setAlignment(CENTER);
		vLayoutCirculos.getChildren().add(paneCirculos);
	}

	private void inicializaConfiguracoesPane() {
		pane = new AnchorPane();
		pane.setPrefSize(800, 600);
		pane.getChildren().addAll(vLayout);
	}

	private void setPontoInicialOuFinal(final ComponenteCirculo circulo) {
		if (pontoInicial == null) {
			setPontoInicial(circulo);
			return;
		}

		setPontoFinal(circulo);
	}

	private void setPontoFinal(final ComponenteCirculo circulo) {
		if (pontoFinal != null)
			pontoFinal.resetaCor();

		if (!circulo.equals(pontoInicial)) {
			circulo.selecionaCirculo();
			pontoFinal = circulo;
		} else
			pontoFinal = null;
	}

	private void setPontoInicial(final ComponenteCirculo circulo) {
		circulo.selecionaCirculo();
		pontoInicial = circulo;
	}

	private void limpaDadosMatriz() {
		pontoInicial = null;
		pontoFinal = null;

		for (Node circulo : paneCirculos.getChildren())
			((ComponenteCirculo)circulo).resetaCor();
	}

}
