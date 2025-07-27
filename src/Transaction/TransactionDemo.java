package Transaction;

import java.sql.*;

public class TransactionDemo {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bank_db", "root", "root")) {

            con.setAutoCommit(false); // ✅ Manual transaction mode ON

            PreparedStatement debit = con.prepareStatement(
                    "UPDATE accounts SET balance = balance - ? WHERE id = ?");
            debit.setDouble(1, 1000);
            debit.setInt(2, 1);
            debit.executeUpdate();

            PreparedStatement credit = con.prepareStatement(
                    "UPDATE accounts SET balance = balance + ? WHERE id = ?");
            credit.setDouble(1, 1000);
            credit.setInt(2, 2);
            credit.executeUpdate();

            con.commit(); // ✅ Both updates commit together
            System.out.println("✅ Transaction successful!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
