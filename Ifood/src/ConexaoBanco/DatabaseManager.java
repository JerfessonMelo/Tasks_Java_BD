package ConexaoBanco;

import java.sql.Connection;

public interface DatabaseManager {
    Connection openConnection();
    void closeConnection(Connection connection);
}
