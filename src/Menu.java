public class Menu {

    public static void runMenu() {

        while(Database.userLogin() == 1) {

            String role;

            /*switch (role) {
                case customer:
                    Customer.customerMenu();
                    break;
                case secretary:
                    Secretary.secretaryMenu();
                    break;
                case facilitator:
                    Facilitator.facilitatorMenu();
                    break;
                case admin:
                    Admin.adminMenu();
                    break;
                default:
                    "Not a valid login";
                    break;

            } */

        }

        System.out.println("Forkert login!");

    }
}
