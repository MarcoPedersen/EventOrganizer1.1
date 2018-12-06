import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Csv {

    // eksporterer arrangementer til en CSV-fil i resources databasen
    public static void exportArrangement() {

        try {

            try {

                String arrfile ="resources/ExportArrangement.csv";

                Database.connectToDatabase();
                FileWriter fw = new FileWriter(arrfile);

                String query = "select * from `arrangement`";
                Statement st = Database.getConnect().createStatement();
                ResultSet rs = st.executeQuery(query);

                while (rs.next()) {
                    fw.append(rs.getString(2));
                    fw.append(',');
                    fw.append(rs.getString(3));
                    fw.append(',');
                    fw.append(rs.getString(4));
                    fw.append(',');
                    fw.append(rs.getString(5));
                    fw.append(',');
                    fw.append(rs.getString(6));
                    fw.append('\n');
                }

                fw.flush();
                fw.close();
                st.close();

                System.out.println("CSV Fil er eksporteret.");
            } catch (IOException i) {
                i.printStackTrace();
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    // eksporterer events til en CSV-fil i resources fra databasen
    public static void exportEvent() {

        try {
            try {

            String evefile ="resources/ExportEvent.csv";
            FileWriter fw = new FileWriter(evefile);
            String query2 = "select * from `event`";
            Statement st = Database.getConnect().createStatement();
            ResultSet rs = st.executeQuery(query2);

            while (rs.next()) {
                fw.append(rs.getString(2));
                fw.append(',');
                fw.append(rs.getString(3));
                fw.append(',');
                fw.append(rs.getString(4));
                fw.append(',');
                fw.append(rs.getString(5));
                fw.append(',');
                fw.append(rs.getString(6));
                fw.append(',');
                fw.append(rs.getString(7));
                fw.append(',');
                fw.append(rs.getString(8));
                fw.append(',');
                fw.append(rs.getString(9));
                fw.append('\n');
            }

            fw.flush();
            fw.close();
            st.close();

            } catch (IOException i) {
                i.printStackTrace();
            }

        } catch (SQLException i) {
            i.printStackTrace();
        }
    }

    // eksporterer brugere til en CSV-fil i resources fra databasen
    public static void exportUsers() {

        try {
            try {

            String userfile = "resources/ExportUsers.csv";
            FileWriter fw = new FileWriter(userfile);
            String query3 = "select * from `users`";
            Statement st = Database.getConnect().createStatement();
            ResultSet rs = st.executeQuery(query3);

            while (rs.next()) {
                fw.append(rs.getString(2));
                fw.append(',');
                fw.append(rs.getString(3));
                fw.append(',');
                fw.append(rs.getString(4));
                fw.append(',');
                fw.append(rs.getString(5));
                fw.append('\n');
            }

            fw.flush();
            fw.close();
            st.close();

            } catch (IOException i) {
                i.printStackTrace();
            }

            } catch (SQLException i) {
                i.printStackTrace();
         }
    }

    // importerer arrangementer fra en CSV-fil i resources til databasen
    public static void importArrangement() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("resources/ExportArrangement.csv"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] array = line.split(",");
                Arrangement arrangement = new Arrangement(array[0], array[1], array[2], array[3], array[4]);
                Database.insertArrangement(arrangement," Din CSV fil er nu importeret", false);
            }
            br.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    // importerer brugere fra en CSV-fil i resources til databasen
    public static void importUsers() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("resources/ExportUsers.csv"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] array = line.split(",");
                Admin admin = new Admin(array[0], array[1], array[2], array[3]);
                Database.userToDatabase(admin, false);
            }
            br.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    // importerer events fra en CSV-fil i resources til databasen
    public static void importEvent() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("resources/ExportEvent.csv"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] array = line.split(",");
                Event event = new Event(array[0], array[1], array[2], array[3], array[4], array[5], array[6], array[7]);
                Database.eventCSVDatabase(event, false);
            }
            br.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
