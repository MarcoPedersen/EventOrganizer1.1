import java.sql.SQLException;
import java.util.Scanner;

public class Customer extends User {


    public static void customerLogin(String n) {

        System.out.println("Arrangementer du er tilmeldt til:");
        getCustomer(n);
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Log ud\t2. Afslut programmet");
        String choice = sc.nextLine();
        switch (choice) {
            case "1":
                ArrangementHandler.arrangementLogin();
                break;
            case "2":
                System.out.println("Program lukker ned....");
                System.exit(0);
                break;
            default:
                System.out.println("Ikke en valgmulighed - prøv igen.");
                ArrangementHandler.arrangementLogin();
        }
    }

    public static void getCustomer(String s) {
        try {
            String query = "SELECT * FROM `arrangement` WHERE `attendees` LIKE '%"+ s +"%'";
            setupStatement(query);

            rs.last();
            if (rs.getRow() == 0) {
                Scanner sc = new Scanner(System.in);
                System.out.println("Du er ikke tilknyttet nogle events.\n1. Log ud\t 2. Afslut program");
                String choice = sc.nextLine();
                if (choice.equals("1")) {
                    ArrangementHandler.arrangementLogin();
                } else if (choice.equals("2")) {
                    System.out.println("Program lukker ned...");
                    System.exit(0);
                } else {
                    System.out.println("Dette er ikke en valgmulighed - prøv igen.");
                }
            } else {
                rs.beforeFirst();

                while (rs.next()) {
                    String aName = rs.getString("aName");
                    String aStart = rs.getString("aStart");
                    String aEnd = rs.getString("aEnd");
                    String attendees = rs.getString("attendees");
                    String price = rs.getString("aPrice");

                    System.out.println("--------------------");
                    System.out.println("Arrangementets navn: " + aName);
                    System.out.println("Arrangementet starter: " + aStart);
                    System.out.println("Arrangementet slutter: " + aEnd);
                    System.out.println("Pris: " + price + " kr.");
                    System.out.println("Arrangementets deltagere: " + attendees);

                }
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }
}
