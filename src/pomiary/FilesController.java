package pomiary;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.io.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by bartekkaleta on 28.07.2017.
 */
public class FilesController {
    private File file;
    private ArrayList<Pomiary> listaPomiary = new ArrayList<>();
    private String lastUpdate;
    private int missedRows;

    FilesController() {
            file = new File("Pomiary.txt");
        }

    public void createNewFile(String current) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

        bufferedWriter.write(current);
        bufferedWriter.close();
    }

    // dopisywanie do istniejacego pliku.
    public void addDataToFile(String current) throws IOException {
    FileWriter fileWriter = new FileWriter(file, true);
    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

    bufferedWriter.write(current);
    bufferedWriter.close();

    }

    public ArrayList<Pomiary> readDataFromFile() throws IOException {
    System.out.println("Odczytuje dane z pliku i zapisuje w tablicy objektow.");
    missedRows = 0;
    int i = 1;
    Scanner scanner = new Scanner(file);
    lastUpdate = scanner.nextLine();
    scanner.nextLine();
    listaPomiary.clear();

    while (scanner.hasNext()){
    String[] s = scanner.nextLine().split(";");
        try {
            listaPomiary.add(
                    new Pomiary(
                            i,
                            s[1],
                            Double.parseDouble(s[2]),
                            Double.parseDouble(s[3]),
                            Double.parseDouble(s[4]),
                            Double.parseDouble(s[5]),
                            Double.parseDouble(s[6]),
                            Double.parseDouble(s[7]),
                            Integer.parseInt(s[8]),
                            Integer.parseInt(s[9]),
                            Double.parseDouble(s[10])
                            ));
            i++;
        } catch (Exception ex) {
            missedRows++;
        }
    }
        Controller.setLastUpdate(lastUpdate);
        Controller.setMissedRows(missedRows);
        ObservableList<Pomiary> list  = Controller.getList();
        list.clear();
        Controller.setList(list);

        return listaPomiary;
    }

    @FXML
    public void pobierzDane() throws IOException {
        DbConnect polaczenie = new DbConnect();

        ResultSet rs = polaczenie.getQuery("SELECT * FROM `pomiary` ORDER BY timeStamp ASC");

        Date date = new Date();

        try {
            createNewFile( date.toString() + "\n\n" );
        } catch (Exception ex) {
            System.out.println("Blad: " + ex);
        }

        try {
            while (rs.next()){
                addDataToFile(
                        rs.getRow()+";"+
                                rs.getString(1)+";"+
                                rs.getString(2)+";"+
                                rs.getString(3)+";"+
                                rs.getString(4)+";"+
                                rs.getString(5)+";"+
                                rs.getString(6)+";"+
                                rs.getString(7)+";"+
                                rs.getString(8)+";"+
                                rs.getString(9)+";"+
                                rs.getString(10)+
                                "\n"
                );
            }

        } catch (Exception ex){
            System.out.println("Wyskoczyl blad: " + ex);
        }
        System.out.println("Pobrano nowe dane z serwera i zapisano do pliku.");
        polaczenie.DbDisconnect();
        ArrayList<Pomiary> listaPomiary = Controller.getListaPomiary();
        listaPomiary.clear();
        Controller.setListaPomiary(listaPomiary);
        Controller.setListaChecked(false);
    }
}
