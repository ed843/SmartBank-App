package projects.first_topic.smart_bank_app.exception;

public class DAOConfigurationException extends RuntimeException {
    public DAOConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
    public DAOConfigurationException(String message) {
        super(message);
    }
}
