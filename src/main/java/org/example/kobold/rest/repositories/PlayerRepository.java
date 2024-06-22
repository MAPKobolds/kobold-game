package org.example.kobold.rest.repositories;

import org.example.kobold.rest.models.Player;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PlayerRepository extends Repository<Player> {

    public PlayerRepository() {
        RELATION = "players";

        String createTableSQL = "CREATE TABLE IF NOT EXISTS Players (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(255))";
        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Player save(Player player) {
        try {
            String sql = "INSERT INTO "+ RELATION + " (name)" + " VALUES (?)";
            PreparedStatement preparedStmt = connection.prepareStatement(sql, new String[]{ "ID" });

            preparedStmt.setString (1, player.getName());
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
    public Player updateById(Player entity, int id) {
        try {
            String sql = "UPDATE "+ RELATION + " SET name = ?" + " WHERE ID = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(sql, new String[]{ "ID" });

            preparedStmt.setString(1, entity.getName());
            preparedStmt.setInt(2, id);

            int executedSt = preparedStmt.executeUpdate();

            return executedSt == 1 ? this.getInsertedOne(preparedStmt) : null;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public Player getById(int id) {
        try {
            String selectSQL = "SELECT * FROM "+ RELATION + " WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int playerId = resultSet.getInt("id");
                String name = resultSet.getString("name");

                return new Player(name, playerId);
            }

            return null;
        } catch (SQLException e) {
            return null;
        }

    }
}
