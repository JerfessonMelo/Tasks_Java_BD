package Controllers;

import java.io.IOException;
import java.time.LocalDateTime;

import Entidades1.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PedidoFinalizadoController {

    @FXML
    private ProgressBar ProgressBarCarregamento;

    @FXML
    private Label lblStatusPedido;

    private Stage stage;
    private Scene scenePedidos;
    private Scene sceneRestaurantes;

    public PedidoFinalizadoController(Stage stage, Scene scenePedidos, Scene sceneRestaurantes) {
        this.stage = stage;
        this.scenePedidos = scenePedidos;
        this.sceneRestaurantes = sceneRestaurantes;
    }

    @FXML
    public void initialize() {
        configurarTelaFinalizacao();
    }

    private void configurarTelaFinalizacao() {
        Label titulo = criarTitulo("Pedido Finalizado");

        VBox vboxFinalizacao = new VBox(10);
        vboxFinalizacao.setPadding(new Insets(20));
        vboxFinalizacao.setStyle("-fx-background-color: #f0f8ff;");

        lblStatusPedido = new Label("Status do pedido: Aguardando confirmação...");
        lblStatusPedido.setStyle("-fx-font-size: 16px; -fx-text-fill: blue;");

        ProgressBarCarregamento = new ProgressBar(0);
        ProgressBarCarregamento.setPrefWidth(400);

        Button btnIrMeusPedidos = new Button("Pedidos");
        btnIrMeusPedidos.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
        btnIrMeusPedidos.setOnAction(_ -> stage.setScene(scenePedidos));

        Button btnVoltar = new Button("Voltar para Restaurantes");
        btnVoltar.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
        btnVoltar.setOnAction(_ -> stage.setScene(sceneRestaurantes));

        vboxFinalizacao.getChildren().addAll(titulo, lblStatusPedido, ProgressBarCarregamento, btnIrMeusPedidos, btnVoltar);
        Scene sceneFinalizacao = new Scene(vboxFinalizacao, 400, 220);
        stage.setScene(sceneFinalizacao);
    }

    @FXML
    void BtnActionPedidos(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Pedidos.fxml"));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.show();
            System.out.println("Tela de Pedidos carregada com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar a tela de Pedidos.");
        }
    }

    @FXML
    void BtnActionRestaurantes(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Restaurantes.fxml"));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.show();
            System.out.println("Tela de Restaurantes carregada com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar a tela de Restaurantes.");
        }
    }

    private void atualizarStatusPedido(Pedido pedido, StatusEntrega novoStatus) {
        Platform.runLater(() -> {
            pedido.setStatusEntrega(novoStatus);
            lblStatusPedido.setText("Status do pedido: " + novoStatus.getStatusEntrega());
        });
    }

    private void atualizarProgresso(double progresso) {
        Platform.runLater(() -> ProgressBarCarregamento.setProgress(progresso));
    }

    public void iniciarThreadStatusPedido(Pedido pedido) {
        new Thread(() -> {
            try {
                double progressStep = 1.0 / 3;
                int delay = 3000;

                Thread.sleep(delay);
                atualizarStatusPedido(pedido, new StatusEntrega(2, 1, LocalDateTime.now(), StatusEntregaEnum.EM_PRODUCAO, pedido));
                atualizarProgresso(progressStep);

                Thread.sleep(delay - 1000);
                atualizarStatusPedido(pedido, new StatusEntrega(3, 1, LocalDateTime.now(), StatusEntregaEnum.EM_ROTA_ENTREGA, pedido));
                atualizarProgresso(2 * progressStep);

                Thread.sleep(delay - 2000);
                atualizarStatusPedido(pedido, new StatusEntrega(4, 1, LocalDateTime.now(), StatusEntregaEnum.ENTREGUE, pedido));
                atualizarProgresso(1.0);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private Label criarTitulo(String texto) {
        Label titulo = new Label(texto);
        titulo.setStyle("-fx-font-size: 20px; -fx-text-fill: black;");
        return titulo;
    }
}
