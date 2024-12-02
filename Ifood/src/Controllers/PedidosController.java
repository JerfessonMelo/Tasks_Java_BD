package Controllers;

import java.io.IOException;
import java.sql.Connection;

import EntidadesDAO1.PedidoDAO;
import Entidades1.Pedido;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class PedidosController {

    @FXML
    private ListView<String> ListViewPedidos;

    private PedidoDAO pedidoDAO;

    public PedidosController(Connection connection) {
        this.pedidoDAO = new PedidoDAO(connection);
    }

    @FXML
    private void configurarTelaPedidos() {
        ObservableList<String> pedidos = FXCollections.observableArrayList();

        for (Pedido pedido : pedidoDAO.listarTodos()) {
            pedidos.add("Pedido #" + pedido.getId() + ": R$ " + String.format("%.2f", pedido.getValor()) + 
                " - " + pedido.getObservacoes());
        }

        ListViewPedidos.setItems(pedidos);
    }

    @FXML
    void BtnActionRestaurantes(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Restaurantes.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            System.out.println("Tela de Restaurantes carregada com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar a tela de Restaurantes.");
        }
    }
}