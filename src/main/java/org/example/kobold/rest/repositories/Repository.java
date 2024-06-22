package org.example.kobold.rest.repositories;
import org.example.kobold.db.DBConnection;
import org.example.kobold.rest.CRUDInterface;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Repository<T> implements CRUDInterface<T> {

    public static String RELATION = "";
    public Connection connection = DBConnection.getConnection();

    protected Repository() {}

    protected T getInsertedOne(Statement statement) throws SQLException {
        ResultSet resultSet = statement.getGeneratedKeys();
        int id = -1;

        while (resultSet.next()) {
            id = resultSet.getInt(1); // returns 0, 0
        }

        return this.getById(id);
    }

}
