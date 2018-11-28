import java.sql.SQLException;

public class Customer extends User {


    public static void customerLogin(String n) {

        System.out.println("Arrangementer du er tilmeldt til:");
        getCustomer(n);
    }

    public static void getCustomer(String s) {
        try {
            String query = "SELECT * FROM `arrangement` WHERE `attendees` LIKE '%"+ s +"%'";
            setupStatement(query);

            if (rs.next()) {
                String aName = rs.getString("aName");
                String aStart = rs.getString("aStart");
                String aEnd = rs.getString("aEnd");
                String attendees = rs.getString("attendees");

                System.out.println("--------------------");
                System.out.println(aName);
                System.out.println("Arrangementet starter: " +aStart);
                System.out.println("Arrangementet slutter: " +aEnd);
                System.out.println("Arrangementets deltagere: " +attendees);

            } else {
                System.out.println("Du er ikke tilmeldt nogle arrangementer.");
                System.exit(0);
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

}
