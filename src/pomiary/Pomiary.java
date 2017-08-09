package pomiary;

/**
 * Created by bartekkaleta on 28.07.2017.
 */
public class Pomiary {
    private int id;
    private String timeStamp;
    private double temp1;
    private double temp2;
    private double temp3;
    private double temp4;
    private double temp5;
    private double temp6;
    private int lux1;
    private int water1;
    private double h1;

    public Pomiary(int id, String timeStamp, double temp1, double temp2, double temp3, double temp4, double temp5, double temp6, int lux1, int
            water1, double h1) {
        this.id = id;
        this.timeStamp = timeStamp;
        this.temp1 = temp1;
        this.temp2 = temp2;
        this.temp3 = temp3;
        this.temp4 = temp4;
        this.temp5 = temp5;
        this.temp6 = temp6;
        this.lux1 = lux1;
        this.water1 = water1;
        this.h1 = h1;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public double getTemp1() {
        return temp1;
    }

    public void setTemp1(double temp1) {
        this.temp1 = temp1;
    }

    public double getTemp2() {
        return temp2;
    }

    public void setTemp2(double temp2) {
        this.temp2 = temp2;
    }

    public double getTemp3() {
        return temp3;
    }

    public void setTemp3(double temp3) {
        this.temp3 = temp3;
    }

    public double getTemp4() {
        return temp4;
    }

    public void setTemp4(double temp4) {
        this.temp4 = temp4;
    }

    public double getTemp5() {
        return temp5;
    }

    public void setTemp5(double temp5) {
        this.temp5 = temp5;
    }

    public double getTemp6() {
        return temp6;
    }

    public void setTemp6(double temp6) {
        this.temp6 = temp6;
    }

    public int getLux1() {
        return lux1;
    }

    public void setLux1(int lux1) {
        this.lux1 = lux1;
    }

    public int getWater1() {
        return water1;
    }

    public void setWater1(int water1) {
        this.water1 = water1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getH1() {
        return h1;
    }

    public void setH1(double h1) {
        this.h1 = h1;
    }

}
