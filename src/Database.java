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
    private static String username;
    private static String password;
    private static String name;
    private static String role;
    private static Arrangement arrangement = new Arrangement();
    private static Event event = new Event();

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
                            Secretary.secretaryLogin();
                        break;

                        case "Facilitator":
                            Facilitator.facilitatorLogin();
                        break;
                        case "Customer":
                            Customer.customerLogin(name);
                        break;
                        case "Admin":
                            Admin.adminLogin();
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

    public static void editArrangementInDatabase(String i) {
        try {

            Arrangement arr1 = arrangement.editArrangement();

            String sql =    "UPDATE `arrangement` SET `aName`='"+ arr1.getName() + "', `aStart`='" + arr1.getStart() +"', `aEnd`='"+ arr1.getEnd() + "', `aPrice`='"
                    + arr1.getPrice() + "',`attendees`='"+ arr1.getAttendees() + "' WHERE `aName`='"+ i +"'";


            st = Database.getConnect().createStatement();
            st.executeUpdate(sql);
            System.out.println("Dit arrangement er nu redigeret.");
            Secretary.secretaryLogin();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void eventToDatabase() {
        Event event1 = event.makeEvent();

        try {

            String sql =    "INSERT INTO `event`(`id`, `eName`, `eDescription`, `eType`,`eFacilitator`,`eText`,`arrangement`) " +
                    "VALUES (null, \"" + event1.geteName() + "\", \"" + event1.geteDescription() + "\", \"" + event1.geteType() + "\", \"" + event1.geteFacilitator() +
                    "\", \"" + event1.geteText()+ "\", \"" + event1.getArrangement() + "\")";

            st = Database.getConnect().createStatement();
            st.execute(sql);
            System.out.println("Dit event er nu oprettet og hører til arrangementet: " + event1.getArrangement());
            Secretary.secretaryLogin();
            st.close();
        } catch (SQLException s){
            s.printStackTrace();
        }

    }

    public static void editEventInDatabase(String i) {
        Event e = event.editEvent();
        try {
            String sql =    "UPDATE `event` SET `eName`='"+ e.geteName() + "', `eDescription`='" + e.geteDescription() +"', `eType`='"+ e.geteType()
                    + "', `eFacilitator`='" + e.geteFacilitator() + "',`eText`='"+ e.geteText() + "', `arrangement`='" + e.getArrangement()
                    + "' WHERE `eName`='"+ i +"'";

            Database.getConnect();
            st = Database.getConnect().createStatement();
            st.executeUpdate(sql);
            System.out.println("Dit arrangement er nu redigeret.");
            Secretary.secretaryLogin();
            st.close();
        } catch(SQLException s){
            s.printStackTrace();
        }
    }
}
