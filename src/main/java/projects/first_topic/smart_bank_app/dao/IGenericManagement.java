package projects.first_topic.smart_bank_app.dao;
import java.sql.*;

public interface IGenericManagement<T, V> {
    void create(T element) throws SQLException;
    T findById(V id) throws SQLException;
}
