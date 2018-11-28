import java.sql.SQLException;
import java.util.Scanner;

public class Secretary extends User {


    public static void secretaryLogin() {
        System.out.println("1. Arrangement menu \t 2. Event menu \t 3. Exporter CSV-fil 4. Log ud");
        Scanner menu = new Scanner(System.in);
        int menuChoice = menu.nextInt();
        Arrangement arrangement = new Arrangement();
        if(menuChoice == 1) {

            System.out.println("1. Tilføj arrangement \t 2. Rediger arrangement \t 3. Slet arrangement");
            Scanner choice = new Scanner(System.in);
            int userChoice = choice.nextInt();
            switch (userChoice) {
                case 1:
                    Database.arrangementToDatabase();
                    break;
                    case 2: System.out.println("Vælg hvilket arrangement du vil redigere:");
                    Arrangement.getArrangements();
                    Scanner edit = new Scanner(System.in);
                    String i = edit.nextLine();
                    Database.editArrangementInDatabase(i);
                    break;
                    case 3: System.out.println("Vælg hvilket arrangement du vil slette:");
                    arrangement.getArrangements();
                    Scanner delete = new Scanner(System.in);
                    String j = delete.nextLine();
                    try {
                        arrangement.deleteArrangement(j.toLowerCase());
                    } catch (SQLException sqlEx) {
                        sqlEx.printStackTrace();
                    }
                    break;
                    default: System.out.println("prøv igen");
                    break;
            }
        } else if(menuChoice == 2) {
                System.out.println("1. Tilføj event \t 2. Rediger event \t 3. Slet event");
                Scanner Choice = new Scanner(System.in);
                int eChoice = Choice.nextInt();
                switch (eChoice) {
                    case 1:
                        Database.eventToDatabase();
                        break;

                    case 2:
                        System.out.println("Vælg hvilket event du vil redigere:");
                        Event.getEvent();
                        Scanner edit = new Scanner(System.in);
                        String i = edit.nextLine();
                        Database.editEventInDatabase(i);
                        break;


                    case 3:
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
            } else if(menuChoice == 3) {

                Csv.exportArrangement();
                Csv.exportEvent();
                Csv.exportUsers();

            } else {
                ArrangementHandler.arrangementLogin();
            }
        }
    }

