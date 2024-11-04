package projects.first_topic.smart_bank_app.exception;
import java.sql.SQLException;

public class DAOException extends SQLException {
    public DAOException(String message) {
        super(message);
    }
}
