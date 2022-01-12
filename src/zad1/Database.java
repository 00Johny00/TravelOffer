package zad1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Database extends JFrame {

    private TravelData travelData;
    private String url;
    private String dateFormat;
    List<String> listToDb = new ArrayList<String>();
    Connection conn = null;
     Statement statement =null;

    JFrame frame = new JFrame();
    JTable table = new JTable();
    JButton buttonChangeLanguagePL = new JButton("POLSKI");
    JButton buttonChangeLanguageUS = new JButton("ENGLISH");


    public Database(String url, TravelData travelData) {
        this.url = url;
        this.travelData = travelData;

    }


    public void create() {

        try {
            this.conn = DriverManager.getConnection(this.url, "root", "root");

            System.out.println("Connected to DB");
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from test");

            String dropTable = "DROP TABLE IF EXISTS TravelDB";
            statement.executeUpdate(dropTable);

            String createTable = "CREATE TABLE TravelDB ("
                            + "id INT NOT NULL AUTO_INCREMENT, "
                            + "language VARCHAR(45) NOT NULL,"
                            + "location VARCHAR(45) NOT NULL,"
                            + "country VARCHAR(45) NOT NULL,"
                            + "dateLeaving DATE NOT NULL,"
                            + "dateArriving DATE NOT NULL,"
                            + "place VARCHAR(45) NOT NULL,"
                            + "currency VARCHAR(4) NOT NULL, PRIMARY KEY(id))";

            statement.executeUpdate(createTable);
            fillDatabase();



        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (conn != null && statement != null) {
                try {
                    conn.close();
                    statement.close();
                } catch (SQLException sqlex) {
                    sqlex.printStackTrace();
                }
            }

     }
    }

    private void fillDatabase() {
//Wyciągnięcie wartości z Travel Data i wrzucenie do bazy


       listToDb =  travelData.getOffersDescriptionsList("pl_PL",dateFormat);
        try {
            String updateSQL = "INSERT INTO TravelDB VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement updatestatement = conn.prepareStatement(updateSQL);
            System.out.println(listToDb.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
//
    }

    public void showGui() {
        String[] columns = {"Lokalizacja", "Kraj", "Data Wyjazdu", "Data Powrotu", "Miejsce", "Cena", "Waluta"};
        String[][] data = {{"pl", "Japonia", "2015-09-01", "2015-10-01", "jezioro", "10000,20", "PLN"},
                {"pl_PL", "Włochy", "2015-07-10", "2015-07-30", "morze", "4000,10", "PLN"},
                {"pl", "Japonia", "2015-09-01", "2015-10-01", "jezioro", "10000,20", "PLN"},
                {"pl_PL", "Włochy", "2015-07-10", "2015-07-30", "morze", "4000,10", "PLN"}

        };

        table = new JTable(data, columns) {
            public boolean isCellEditable(int data, int columns) {
                return false;
            }
        };

        frame.setVisible(true);
        frame.setBounds(300, 300, 600, 600);
        frame.setDefaultCloseOperation(3);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));

        JScrollPane jScrollPane = new JScrollPane(table);
        frame.add(buttonChangeLanguagePL);
        frame.add(buttonChangeLanguageUS);
        frame.add(jScrollPane);
        buttonChangeLanguagePL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] columns = {"Lokalizacja", "Kraj", "Data Wyjazdu", "Data Powrotu", "Miejsce", "Cena", "Waluta"};
                //Wrzucić w data dane z PL z bazydanych
                String[][] data = {{"pl", "Japonia", "2015-09-01", "2015-10-01", "jezioro", "10000,20", "PLN"},
                        {"pl_PL", "Włochy", "2015-07-10", "2015-07-30", "morze", "4000,10", "PLN"},
                        {"pl", "Japonia", "2015-09-01", "2015-10-01", "jezioro", "10000,20", "PLN"},
                        {"pl_PL", "Włochy", "2015-07-10", "2015-07-30", "morze", "4000,10", "PLN"}};
                SwingUtilities.updateComponentTreeUI(frame);

            }
        });
        buttonChangeLanguageUS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] columns = {"Location", "Country", "Depature Data", "Return Data", "Place", "Price", "Currency"};
                //Wrzucić w data dane z GB z bazydanych
                String[][] data = {{"US", "Japan", "2015-09-01", "2015-10-01", "lake", "10000,20", "PLN"},
                        {"US", "Italy", "2015-07-10", "2015-07-30", "see", "4000,10", "PLN"},
                        {"US", "Japan", "2015-09-01", "2015-10-01", "lake", "10000,20", "PLN"},
                        {"US", "Italy", "2015-07-10", "2015-07-30", "see", "4000,10", "PLN"}};
                table = new JTable(data, columns) {
                    public boolean isCellEditable(int data, int columns) {
                        return false;
                    }
                };
                SwingUtilities.updateComponentTreeUI(frame);
            }
        });
    }
}

