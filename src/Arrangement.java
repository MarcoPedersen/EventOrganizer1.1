import java.sql.SQLException;
import java.sql.Statement;


public class Arrangement {

    private int id;
    private int arrangementDuration;
    private String name;
    private String type;
    private String description;
    private String additionalInfo;
    private static Statement st;


    public static void makeArrangement(int id, String arrangementDuration, String name, String type, String description, String additionalInfo) throws SQLException {

        String sql = "INSERT INTO `arrangement`(`id`, `arrangementDuration`, `name`, `type`,`description`,`additionalInfo` ) VALUES (\"" + id + "\", \"" + arrangementDuration + "\", \"" + name + "\", \"" + type + "\", \"" + description + "\" + \"" + additionalInfo + "\")";

        st = Database.getConnect().createStatement();
        st.execute(sql);
        st.close();

    }

    public static void editArrangement() {

    }

    public static void deleteArrangement() {

    }

}
