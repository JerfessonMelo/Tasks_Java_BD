package ConexaoBanco;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EnvConfig {
    private static Properties properties = new Properties();

    static {

        File envFile = new File("C:/Users/jerfe/OneDrive/Aplicativos/Documentos/VsCodeJava/Ifood/src/.env");
        if (!envFile.exists()) {
            throw new RuntimeException("Arquivo .env não encontrado no diretório: " + envFile.getAbsolutePath());
        }

        try {
            properties.load(new FileInputStream(envFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
