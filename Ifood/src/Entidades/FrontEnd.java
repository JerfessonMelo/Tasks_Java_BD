import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.sql.SQLException;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.ArrayList;

import ConexaoBanco.DatabaseConnection;

public class FrontEnd extends Application {

    private Scene sceneRestaurantes, sceneProdutos, sceneCarrinho, sceneFinalizacao, scenePedidos;
    private Restaurante restauranteSelecionado;
    private Pedido pedido = new Pedido(0, 0, null, null, 0, 0, STYLESHEET_CASPIAN, null, restauranteSelecionado, null, null, null);
    private ListView<String> pedidosListView = new ListView<>();
    private PedidoDAO pedidoDAO;
    private ProdutoDAO produtoDAO;
    private FormaPagamentoDAO formaPagamentoDAO;
    private EnderecoDAO enderecoDAO;
    private RestauranteDAO restauranteDAO;
    private Connection connection;
    private Label lblStatusPedido;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Ifood");

        try {
            connection = DatabaseConnection.getConnection();
            pedidoDAO = new PedidoDAO(connection);
            restauranteDAO = new RestauranteDAO(connection);
            produtoDAO = new ProdutoDAO(connection);
            formaPagamentoDAO = new FormaPagamentoDAO(connection);
            enderecoDAO = new EnderecoDAO(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Erro ao conectar ao banco de dados.");
            alert.showAndWait();
            return;
        }

        configurarTelaRestaurantes(primaryStage);
        configurarTelaProdutos(primaryStage);
        configurarTelaCarrinho(primaryStage);
        configurarTelaFinalizacao(primaryStage);
        configurarTelaPedidos(primaryStage);
        primaryStage.setScene(sceneRestaurantes);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
    super.stop();
        if (connection != null) {
            connection.close();
        }
    }

    private void configurarTelaRestaurantes(Stage stage) {
        Label titulo = criarTitulo("Restaurantes Disponíveis");
        VBox vboxRestaurantes = new VBox(10);
        vboxRestaurantes.setPadding(new Insets(20));
        vboxRestaurantes.setStyle("-fx-background-color: #f0f8ff;");

            ArrayList<Restaurante> restaurantes = restauranteDAO.listarTodos();

            ListView<Button> restaurantListView = new ListView<>();
            for (Restaurante r : restaurantes) {
                Button btnRestaurante = new Button(r.getNome());
                btnRestaurante.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
                btnRestaurante.setOnAction(_ -> {
                    restauranteSelecionado = r;
                    this.pedido = new Pedido(0, 0, null, null, 0, 0, STYLESHEET_CASPIAN, null, restauranteSelecionado, null, null, null);
                    pedido.setRestaurante(restauranteSelecionado);
                    configurarTelaProdutos(stage);
                    stage.setScene(sceneProdutos);
                });
                restaurantListView.getItems().add(btnRestaurante);
            }

            vboxRestaurantes.getChildren().addAll(titulo, restaurantListView);
            sceneRestaurantes = new Scene(vboxRestaurantes, 500, 500);


        Button btnIrPedidos = new Button("Pedidos");
        btnIrPedidos.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
        btnIrPedidos.setOnAction(_ -> stage.setScene(scenePedidos));

        vboxRestaurantes.getChildren().add(btnIrPedidos);
    }

