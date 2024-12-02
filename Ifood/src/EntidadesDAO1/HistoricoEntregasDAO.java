package EntidadesDAO1;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ConexaoBanco.DatabaseManager;
import ConexaoBanco.MySQLDatabaseManager;
import Entidades1.Base;
import Entidades1.HistoricoEntregas;
import Entidades1.Pedido;
import Entidades1.StatusEntrega;

public class HistoricoEntregasDAO {
    private final DatabaseManager dbManager = new MySQLDatabaseManager();
    private final StatusEntregaDAO statusEntregaDAO;

    public HistoricoEntregasDAO(StatusEntregaDAO statusEntregaDAO) {
        this.statusEntregaDAO = new StatusEntregaDAO();
    }

    public void salvar(HistoricoEntregas historicoEntregas) {
        String sql = "INSERT INTO historico_entregas (dataEntrega, statusEntrega_id, pedido_id) VALUES (?, ?, ?)";

        try (Connection connection = dbManager.openConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setTimestamp(1, Timestamp.valueOf(historicoEntregas.getDataHora()));
            stmt.setInt(2, historicoEntregas.getStatusEntrega().getId());
            stmt.setInt(3, historicoEntregas.getPedido().getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir histórico de entregas", e);
        }
    }

    public ArrayList<HistoricoEntregas> listarTodos() {
        String sql = "SELECT * FROM historico_entregas";
        List<HistoricoEntregas> historicosEntregas = new ArrayList<>();

        try (Connection connection = dbManager.openConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                HistoricoEntregas historicoEntregas = new HistoricoEntregas(
                        rs.getInt("id"),
                        Base.ENABLED,
                        rs.getTimestamp("dataCreat").toLocalDateTime(),
                        rs.getTimestamp("dataEntrega").toLocalDateTime()
                );
                historicosEntregas.add(historicoEntregas);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar históricos de entregas", e);
        }

        return new ArrayList<>(historicosEntregas);
    }

    public HistoricoEntregas buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM historicoDeEntregas WHERE id = ?";

        try (Connection connection = dbManager.openConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    StatusEntrega statusEntrega = statusEntregaDAO.buscarPorId(rs.getInt("id_status_entrega"));
                    Pedido pedido = new PedidoDAO(connection).buscarPorId(rs.getInt("id_pedido"));
                    LocalDateTime dataHora = rs.getTimestamp("data_hora").toLocalDateTime();
                    return new HistoricoEntregas(
                        rs.getInt("id"),
                        Base.ENABLED,
                        LocalDateTime.now(),
                        dataHora,
                        statusEntrega,
                        pedido
                    );
                }
            }
        }
        return null;
    }

    public void atualizar(HistoricoEntregas historicoEntregas) {
        String sql = "UPDATE historico_entregas SET dataEntrega = ?, statusEntrega_id = ?, pedido_id = ? WHERE id = ?";

        try (Connection connection = dbManager.openConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setTimestamp(1, Timestamp.valueOf(historicoEntregas.getDataHora()));
            stmt.setInt(2, historicoEntregas.getStatusEntrega().getId());
            stmt.setInt(3, historicoEntregas.getPedido().getId());
            stmt.setInt(4, historicoEntregas.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar histórico de entregas", e);
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM historico_entregas WHERE id = ?";

        try (Connection connection = dbManager.openConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir histórico de entregas", e);
        }
    }
}