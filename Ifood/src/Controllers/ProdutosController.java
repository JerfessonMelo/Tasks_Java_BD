package Controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class ProdutosController {

    @FXML
    private ListView<?> ListViewProdutos;

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
