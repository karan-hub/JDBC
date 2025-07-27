package ResultSet;
import  java.sql.*;
public class StoredProcedure {

    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bank_db", "root", "root")) {

            // ✅ Stored procedure call (1 parameter)
            CallableStatement cs = con.prepareCall("{call getAccountById(?)}");
            cs.setInt(1, 1);

            ResultSet rs = cs.executeQuery();

            System.out.println("🔍 Account Details:");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " +
                        rs.getString("name") + " | " +
                        rs.getDouble("balance"));
            }
        }
    }
    }
