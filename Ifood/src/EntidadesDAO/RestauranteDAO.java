import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import ConexaoBanco.DatabaseManager;
import ConexaoBanco.MySQLDatabaseManager;

public class RestauranteDAO {
    private final DatabaseManager dbManager = new MySQLDatabaseManager();
    private final EnderecoDAO enderecoDAO;

    public RestauranteDAO(Connection connection) {
        this.enderecoDAO = new EnderecoDAO(connection);
    }
    
    public ArrayList<Restaurante> listarTodos() {
        String sql = "SELECT * FROM restaurante";
        List<Restaurante> restaurantes = new ArrayList<>();

        try (Connection connection = dbManager.openConnection();
            Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Restaurante restaurante = new Restaurante(
                        rs.getInt("id"),
                        rs.getInt("status"),
                        null,
                        rs.getString("nome"),
                        rs.getString("telefone"),
                        rs.getFloat("valorMinimoEntrega"),
                        rs.getBoolean("is_retirada"),
                        null,
                        null,
                        null
                );
                restaurantes.add(restaurante);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar restaurantes", e);
        }

        return new ArrayList<>(restaurantes);
    }

    public Restaurante buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Restaurante WHERE id = ?";

        try (Connection connection = dbManager.openConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Endereco endereco = enderecoDAO.buscarPorId(rs.getInt("id_endereco_restaurante"));
                    return new Restaurante(
                        rs.getInt(" id"),
                        Base.ENABLED,
                        rs.getTimestamp("dataCreat2").toLocalDateTime(),
                        rs.getString("nome"),
                        rs.getString("telefone"),
                        rs.getFloat("valorMinimoEntrega"),
                        rs.getBoolean("is_retirada"),
                        null,
                        endereco,
                        null
                    );
                }
            }
        }
        return null;
    }
}