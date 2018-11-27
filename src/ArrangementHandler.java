import java.util.Scanner;
public class ArrangementHandler {

    public static void main(String[] args) {

        Database.connectToDatabase();
        Scanner login = new Scanner(System.in);

        System.out.println("Brugernavn: ");
        String u = login.nextLine();
        System.out.println("Kodeord: ");
        String p = login.nextLine();

        Database.userLogin(u.toLowerCase(), p.toLowerCase());
    }


}
