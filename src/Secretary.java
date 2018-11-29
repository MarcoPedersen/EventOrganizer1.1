import java.sql.SQLException;
import java.util.Scanner;

public class Secretary extends User {


    public static void secretaryLogin() {
        System.out.println("1. Arrangement-menu \t 2. Event-menu \t 3. Eksporter CSV-fil \t 4. Log ud \t 5. Afslut program");
        Scanner menu = new Scanner(System.in);
        String menuChoice = menu.nextLine();
        Arrangement arrangement = new Arrangement();
        if (menuChoice.equals("1")) {

            System.out.println("1. Tilføj arrangement \t 2. Rediger arrangement \t 3. Slet arrangement");
            Scanner choice = new Scanner(System.in);
            String userChoice = choice.nextLine();
            switch (userChoice) {
                case "1":
                    Database.arrangementToDatabase();
                    break;
                case "2":
                    System.out.println("Vælg hvilket arrangement du vil redigere:");
                    Arrangement.getArrangements();
                    Scanner edit = new Scanner(System.in);
                    String i = edit.nextLine();
                    Database.editArrangementInDatabase(i);
                    break;
                case "3":
                    System.out.println("Vælg hvilket arrangement du vil slette:");
                    arrangement.getArrangements();
                    Scanner delete = new Scanner(System.in);
                    String j = delete.nextLine();
                    try {
                        arrangement.deleteArrangement(j.toLowerCase());
                    } catch (SQLException sqlEx) {
                        sqlEx.printStackTrace();
                    }
                    break;
                default:
                    System.out.println("Ikke en valgmulighed - prøv igen.");
                    secretaryLogin();
                    break;
            }
        } else if (menuChoice.equals("2")) {
            System.out.println("1. Tilføj event \t 2. Rediger event \t 3. Slet event");
            Scanner Choice = new Scanner(System.in);
            String eChoice = Choice.nextLine();
            switch (eChoice) {
                case "1":
                    Database.eventToDatabase();
                    break;

                case "2":
                    System.out.println("Vælg hvilket event du vil redigere:");
                    Event.getEvent();
                    Scanner edit = new Scanner(System.in);
                    String i = edit.nextLine();
                    Database.editEventInDatabase(i);
                    break;


                case "3":
                    System.out.println("Vælg hvilket event du vil slette:");
                    Event.getEvent();
                    Scanner delete = new Scanner(System.in);
                    String j = delete.nextLine();
                    try {
                        Event.deleteEvent(j.toLowerCase());
                    } catch (SQLException sqlEx) {
                        sqlEx.printStackTrace();
                    }
                    break;

                default:
                    System.out.println("Noget gik galt");
            }

        } else if (menuChoice.equals("3")) {

            Csv.exportArrangement();
            Csv.exportEvent();
            Csv.exportUsers();

        } else if (menuChoice.equals("4")) {
            ArrangementHandler.arrangementLogin();
        } else if (menuChoice.equals("5")){
            System.out.println("Program lukker ned...");
            System.exit(0);
        } else {
            System.out.println("Ikke en valgmulighed.");
            secretaryLogin();
        }
    }
}

