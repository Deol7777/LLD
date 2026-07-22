package Encapsulation;

import java.util.ArrayList;
import java.util.List;

public class TemperatureSensor {

    private List<Double> readings;

    public TemperatureSensor() {
        readings = new ArrayList<>();
    }

    public void addReading(Double value) {
        if (value < -50 || value > 150) {
            System.out.println("Invalid temperature reading");
            return;
        }
        readings.add(value);
        return;
    }

    public Double getAverage() {
        double avg = 0;
        for (Double entry : readings) {
            avg += entry;
        }
        return Math.round((avg / readings.size()) * 100.0) / 100.0;
    }

    public int getReadingCount() {
        return readings.size();
    }

    public List<Double> getReadings() {
        return new ArrayList<>(readings);
    }
}
