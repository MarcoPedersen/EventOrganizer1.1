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
    private static Admin a;
    private static Customer c;
    private static String username;
    private static String password;
    private static String name;
    private static String role;

    public static String getUrl() {
        final String url;
        return url = "jdbc:mysql://212.237.138.123:3306/thomas?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    public static String getName() {
        return name;
    }

    public static String getRole() {
        return role;
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
        return connect = DriverManager.getConnection(url, "thomas", "123456");
    }


    public static void userLogin(String u, String p) {

        try {

            String query = "SELECT * FROM users WHERE username='"+ u + "' and password='"+ p +"'";
            rs = st.executeQuery(query);

            if (rs.next()) {

                username = rs.getString("username");
                password = rs.getString("password");
                name = rs.getString("name");
                role = rs.getString("role");

                if (u.equals(username) && p.equals(password)) {
                    System.out.println("Velkommen, " + name + ". Din rolle er: " + role);

                    switch (role) {
                        case "Secretary":
                            s.secretaryLogin();
                        break;

                        case "Facilitator":
                            f.facilitatorLogin();
                        break;
                        case "Customer":
                            c.customerLogin(name);
                        break;
                        case "Admin":
                            a.adminLogin();
                        break;
                        default: System.out.println("Fejl med login.");
                        break;
                    }

                } else {
                    System.out.println("Forkert brugernavn eller kodeord.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Fejl med databasen.");
        }

    }

    public static void arrangementToDatabase() {
    try {
        Arrangement arrangement = new Arrangement();

        Arrangement arr1 = arrangement.makeArrangement();



        String sql =    "INSERT INTO `arrangement`(`id`, `aName`, `aStart`, `aEnd`, `aPrice`,`attendees`) VALUES (null, \""
                + arr1.getName() + "\", \"" + arr1.getStart() + "\", \"" + arr1.getEnd() + "\", \"" + arr1.getPrice() + "\", \"" + arr1.getAttendees() + "\")";

        st = Database.getConnect().createStatement();
        st.execute(sql);
        System.out.println("Dit arrangement er nu oprettet.");
        Secretary.secretaryLogin();
        st.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    }
}
