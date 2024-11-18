import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FrontEnd {
    private JList<String> listProdutos;
    private JList<String> listAdicionais;
    private DefaultListModel<String> pedidoModel;
    private JLabel tempo;
    private JFrame frame;
    private String formaPagamento;

    @SuppressWarnings("unused")
    public FrontEnd() {
        frame = new JFrame("Ifood");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 500);
        frame.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel listasPanel = new JPanel(new GridLayout(1, 4, 10, 10));

        listProdutos = new JList<>(new String[]{"Pizza", "Hambúrguer", "Cachorro Quente"});
        listProdutos.addListSelectionListener(e -> carregarAdicionais());

        listAdicionais = new JList<>(new DefaultListModel<>());
        listAdicionais.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        JButton btnAdicionar = new JButton("Adicionar ao Pedido");
        btnAdicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleAdicionar();
            }
        });

        pedidoModel = new DefaultListModel<>();
        JList<String> pedido = new JList<>(pedidoModel);

        listasPanel.add(new JScrollPane(listProdutos));
        listasPanel.add(new JScrollPane(listAdicionais));
        listasPanel.add(btnAdicionar);
        listasPanel.add(new JScrollPane(pedido));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel lblTempo = new JLabel("Tempo:");
        tempo = new JLabel("0");

        JButton btnFinalizar = new JButton("Finalizar Pedido");
        btnFinalizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleFinalizarCompra();
            }
        });

        JButton btnRegistrarEndereco = new JButton("Registrar Endereço");
        btnRegistrarEndereco.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleRegistroEndereco();
            }
        });

        JButton btnStatusPedido = new JButton("Ver Status do Pedido");
        btnStatusPedido.addActionListener(e -> mostrarTelaPedido());

        buttonPanel.add(btnFinalizar);
        buttonPanel.add(lblTempo);
        buttonPanel.add(tempo);
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(btnRegistrarEndereco);
        buttonPanel.add(btnStatusPedido);

        mainPanel.add(listasPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void carregarAdicionais() {
        DefaultListModel<String> adicionaisModel = new DefaultListModel<>();
        String produtoSelecionado = listProdutos.getSelectedValue();
        if (produtoSelecionado != null) {
            switch (produtoSelecionado) {
                case "Pizza":
                    adicionaisModel.addElement("Calabresa Extra");
                    adicionaisModel.addElement("Cebola Caramelizada");
                    adicionaisModel.addElement("Batata Frita");
                    break;
                case "Hambúrguer":
                    adicionaisModel.addElement("Batata Frita");
                    adicionaisModel.addElement("Coca-cola");
                    adicionaisModel.addElement("Salada Extra");
                    break;
                case "Cachorro Quente":
                    adicionaisModel.addElement("Batata Palha");
                    adicionaisModel.addElement("Molho verde");
                    adicionaisModel.addElement("Purê de Batata");
                    break;
                default:
                    adicionaisModel.addElement("Nenhum adicional disponível");
            }
        }
        listAdicionais.setModel(adicionaisModel);
    }

    private void handleAdicionar() {
        String produto = listProdutos.getSelectedValue();
        if (produto == null) {
            JOptionPane.showMessageDialog(frame, "Selecione um produto.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        List<String> adicionaisSelecionados = listAdicionais.getSelectedValuesList();
        if (adicionaisSelecionados.size() > 3) {
            JOptionPane.showMessageDialog(frame, "Você pode selecionar no máximo 3 adicionais.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String pedidoText = produto + " + " + String.join(", ", adicionaisSelecionados);
        pedidoModel.addElement(pedidoText);
    }

    @SuppressWarnings("unused")
    private void handleRegistroEndereco() {
        JDialog enderecoDialog = new JDialog(frame, "Registrar Endereço", true);
        enderecoDialog.setSize(400, 400);
        enderecoDialog.setLayout(new GridLayout(10, 1));

        JTextField cep = new JTextField();
        cep.setBorder(BorderFactory.createTitledBorder("Cep"));
        JTextField rua = new JTextField();
        rua.setBorder(BorderFactory.createTitledBorder("Rua"));
        JTextField numero = new JTextField();
        numero.setBorder(BorderFactory.createTitledBorder("Número"));
        JTextField bairro = new JTextField();
        bairro.setBorder(BorderFactory.createTitledBorder("Bairro"));
        JTextField cidade = new JTextField();
        cidade.setBorder(BorderFactory.createTitledBorder("Cidade"));
        JTextField estado = new JTextField();
        estado.setBorder(BorderFactory.createTitledBorder("Estado"));
        JTextField complemento = new JTextField();
        complemento.setBorder(BorderFactory.createTitledBorder("Complemento"));
        JTextField referencia = new JTextField();
        referencia.setBorder(BorderFactory.createTitledBorder("Referência"));
        JTextField tipoEndereco = new JTextField();
        tipoEndereco.setBorder(BorderFactory.createTitledBorder("Tipo de Endereço"));

        JButton btnSalvar = new JButton("Salvar Endereço");
        btnSalvar.addActionListener(e -> {
            if (cep.getText().isEmpty() || rua.getText().isEmpty() || numero.getText().isEmpty()) {
                JOptionPane.showMessageDialog(enderecoDialog, "Todos os campos obrigatórios devem ser preenchidos.", "Erro", JOptionPane.ERROR_MESSAGE);
            } else if (!cep.getText().matches("\\d+")) {
                JOptionPane.showMessageDialog(enderecoDialog, "Cep deve conter apenas números.", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(enderecoDialog, "Endereço registrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                enderecoDialog.dispose();
            }
        });

        enderecoDialog.add(cep);
        enderecoDialog.add(rua);
        enderecoDialog.add(numero);
        enderecoDialog.add(bairro);
        enderecoDialog.add(cidade);
        enderecoDialog.add(estado);
        enderecoDialog.add(complemento);
        enderecoDialog.add(referencia);
        enderecoDialog.add(tipoEndereco);
        enderecoDialog.add(btnSalvar);

        enderecoDialog.setVisible(true);
    }

    private void handleFinalizarCompra() {
        if (pedidoModel.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Adicione produtos ao carrinho antes de finalizar.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        showFormaPagamentoDialog();
        if (formaPagamento == null || formaPagamento.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Forma de pagamento não selecionada.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(frame, "Pedido finalizado com a forma de pagamento: " + formaPagamento, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        startTemporizador();
    }

    private void showFormaPagamentoDialog() {
        String[] opcoesPagamento = {"Cartão de Crédito", "Cartão de Débito", "Pix", "Dinheiro"};
        formaPagamento = (String) JOptionPane.showInputDialog(
                frame,
                "Escolha a forma de pagamento:",
                "Forma de Pagamento",
                JOptionPane.PLAIN_MESSAGE,
                null,
                opcoesPagamento,
                opcoesPagamento[0]
        );
    }

    private void startTemporizador() {
        new Thread(() -> {
            try {
                for (int i = 1; i <= 30; i++) {
                    final int seconds = i;
                    SwingUtilities.invokeLater(() -> tempo.setText(String.valueOf(seconds)));
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
@SuppressWarnings("unused")
private void mostrarTelaPedido() {
    JDialog statusDialog = new JDialog(frame, "Detalhes do Pedido", true);
    statusDialog.setSize(500, 400);
    statusDialog.setLayout(new BorderLayout());

    DefaultListModel<String> pedidoDetalhesModel = new DefaultListModel<>();
    for (int i = 0; i < pedidoModel.getSize(); i++) {
        pedidoDetalhesModel.addElement(pedidoModel.getElementAt(i));
    }
    JList<String> pedidoList = new JList<>(pedidoDetalhesModel);

    JLabel pagamentoLabel = new JLabel("Forma de pagamento: Cartão de Crédito");

    JLabel statusLabel = new JLabel("Status do pedido: Em produção");

    JPanel detalhesPanel = new JPanel(new BorderLayout());
    detalhesPanel.add(new JScrollPane(pedidoList), BorderLayout.CENTER);
    detalhesPanel.add(pagamentoLabel, BorderLayout.NORTH);
    detalhesPanel.add(statusLabel, BorderLayout.SOUTH);

    statusDialog.add(detalhesPanel, BorderLayout.CENTER);

    JButton avancarStatusBtn = new JButton("Avançar Status");
    avancarStatusBtn.addActionListener(e -> {
        String status = statusLabel.getText();
        if (status.contains("Em produção")) {
            statusLabel.setText("Status do pedido: Em rota de entrega");
        } else if (status.contains("Em rota de entrega")) {
            statusLabel.setText("Status do pedido: Entregue");
            avancarStatusBtn.setEnabled(false);
        }
    });

    statusDialog.add(avancarStatusBtn, BorderLayout.SOUTH);

    statusDialog.setVisible(true);
}
}

