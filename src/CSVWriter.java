import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CSVWriter {
    public static void fileWriter(){
        String filename = "desktop:test.csv";
        try {
            FileWriter fw = new FileWriter(filename);
            Class.forName("com.mysql.jdbc-Driver").getConstructor().newInstance();
            Connection conn =

                    DriverManager.getConnection("jdbc:mysql://localhost:3306/event?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false", "brugernavn", "kode");
            String query = "select * from testtable";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                fw.append(rs.getString(1));
                fw.append(',');
                fw.append(rs.getString(2));
                fw.append(',');
                fw.append(rs.getString(3));
                fw.append('\n');
            }
            fw.flush();
            fw.close();
            conn.close();
            System.out.println("CSV fil er blevet dannet lokalt på din desktop.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
