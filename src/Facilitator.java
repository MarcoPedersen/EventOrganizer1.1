import java.util.Scanner;
import java.sql.SQLException;
public class Facilitator extends User {

    public static Arrangement b;

    public static void facilitatorLogin() {
        System.out.println("1. se arrangementer du er tilmeldt \t 2.log af");
        Scanner choice = new Scanner(system.in);
        int userChoice = choice.nextInt();
        switch (userChoice) {
            case 1: System.out.println("du er tilmeldt til følgende arrangement(er) +*link til disse*");
                try {
                    b.viewArrangement(id, aDuration, name, type, description, additionalInfo);
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            break;
            case 2: System.out.println("du logger nu af systemet");

        }
    }


}
