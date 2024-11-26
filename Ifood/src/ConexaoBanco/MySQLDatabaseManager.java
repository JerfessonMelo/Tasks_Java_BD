package ConexaoBanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDatabaseManager implements DatabaseManager {
    private final String url;
    private final String username;
    private final String password;

    public MySQLDatabaseManager() {
        this.url = "jdbc:mysql://" + EnvConfig.get("DB_HOST") + ":" + EnvConfig.get("DB_PORT") + "/" + EnvConfig.get("DB_NAME");
        this.username = EnvConfig.get("DB_USER");
        this.password = EnvConfig.get("DB_PASSWORD");
    }

    @Override
    public Connection openConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao abrir conexão com o banco de dados", e);
        }
    }

    @Override
    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão com o banco de dados", e);
            }
        }
    }
}
