package queens;
import java.sql.*;
import java.util.*;

public class DatabaseManager {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/backtracking";
    private static final String USERNAME = "backtracking";
    private static final String PASSWORD = "backtracking";
    private static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS solutions (id INT AUTO_INCREMENT PRIMARY KEY, solution VARCHAR(8))";
    private static final String INSERT_SOLUTION_SQL = "INSERT INTO solutions (solution) VALUES (?)";

    public void createTable() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(CREATE_TABLE_SQL)) {
            statement.executeUpdate();
            System.out.println("Table created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertSolution(String solution) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(INSERT_SOLUTION_SQL)) {
            statement.setString(1, solution);
            statement.executeUpdate();
            System.out.println("Solution inserted successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.createTable();
    }
}

