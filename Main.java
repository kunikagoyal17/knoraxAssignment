package parking;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map<String, Integer> capacity = new HashMap<>();
        capacity.put("Car", 2);
        capacity.put("Bike", 5);

        ParkingLot parkingLot = new ParkingLot(2, capacity);

        CostStrategy costStrategy = new CostStrategy("₹");
        costStrategy.setCost("Car", 20);
        costStrategy.setCost("Bike", 10);

        parkingLot.configureCostStrategy(costStrategy);

        Vehicle car1 = new Vehicle("Car", "DL123", "Red", System.currentTimeMillis());
        Vehicle car2 = new Vehicle("Car", "DL456", "Blue", System.currentTimeMillis());

        String token1 = parkingLot.addVehicle(car1);
        String token2 = parkingLot.addVehicle(car2);

        parkingLot.displayStatus();

        try {
            Vehicle car3 = new Vehicle("Car", "DL789", "Black", System.currentTimeMillis());
            parkingLot.addVehicle(car3);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        long exitTime = System.currentTimeMillis() + 3600000; // 1 hour later
        System.out.println(parkingLot.removeVehicle(token1, exitTime));
    }
}
