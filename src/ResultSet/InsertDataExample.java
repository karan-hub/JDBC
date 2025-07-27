package ResultSet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertDataExample    {
    public static void main(String[] args) throws  ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try(Connection connection = DriverManager.getConnection(  "jdbc:mysql://localhost:3306/bank_db", "root", "root")){
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO accounts(id, name, balance) VALUES(?, ?, ?)"
            );

            preparedStatement.setInt(1,1);
            preparedStatement.setString(2, "Karan");
            preparedStatement.setDouble(3, 5000);
            preparedStatement.executeUpdate();

            preparedStatement.setInt(1, 2);
            preparedStatement.setString(2, "DJ");
            preparedStatement.setDouble(3, 8000);
            preparedStatement.executeUpdate();
            System.out.println("date inserted");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
