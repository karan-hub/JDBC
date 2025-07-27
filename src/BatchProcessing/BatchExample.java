package BatchProcessing;

import java.sql.*;

public class BatchExample {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bank_db", "root", "root")) {

            con.setAutoCommit(false); // ✅ Performance + Transaction safety

            PreparedStatement pst = con.prepareStatement(
                    "INSERT INTO accounts(id, name, balance) VALUES(?, ?, ?)");

            // ✅ Record 1
            pst.setInt(1, 11);
            pst.setString(2, "Raj");
            pst.setDouble(3, 5000);
            pst.addBatch();

            // ✅ Record 2
            pst.setInt(1, 12);
            pst.setString(2, "Neha");
            pst.setDouble(3, 6000);
            pst.addBatch();

            // ✅ Execute batch
            int[] results = pst.executeBatch();
            con.commit();

            System.out.println("✅ Batch executed, rows affected: " + results.length);
        }
    }
}
