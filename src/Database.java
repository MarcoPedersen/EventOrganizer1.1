import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Database {

    private static Connection connect;
    private static Statement st;
    private static ResultSet rs;
    private static Secretary s;
    private static Facilitator f;

    public static String getUrl() {
        String url;
        return url = "jdbc:mysql://localhost:3306/event?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    }

    public static void connectToDatabase() {

        try {
            st = getConnect().createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Connection getConnect() throws SQLException {
        String url = getUrl();
        return connect = DriverManager.getConnection(getUrl(), "root", "");
    }

    public static void userLogin(String u, String p) {

        try {

            String query = "SELECT * FROM users WHERE username='"+ u + "' and password='"+ p +"'";
            rs = st.executeQuery(query);

            if (rs.next()) {

                String username = rs.getString("username");
                String password = rs.getString("password");
                String name = rs.getString("name");
                String role = rs.getString("role");

            }
        } catch (SQLException e) {
            System.out.println("Fejl med databasen.");
        }

    }
}
