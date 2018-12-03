import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class Facilitator extends User {

    public static void facilitatorLogin() {
        getEventsToFacilitator(Database.getName());
        Scanner sc = new Scanner(System.in);
        System.out.println("Vælg hvilket event du vil se information om:");
        String event = sc.nextLine();
        getFacilitatorInformation(event);
    }

    public static void getFacilitatorInformation(String s) {
        try {
            String query = "SELECT * FROM `event` WHERE `eName`='"+ s + "'";
            setupStatement(query);

            if (rs.next()) {
                String name = rs.getString("eName");
                String description = rs.getString("eDescription");
                String type = rs.getString("eType");
                String text = rs.getString("eText");
                String arrangement = rs.getString("arrangement");
                String duration = rs.getString("eDuration");

                System.out.println("Event-navn: " + name);
                System.out.println("Beskrivelse: " + description);
                System.out.println("Event-type: " + type);
                System.out.println("Løs tekst: " + text);
                System.out.println("Tilknyttet arrangement: " + arrangement);
                System.out.println("Eventets varighed: " + duration);

                String query2 = "SELECT * FROM `arrangement` WHERE `aName`='"+ arrangement + "'";
                setupStatement(query2);

                if(rs.next()) {
                    String aName = rs.getString("aName");
                    String aStart = rs.getString("aStart");
                    String aEnd = rs.getString("aEnd");
                    String aPrice = rs.getString("aPrice");
                    String attendees = rs.getString("attendees");

                    System.out.println("\nArrangementets navn: " + aName);
                    System.out.println("Arrangementet starter: " + aStart);
                    System.out.println("Arrangementet slutter: " + aEnd);
                    System.out.println("Pris: " + aPrice);
                    System.out.println("Deltagere: " + attendees);

                    Facilitator.facilitatorLogin();
                }

            } else {
                System.out.println("Dette arrangement findes ikke - prøv igen.");
                Facilitator.facilitatorLogin();
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    public static void getEventsToFacilitator(String name) {
        try {
            String query = "SELECT `eName` FROM `event` WHERE `eFacilitator`='"+ name + "'";
            setupStatement(query);

            if (rs.next()) {
                System.out.println("Du er ansvarlig for følgende events: ");
                String eName = rs.getString("eName");
                System.out.println(eName);

            } else {
                System.out.println("Du er ikke ansvarlig for nogle events - endnu.\nLogger ud...");
                ArrangementHandler.arrangementLogin();
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    public static void getFacilitator() {
        try {
            String query = "SELECT * FROM users WHERE role='Facilitator'";
            setupStatement(query);

            while (rs.next()) {
                String name = rs.getString("name");
                System.out.println("-----------------");
                System.out.println(name);
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    public static boolean verifyFacilitator(String i) {
        boolean isFacilitator = false;
        try {
            String query = "SELECT role FROM users WHERE name='"+ i +"'";
            setupStatement(query);
            ResultSet resultSet = st.executeQuery(query);
            if (resultSet.getRow() == 0) {
                System.out.println("Ingen facilitatorer med dette navn - prøv igen.");
                isFacilitator = false;
            } else {
                isFacilitator = true;
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return isFacilitator;
    }
}
