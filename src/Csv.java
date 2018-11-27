import java.io.FileWriter;
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

        } catch (SQLException e) {
            e.printStackTrace();
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

    }
