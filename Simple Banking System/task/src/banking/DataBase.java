package banking;

import java.sql.*;

public class DataBase {

    private static String fileDB = "db_any.s3db";

    private static final String selectCard = "SELECT id, number, pin, balance FROM card WHERE id = ?";

    public DataBase(String fileName) {
        fileDB = (fileName.equals("")) ? fileDB : fileName;
        createTable();
    }

    private Connection connectDatabase() {
        String urlFileDB = "jdbc:sqlite:" + fileDB;
        Connection conn = null;

        try  {
            conn = DriverManager.getConnection(urlFileDB);
        } catch (SQLException e) {
            System.out.println("error - " + e.getMessage());
        }
        return conn;
    }

    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS card (\n"
                + "	id INTEGER PRIMARY KEY,\n"
                + "	number TEXT NOT NULL,\n"
                + "	pin TEXT NOT NULL,\n"
                + "	balance INTEGER DEFAULT 0\n"
                + ");";
        try(Connection conn = connectDatabase();
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean createAccount(String pan, String pin) {
        String sql = "INSERT INTO card(number, pin) VALUES(?, ?)";

        try (Connection conn = connectDatabase();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pan);
            stmt.setString(2, pin);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public int testCard(String pan, String pin) {
        String selectCard = "SELECT id, number, pin, balance FROM card WHERE number = ? AND pin = ?";
        try (Connection conn = connectDatabase();
             PreparedStatement pstmt  = conn.prepareStatement(selectCard)) {

            pstmt.setString(1, pan);
            pstmt.setString(2, pin);
            ResultSet rs  = pstmt.executeQuery();
            if (rs.next())
                return rs.getInt("id");
            return 0;
        } catch (SQLException e) {
            return 0;
        }
    }

    public int isExists(String pan) {
        String existsCard = "SELECT id, number, pin, balance FROM card WHERE number = ?";
        try (Connection conn = connectDatabase();
             PreparedStatement pstmt  = conn.prepareStatement(existsCard)) {

            pstmt.setString(1, pan);
            ResultSet rs  = pstmt.executeQuery();
            if (rs.next())
                return rs.getInt("id");
            return 0;
        } catch (SQLException e) {
            return 0;
        }
    }

    public int getBalance(int id) {
        int balance = 0;
        try (Connection conn = connectDatabase();
             PreparedStatement pstmt = conn.prepareStatement(selectCard)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next())
                balance = rs.getInt("balance");

        } catch (SQLException e) {
            return 0;
        }
        return balance;
    }

    public void addIncome(int id, int balance) {
        int new_balance = getBalance(id) + balance;

        String updateCard = "UPDATE card SET balance = ? WHERE id = ?";
        try (Connection conn = connectDatabase();
             PreparedStatement pstmt  = conn.prepareStatement(updateCard)) {

            pstmt.setInt(1, new_balance);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeAccount(int id) {
        String updateCard = "DELETE FROM card WHERE id = ?";
        try (Connection conn = connectDatabase();
             PreparedStatement pstmt  = conn.prepareStatement(updateCard)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}