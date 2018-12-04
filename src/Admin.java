import java.sql.*;
import java.util.Scanner;

public class Admin extends User {

    private static String username;
    private static String password;
    private static String fullName;
    private static String role;

    public Admin() {

    }

    public Admin(String username, String password, String fullName, String role) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getRole() {
        return role;
    }

    public static void adminLogin() {
        System.out.println("1. Tilføj bruger \t 2. Redigér bruger \t 3. Slet bruger \t 4. Log ud \t 5. Afslut program");
        Scanner choice = new Scanner(System.in);
        String userChoice = choice.nextLine();
        switch (userChoice) {
            case "1":
                Admin admin= new Admin();
                Admin t = admin.newUser();
                Database.userToDatabase(t, true);
                break;

            case "2":
                System.out.println("Hvilken bruger du vil redigere - skriv kun navn:");
                getUser();
                Scanner edit = new Scanner(System.in);
                String i = edit.nextLine();

                Database.editUserInDatabase(i);
                break;

            case "3":
                System.out.println("Hvilken bruger du vil slette - skriv kun navn:");
                getUser();
                Scanner delete = new Scanner(System.in);
                String j = delete.nextLine();
                deleteUser(j.toLowerCase());
                break;

            case "4":
                ArrangementHandler.arrangementLogin();

            case "5":
                System.out.println("Program lukker ned....");
                System.exit(0);
            default:
                System.out.println("Ikke en valgmulighed.");
                adminLogin();
                break;
        }
    }

    public Admin newUser() {

        System.out.println("Udfyld felterne:");
        Scanner arrScanner = new Scanner(System.in);

        System.out.println("Brugernavn: ");
        username = arrScanner.nextLine();
        System.out.println("Kodeord: ");
        password = arrScanner.nextLine();
        System.out.println("Fulde navn: ");
        fullName = arrScanner.nextLine();
        System.out.println("Rolle:\t 1. Customer\t2. Facilitator\t  3. Secretary\t 4. Admin");
        boolean hasRole = false;
        while (!hasRole) {
            String roleChoice = arrScanner.nextLine();
            switch (roleChoice) {
                case "1":
                    role = "Customer";
                    hasRole = true;
                    break;
                case "2":
                    role = "Facilitator";
                    hasRole = true;
                    break;
                case "3":
                    role = "Secretary";
                    hasRole = true;
                    break;
                case "4":
                    role = "Admin";
                    hasRole = true;
                    break;
                default:
                    System.out.println("Ikke en valgmulighed - prøv igen.");
            }
        }
        Admin a = new Admin(username, password, fullName, role);
        return a;
    }
    public static void deleteUser(String i) {
        try {
            String query = "SELECT * FROM users WHERE username='" + i + "'";
            st = Database.getConnect().createStatement();
            ResultSet resultSet = st.executeQuery(query);
            resultSet.last();
            if (resultSet.getRow() == 0) {
                System.out.println("Ingen brugere med dette navn.");
                st.close();
                resultSet.close();
                adminLogin();
            } else {
                String sql = "DELETE FROM users WHERE username='" + i + "'";

                st = Database.getConnect().createStatement();
                st.execute(sql);
                System.out.println("Brugeren " + i + " er nu slettet.");
                st.close();
                adminLogin();
            }
        } catch(SQLException sqlEx){
                sqlEx.printStackTrace();
        }
    }
    public static void getUser() {
        try {
            String query = "SELECT * FROM users";
            setupStatement(query);

            while (rs.next()) {
                String username = rs.getString("username");
                String role = rs.getString("role");
                System.out.println("-----------------");
                System.out.println(username + " - " + role);
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }
}