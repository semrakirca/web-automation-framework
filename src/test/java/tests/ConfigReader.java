package tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

// Bu sınıf config.properties dosyasını okur
public class ConfigReader {

    private static Properties properties;

    static {
        try {
            properties = new Properties();
            FileInputStream fis =
                    new FileInputStream("src/test/resources/config.properties");
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("config.properties okunamadı!");
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
