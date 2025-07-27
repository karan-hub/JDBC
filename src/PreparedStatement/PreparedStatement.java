package PreparedStatement;

import java.sql.*;

public class PreparedStatement {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bank_db", "root", "root")) {

            // ✅ Insert using PreparedStatement
            String insertSQL = "INSERT INTO accounts(id, name, balance) VALUES(?, ?, ?)";
            java.sql.PreparedStatement pst = con.prepareStatement(insertSQL);

            pst.setInt(1, 6);
            pst.setString(2, "Karan");
            pst.setDouble(3, 12000);
            int rows = pst.executeUpdate();
            System.out.println("✅ Rows inserted: " + rows);

            // ✅ Select using PreparedStatement
            String selectSQL = "SELECT * FROM accounts WHERE balance > ?";
            java.sql.PreparedStatement ps2 = con.prepareStatement(selectSQL);
            ps2.setDouble(1, 5000);
            ResultSet rs = ps2.executeQuery();

            System.out.println("\n🔍 Accounts with balance > 5000:");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " +
                        rs.getString("name") + " | " +
                        rs.getDouble("balance"));
            }
        }
    }
}
