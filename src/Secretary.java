import java.sql.SQLException;
import java.util.Scanner;

public class Secretary extends User implements Login {


    // Secretary login-side
    public static void login() {
        System.out.println("1. Arrangement-menu \t 2. Event-menu \t 3. Eksporter CSV-fil \t 4. Importer CSV-fil \t 5. Log ud \t 6. Afslut program");
        Scanner menu = new Scanner(System.in);
        String menuChoice = menu.nextLine();
        Arrangement arrangement = new Arrangement();
        if (menuChoice.equals("1")) {

            System.out.println("1. Tilføj arrangement \t 2. Rediger arrangement \t 3. Slet arrangement \t 4. Tilbage");
            Scanner choice = new Scanner(System.in);
            boolean isChoice = false;
            while (!isChoice) {
                String userChoice = choice.nextLine();
                switch (userChoice) {
                    case "1":
                        isChoice = true;
                        Arrangement a = arrangement.newArrangement();
                        Database.insertArrangement(a, "Dit arrangement er nu oprettet", true);
                        break;
                    case "2":
                        isChoice = true;
                        System.out.println("Vælg hvilket arrangement du vil redigere:");
                        Arrangement.getArrangements();
                        Scanner edit = new Scanner(System.in);
                        String i = edit.nextLine();
                        Database.editArrangementInDatabase(i);
                        break;
                    case "3":
                        isChoice = true;
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
                    case "4":
                        isChoice = true;
                        login();
                        break;
                    default:
                        System.out.println("Ikke en valgmulighed - prøv igen.");
                        break;
                }
            }
        } else if (menuChoice.equals("2")) {
            System.out.println("1. Tilføj event \t2. Rediger event \t 3. Slet event \t  4. Tilbage");
            Scanner Choice = new Scanner(System.in);
            boolean isChoice = false;
            while (!isChoice) {
                String eChoice = Choice.nextLine();
                switch (eChoice) {
                    case "1":
                        Event event = new Event();
                        Event e = event.newEvent();
                        isChoice = true;
                        Database.eventToDatabase(e, true);
                        break;

                    case "2":
                        System.out.println("Vælg hvilket event du vil redigere:");
                        Event.getEvent();
                        Scanner edit = new Scanner(System.in);
                        String i = edit.nextLine();
                        isChoice = true;
                        Database.editEventInDatabase(i);
                        break;


                    case "3":
                        System.out.println("Vælg hvilket event du vil slette:");
                        Event.getEvent();
                        isChoice = true;
                        Scanner delete = new Scanner(System.in);
                        String j = delete.nextLine();
                        try {
                            Event.deleteEvent(j.toLowerCase());
                        } catch (SQLException sqlEx) {
                            sqlEx.printStackTrace();
                        }
                        break;
                    case "4":
                        isChoice = true;
                        login();
                        break;
                    default:
                        System.out.println("Ikke en valgmulighed - prøv igen.");
                }
            }
        } else if (menuChoice.equals("3")) {
            Csv.exportArrangement();
            Csv.exportEvent();
            Csv.exportUsers();
        } else if (menuChoice.equals("4")) {
            Csv.importArrangement();
            Csv.importEvent();
            Csv.importUsers();
            login();
        } else if (menuChoice.equals("5")) {
            ArrangementHandler.arrangementLogin();
        } else if (menuChoice.equals("6")){
            System.out.println("Program lukker ned...");
            System.exit(0);
        } else {
            System.out.println("Ikke en valgmulighed.");
            login();
        }
    }
}

