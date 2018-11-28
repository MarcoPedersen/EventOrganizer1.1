import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Database {

    private static final String URL = "jdbc:mysql://212.237.138.123:3306/thomas?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    private static Connection connect;
    private static Statement st;
    private static ResultSet rs;
    private static String name;
    private static Arrangement arrangement = new Arrangement();
    private static Event event = new Event();
    private static Admin admin = new Admin();

    public static String getUrl() {
        return URL;
    }

    public static String getName() {
        return name;
    }

    public static void connectToDatabase() {

        try {
            st = getConnect().createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Connection getConnect() throws SQLException {

        return connect = DriverManager.getConnection(getUrl(), "thomas", "123456");

    }

    public static void userLogin(String u, String p) {

        try {
            String query = "SELECT * FROM users WHERE username='"+ u + "' and password='"+ p +"'";
            rs = st.executeQuery(query);
            rs.last();
            if (rs.getRow() == 0) {
                System.out.println("Forkert login");
                st.close();
                rs.close();
                ArrangementHandler.arrangementLogin();
            }
            rs.beforeFirst();
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                name = rs.getString("name");
                String role = rs.getString("role");

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
                        default:
                            System.out.println("Ikke gyldig rolle - prøv venligst igen");
                            st.close();
                            rs.close();
                            ArrangementHandler.arrangementLogin();
                            break;
                    }
                }
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }

    }

    public static void arrangementToDatabase() {
    try {

        Arrangement a = arrangement.newArrangement();

        String sql =    "INSERT INTO `arrangement`(`id`, `aName`, `aStart`, `aEnd`, `aPrice`,`attendees`) VALUES (null, \""
                + a.getName() + "\", \"" + a.getStart() + "\", \"" + a.getEnd() + "\", \"" + a.getPrice() + "\", \"" + a.getAttendees() + "\")";

        st = Database.getConnect().createStatement();
        st.execute(sql);
        System.out.println("Dit arrangement er nu oprettet.");
        st.close();
        Secretary.secretaryLogin();
    } catch (SQLException sqlEx) {
        sqlEx.printStackTrace();
    }

    }

    public static void editArrangementInDatabase(String i) {
        try {

            Arrangement a = arrangement.newArrangement();

            String sql =    "UPDATE `arrangement` SET `aName`='"+ a.getName() + "', `aStart`='" + a.getStart() +"', `aEnd`='"+ a.getEnd() + "', `aPrice`='"
                    + a.getPrice() + "',`attendees`='"+ a.getAttendees() + "' WHERE `aName`='"+ i +"'";

            st = Database.getConnect().createStatement();
            st.executeUpdate(sql);
            System.out.println("Dit arrangement er nu redigeret.");
            st.close();
            Secretary.secretaryLogin();
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }


    public static void eventToDatabase() {
        Event e = event.newEvent();
        try {

            String sql =    "INSERT INTO `event`(`id`, `eName`, `eDescription`, `eType`,`eFacilitator`,`eText`,`arrangement`) " +
                    "VALUES (null, \"" + e.geteName() + "\", \"" + e.geteDescription() + "\", \"" + e.geteType() + "\", \"" + e.geteFacilitator() +
                    "\", \"" + e.geteText()+ "\", \"" + e.getArrangement() + "\")";

            st = Database.getConnect().createStatement();
            st.execute(sql);
            System.out.println("Dit event er nu oprettet og hører til arrangementet: " + e.getArrangement());
            st.close();
            Secretary.secretaryLogin();
        } catch (SQLException sqlEx){
            sqlEx.printStackTrace();
        }

    }

    public static void editEventInDatabase(String i) {
        Event e = event.newEvent();
        try {
            String sql =    "UPDATE `event` SET `eName`='"+ e.geteName() + "', `eDescription`='" + e.geteDescription() +"', `eType`='"+ e.geteType()
                    + "', `eFacilitator`='" + e.geteFacilitator() + "',`eText`='"+ e.geteText() + "', `arrangement`='" + e.getArrangement()
                    + "' WHERE `eName`='"+ i +"'";

            Database.getConnect();
            st = Database.getConnect().createStatement();
            st.executeUpdate(sql);
            System.out.println("Dit arrangement er nu redigeret.");
            st.close();
            Secretary.secretaryLogin();
        } catch(SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    public static void userToDatabase() {

        Admin a = admin.newUser();

        try {
            String sql = "INSERT INTO `users`(`id`, `username`, `password`, `name`,`role`) VALUES (null, \"" + a.getUsername() + "\", \"" + a.getPassword() + "\", \"" + a.getFullName() + "\", \"" + a.getRole() + "\")";

            st = Database.getConnect().createStatement();
            st.execute(sql);
            System.out.println("Bruger " + a.getUsername() + " er nu oprettet.");
            st.close();
            Admin.adminLogin();
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    public static void editUserInDatabase(String i) {
        Admin a = admin.newUser();
        try {
            String sql = "UPDATE `users` SET `username`='"+ a.getUsername() + "', `password`='" + a.getPassword() +"', `name`='"+ a.getFullName() + "', `role`='" + a.getRole() + "' WHERE `username`='"+ i +"'";
            Database.getConnect();
            st = Database.getConnect().createStatement();
            st.executeUpdate(sql);
            System.out.println("Brugeroplysningerne er nu opdateret.");
            st.close();
            Admin.adminLogin();
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }

    }
}
