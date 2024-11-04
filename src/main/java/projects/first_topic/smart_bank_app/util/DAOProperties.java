package projects.first_topic.smart_bank_app.util;
import projects.first_topic.smart_bank_app.exception.DAOConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;
import static projects.first_topic.smart_bank_app.constant.ProjectConstant.*;

public class DAOProperties {
    private static final Properties PROPERTIES = new Properties();

    private final String propertyKeyName;

    static {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream propertiesFile = classLoader.getResourceAsStream(PROPERTIES_FILE);
        if (propertiesFile == null) {
            throw new DAOConfigurationException(
                    "Properties file '" + PROPERTIES_FILE + "' is missing in classpath.");
        }

        try {
            PROPERTIES.load(propertiesFile);
        } catch (IOException e) {
            throw new DAOConfigurationException(
                    "Cannot load properties file '" + PROPERTIES_FILE + "'.", e);
        }
    }

    public DAOProperties(String propertyKeyName) throws DAOConfigurationException {
        this.propertyKeyName = propertyKeyName;
    }

    /**
     * Returns the associate property value from the DAOProperties instance for the given key,
     * with an option to specify whether the property is mandatory or not.
     */
    public String getProperty(String key, boolean required) throws DAOConfigurationException {
        String fullKey = propertyKeyName + "." + key;
        String property = Optional.ofNullable(PROPERTIES.getProperty(fullKey))
                .map(String::trim)
                .filter(value -> !value.isEmpty())
                .orElse(null);
        if (required && property == null) {
            throw new DAOConfigurationException(
                    String.format("Required property '%s' is missing in properties file '%s'.",
                            fullKey, PROPERTIES_FILE));
        }
        return property;
    }
}
