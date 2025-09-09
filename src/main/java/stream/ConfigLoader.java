package stream;

import util.PropertyLoader;

import java.util.Properties;

public class ConfigLoader {

    public static ApiParameters readConfiguration() {
        Properties properties = PropertyLoader.loadApplicationProperties();

        String apiKey = properties.getProperty("api-key");
        String modelName = properties.getProperty("model-name");
        String apiUrl = properties.getProperty("api-url")
                .replace("$api-key", apiKey)
                .replace("$model-name", modelName);

        return new ApiParameters(apiKey, modelName,apiUrl);
    }

}
