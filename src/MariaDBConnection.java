import java.sql.*;

public class MariaDBConnection {
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bank_db", "root", "root");
            System.out.println("Connected!");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from employee");
            while (resultSet.next()){
                System.out.println(resultSet.getInt("employee_id") + "--" + resultSet.getString("name") );
            }
            connection.close();
            System.out.println("closed");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
