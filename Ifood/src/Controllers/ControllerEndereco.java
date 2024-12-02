package Controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerEndereco {

    @FXML
    private ListView<?> ListViewEnderecos;

    @FXML
    private TextField TextFieldBairro;

    @FXML
    private TextField TextFieldCep;

    @FXML
    private TextField TextFieldCepReferencia;

    @FXML
    private TextField TextFieldCidade;

    @FXML
    private TextField TextFieldComplemento;

    @FXML
    private TextField TextFieldEstado;

    @FXML
    private TextField TextFieldNumero;

    @FXML
    private TextField TextFieldRua;

    @FXML
    void BtnClickBuscar(ActionEvent event) {

    }

    @FXML
    void BtnClickCadastrar(ActionEvent event) {

    }

    @FXML
    void BtnClickCarrinho(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Carrinho.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            System.out.println("Tela de Carrinho carregada com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar a tela de Carrinho.");
        }
    }

    @FXML
    void BtnClickSelecionaEnd(ActionEvent event) {

    }

}
