package pomiary;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by bartekkaleta on 29.07.2017.
 */
public class GraphsController implements Initializable{
    @FXML private Button powrotdotabeli;
    @FXML LineChart<String, Number> wykres_temp;
    @FXML LineChart<String, Number> wykres_lux;
    @FXML LineChart<String, Number> wykres_water;
    @FXML LineChart<String, Number> wykres_wilgotnosc;
    @FXML Tab wykresTemperatury;
    @FXML Slider sliderZoom;
    @FXML Slider sliderMove;
    @FXML TabPane tabPane_graf;
    @FXML AnchorPane anchorPane_temp;
    @FXML AnchorPane anchorPane_lux;
    @FXML AnchorPane anchorPane_water;
    @FXML AnchorPane anchorPane_wilgotnosc;
    @FXML BorderPane borderPane;
    @FXML Label moveLabel;
    @FXML Label zoomLabel;

    private int fI, tI;

    private ArrayList<Pomiary> listaPomiary;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            runGraph();
        }catch (Exception ex){
            System.out.println("BLad:" + ex);
        }
    }


    public void goBackToMainScene() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("Temp.fxml"));
        Scene scene = powrotdotabeli.getScene();
        scene.setRoot(root);
    }

    public void runGraph() throws IOException{
        listaPomiary = Controller.getListaPomiary();

        fI = Controller.getfI();
        tI = Controller.gettI();

        XYChart.Series<String, Number> czujnik1 = new XYChart.Series<>();
        czujnik1.setName("Czunik 1");
        for (Pomiary s: listaPomiary){
            if (s.getId() >= fI && s.getId() <= tI)
            czujnik1.getData().add(new XYChart.Data<String, Number>(s.getTimeStamp(), s.getTemp1()));
        }

        XYChart.Series<String, Number> czujnik2 = new XYChart.Series<>();
        czujnik2.setName("Czunik 2");
        for (Pomiary s: listaPomiary){
            if (s.getId() >= fI && s.getId() <= tI)
                czujnik2.getData().add(new XYChart.Data<String, Number>(s.getTimeStamp(), s.getTemp2()));
        }

        XYChart.Series<String, Number> czujnik3 = new XYChart.Series<>();
        czujnik3.setName("Czunik 3");
        for (Pomiary s: listaPomiary){
            if (s.getId() >= fI && s.getId() <= tI)
                czujnik3.getData().add(new XYChart.Data<String, Number>(s.getTimeStamp(), s.getTemp3()));
        }

        XYChart.Series<String, Number> czujnik4 = new XYChart.Series<>();
        czujnik4.setName("Czunik 4");
        for (Pomiary s: listaPomiary){
            if (s.getId() >= fI && s.getId() <= tI)
                czujnik4.getData().add(new XYChart.Data<String, Number>(s.getTimeStamp(), s.getTemp4()));
        }

        XYChart.Series<String, Number> czujnik5 = new XYChart.Series<>();
        czujnik5.setName("Czunik 5");
        for (Pomiary s: listaPomiary){
            if (s.getId() >= fI && s.getId() <= tI)
            czujnik5.getData().add(new XYChart.Data<String, Number>(s.getTimeStamp(), s.getTemp5()));
        }

        XYChart.Series<String, Number> czujnik6 = new XYChart.Series<>();
        czujnik6.setName("Czunik 6");
        for (Pomiary s: listaPomiary){
            if (s.getId() >= fI && s.getId() <= tI)
            czujnik6.getData().add(new XYChart.Data<String, Number>(s.getTimeStamp(), s.getTemp6()));
        }

        // serie swiatlo
        XYChart.Series<String, Number> lux = new XYChart.Series<>();
        lux.setName("Lux");
        for (Pomiary s: listaPomiary){
            if (s.getId() >= fI && s.getId() <= tI)
            lux.getData().add(new XYChart.Data<String, Number>(s.getTimeStamp(), s.getLux1()));
        }

        // serie zalanie
        XYChart.Series<String, Number> water = new XYChart.Series<>();
        water.setName("Zalanie");
        for (Pomiary s: listaPomiary){
            if (s.getId() >= fI && s.getId() <= tI)
            water.getData().add(new XYChart.Data<String, Number>(s.getTimeStamp(), s.getWater1()));
        }

        // serie wilgotnosc
        XYChart.Series<String, Number> wilgotnosc = new XYChart.Series<>();
        wilgotnosc.setName("Wilgotnosc");
        for (Pomiary s: listaPomiary){
            if (s.getId() >= fI && s.getId() <= tI)
            wilgotnosc.getData().add(new XYChart.Data<String, Number>(s.getTimeStamp(), s.getH1()));
        }


        wykres_temp.getData().addAll(czujnik1, czujnik2, czujnik3, czujnik4, czujnik5, czujnik6);
        System.out.println(anchorPane_temp.getWidth());
        wykres_temp.setPrefSize(borderPane.getWidth(), borderPane.getHeight());



        wykres_lux.getData().add(lux);
        wykres_lux.setPrefSize(910,520);
        wykres_lux.autosize();

        wykres_water.getData().add(water);
        wykres_water.setPrefSize(910,520);
        wykres_water.autosize();

        wykres_wilgotnosc.getData().add(wilgotnosc);
        wykres_wilgotnosc.setPrefSize(910,520);
        wykres_wilgotnosc.autosize();

        sliderMove.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                moveSlider(sliderMove.getValue());
            }
        });

        sliderZoom.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                zoomSlider(sliderZoom.getValue());
            }
        });


    }

    @FXML public void zoomSlider(double i) {

        AnchorPane.clearConstraints(wykres_temp);
        AnchorPane.clearConstraints(wykres_lux);
        AnchorPane.clearConstraints(wykres_water);
        AnchorPane.clearConstraints(wykres_wilgotnosc);

        System.out.println("Szerokosc grafu: " + wykres_temp.getWidth());
        double w = borderPane.getWidth() + (i * 30);
        wykres_temp.setPrefSize(w, (borderPane.getHeight()-130));

        wykres_lux.setPrefSize(w, (borderPane.getHeight()-130));
        wykres_water.setPrefSize(w, (borderPane.getHeight()-130));
        wykres_wilgotnosc.setPrefSize(w, (borderPane.getHeight()-130));

        if (wykres_temp.getWidth() > borderPane.getWidth() + 50) {
            moveLabel.setVisible(true);
            sliderMove.setVisible(true);
        } else {
            moveLabel.setVisible(false);
            sliderMove.setVisible(false);
        }

        sliderMove.setMin(borderPane.getWidth() - wykres_temp.getWidth() - 20);
        sliderMove.setMax(20);

    }

    @FXML public void moveSlider(double i) {

        double pozycja = anchorPane_temp.getLayoutX() + (i);
        System.out.println("Pozycja X:" +pozycja);

        System.out.println("Szerokość" + wykres_temp.getWidth());
        wykres_temp.setLayoutX(pozycja);
        wykres_lux.setLayoutX(pozycja);
        wykres_wilgotnosc.setLayoutX(pozycja);
        wykres_water.setLayoutX(pozycja);

        System.out.println(wykres_temp.getLegendSide());

    }

    @FXML public void zoomReset(){
        sliderZoom.setValue(0);
    }

    @FXML public void moveReset(){
        sliderMove.setValue(0);

    }


}
