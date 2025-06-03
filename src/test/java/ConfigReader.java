import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    static {
        try {
            // Путь к нашему файлу конфигурации
            String path = "src/test/resources/test.properties";
            FileInputStream input = new FileInputStream(path);
            properties = new Properties();
            properties.load(input);
            input.close();
        } catch (IOException e) {
            // Если файл не найден или не удалось прочитать, выводим ошибку
            e.printStackTrace();
        }
    }

    // Метод, который будет возвращать значение по его ключу
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}