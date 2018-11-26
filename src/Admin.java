import java.util.Scanner;

public class Admin {

    public static void adminMenu() {
        System.out.print("Welcome to EventOrganizer!\n1. Add user\t2. Delete user\t3. Exit");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch(choice) {
            case 1: addUser();
            break;
            case 2: deleteUser();
            break;
            case 3:
            break;
        }
    }

}
