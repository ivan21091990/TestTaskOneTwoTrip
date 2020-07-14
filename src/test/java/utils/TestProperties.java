package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Класс для работы с properties
 */
public class TestProperties {
    private static Properties properties = new Properties();
    private static TestProperties INSTANCE = null;

    private TestProperties(){
        try {
            properties.load(new InputStreamReader(new FileInputStream(new File("src/test/java/resources/environment.properties")), "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static TestProperties getInstance() {
        if (INSTANCE == null){
            INSTANCE = new TestProperties();
        }
        else {
            try {
                properties.load(new InputStreamReader(new FileInputStream(new File("src/test/java/resources/environment.properties")), "UTF-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return INSTANCE;
    }

    public Properties getProperties() {
        return properties;
    }
}
