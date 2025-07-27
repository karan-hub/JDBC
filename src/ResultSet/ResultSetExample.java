package ResultSet;
import java.sql.*;

public class ResultSetExample {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try(Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bank_db", "root", "root")
        ) {
            Statement statement = connection.createStatement(
//              ResultSet.TYPE_SCROLL_INSENSITIVE ,
              ResultSet.TYPE_SCROLL_SENSITIVE,
              ResultSet.CONCUR_UPDATABLE

            );
            ResultSet resultSet =  statement.executeQuery("SELECT * FROM accounts");
            System.out.println("before update");

            while (resultSet.next()){
                System.out.println(
                        resultSet.getInt("id") +" | "
                        + resultSet.getString("name") +" | "
                        + resultSet.getDouble("balance")
                );
            }

//        Move cursor to first row and update balance
resultSet.beforeFirst();
            if (resultSet.next()){
                resultSet.updateDouble("balance" , 899999.0);
                resultSet.updateRow();
                System.out.println("balance updated");
            }

            resultSet.beforeFirst();
            System.out.println("\n🔍 After Update:");
            while(resultSet.next()) {
                System.out.println(resultSet.getInt("id") + " | " +
                        resultSet.getString("name") + " | " +
                        resultSet.getDouble("balance"));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
