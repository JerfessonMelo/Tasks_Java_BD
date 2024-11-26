import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import ConexaoBanco.DatabaseManager;
import ConexaoBanco.MySQLDatabaseManager;

public class PedidoDAO {
    private final DatabaseManager dbManager = new MySQLDatabaseManager();
    private FormaPagamentoDAO formaPagamentoDAO;
    private EnderecoDAO enderecoDAO;
    @SuppressWarnings("unused")
    private Connection connection;
    private List<Produto> carrinho = new ArrayList<>();
    
    public PedidoDAO(Connection connection) {
        this.formaPagamentoDAO = new FormaPagamentoDAO(connection);
        this.enderecoDAO = new EnderecoDAO(connection);
        this.connection = connection;
    }

    public void salvar(Pedido pedido) {
        String sql = "INSERT INTO pedido (dataPedido, id_restaurante, valor, taxaEntrega, id_forma_pagamento, observacoes, troco, id_endereco) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = dbManager.openConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setTimestamp(1, Timestamp.valueOf(pedido.getDataPedido()));
            stmt.setInt(2, pedido.getRestaurante().getId());
            stmt.setFloat(3, pedido.getValor());
            stmt.setFloat(4, pedido.getTaxaEntrega());
            stmt.setInt(5, pedido.getFormaPagamento().getId());
            stmt.setString(6, pedido.getObservacoes());
            stmt.setObject(7, pedido.getTroco());
            stmt.setInt(8, pedido.getEndereco().getId());
            stmt.executeUpdate();

            carrinho.clear();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir pedido", e);
        }
    }

    public Pedido buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM pedido WHERE id = ?";

        try (Connection connection = dbManager.openConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    FormaPagamento formaPagamento = formaPagamentoDAO.buscarFormaPagamentoPorId(rs.getInt("id_forma_pagamento"));
                    Endereco endereco = enderecoDAO.buscarPorId(rs.getInt("id_endereco"));
                    Pedido pedido = new Pedido(
                        rs.getInt("id"),
                        Base.ENABLED,
                        null,
                        rs.getTimestamp("dataPedido").toLocalDateTime(),
                        rs.getFloat("valor"),
                        rs.getFloat("taxaEntrega"),
                        rs.getString("observacoes"),
                        rs.getFloat("troco"),
                        null,
                        formaPagamento,
                        null,
                        endereco
                    );
                    return pedido;
                }
            }
        }
        return null;
    }

    public ArrayList<Pedido> listarTodos() {
        String sql = "SELECT * FROM pedido";
        List<Pedido> pedidos = new ArrayList<>();

        try (Connection connection = dbManager.openConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                FormaPagamento formaPagamento = formaPagamentoDAO.buscarFormaPagamentoPorId(rs.getInt("id_forma_pagamento"));
                Pedido pedido = new Pedido(
                        rs.getInt("id"),
                        Base.ENABLED,
                        null,
                        rs.getTimestamp("dataPedido").toLocalDateTime(),
                        rs.getFloat("valor"),
                        rs.getFloat("taxaEntrega"),
                        rs.getString("observacoes"),
                        rs.getObject("troco", Float.class),
                        null,
                        formaPagamento,
                        null,
                        null
                );
                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar pedidos", e);
        }

        return new ArrayList<>(pedidos);
    }

}