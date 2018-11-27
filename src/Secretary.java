import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Date;

public class Secretary extends User{

    private static Arrangement a;

    public static void secretaryLogin() {
        System.out.println("1. Arrangement menu \t 2. Event menu \t 3. Exporter CSV-fil 4. Afslut program");
        Scanner menu = new Scanner(System.in);
        int menuChoice = menu.nextInt();
        if(menuChoice == 1) {

                System.out.println("1. Tilføj arrangement \t 2. Rediger arrangement \t 3. Slet arrangement");
                Scanner choice = new Scanner(System.in);
                int userChoice = choice.nextInt();
                switch (userChoice) {
                    case 1:
                        try {
                            a.makeArrangement();
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 2:
                        System.out.println("Vælg hvilket arrangement du vil redigere:");
                        a.getArrangements();
                        Scanner edit = new Scanner(System.in);
                        String i = edit.nextLine();
                        try {
                            a.editArrangement(i.toLowerCase());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        break;

                    case 3:
                        System.out.println("Vælg hvilket arrangement du vil slette:");
                        a.getArrangements();
                        Scanner delete = new Scanner(System.in);
                        String j = delete.nextLine();
                        try {
                            a.deleteArrangement(j.toLowerCase());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        break;

                    default:
                        System.out.println("prøv igen");
                        break;
                }
        } else if(menuChoice == 2) {
                System.out.println("1. Tilføj event \t 2. Rediger event \t 3. Slet event");
                Scanner Choice = new Scanner(System.in);
                int eChoice = Choice.nextInt();
                switch (eChoice) {
                    case 1:
                        try {
                            Event.makeEvent();
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 2:
                        System.out.println("Vælg hvilket event du vil redigere:");
                        Event.getEvent();
                        Scanner edit = new Scanner(System.in);
                        String i = edit.nextLine();
                        try {
                            Event.editEvent(i);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        break;


                    case 3:
                        System.out.println("Vælg hvilket event du vil slette:");
                        Event.getEvent();
                        Scanner delete = new Scanner(System.in);
                        String j = delete.nextLine();
                        try {
                            Event.deleteEvent(j.toLowerCase());
                        } catch (SQLException e) {
                            e.printStackTrace();
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
                System.out.println("Programmet lukker ned...");
                System.exit(0);
            }
        }
    }

