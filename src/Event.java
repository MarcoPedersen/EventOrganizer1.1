import java.sql.*;
import java.util.Scanner;


public class Event {

    private static Statement st;
    private static ResultSet rs;
    private String eName;
    private String eDescription;
    private String eType;
    private String eFacilitator;
    private String eText;
    private String arrangement;

    public Event() {

    }
    public Event(String eName, String eDescription, String eType, String eFacilitator, String eText, String arrangement) {
        this.eName = eName;
        this.eDescription = eDescription;
        this.eType = eType;
        this.eFacilitator = eFacilitator;
        this.eText = eText;
        this.arrangement = arrangement;
    }

    public String geteName() {
        return eName;
    }

    public String geteDescription() {
        return eDescription;
    }

    public String geteType() {
        return eType;
    }

    public String geteFacilitator() {
        return eFacilitator;
    }

    public String geteText() {
        return eText;
    }

    public String getArrangement() {
        return arrangement;
    }

    public Event newEvent() {

        Scanner arrScanner = new Scanner(System.in);
        System.out.println("Vælg et arrangement som eventet skal tilføjes til: ");
        Arrangement.getArrangements();
        String arrangement = arrScanner.nextLine();


        if(!Database.arrangementExists(arrangement)) {
            System.out.println("Dette arrangement eksisterer ikke");
            Secretary.secretaryLogin();
        }

        System.out.println("Navn på eventet: ");
        String eName = arrScanner.nextLine();
        System.out.println("Beskrivelse af eventet: ");
        String eDescription = arrScanner.nextLine();
        System.out.println("Eventtype: ");
        String eType = arrScanner.nextLine();
        System.out.println("Vælg den ansvarlige facilitator: ");
        Facilitator.getFacilitator();
        String eFacilitator = arrScanner.nextLine();
        System.out.println("Løs tekst: ");
        String eText = arrScanner.nextLine();

        Event event = new Event(eName, eDescription, eType, eFacilitator, eText, arrangement);
        return event;

    }

    public static void deleteEvent(String i) throws SQLException {
        String sql = "DELETE FROM event WHERE eName='"+ i + "'";
        getEvent();
        st = Database.getConnect().createStatement();
        st.execute(sql);
        System.out.println("Dit event er nu slettet.");
        st.close();
        Secretary.secretaryLogin();
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
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }
}
