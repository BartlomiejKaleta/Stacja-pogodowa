package pomiary;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.text.Collator;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by bartekkaleta on 28.07.2017.
 */
public class Controller implements Initializable{

    @FXML  private TableView<Pomiary> table;
    @FXML  private TableColumn<Pomiary, Integer> id;
    @FXML  private TableColumn<Pomiary, String> timeStamp;
    @FXML  private TableColumn<Pomiary, Double> temp1;
    @FXML  private TableColumn<Pomiary, Double> temp2;
    @FXML  private TableColumn<Pomiary, Double> temp3;
    @FXML  private TableColumn<Pomiary, Double> temp4;
    @FXML  private TableColumn<Pomiary, Double> temp5;
    @FXML  private TableColumn<Pomiary, Double> temp6;
    @FXML  private TableColumn<Pomiary, Integer> lux1;
    @FXML  private TableColumn<Pomiary, Integer> water1;
    @FXML  private TableColumn<Pomiary, Double> h1;

    @FXML private Label label;
    @FXML private TextField fromIndex;
    @FXML private TextField toIndex;

    private static int  fI ;
    private static int  tI ;

    private static boolean listaChecked = false;

    private static String lastUpdate;

    private static int missedRows;

    private FilesController filesController ;

    private static ArrayList<Pomiary> listaPomiary = new ArrayList<>();

    private static ObservableList<Pomiary> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources){
        id.setCellValueFactory(new PropertyValueFactory<Pomiary, Integer>("id"));
        timeStamp.setCellValueFactory(new PropertyValueFactory<Pomiary, String>("timeStamp"));
        temp1.setCellValueFactory(new PropertyValueFactory<Pomiary, Double>("temp1"));
        temp2.setCellValueFactory(new PropertyValueFactory<Pomiary, Double>("temp2"));
        temp3.setCellValueFactory(new PropertyValueFactory<Pomiary, Double>("temp3"));
        temp4.setCellValueFactory(new PropertyValueFactory<Pomiary, Double>("temp4"));
        temp5.setCellValueFactory(new PropertyValueFactory<Pomiary, Double>("temp5"));
        temp6.setCellValueFactory(new PropertyValueFactory<Pomiary, Double>("temp6"));
        lux1.setCellValueFactory(new PropertyValueFactory<Pomiary, Integer>("lux1"));
        water1.setCellValueFactory(new PropertyValueFactory<Pomiary, Integer>("water1"));
        h1.setCellValueFactory(new PropertyValueFactory<Pomiary, Double>("h1"));
        table.setItems(list);


        try {
            showDataInTable();
        } catch (IOException ex){
            System.out.println("Blad:" + ex);
            label.setText("Nie mam pliku z danymi - pobierz dane z serwera!");

        }
    }

    @FXML public void pobierzDane() throws IOException {
        if (filesController == null){
            System.out.println("Nie stworzono jeszcze obieklu filecontroller");
            filesController = new FilesController();
        }

        filesController.pobierzDane();
        showDataInTable();
    }

    public void showDataInTable() throws IOException {
        System.out.println("Get data from file");

        if (listaPomiary.isEmpty()) {
            filesController = new FilesController();
            listaPomiary =filesController.readDataFromFile();
        }

        if (!listaChecked) {
            fI = ((listaPomiary.size() - 100) >0) ? (listaPomiary.size() - 100) : 1;
            tI = listaPomiary.size();

            listaChecked = true;
        }

        fromIndex.setText("" + fI);
        toIndex.setText("" + tI);

        if (list.isEmpty()) {
            for (Pomiary l: listaPomiary){
                list.add(l);
            }
            table.getSortOrder().add(id);
        }


        if (missedRows > 0)
        label.setText(lastUpdate + "\nPominieto "+ missedRows +" wierszy ze względu na brak danych!");
        else
            label.setText(lastUpdate);
    }

    public void changeScene() throws IOException {
        if (fromIndex.getText().isEmpty()) {
            label.setText("Pobierz dane i podaj zakres do wykresu.");
        } else {
            fI = Integer.parseInt(fromIndex.getText());
            tI = Integer.parseInt(toIndex.getText());

            Parent root = FXMLLoader.load(getClass().getResource("Graphs.fxml"));
            Scene scene = label.getScene();
            scene.setRoot(root);
        }
    }

    public static int getfI() {
        return fI;
    }

    public static void setfI(int fI) {
        Controller.fI = fI;
    }

    public static int gettI() {
        return tI;
    }

    public static void settI(int tI) {
        Controller.tI = tI;
    }

    public static boolean isListaChecked() {
        return listaChecked;
    }

    public static void setListaChecked(boolean listaChecked) {
        Controller.listaChecked = listaChecked;
    }

    public static String getLastUpdate() {
        return lastUpdate;
    }

    public static void setLastUpdate(String lastUpdate) {
        Controller.lastUpdate = lastUpdate;
    }

    public static int getMissedRows() {
        return missedRows;
    }

    public static void setMissedRows(int missedRows) {
        Controller.missedRows = missedRows;
    }

    public static ArrayList<Pomiary> getListaPomiary() {
        return listaPomiary;
    }

    public static void setListaPomiary(ArrayList<Pomiary> listaPomiary) {
        Controller.listaPomiary = listaPomiary;
    }

    public static ObservableList<Pomiary> getList() {
        return list;
    }

    public static void setList(ObservableList<Pomiary> list) {
        Controller.list = list;
    }
}
