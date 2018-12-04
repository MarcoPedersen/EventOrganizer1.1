import java.sql.SQLException;
import java.util.Scanner;

public class Facilitator extends User {

    public static void facilitatorLogin() {
        getEventsToFacilitator(Database.getName());
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Log ud\t 2. Afslut program \nVælg hvilket event du vil se information om:");
        String event = sc.nextLine();
        if (event.equals("1")) {
            ArrangementHandler.arrangementLogin();
        } else if(event.equals("2")) {
            System.out.println("Program lukker ned...");
            System.exit(0);
        } else {
            getFacilitatorInformation(event);

        }
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

                System.out.print("Event-navn: " + name + " \nBeskrivelse: " + description + " \nEvent-type: " + type + "" +
                        " \nLøs tekst: " + text + " \nTilknyttet arrangement: " + arrangement + " \nEventets varighed: " + duration + " time(r)");

                String query2 = "SELECT * FROM `arrangement` WHERE `aName`='"+ arrangement + "'";
                setupStatement(query2);

                if(rs.next()) {
                    String aName = rs.getString("aName");
                    String aStart = rs.getString("aStart");
                    String aEnd = rs.getString("aEnd");
                    String aPrice = rs.getString("aPrice");
                    String attendees = rs.getString("attendees");

                    System.out.println("\n" + "Tilknyttet til arrangementet: " + aName);
                    System.out.println("\nArrangementet starter: " + aStart + "\nArrangementet slutter: " + aEnd + "\nPris: " + aPrice
                    + "\nDeltagere: " + attendees);

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
            rs.last();
            if (rs.getRow() == 0) {
                System.out.println("Du er ikke ansvarlig for nogle events - endnu.\n1. Log ud\t 2. Afslut program");
                Scanner sc = new Scanner(System.in);
                String choice = sc.nextLine();
                if (choice.equals("1")) {
                    ArrangementHandler.arrangementLogin();
                } else if(choice.equals("2")) {
                    System.exit(0);
                } else {
                    System.out.println("Ikke en valgmulighed - prøv igen.");
                }
            } else {
                rs.beforeFirst();
                System.out.println("Du er ansvarlig for følgende events: ");
                while (rs.next()) {
                    String eName = rs.getString("eName");
                    System.out.println(eName);
                    System.out.println("----------------");
                }
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
        try {
            String query = "SELECT * FROM `users` WHERE `name`='" + i + "' AND `role`='Facilitator'";
            setupStatement(query);
            rs.last();
            if (rs.getRow() == 0) {
                System.out.println("Ingen facilitatorer med dette navn - prøv igen.");
                return false;
            } else {
                return true;
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return false;
    }
}