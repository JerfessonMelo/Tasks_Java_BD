package EntidadesDAO1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import ConexaoBanco.DatabaseManager;
import ConexaoBanco.MySQLDatabaseManager;
import Entidades1.Base;
import Entidades1.Produto;
import Entidades1.Restaurante;

public class ProdutoDAO {
    private final DatabaseManager dbManager = new MySQLDatabaseManager();
    @SuppressWarnings("unused")
    private Connection connection;

    public ProdutoDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Produto> listarTodos() {
        String sql = "SELECT * FROM produto";
        List<Produto> produtos = new ArrayList<>();

        try (Connection connection = dbManager.openConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Produto produto = new Produto(
                        rs.getInt("id"),
                        rs.getInt("status"),
                        rs.getTimestamp("dataCreat").toLocalDateTime(),
                        rs.getString("nome"),
                        rs.getFloat("preco")
                );
                produtos.add(produto);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar produtos", e);
        }

        return produtos;
    }

    public ArrayList<Produto> listarPorRestaurante(int restauranteId) {
        String sql = "SELECT * FROM produto WHERE id_restaurante = ?";
        List<Produto> produtos = new ArrayList<>();

        try (Connection connection = dbManager.openConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {
        
            stmt.setInt(1, restauranteId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto(
                    rs.getInt("id"),
                    Base.ENABLED,
                    null,
                    rs.getString("nome"),
                    rs.getFloat("preco")
                );
                produtos.add(produto);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar produtos", e);
        }
        return new ArrayList<>(produtos);
    }

    public Produto buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Produto WHERE id = ?";

        try (Connection connection = dbManager.openConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Restaurante restaurante = new RestauranteDAO(connection).buscarPorId(rs.getInt("id_restaurante"));

                    Produto produto = new Produto(
                        rs.getInt("id"),
                        Base.ENABLED,
                        null,
                        rs.getString("nome"),
                        rs.getFloat("preco")
                    );
                    produto.setRestaurante(restaurante);
                    return produto;
                }
            }
        }
        return null;
    }
}