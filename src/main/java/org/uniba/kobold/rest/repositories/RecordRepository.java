package org.uniba.kobold.rest.repositories;

import org.uniba.kobold.rest.models.Record;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RecordRepository extends Repository<Record> {

    public RecordRepository() {
        RELATION = "records";

        String createTableSQL = "CREATE TABLE IF NOT EXISTS records (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(255))";
        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Record save(Record record) {
        try {
            String sql = "INSERT INTO "+ RELATION + " (name, time)" + " VALUES (?, ?)";
            PreparedStatement preparedStmt = connection.prepareStatement(sql, new String[]{ "ID" });

            preparedStmt.setString (1, record.getName());
            preparedStmt.setLong(2, record.getTime());

            int executedSt = preparedStmt.executeUpdate();

            return executedSt == 1 ? this.getInsertedOne(preparedStmt) : null;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public int deleteById(int id) {
        try {
            String sql = "DELETE FROM "+ RELATION + " WHERE ID = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(sql);

            preparedStmt.setInt(1, id);
            int executedSt = preparedStmt.executeUpdate();

            return executedSt == 1 ? id : -1;
        } catch (SQLException e) {
            return -1;
        }
    }

    @Override
    public Record updateById(Record entity, int id) {
        try {
            String sql = "UPDATE "+ RELATION + " SET name = ?, time = ?" + " WHERE ID = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(sql, new String[]{ "ID" });

            preparedStmt.setString(1, entity.getName());
            preparedStmt.setLong(2, entity.getTime());

            int executedSt = preparedStmt.executeUpdate();

            return executedSt == 1 ? this.getInsertedOne(preparedStmt) : null;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public Record getById(int id) {
        try {
            String selectSQL = "SELECT * FROM "+ RELATION + " WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int recordId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Long time = resultSet.getLong("time");

                return new Record(name, time, recordId);
            }

            return null;
        } catch (SQLException e) {
            return null;
        }
    }
}
