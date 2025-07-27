package PreparedStatement;
import  java.sql.*;
public class StatementDemo {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bank_db", "root", "root")){
            Statement statement = con.createStatement();
            String insertSQL = "INSERT INTO accounts(id, name, balance) VALUES(5, 'Rahul', 6000)";
            int rows = statement.executeUpdate(insertSQL);
            System.out.println("✅ Rows inserted: " + rows);
            // ✅ Select using Statement
            ResultSet rs = statement.executeQuery("SELECT * FROM accounts");
            System.out.println("\n🔍 Account Records:");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " +
                        rs.getString("name") + " | " +
                        rs.getDouble("balance"));
            }
        }
    }
}
