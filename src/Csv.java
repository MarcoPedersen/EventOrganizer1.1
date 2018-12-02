import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Csv {

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
                fw.append('\n');
            }

            fw.flush();
            fw.close();
            st.close();

            } catch (IOException i) {
                i.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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

            } catch (SQLException e) {
                e.printStackTrace();
         }
        }
    public static void importArrangement() {

        try {
            BufferedReader bReader = new BufferedReader(new FileReader("resources/ExportArrangement.csv"));

            while (bReader != null) {
                String read;
                try {
                    read = bReader.readLine();
                    if (read != null)
                    {
                        String[] array = read.split(",+");
                        Arrangement arrangement = new Arrangement(array[0], array[1], array[2], array[3], array[4]);
                        Database.insertArrangement(arrangement," Din CSV fil er nu importeret", false);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                finally
                {
                    if (bReader == null)
                    {
                        bReader.close();
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public static void importEvent() {

        try {
            BufferedReader bReader = new BufferedReader(new FileReader("resources/ExportEvent.csv"));

            while (bReader != null) {
                String read;
                try {
                    read = bReader.readLine();
                    if (read != null)
                    {
                        String[] array = read.split(",+");
                        Event event = new Event(array[0], array[1], array[2], array[3], array[4], array[5]);
                        Database.eventToDatabase(event);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                finally
                {
                    if (bReader == null)
                    {
                        bReader.close();
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
