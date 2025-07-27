import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class TransactionExample {
    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bank_db", "root", "root")) {

            con.setAutoCommit(false); // ✅ Manual transaction mode ON

            PreparedStatement pst1 = con.prepareStatement(
                    "INSERT INTO employee(employee_id, name) VALUES(?, ?)");
            pst1.setInt(1, 101);
            pst1.setString(2, "Karan");
            pst1.executeUpdate();

            PreparedStatement pst2 = con.prepareStatement(
                    "INSERT INTO employee(employee_id, name) VALUES(?, ?)");
            pst2.setInt(1, 102);
            pst2.setString(2, "DJ");
            pst2.executeUpdate();

            con.commit(); // ✅ Dono ek saath save

            System.out.println("✅ Both records inserted successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}