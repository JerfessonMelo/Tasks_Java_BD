package EntidadesDAO1;

import java.sql.*;

import ConexaoBanco.DatabaseManager;
import ConexaoBanco.MySQLDatabaseManager;
import Entidades1.Base;
import Entidades1.StatusEntrega;
import Entidades1.StatusEntregaEnum;

public class StatusEntregaDAO {
    private final DatabaseManager dbManager = new MySQLDatabaseManager();

    public void salvar(StatusEntrega statusEntrega) {
        String sql = "INSERT INTO status_entrega (statusEntrega) VALUES (?)";

        try (Connection connection = dbManager.openConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, statusEntrega.getStatusEntrega().getDescricao());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir status de entrega", e);
        }
    }

    public StatusEntrega buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM StatusEntrega WHERE id = ?";
        try (Connection connection = dbManager.openConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new StatusEntrega(
                        rs.getInt("id"),
                        Base.ENABLED,
                        null,
                        StatusEntregaEnum.valueOf(rs.getString("statusEntrega")),
                        null
                    );
                }
            }
        }
        return null;
    }

}