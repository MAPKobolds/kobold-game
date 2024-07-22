package org.uniba.kobold.rest.repositories;
import org.uniba.kobold.rest.db.DBConnection;
import org.uniba.kobold.rest.CRUDInterface;

import java.sql.*;

/**
 * The type Repository.
 *
 * @param <T> the type parameter
 */
public abstract class Repository<T> implements CRUDInterface<T> {

    /**
     * The constant RELATION.
     */
    public static String RELATION = "";
    /**
     * The Connection.
     */
    public Connection connection = DBConnection.getConnection();

    /**
     * Instantiates a new Repository.
     */
    protected Repository() {}

    /**
     * Gets inserted one.
     *
     * @param statement the statement
     * @return the inserted one
     * @throws SQLException the sql exception
     */
    protected T getInsertedOne(Statement statement) throws SQLException {
        ResultSet resultSet = statement.getGeneratedKeys();
        int id = -1;

        while (resultSet.next()) {
            id = resultSet.getInt(1); // returns 0, 0
        }

        return this.getById(id);
    }

}
