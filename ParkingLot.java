package parking;

import java.util.*;

public class ParkingLot {
    private final List<Floor> floors;
    private CostStrategy costStrategy;

    public ParkingLot(int numberOfFloors, Map<String, Integer> capacityPerVehicleType) {
        floors = new ArrayList<>();
        for (int i = 1; i <= numberOfFloors; i++) {
            floors.add(new Floor(i, capacityPerVehicleType));
        }
    }

    public void configureCostStrategy(CostStrategy costStrategy) {
        this.costStrategy = costStrategy;
    }

    public String addVehicle(Vehicle vehicle) {
        for (Floor floor : floors) {
            if (floor.hasSpace(vehicle.getType())) {
                return floor.parkVehicle(vehicle);
            }
        }
        throw new RuntimeException("Parking lot is full for " + vehicle.getType());
    }

    public String removeVehicle(String tokenId, long exitTimestamp) {
        for (Floor floor : floors) {
            if (floor.containsVehicle(tokenId)) {
                Vehicle vehicle = floor.unparkVehicle(tokenId);
                double cost = costStrategy.calculateCost(vehicle.getType(), vehicle.getEntryTimestamp(), exitTimestamp);
                return "Vehicle removed: " + vehicle + ", Total Cost: " + costStrategy.getFormattedCost(cost);
            }
        }
        throw new RuntimeException("Invalid token ID");
    }

    public boolean checkAvailability(int floorNumber, String vehicleType) {
        return floors.get(floorNumber - 1).hasSpace(vehicleType);
    }

    public void displayStatus() {
        for (Floor floor : floors) {
            floor.displayStatus();
        }
    }
}
