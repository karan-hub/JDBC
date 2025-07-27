package ResultSet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Statement;

public class CreateTableExample {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try( java.sql.Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_db", "root" ,"root")){
            Statement statement = connection.createStatement();

            statement.execute("CREATE TABLE IF NOT EXISTS accounts (" +
                    "id INT PRIMARY KEY, name VARCHAR(50), balance DOUBLE)");
            System.out.println("table created");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
