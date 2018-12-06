import java.util.Scanner;
public class ArrangementHandler {

    public static void main(String[] args) {
        arrangementLogin();
    }

    // Startpunktet, hvor man også lander, hvis man logger ud
    public static void arrangementLogin() {
        Database.connectToDatabase();
        Scanner login = new Scanner(System.in);

        System.out.println("Brugernavn: ");
        String u = login.nextLine();
        System.out.println("Kodeord: ");
        String p = login.nextLine();

        Database.userLogin(u.toLowerCase(), p.toLowerCase());


    }

}
