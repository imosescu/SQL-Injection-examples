import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class NonInjectableExample {

    private static final String DB_URL = "jdbc:mysql://localhost/inject_sql_test";

    //  Database credentials
    static final String USER = "injection_user";
    static final String PASS = "injection_pass";

    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
//            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * from t_one where id=?");
//            preparedStatement.setString(1, "1 OR 1=1");

            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * from t_one where value=?");
            preparedStatement.setString(1, "'a' OR \"\"=\"\"");

            ResultSet result = preparedStatement.executeQuery();
            ResultSetMetaData metaData = result.getMetaData();

            for (int i=0; i<metaData.getColumnCount(); i++) {
                System.out.print(metaData.getColumnName(i+1) + " ");
            }
            System.out.println();

            while (result.next()) {
                for (int i=0; i<metaData.getColumnCount(); i++) {
                    System.out.print(result.getString(i+1) + "  ");
                }
                System.out.println();
            }


            result.close();
            conn.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
