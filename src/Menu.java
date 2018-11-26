public class Menu {

    Database.getRole();

    switch(role) {
        case customer: Customer.customerMenu();
            break;
        case secretary: Secretary.secretaryMenu();
            break;
        case facilitator: Facilitator.facilitatorMenu();
            break;
        case admin: Admin.adminMenu();
        default: "Not a valid login";
            break;

    }

    public 2

}