    private void configurarTelaProdutos(Stage stage) {
        if (restauranteSelecionado == null) {
            System.err.println("Erro: Nenhum restaurante foi selecionado.");
            return;
        }
        Label titulo = criarTitulo("Produtos do Restaurante");
        VBox vboxProdutos = new VBox(10);
        vboxProdutos.setPadding(new Insets(20));
        vboxProdutos.setStyle("-fx-background-color: #f0f8ff;");

            ArrayList<Produto> produtos = produtoDAO.listarPorRestaurante(restauranteSelecionado.getId());

            ListView<Button> produtoListView = new ListView<>();
            for (Produto p : produtos) {
                Button btnProduto = new Button(p.getNome() + " - R$ " + String.format("%.2f", p.getPreco()));
                btnProduto.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
                btnProduto.setOnAction(_ -> {
                    PedidoProduto pedidoProduto = new PedidoProduto(0, 1, LocalDateTime.now(), 1);
                    pedidoProduto.getProduto().add(p);
                    pedidoProduto.setPreco(p.getPreco());
                    pedido.getPedidoProduto().add(pedidoProduto);
                    pedido.setValor(pedido.getValor() + p.getPreco());

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Produto adicionado ao pedido");
                    alert.setContentText("Você selecionou " + p.getNome() + "\nPreço R$ " + String.format("%.2f", p.getPreco()));
                    alert.showAndWait();
                });
                produtoListView.getItems().add(btnProduto);
            }

            vboxProdutos.getChildren().addAll(titulo, produtoListView);
            sceneProdutos = new Scene(vboxProdutos, 500, 500);

        Button btnCarrinho = new Button("Ir para o Carrinho");
        btnCarrinho.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
        btnCarrinho.setOnAction(_ -> {
            configurarTelaCarrinho(stage);
            stage.setScene(sceneCarrinho);
        });

        vboxProdutos.getChildren().addAll(btnCarrinho);
    }

    private void configurarTelaCarrinho(Stage stage) {
        if (pedido == null) {
            System.err.println("Erro: Pedido não foi inicializado.");
            return;
        }

        Label titulo = criarTitulo("Carrinho");
        VBox vboxCarrinho = new VBox(10);
        vboxCarrinho.setPadding(new Insets(20));
        vboxCarrinho.setStyle("-fx-background-color: #f0f8ff;");

        if (pedido.getPedidoProduto() != null) {
            for (PedidoProduto pp : pedido.getPedidoProduto()) {
                Label lblProduto = new Label(pp.getProduto().get(0).getNome() + " x" + pp.getQuantidade() + 
                                             " - R$" + pp.getPreco());
                lblProduto.setStyle("-fx-text-fill: blue;");
                vboxCarrinho.getChildren().add(lblProduto);
            }
        }

        Label lblTotal = new Label("Total: R$ " + pedido.getValor());
        lblTotal.setStyle("-fx-font-size: 18px; -fx-text-fill: blue;");

        Label lblEnderecoSelecionado = new Label("Endereço: " + (pedido.getEndereco() != null ? pedido.getEndereco() : "Nenhum endereço selecionado"));
        lblEnderecoSelecionado.setStyle("-fx-text-fill: blue;");

        Label lblFormaPagamentoSelecionada = new Label("Pagamento: " + (pedido.getFormaPagamento() != null ? pedido.getFormaPagamento().getFormaPagamento() : "Nenhuma forma pagamento selecionada"));
        lblFormaPagamentoSelecionada.setStyle("-fx-text-fill: blue;");

        ComboBox<FormaPagamento> comboFormaPagamento = new ComboBox<>();
        comboFormaPagamento.setPromptText("Escolha uma forma de pagamento");

        ArrayList<FormaPagamento> formasPagamento = formaPagamentoDAO.listarTodos();
        comboFormaPagamento.getItems().addAll(formasPagamento);

        comboFormaPagamento.setOnAction(_ -> {
            FormaPagamento formaSelecionada = comboFormaPagamento.getValue();
                if (formaSelecionada != null) {
                    pedido.setFormaPagamento(formaSelecionada);
                    lblFormaPagamentoSelecionada.setText("Forma de Pagamento: " + formaSelecionada.getFormaPagamento());
                } else {
                     System.err.println("Forma de pagamento não selecionada.");
                }
            });

        Button btnFinalizar = new Button("Finalizar Pedido");
        btnFinalizar.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
        btnFinalizar.setOnAction(_ -> {
            if (pedido != null) {
                if (pedido.getEndereco() == null) {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Por favor, selecione um endereço.");
                    alert.showAndWait();
                    return;
                }

                if (pedido.getStatusEntrega() == null) {
                pedido.setStatusEntrega(new StatusEntrega(0, 0, LocalDateTime.now(), StatusEntregaEnum.EM_PRODUCAO, pedido));
                }
                
                iniciarThreadStatusPedido(pedido);
                pedido.definirDataPedidoAutomaticamente();
                pedidoDAO.salvar(pedido);

                configurarTelaFinalizacao(stage);
                stage.setScene(sceneFinalizacao);
                pedido = null;
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Nenhum pedido foi realizado.");
                alert.showAndWait();
            }
        });

        Button btnSelecionarEndereco = new Button("Cadastra Endereço");
        btnSelecionarEndereco.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
        btnSelecionarEndereco.setOnAction(_ -> mostrarEnderecos(stage));

        Button btnSelecionarPagamento = new Button("Selecionar Forma de Pagamento");
        btnSelecionarPagamento.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
        btnSelecionarPagamento.setOnAction(_ -> mostrarFormasPagamento(stage));

        vboxCarrinho.getChildren().addAll(lblEnderecoSelecionado, lblFormaPagamentoSelecionada, titulo, lblTotal, btnSelecionarPagamento, btnSelecionarEndereco, btnFinalizar);
        sceneCarrinho = new Scene(vboxCarrinho, 500, 500);
    }

