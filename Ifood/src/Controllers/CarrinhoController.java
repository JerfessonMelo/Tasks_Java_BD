package Controllers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import Entidades1.*;
import EntidadesDAO1.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class CarrinhoController {

    @FXML
    private ListView<String> ListViewCarrinho;

    @FXML
    private ComboBox<FormaPagamento> ComboboxPagamento;

    private Pedido pedido;
    private PedidoDAO pedidoDAO;
    private FormaPagamentoDAO formaPagamentoDAO;
    private Stage stage;

    public CarrinhoController(Pedido pedido, PedidoDAO pedidoDAO, FormaPagamentoDAO formaPagamentoDAO, Stage stage) {
        this.pedido = pedido;
        this.pedidoDAO = pedidoDAO;
        this.formaPagamentoDAO = formaPagamentoDAO;
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        configurarTelaCarrinho();
    }

    private void configurarTelaCarrinho() {
        if (pedido == null) {
            System.err.println("Erro: Pedido não foi inicializado.");
            return;
        }

        ListViewCarrinho.getItems().clear();

        if (pedido.getPedidoProduto() != null) {
            for (PedidoProduto pp : pedido.getPedidoProduto()) {
                String item = pp.getProduto().get(0).getNome() + " x" + pp.getQuantidade() +
                              " - R$" + pp.getPreco();
                ListViewCarrinho.getItems().add(item);
            }
        }

        if (pedido.getValor() != 0) {
            ListViewCarrinho.getItems().add("Total: R$ " + pedido.getValor());
        }

        ComboboxPagamento.getItems().clear();
        ArrayList<FormaPagamento> formasPagamento = formaPagamentoDAO.listarTodos();
        ComboboxPagamento.getItems().addAll(formasPagamento);

        ComboboxPagamento.setOnAction(_ -> {
            FormaPagamento formaSelecionada = ComboboxPagamento.getValue();
            if (formaSelecionada != null) {
                pedido.setFormaPagamento(formaSelecionada);
            } else {
                System.err.println("Forma de pagamento não selecionada.");
            }
        });
    }

    @FXML
    void BtnActionEndereco(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Endereco.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            System.out.println("Tela de Endereço carregada com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar a tela de Endereço.");
        }
    }

    @FXML
    void BtnActionFinalizar(ActionEvent event) {
        if (pedido == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Nenhum pedido foi realizado.");
            alert.showAndWait();
            return;
        }

        if (pedido.getEndereco() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Por favor, selecione um endereço.");
            alert.showAndWait();
            return;
        }

        if (pedido.getStatusEntrega() == null) {
            pedido.setStatusEntrega(new StatusEntrega(0, 0, LocalDateTime.now(), StatusEntregaEnum.EM_PRODUCAO, pedido));
        }

        pedido.definirDataPedidoAutomaticamente();
        pedidoDAO.salvar(pedido);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Finalizacao.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            System.out.println("Tela de Pedido Finalizado carregada com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar a tela de Pedido Finalizado.");
        }
    }
}
