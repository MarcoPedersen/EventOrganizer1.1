import java.sql.*;
import java.util.Scanner;

public class Arrangement {

    private int id;
    private int arrangementDuration;
    private String name;
    private String type;
    private String description;
    private String additionalInfo;
    private static Statement st;
    private static ResultSet rs;

    public Arrangement(int id, int arrangementDuration, String name, String type, String description, String additionalInfo) {
        this.id = id;
        this.arrangementDuration = arrangementDuration;
        this.name = name;
        this.type = type;
        this.description = description;
        this.additionalInfo = additionalInfo;
    }
    public int getId() {

        return id;
    }

    public int getArrangementDuration() {
        return arrangementDuration;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
    public String getDescription() {
        return description;
    }
    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public static void makeArrangement() throws SQLException {

        System.out.println("Udfyld felterne:");
        Scanner arrScanner = new Scanner(System.in);

        System.out.println("Navn på arrangement: ");
        String aName = arrScanner.nextLine();
        System.out.println("Arrangementets startstidspunkt: ");
        String aStart = arrScanner.nextLine();
        System.out.println("Arrangementets sluttidspunkt: ");
        String aEnd = arrScanner.nextLine();
        System.out.println("Prisen for arrangementet: ");
        String aPrice = arrScanner.nextLine();
        System.out.println("Tilmeldte: ");
        String attendees = arrScanner.nextLine();

        String sql =    "INSERT INTO `arrangement`(`id`, `aName`, `aStart`, `aEnd`, `aPrice`,`attendees`) VALUES (null, \""
                        + aName + "\", \"" + aStart + "\", \"" + aEnd + "\", \"" + aPrice + "\", \"" + attendees + "\")";

        st = Database.getConnect().createStatement();
        st.execute(sql);
        System.out.println("Dit arrangement er nu oprettet.");
        Secretary.secretaryLogin();
        st.close();

    }

    public static void editArrangement(String i) throws SQLException {

        System.out.println("Skriv de nye informationer:");
        System.out.println("-----------------------------");
        Scanner arrScanner = new Scanner(System.in);

        System.out.println("Navn på arrangement: ");
        String Name = arrScanner.nextLine();
        System.out.println("Arrangementets startstidspunkt: ");
        String Start = arrScanner.nextLine();
        System.out.println("Arrangementets sluttidspunkt: ");
        String End = arrScanner.nextLine();
        System.out.println("Prisen for arrangementet: ");
        String Price = arrScanner.nextLine();
        System.out.println("Tilmeldte: ");
        String Attendees = arrScanner.nextLine();

        String sql =    "UPDATE `arrangement` SET `aName`='"+ Name + "', `aStart`='" + Start +"', `aEnd`='"+ End + "', `aPrice`='"
                        + Price + "',`attendees`='"+ Attendees + "' WHERE `aName`='"+ i +"'";
        Database.getConnect();
        st = Database.getConnect().createStatement();
        st.executeUpdate(sql);
        System.out.println("Dit arrangement er nu redigeret.");
        Secretary.secretaryLogin();
        st.close();
    }

    public static void deleteArrangement(String i) throws SQLException {

        String sql = "DELETE FROM arrangement WHERE aName='"+ i + "'";
        getArrangements();
        st = Database.getConnect().createStatement();
        st.execute(sql);
        System.out.println("Dit arrangement er nu slettet.");
        Secretary.secretaryLogin();
        st.close();
    }

    public static void getArrangements() {
        try {
            String query = "SELECT * FROM arrangement";
            st = Database.getConnect().createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                String name = rs.getString("aName");
                System.out.println("-----------------");
                System.out.println(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
