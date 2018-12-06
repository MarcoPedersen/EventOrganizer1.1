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
                            Secretary.login();
                            break;
                        case "Facilitator":
                            Facilitator.login();
                            break;
                        case "Customer":
                            Customer.login(name);
                            break;
                        case "Admin":
                            Admin.login();
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

    public static void insertArrangement (Arrangement a, String message, boolean returnAfter) {
        try {
            String sql =    "INSERT INTO `arrangement`(`id`, `aName`, `aStart`, `aEnd`, `aPrice`,`attendees`) VALUES  (null, \""
                    + a.getName() + "\", \"" + a.getStart() + "\", \"" + a.getEnd() + "\", \"" + a.getPrice() + "\", \"" + a.getAttendees() + "\")  ON DUPLICATE KEY UPDATE aName =\"" + a.getName() + "\", " +
                    "aStart=\"" + a.getStart() + "\", aEnd=\"" + a.getEnd() + "\", aPrice=\"" + Double.parseDouble(a.getPrice()) + "\", attendees=\"" + a.getAttendees() + "\"";

            st = Database.getConnect().createStatement();
            st.execute(sql);
            System.out.println(message);
            st.close();

            if(returnAfter) {
                Secretary.login();
            }
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
            Secretary.login();
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    public static void eventToDatabase(Event e,boolean returnAfter) {
        try {
            String sql =    "INSERT INTO `event`(`id`, `eName`, `eDescription`, `eType`,`eFacilitator`,`eText`,`arrangement`,`eDuration`, `ePrice`) " +
                    "VALUES (null, \"" + e.geteName() + "\", \"" + e.geteDescription() + "\", \"" + e.geteType() + "\", \"" + e.geteFacilitator() +
                    "\", \"" + e.geteText()+ "\", \"" + e.getArrangement() + "\", \"" + e.geteDuration() + "\", \"" + e.getePrice() + "\") ON DUPLICATE KEY UPDATE eName=\"" + e.geteName() + "\", " +
                    "eDescription=\"" + e.geteDescription() + "\", eType=\"" + e.geteType() + "\", eFacilitator=\"" + e.geteFacilitator() + "\", eText=\"" + e.geteText() + "\", arrangement=\""
                    + e.getArrangement() + "\", eDuration=\"" + e.geteDuration() + "\", ePrice=\"" + e.getePrice() + "\"";

            st = Database.getConnect().createStatement();
            st.execute(sql);
            System.out.println("Dit event er nu oprettet og hører til arrangementet: " + e.getArrangement());
            updateArrangementPrice(e.getePrice(), e.getArrangement());
            st.close();
            if (returnAfter) {
                Secretary.login();
            }
        } catch (SQLException sqlEx){
            sqlEx.printStackTrace();
        }

    }

    public static void eventCSVDatabase(Event e,boolean returnAfter) {

        try {
            String sql =    "INSERT INTO `event`(`id`, `eName`, `eDescription`, `eType`,`eFacilitator`,`eText`,`arrangement`,`eDuration`, `ePrice`) " +
                    "VALUES (null, \"" + e.geteName() + "\", \"" + e.geteDescription() + "\", \"" + e.geteType() + "\", \"" + e.geteFacilitator() +
                    "\", \"" + e.geteText()+ "\", \"" + e.getArrangement() + "\", \"" + e.geteDuration() + "\", \"" + e.getePrice() + "\") ON DUPLICATE KEY UPDATE eName=\"" + e.geteName() + "\", " +
                    "eDescription=\"" + e.geteDescription() + "\", eType=\"" + e.geteType() + "\", eFacilitator=\"" + e.geteFacilitator() + "\", eText=\"" + e.geteText() + "\", arrangement=\""
                    + e.getArrangement() + "\", eDuration=\"" + e.geteDuration() + "\", ePrice=\"" + e.getePrice() + "\"";

            st = Database.getConnect().createStatement();
            st.execute(sql);
            System.out.println("Dit event er nu oprettet og hører til arrangementet: " + e.getArrangement());
            st.close();
            if (returnAfter) {
                Secretary.login();
            }
        } catch (SQLException sqlEx){
            sqlEx.printStackTrace();
        }

    }

    public static void editEventInDatabase(String i) {
        try {
            String query = "SELECT * FROM event WHERE eName='" + i + "'";
            st = Database.getConnect().createStatement();
            rs = st.executeQuery(query);
            rs.last();
            if (rs.getRow() == 0) {
                System.out.println("Event findes ikke");
                Secretary.login();
            } else {
                    Event e = event.newEvent();
                    String oldPrice = rs.getString("ePrice");

                    String sql = "UPDATE `event` SET `eName`='" + e.geteName() + "', `eDescription`='" + e.geteDescription() + "', `eType`='" + e.geteType()
                            + "', `eFacilitator`='" + e.geteFacilitator() + "',`eText`='" + e.geteText() + "', `arrangement`='" + e.getArrangement()
                            + "', `eDuration`='" + e.geteDuration() + "', `ePrice`='" + e.getePrice() + "' WHERE `eName`='" + i + "'";

                    st = Database.getConnect().createStatement();
                    st.executeUpdate(sql);
                    System.out.println("Dit event er nu redigeret.");
                    editArrangementPrice(e.getePrice(), e.getArrangement(), oldPrice);
                    Secretary.login();
            }
        } catch(SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    public static void userToDatabase(Admin a, boolean returnAfter) {
        try {
            String sql = "INSERT INTO `users`(`id`, `username`, `password`, `name`,`role`) VALUES (null, \"" + a.getUsername().toLowerCase() + "\", \""
                    + a.getPassword().toLowerCase() + "\", \"" + a.getFullName() + "\", \"" + a.getRole() + "\") ON DUPLICATE KEY UPDATE username=\""
                    + a.getUsername() + "\", password=\"" + a.getPassword() + "\", `name`=\"" + a.getFullName() + "\", role=\"" + a.getRole() + "\"";

            st = Database.getConnect().createStatement();
            st.execute(sql);
            System.out.println("Bruger " + a.getUsername() + " er nu oprettet.");
            st.close();
            if (returnAfter){
                Admin.login();
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    public static void editUserInDatabase(String i) {
        try {
            String query = "SELECT * FROM users WHERE username='" + i + "'";
            st = Database.getConnect().createStatement();
            ResultSet resultSet = st.executeQuery(query);
            resultSet.last();
            if (resultSet.getRow() == 0) {
                System.out.println("Ingen brugere med dette navn.");
                st.close();
                resultSet.close();
                Admin.login();
            } else {
                Admin a = admin.newUser();
                String sql = "UPDATE `users` SET `username`='"+ a.getUsername() + "', `password`='" + a.getPassword() +"', `name`='"+ a.getFullName() + "', `role`='" + a.getRole() + "' WHERE `username`='"+ i +"'";
                Database.getConnect();
                st = Database.getConnect().createStatement();
                st.executeUpdate(sql);
                System.out.println("Brugeroplysningerne er nu opdateret.");
                st.close();
                Admin.login();
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    public static boolean arrangementExists(String arr) {
        String sql = "SELECT * FROM arrangement WHERE aName='" + arr + "'";
        try {
            st = Database.getConnect().createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.last();
            if(rs.getRow() == 0) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return false;
    }

    public static void updateArrangementPrice(String ePrice, String aName) {

        try {

            String query = "SELECT * FROM `arrangement` WHERE `aName`='"+ aName + "'";
            st = Database.getConnect().createStatement();
            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {
                String price = rs.getString("aPrice");

                String sql = "UPDATE `arrangement` SET `aPrice`='" + (Double.parseDouble(price) + Double.parseDouble(ePrice)) + "' WHERE `aName`='" + aName + "'";

                st = Database.getConnect().createStatement();
                st.executeUpdate(sql);
                st.close();
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }

    }

    public static void deleteArrangementPrice(String i) {

        try {

            String query = "SELECT * FROM `event` WHERE `eName`='"+ i + "'";
            st = Database.getConnect().createStatement();
            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {
                String price = rs.getString("ePrice");
                String arrName = rs.getString("arrangement");

                String query2 = "SELECT * FROM `arrangement`";
                st = Database.getConnect().createStatement();
                ResultSet rs2 = st.executeQuery(query2);

                if(rs2.next()) {
                    String aPrice = rs2.getString("aPrice");
                    String sql = "UPDATE `arrangement` SET `aPrice`='" + (Double.parseDouble(aPrice) - Double.parseDouble(price)) + "' WHERE `aName`='" + arrName + "'";

                    st = Database.getConnect().createStatement();
                    st.executeUpdate(sql);
                    st.close();
                }
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }

    }


    public static void editArrangementPrice(String ePrice, String aName, String oldPrice) {

        try {

            String query = "SELECT * FROM `arrangement` WHERE `aName`='"+ aName + "'";
            st = Database.getConnect().createStatement();
            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {
                String aPrice = rs.getString("aPrice");

                String sql = "UPDATE `arrangement` SET `aPrice`='" + (Double.parseDouble(aPrice) - Double.parseDouble(oldPrice) + Double.parseDouble(ePrice)) + "' WHERE `aName`='" + aName + "'";

                st = Database.getConnect().createStatement();
                st.executeUpdate(sql);
                st.close();
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }

    }

}