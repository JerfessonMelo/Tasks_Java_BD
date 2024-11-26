import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import ConexaoBanco.DatabaseManager;
import ConexaoBanco.MySQLDatabaseManager;

public class AcompanhamentoDAO {
    private final DatabaseManager dbManager = new MySQLDatabaseManager();

    public ArrayList<Acompanhamento> listarTodos() {
        String sql = "SELECT * FROM acompanhamentos";
        List<Acompanhamento> acompanhamentos = new ArrayList<>();

        try (Connection connection = dbManager.openConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Acompanhamento acompanhamento = new Acompanhamento(
                        rs.getInt("id"),
                        Base.ENABLED,
                        null,
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getFloat("valor")
                );
                acompanhamentos.add(acompanhamento);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar acompanhamentos", e);
        }

        return new ArrayList<>(acompanhamentos);
    }

}