    private void mostrarFormasPagamento(Stage stage) {
        VBox vboxFormasPagamento = new VBox(10);
        vboxFormasPagamento.setPadding(new Insets(20));
        vboxFormasPagamento.setStyle("-fx-background-color: #f0f8ff;");

        Label titulo = criarTitulo("Selecione uma Forma de Pagamento");
        vboxFormasPagamento.getChildren().add(titulo);

        ArrayList<FormaPagamento> formasPagamento;
        formasPagamento = formaPagamentoDAO.listarTodos();
              

        for (FormaPagamento forma : formasPagamento) {
            Button btnFormaPagamento = new Button(forma.getFormaPagamento());
            btnFormaPagamento.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
            btnFormaPagamento.setOnAction(_ -> {
                pedido.setFormaPagamento(forma);
                configurarTelaCarrinho(stage);
                stage.setScene(sceneCarrinho);
            });
            vboxFormasPagamento.getChildren().add(btnFormaPagamento);
        }

        Button btnVoltar = new Button("Voltar");
        btnVoltar.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
        btnVoltar.setOnAction(_ -> stage.setScene(sceneCarrinho));
        vboxFormasPagamento.getChildren().add(btnVoltar);

        Scene sceneFormasPagamento = new Scene(vboxFormasPagamento, 500, 500);
        stage.setScene(sceneFormasPagamento);
    }

    private void mostrarEnderecos(Stage stage) {
        VBox vboxEnderecos = new VBox(10);
        vboxEnderecos.setPadding(new Insets(20));
        vboxEnderecos.setStyle("-fx-background-color: #f0f8ff;");

        Label titulo = criarTitulo("Selecione um Endereço");
        vboxEnderecos.getChildren().add(titulo);

        TextField cepField = new TextField();
        cepField.setPromptText("Digite o CEP");
        vboxEnderecos.getChildren().add(cepField);

        Button btnBuscar = new Button("Buscar Endereço");
        btnBuscar.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
        btnBuscar.setOnAction(_ -> {
            String cep = cepField.getText();
            buscarEnderecoPorCep(cep, vboxEnderecos, stage);
        });
        vboxEnderecos.getChildren().add(btnBuscar);

        ArrayList<Endereco> enderecos;
        enderecos = enderecoDAO.listarTodos();


        for (Endereco endereco : enderecos) {
            Button btnEndereco = new Button(endereco.getRua() + ", " + endereco.getNumero() + " - " + endereco.getCidade());
            btnEndereco.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
            btnEndereco.setOnAction(_ -> {
                pedido.setEndereco(endereco);
                configurarTelaCarrinho(stage);
                stage.setScene(sceneCarrinho); 
            });

            vboxEnderecos.getChildren().add(btnEndereco);
        }

        Button btnVoltar = new Button("Voltar");
        btnVoltar.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
        btnVoltar.setOnAction(_ -> stage.setScene(sceneCarrinho));
        vboxEnderecos.getChildren().add(btnVoltar);;

        Scene sceneEnderecos = new Scene(vboxEnderecos, 500, 500);
        stage.setScene(sceneEnderecos);

    }

