package banking;

import java.sql.*;

public class DataBase {

    private static String fileDB = "db_any.s3db";

    private static final String selectCard = "SELECT id, number, pin, balance FROM card WHERE number = ? AND pin = ?";


    public DataBase(String fileName) {
        fileDB = (fileName.equals("")) ? fileDB : fileName;
        createTable();
//        getAll();
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

    public boolean testCard (String pan, String pin) {
        try (Connection conn = connectDatabase();
             PreparedStatement pstmt  = conn.prepareStatement(selectCard)) {

            pstmt.setString(1, pan);
            pstmt.setString(2, pin);
            ResultSet rs  = pstmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            return false;
        }
    }

    public int getBalance(String pan, String pin) {
        int balance = 0;
        try (Connection conn = connectDatabase();
             PreparedStatement pstmt = conn.prepareStatement(selectCard)) {

            pstmt.setString(1, pan);
            pstmt.setString(2, pin);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next())
                balance = rs.getInt("balanse");

        } catch (SQLException e) {
            return 0;
        }
        return balance;
    }
}