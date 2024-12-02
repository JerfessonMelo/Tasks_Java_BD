package EntidadesDAO1;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import ConexaoBanco.DatabaseManager;
import ConexaoBanco.MySQLDatabaseManager;
import Entidades1.Base;
import Entidades1.FormaPagamento;

public class FormaPagamentoDAO {
    private final DatabaseManager dbManager = new MySQLDatabaseManager();
    @SuppressWarnings("unused")
    private Connection connection;

    public FormaPagamentoDAO(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<FormaPagamento> listarTodos() {
        String sql = "SELECT * FROM formapagamento";
        List<FormaPagamento> formasPagamento = new ArrayList<>();

        try (Connection connection = dbManager.openConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                FormaPagamento formaPagamento = new FormaPagamento(
                        rs.getInt("id"),
                        Base.ENABLED,
                        null,
                        rs.getString("formaPagamento")
                );
                formasPagamento.add(formaPagamento);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar formas de pagamento", e);
        }

        return new ArrayList<>(formasPagamento);
    }

        public FormaPagamento buscarFormaPagamentoPorId(int id) throws SQLException {
        String sql = "SELECT * FROM formapagamento WHERE id = ?";

        try (Connection connection = dbManager.openConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new FormaPagamento(
                        rs.getInt("id"),
                        Base.ENABLED,
                        null,
                        rs.getString("formaPagamento")
                    );
                }
            }
        }
        return null;
    }
}