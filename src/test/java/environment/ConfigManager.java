package environment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigManager
{
    private static final String pathToProperties = "src/test/resources/setup.properties";
    private static final Map<String, Properties> config = new HashMap<>();

    public static void init(String fileName)
    {
        try(InputStream inputStream = new FileInputStream(fileName))
        {
            Properties properties = new Properties();
            properties.load(inputStream);
            config.put(fileName, properties);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    protected static String getValue(String key, String fileName)
    {
        if(config == null || !config.containsKey(fileName))
        {
            init(fileName);
        }

        String value = config.get(fileName).getProperty(key);
        if(value != null)
        {
            return value;
        }

        return System.getenv(key);
    }

    protected static String getValue(String key) { return getValue(key, pathToProperties); }
}