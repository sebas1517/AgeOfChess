import java.sql.*;

public class DatabaseOperations {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Age_Of_Chess";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Cbass0916##";

    public static boolean usernameExists(String username) {
        boolean exists = false;

        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Create a PreparedStatement with a SQL query that checks if the given username exists in the database
            PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM users WHERE username = ?");
            stmt.setString(1, username);

            // Execute the query and check if the result has a count greater than 0
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    exists = true;
                }
            }

            // Close the resources
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exists;
    }

    public static boolean authenticate(String username, String password) {
        boolean isAuthenticated = false;

        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Create a PreparedStatement with a SQL query that checks if the given username and password exist in the database
            PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM users WHERE username = ? AND password = ?");
            stmt.setString(1, username);
            stmt.setString(2, password);

            // Execute the query and check if the result has a count greater than 0
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    isAuthenticated = true;
                }
            }

            // Close the resources
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isAuthenticated;
    }

    public static void addUser(String username, String password) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Create a PreparedStatement with an INSERT query that adds a new record to the users table
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (username, password, wins, games_played) VALUES (?, ?,?,?)");
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setInt(3, 0);
            stmt.setInt(4, 0);



            // Execute the query
            stmt.executeUpdate();

            // Close the resources
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getWinsForUser(String username) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        String sql = "SELECT wins FROM users WHERE username=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        int wins = 0;
        if (rs.next()) {
            wins = rs.getInt("wins");
        }
        rs.close();
        stmt.close();
        conn.close();
        return wins;
    }

    public static int getGamesForUser(String username) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        String sql = "SELECT games_played FROM users WHERE username=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        int games = 0;
        if (rs.next()) {
            games = rs.getInt("games_played");
        }
        rs.close();
        stmt.close();
        conn.close();
        return games;
    }

    public static void updateGamesPlayed(String username) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        String sql = "UPDATE users SET games_played = games_played + 1 WHERE username = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, username);

        int rowsUpdated = stmt.executeUpdate();

        stmt.close();
        conn.close();

    }
    public static void updateWins(String username) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        String sql = "UPDATE users SET wins = wins + 1 WHERE username = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, username);

        int rowsUpdated = stmt.executeUpdate();

        stmt.close();
        conn.close();

    }
}
