import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User {

    static Statement st;
    static ResultSet rs;


    public User() {

    }

    public static void setupStatement(String query) {
        try {
            st = Database.getConnect().createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}