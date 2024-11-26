package ConexaoBanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://" + EnvConfig.get("DB_HOST") + ":" + EnvConfig.get("DB_PORT") + "/" + EnvConfig.get("DB_NAME");
        String user = EnvConfig.get("DB_USER");
        String password = EnvConfig.get("DB_PASSWORD");
        return DriverManager.getConnection(url, user, password);
    }
}
