import javax.net.ssl.SSLException;
import java.sql.SQLException;
import java.util.Scanner;

public class Secretary extends User{

    private static Arrangement a;

    public static void secretaryLogin() {
        System.out.println("1. Tilføj arrangement \t 2. Rediger arrangement \t 3. Slet arrangement");
        Scanner choice = new Scanner(System.in);
        int userChoice = choice.nextInt();
        switch (userChoice) {
            case 1:
                System.out.println("Udfyld felterne:");
                Scanner arrScanner = new Scanner(System.in);

                System.out.println("Navn på arrangement: ");
                name = arrScanner.nextLine();
                System.out.println("Arrangement type: ");
                type = arrScanner.nextLine();
                System.out.println("Beskrivelse: ");
                description = arrScanner.nextLine();
                System.out.println("Varighed: ");
                aDuration = arrScanner.nextLine();
                   System.out.println("Anden info: ");
                additionalInfo = arrScanner.nextLine();
                System.out.println("id: ");
                id = arrScanner.nextInt();

                try {
                    a.makeArrangement(id, aDuration, name, type, description, additionalInfo);
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }

                break;


            case 2:
                System.out.println("test2");
                break;

            case 3:
                System.out.println("test3");
                break;

            default:
                System.out.println("prøv igen");
                break;
        }
    }
}
