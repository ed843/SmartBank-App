package projects.first_topic.smart_bank_app.util;
import java.sql.*;

public class DAOUtil {
    private DAOUtil() {
    }

    /**
     * Returns a PreparedStatement of the given connection, set with the given SQL query and the
     * given parameter values.
     */
    public static PreparedStatement preparedStatement
    (Connection connection, String sql, boolean returnGenertedKeys, Object... values) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql,
                returnGenertedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);
        setValues(statement, values);
        return statement;
    }

    /**
     * Set the given parameter values in the given PreparedStatement.
     */
    public static void setValues(PreparedStatement statement, Object... values)
            throws SQLException{
        for (int i = 0; i < values.length; i++) {
            statement.setObject(i + 1, values[i]);
        }
    }
}
