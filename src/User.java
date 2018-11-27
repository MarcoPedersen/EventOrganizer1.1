import java.sql.*;
import java.util.Scanner;

public class User {

    static Statement st;
    static ResultSet rs;

    public static void adminLogin() {
        System.out.println("1. Tilføj bruger \t 2. Redigér bruger \t 3. Slet bruger \t 4. Afslut program");
        Scanner choice = new Scanner(System.in);
        int userChoice = choice.nextInt();
        switch (userChoice) {
            case 1:
                System.out.println("Udfyld felterne:");
                Scanner arrScanner = new Scanner(System.in);

                System.out.println("Brugernavn: ");
                String username = arrScanner.nextLine();
                System.out.println("Kodeord: ");
                String password = arrScanner.nextLine();
                System.out.println("Fulde navn: ");
                String fullName = arrScanner.nextLine();
                System.out.println("Rolle: ");
                String role = arrScanner.nextLine();

                addUser(username.toLowerCase(), password.toLowerCase(), fullName, role);

                break;

            case 2:
                System.out.println("Vælg hvilken bruger du vil redigere:");
                getUser();
                Scanner edit = new Scanner(System.in);
                String i = edit.nextLine();
                try {
                    editUser(i);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
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

    public static void addUser(String u, String p, String fn, String r) {
        try {
            String sql = "INSERT INTO `users`(`id`, `username`, `password`, `name`,`role`) VALUES (null, \"" + u + "\", \"" + p + "\", \"" + fn + "\", \"" + r + "\")";

            st = Database.getConnect().createStatement();
            st.execute(sql);
            System.out.println("Bruger " + u + " er nu oprettet.");
            adminLogin();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void editUser(String i) throws SQLException {

        System.out.println("Skriv de nye informationer:");
        System.out.println("-----------------------------");
        Scanner userScanner = new Scanner(System.in);

        System.out.println("Nyt brugernavn: ");
        String username = userScanner.nextLine();
        System.out.println("Nyt kodeord: ");
        String password = userScanner.nextLine();
        System.out.println("Fulde navn: ");
        String name = userScanner.nextLine();
        System.out.println("Rolle: ");
        String role = userScanner.nextLine();


        String sql = "UPDATE `users` SET `username`='"+ username + "', `password`='" + password +"', `name`='"+ name + "', `role`='" + role + "' WHERE `username`='"+ i +"'";
        Database.getConnect();
        st = Database.getConnect().createStatement();
        st.executeUpdate(sql);
        System.out.println("Brugeroplysningerne er nu opdateret.");
        adminLogin();
        st.close();
    }

    public static void deleteUser(String i) throws SQLException {

        String sql = "DELETE FROM users WHERE username='"+ i + "'";

        st = Database.getConnect().createStatement();
        st.execute(sql);
        System.out.println("Brugeren " + i + " er nu slettet.");
        adminLogin();
        st.close();
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}