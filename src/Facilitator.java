import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.Scanner;
public class Facilitator extends User {

    public static void facilitatorLogin() {
        System.out.print("Du er ansvarlig for følgende events: ");
        getEventsToFacilitator(Database.getName());

        System.out.println("Her kan du se de forskellige arrangementer:");
        Arrangement.getArrangements();
        System.out.println("Vælg hvilket arrangement du vil se informationer om:");
        Scanner aScanner = new Scanner(System.in);
        String s = aScanner.nextLine();
        getFacilitatorInformation(s.toLowerCase());

        System.out.println("Vil du gerne importere data fra CSV-fil?");
        System.out.println("ja eller nej");
        Scanner csvChoice = new Scanner(System.in);
        String csv = csvChoice.nextLine().toLowerCase();
        if(csv.equals("ja")) {

//                Csv.importArrangement();
//                Csv.importEvent();
//                Csv.ImportUsers();

        } else {
            System.out.println("Tak for denne gang...");
            System.exit(0);
        }

    }

    public static void getFacilitatorInformation(String s) {
        try {
            String query = "SELECT * FROM `arrangement` WHERE `aName`='"+ s + "'";
            st = Database.getConnect().createStatement();
            rs = st.executeQuery(query);

            if (rs.next()) {
                String start = rs.getString("aStart");
                String end = rs.getString("Aend");
                String price = rs.getString("aPrice");
                String attendees = rs.getString("attendees");

                System.out.println("Arrangementet starter: " + start);
                System.out.println("Arrangementet slutter: " + end);
                System.out.println("Arrangementet koster: " + price);
                System.out.println("Deltagere : " +attendees);
            } else {
                System.out.println("Fejl...");
                Facilitator.facilitatorLogin();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getEventsToFacilitator(String name) {
        try {
            String query = "SELECT `eName` FROM `event` WHERE `eFacilitator`='"+ name + "'";
            st = Database.getConnect().createStatement();
            rs = st.executeQuery(query);

            if (rs.next()) {

                String eName = rs.getString("eName");
                System.out.println(eName);

            } else {
                System.out.println("Fejl...");
                Facilitator.facilitatorLogin();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getFacilitator() {
        try {
            String query = "SELECT * FROM users WHERE role='Facilitator'";
            st = Database.getConnect().createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                String name = rs.getString("name");
                System.out.println("-----------------");
                System.out.println(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
