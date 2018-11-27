import java.sql.*;
import java.util.Scanner;

public class Arrangement {

    private String name;
    private String start;
    private String end;
    private String price;
    private String attendees;
    private static Statement st;
    private static ResultSet rs;

    public Arrangement(String name, String start, String end, String price, String attendees) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.price = price;
        this.attendees = attendees;
    }

    public Arrangement() {

    }

    public String getName() {
        return name;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public String getPrice() {
        return price;
    }

    public String getAttendees() {
        return attendees;
    }

    public Arrangement makeArrangement() {

        System.out.println("Udfyld felterne:");
        Scanner arrScanner = new Scanner(System.in);

        System.out.println("Navn på arrangement: ");
        String aName = arrScanner.nextLine();
        System.out.println("Arrangementets startstidspunkt: ");
        String aStart = arrScanner.nextLine();
        System.out.println("Arrangementets sluttidspunkt: ");
        String aEnd = arrScanner.nextLine();
        System.out.println("Prisen for arrangementet: ");
        String aPrice = arrScanner.nextLine();
        System.out.println("Tilmeldte: ");
        String attendees = arrScanner.nextLine();

        Arrangement arrangement = new Arrangement(aName, aStart, aEnd, aPrice, attendees);

        return arrangement;
    }

    public Arrangement editArrangement() {

        System.out.println("Skriv de nye informationer:");
        System.out.println("-----------------------------");
        Scanner arrScanner = new Scanner(System.in);

        System.out.println("Navn på arrangement: ");
        String Name = arrScanner.nextLine();
        System.out.println("Arrangementets startstidspunkt: ");
        String Start = arrScanner.nextLine();
        System.out.println("Arrangementets sluttidspunkt: ");
        String End = arrScanner.nextLine();
        System.out.println("Prisen for arrangementet: ");
        String Price = arrScanner.nextLine();
        System.out.println("Tilmeldte: ");
        String Attendees = arrScanner.nextLine();

        Arrangement arrangement = new Arrangement(Name, Start, End, Price, Attendees);

        return arrangement;
    }

    public void deleteArrangement(String i) throws SQLException {

        String sql = "DELETE FROM arrangement WHERE aName='"+ i + "'";
        st = Database.getConnect().createStatement();
        st.execute(sql);
        System.out.println("Dit arrangement er nu slettet.");
        Secretary.secretaryLogin();
        st.close();
    }

    public static void getArrangements() {
        try {
            String query = "SELECT * FROM arrangement";
            st = Database.getConnect().createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                String name = rs.getString("aName");
                System.out.println("-----------------");
                System.out.println(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
