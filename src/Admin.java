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
        System.out.println("1. Tilføj bruger \t 2. Redigér bruger \t 3. Slet bruger \t 4. Afslut program");
        Scanner choice = new Scanner(System.in);
        int userChoice = choice.nextInt();
        switch (userChoice) {
            case 1:
                Database.userToDatabase();
                break;

            case 2:
                System.out.println("Vælg hvilken bruger du vil redigere:");
                getUser();
                Scanner edit = new Scanner(System.in);
                String i = edit.nextLine();

                Database.editUserInDatabase(i);
                break;

            case 3:
                System.out.println("Vælg hvilken bruger du vil slette:");
                getUser();
                Scanner delete = new Scanner(System.in);
                String j = delete.nextLine();
                try {
                    deleteUser(j.toLowerCase());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;

            case 4:
                System.out.println("Program lukker ned....");
                System.exit(0);
            default:
                System.out.println("prøv igen");
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
        System.out.println("Rolle: ");
        role = arrScanner.nextLine();

        Admin a = new Admin(username, password, fullName, role);

        return a;
    }


    public static void deleteUser(String i) throws SQLException {

        String sql = "DELETE FROM users WHERE username='"+ i + "'";

        st = Database.getConnect().createStatement();
        st.execute(sql);
        System.out.println("Brugeren " + i + " er nu slettet.");
        st.close();
        adminLogin();
    }

    public static void getUser() {
        try {
            String query = "SELECT * FROM users";
            st = Database.getConnect().createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                String username = rs.getString("username");
                System.out.println("-----------------");
                System.out.println(username);
            }
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }
}
