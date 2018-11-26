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
        return url = "jdbc:mysql://212.237.138.123:3306/thomas?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
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
        return connect = DriverManager.getConnection(getUrl(), "thomas", "123456");
    }

    public static int userLogin() {

        int isLogin = 0;

        try {

            Scanner login = new Scanner(System.in);

            System.out.println("Brugernavn: ");
            String u = login.nextLine();
            System.out.println("Kodeord: ");
            String p = login.nextLine();

            connectToDatabase();
            String query = "SELECT * FROM user WHERE username='"+ u + "' and password='"+ p +"'";
            rs = st.executeQuery(query);

            if (rs.next()) {

                String username = rs.getString("username");
                String password = rs.getString("password");
                String name = rs.getString("name");
                String role = rs.getString("role");
                if (u.equals(username) && p.equals(password)) {
                    isLogin = 1;
                } else {
                    isLogin = 0;
                }
            }

        } catch (SQLException e) {
            System.out.println("Fejl med databasen.");
        }
        return isLogin;
    }
}
