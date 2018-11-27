import java.sql.*;
import java.util.Scanner;


public class Event {

    private static Statement st;
    private static ResultSet rs;

    public static void makeEvent() throws SQLException {

        System.out.println("Udfyld felterne:");
        Scanner arrScanner = new Scanner(System.in);

        System.out.println("Navn på eventet: ");
        String eName = arrScanner.nextLine();
        System.out.println("Beskrivelse af eventet: ");
        String eDescription = arrScanner.nextLine();
        System.out.println("Eventtype: ");
        String eType = arrScanner.nextLine();
        System.out.println("Ansvarlig facilitator: ");
        String eFacilitator = arrScanner.nextLine();
        System.out.println("Løs tekst: ");
        String eText = arrScanner.nextLine();
        System.out.println("Vælg et arrangement som eventet skal tilføjes til: ");
        Arrangement.getArrangements();
        String arrangement = arrScanner.nextLine();

        String sql =    "INSERT INTO `event`(`id`, `eName`, `eDescription`, `eType`,`eFacilitator`,`eText`,`arrangement`) " +
                        "VALUES (null, \"" + eName + "\", \"" + eDescription + "\", \"" + eType + "\", \"" + eFacilitator +
                        "\", \"" + eText + "\", \"" + arrangement + "\")";

        st = Database.getConnect().createStatement();
        st.execute(sql);
        System.out.println("Dit event er nu oprettet og hører til arrangementet: " + arrangement);
        Secretary.secretaryLogin();
        st.close();

    }

    public static void editEvent(String i) throws SQLException {

        System.out.println("Skriv de nye informationer:");
        System.out.println("-----------------------------");
        Scanner arrScanner = new Scanner(System.in);

        System.out.println("Navn på eventet: ");
        String Name = arrScanner.nextLine();
        System.out.println("Beskrivelse af eventet: ");
        String description = arrScanner.nextLine();
        System.out.println("Event type: ");
        String type = arrScanner.nextLine();
        System.out.println("Ansvarlig facilitator: ");
        String facilitator = arrScanner.nextLine();
        System.out.println("Ekstra informationer: ");
        String text = arrScanner.nextLine();
        System.out.println("Vælg et arrangement som eventet skal tilføjes til: ");
        Arrangement.getArrangements();
        String eArr = arrScanner.nextLine();

        String sql =    "UPDATE `event` SET `eName`='"+ Name + "', `eDescription`='" + description +"', `eType`='"+ type
                        + "', `eFacilitator`='" + facilitator + "',`eText`='"+ text + "', `arrangement`='" + eArr
                        + "' WHERE `eName`='"+ i +"'";

        Database.getConnect();
        st = Database.getConnect().createStatement();
        st.executeUpdate(sql);
        System.out.println("Dit arrangement er nu redigeret.");
        Secretary.secretaryLogin();
        st.close();
    }

    public static void deleteEvent(String i) throws SQLException {

        String sql = "DELETE FROM event WHERE eName='"+ i + "'";
        getEvent();
        st = Database.getConnect().createStatement();
        st.execute(sql);
        System.out.println("Dit event er nu slettet.");
        Secretary.secretaryLogin();
        st.close();
    }

    public static void getEvent() {
        try {
            String query = "SELECT * FROM event";
            st = Database.getConnect().createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                String name = rs.getString("eName");
                System.out.println("-----------------");
                System.out.println(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
