package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

// to read configuration from config.properties
// у этото класс единственная задача - взять информацию  из config.properties

public class ConfigReader {

    private static Properties properties;

    /*
    Checked and Unchecked exceptions
    checked before its compiled
     */

    static {
        // try with resource --> try/catch block can take a parameter, AutoCloseable:
        try(FileInputStream fileInputStream = new FileInputStream("config.properties")) {
            // file reader gets information as a byte code and makes it readable for java
            //  fileInputStream here is our config file as byte code
            properties = new Properties();
            // properties class can read byte code, so we need the file as byte code
            properties.load(fileInputStream);

        } catch (Exception e) {
            System.out.println("The config file did not load");
            e.printStackTrace();
        }

    }


    public static String getProperty(String key){
        return properties.getProperty(key);
    }




}
