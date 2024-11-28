package parking;

import java.util.HashMap;
import java.util.Map;

public class CostStrategy {
    private final Map<String, Double> costPerHour;
    private final String currencySymbol;

    public CostStrategy(String currencySymbol) {
        this.currencySymbol = currencySymbol;
        this.costPerHour = new HashMap<>();
    }

    public void setCost(String vehicleType, double cost) {
        costPerHour.put(vehicleType, cost);
    }

    public double calculateCost(String vehicleType, long entryTimestamp, long exitTimestamp) {
        long durationInHours = (exitTimestamp - entryTimestamp) / (1000 * 60 * 60);
        return costPerHour.getOrDefault(vehicleType, 0.0) * durationInHours;
    }

    public String getFormattedCost(double cost) {
        return currencySymbol + String.format("%.2f", cost);
    }
}