    private void buscarEnderecoPorCep(String cep, VBox vboxEnderecos, Stage stage) {
    new Thread(() -> {
        try {
            String json = ViaCepClient.buscarEnderecoPorCep(cep);
            String logradouro = ViaCepClient.extractValueFromJson(json, "logradouro");
            String bairro = ViaCepClient.extractValueFromJson(json, "bairro");
            String localidade = ViaCepClient.extractValueFromJson(json, "localidade");
            String uf = ViaCepClient.extractValueFromJson(json, "uf");

            Endereco endereco = new Endereco(1, 1, LocalDateTime.now(), "Rua A", "Bairro B", "123", "Cidade C", "Estado D", "Ponto X", "Complemento Y", "12345-678");
            endereco.setCep(cep);
            endereco.setRua(logradouro != null ? logradouro : "");
            endereco.setBairro(bairro != null ? bairro : "");
            endereco.setCidade(localidade != null ? localidade : "");
            endereco.setEstado(uf != null ? uf : "");

            Platform.runLater(() -> mostrarFormularioEndereco(endereco, vboxEnderecos, stage));
        } catch (Exception e) {
            e.printStackTrace();
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Erro ao buscar endereço: " + e.getMessage());
                alert.showAndWait();
            });
        }
    }).start();
}


private void mostrarFormularioEndereco(Endereco endereco, VBox vboxEnderecos, Stage stage) {
    vboxEnderecos.getChildren().clear();

    Label titulo = criarTitulo("Cadastrar Endereço");
    vboxEnderecos.getChildren().add(titulo);

    TextField txtRua = new TextField(endereco.getRua().isEmpty() ? "" : endereco.getRua());
    txtRua.setPromptText("Rua (preencha se estiver vazio)");

    TextField txtBairro = new TextField(endereco.getBairro().isEmpty() ? "" : endereco.getBairro());
    txtBairro.setPromptText("Bairro (preencha se estiver vazio)");

    TextField txtCidade = new TextField(endereco.getCidade().isEmpty() ? "" : endereco.getCidade());
    txtCidade.setPromptText("Cidade (preencha se estiver vazio)");

    TextField txtEstado = new TextField(endereco.getEstado().isEmpty() ? "" : endereco.getEstado());
    txtEstado.setPromptText("Estado (preencha se estiver vazio)");

    TextField txtNumero = new TextField();
    txtNumero.setPromptText("Número");

    TextField txtComplemento = new TextField();
    txtComplemento.setPromptText("Complemento");

    TextField txtPontoReferencia = new TextField();
    txtPontoReferencia.setPromptText("Ponto de Referência");

    TextField txtTipoEndereco = new TextField();
    txtTipoEndereco.setPromptText("Tipo de Endereço");

    Label lblCep = new Label("CEP: " + endereco.getCep());

    vboxEnderecos.getChildren().addAll(
        lblCep, txtRua, txtBairro, txtCidade, txtEstado,
        txtNumero, txtComplemento, txtPontoReferencia, txtTipoEndereco
    );

    Button btnSalvar = new Button("Salvar Endereço");
    btnSalvar.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
    btnSalvar.setOnAction(_ -> {
        String rua = txtRua.getText().trim();
        String bairro = txtBairro.getText().trim();
        String cidade = txtCidade.getText().trim();
        String estado = txtEstado.getText().trim();
        String numero = txtNumero.getText().trim();
        String complemento = txtComplemento.getText().trim();
        String pontoReferencia = txtPontoReferencia.getText().trim();
        String tipoEndereco = txtTipoEndereco.getText().trim();

        if (rua.isEmpty() || bairro.isEmpty() || cidade.isEmpty() || estado.isEmpty() || numero.isEmpty() || tipoEndereco.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Preencha todos os campos obrigatórios.");
            alert.showAndWait();
            return;
        }

        endereco.setRua(rua);
        endereco.setBairro(bairro);
        endereco.setCidade(cidade);
        endereco.setEstado(estado);
        endereco.setNumero(numero);
        endereco.setComplemento(complemento);
        endereco.setPontoReferencia(pontoReferencia);
        endereco.setTipoEndereco(tipoEndereco);

        enderecoDAO.salvar(endereco);
        pedido.setEndereco(endereco);

        configurarTelaCarrinho(stage);
        stage.setScene(sceneCarrinho);
    });

    vboxEnderecos.getChildren().add(btnSalvar);
}


    private void configurarTelaFinalizacao(Stage stage) {
        Label titulo = criarTitulo("Pedido Finalizado");
        VBox vboxFinalizacao = new VBox(10);
        vboxFinalizacao.setPadding(new Insets(20));
        vboxFinalizacao.setStyle("-fx-background-color: #f0f8ff;");

        lblStatusPedido = new Label("Status do pedido: Aguardando confirmação...");
        lblStatusPedido.setStyle("-fx-font-size: 16px; -fx-text-fill: blue;");

        Button btnIrMeusPedidos = new Button("Pedidos");
        btnIrMeusPedidos.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
        btnIrMeusPedidos.setOnAction(_ -> stage.setScene(scenePedidos));

        Button btnVoltar = new Button("Voltar para Restaurantes");
        btnVoltar.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
        btnVoltar.setOnAction(_ -> stage.setScene(sceneRestaurantes));

        vboxFinalizacao.getChildren().addAll(titulo,lblStatusPedido, btnIrMeusPedidos, btnVoltar);
        sceneFinalizacao = new Scene(vboxFinalizacao, 500, 500);
    }

    private void configurarTelaPedidos(Stage stage) {
        Label titulo = criarTitulo("Meus Pedidos");
        VBox vboxPedidos = new VBox(10);
        vboxPedidos.setPadding(new Insets(20));
        vboxPedidos.setStyle("-fx-background-color: #f0f8ff;");

        ObservableList<String> pedidos = FXCollections.observableArrayList();

        for (Pedido pedido : pedidoDAO.listarTodos()) {
            pedidos.add("Pedido #" + pedido.getId() + ": R$ " + String.format("%.2f", pedido.getValor()) + 
                " - " + pedido.getObservacoes());
        }

        pedidosListView.setItems(pedidos);

        vboxPedidos.getChildren().addAll(titulo, pedidosListView);
    
        Button btnVoltar = new Button("Voltar");
        btnVoltar.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
        btnVoltar.setOnAction(_ -> stage.setScene(sceneRestaurantes));

        vboxPedidos.getChildren().add(btnVoltar);
        scenePedidos = new Scene(vboxPedidos, 500, 500);
    }

    private void atualizarStatusPedido(Pedido pedido, StatusEntrega novoStatus) {
        Platform.runLater(() -> {
            pedido.setStatusEntrega(novoStatus);
            lblStatusPedido.setText("Status do pedido: " + novoStatus.getStatusEntrega());
        });
    }

    private void iniciarThreadStatusPedido(Pedido pedido) {
        new Thread(() -> {
            try {
                Thread.sleep(5000);
                atualizarStatusPedido(pedido, new StatusEntrega(2, 1, LocalDateTime.now(), StatusEntregaEnum.EM_PRODUCAO, pedido));

                Thread.sleep(4000);
                atualizarStatusPedido(pedido, new StatusEntrega(3, 1 , LocalDateTime.now(), StatusEntregaEnum.EM_ROTA_ENTREGA, pedido));

                Thread.sleep(3000);
                atualizarStatusPedido(pedido, new StatusEntrega(4, 1, LocalDateTime.now(), StatusEntregaEnum.ENTREGUE, pedido));

            } catch (InterruptedException e) {
            e.printStackTrace();
            }
        }).start();
    }


    private Label criarTitulo(String texto) {
        Label titulo = new Label(texto);
        titulo.setStyle("-fx-font-size: 24px; -fx-text-fill: blue;");
        return titulo;
    }
}