package EntidadesDAO1;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ConexaoBanco.DatabaseManager;
import ConexaoBanco.MySQLDatabaseManager;
import Entidades1.Base;
import Entidades1.Endereco;

public class EnderecoDAO {
    private final DatabaseManager dbManager = new MySQLDatabaseManager();
    @SuppressWarnings("unused")
    private Connection connection;

    public EnderecoDAO(Connection connection) {
        this.connection = connection;
    }

    public void salvar(Endereco endereco) {
        String sql = "INSERT INTO endereco (rua, bairro, numero, cidade, estado, pontoReferencia, complemento, cep, tipo_endereco) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = dbManager.openConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, endereco.getRua());
            stmt.setString(2, endereco.getBairro());
            stmt.setString(3, endereco.getNumero());
            stmt.setString(4, endereco.getCidade());
            stmt.setString(5, endereco.getEstado());
            stmt.setString(6, endereco.getPontoReferencia());
            stmt.setString(7, endereco.getComplemento());
            stmt.setString(8, endereco.getCep());
            stmt.setString(9, endereco.getTipoEndereco());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir endereço", e);
        }
    }

    public ArrayList<Endereco> listarTodos() {
        String sql = "SELECT * FROM endereco";
        List<Endereco> enderecos = new ArrayList<>();

        try (Connection connection = dbManager.openConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Endereco endereco = new Endereco(
                        rs.getInt("id"),
                        Base.ENABLED,
                        null,
                        rs.getString("rua"),
                        rs.getString("bairro"),
                        rs.getString("numero"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("pontoReferencia"),
                        rs.getString("complemento"),
                        rs.getString("cep")
                );
                enderecos.add(endereco);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar endereços", e);
        }

        return new ArrayList<>(enderecos);
    }

    public Endereco buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM endereco WHERE id = ?";

        try (Connection connection = dbManager.openConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Endereco(
                        rs.getInt("id"),
                        Base.ENABLED,
                        LocalDateTime.now(),
                        rs.getString("rua"),
                        rs.getString("bairro"),
                        rs.getString("numero"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("pontoReferencia"),
                        rs.getString("complemento"),
                        rs.getString("cep")
                    );
                }
            }
        }
        return null;
    }

}