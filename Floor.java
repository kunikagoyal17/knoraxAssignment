package parking;

import java.util.*;

public class Floor {
    private final int floorNumber;
    private final Map<String, Queue<Integer>> availableSpaces;
    private final Map<String, VehicleSpace> occupiedSpaces;

    public Floor(int floorNumber, Map<String, Integer> capacityPerVehicleType) {
        this.floorNumber = floorNumber;
        this.availableSpaces = new HashMap<>();
        this.occupiedSpaces = new HashMap<>();

        for (Map.Entry<String, Integer> entry : capacityPerVehicleType.entrySet()) {
            Queue<Integer> spaces = new LinkedList<>();
            for (int i = 1; i <= entry.getValue(); i++) {
                spaces.add(i);
            }
            availableSpaces.put(entry.getKey(), spaces);
        }
    }

    public boolean hasSpace(String vehicleType) {
        return availableSpaces.containsKey(vehicleType) && !availableSpaces.get(vehicleType).isEmpty();
    }

    public String parkVehicle(Vehicle vehicle) {
        if (!hasSpace(vehicle.getType())) {
            throw new RuntimeException("No space available for " + vehicle.getType() + " on floor " + floorNumber);
        }
        int spaceNumber = availableSpaces.get(vehicle.getType()).poll();
        VehicleSpace space = new VehicleSpace(spaceNumber, vehicle);
        occupiedSpaces.put(vehicle.getTokenId(), space);
        return vehicle.getTokenId();
    }

    public Vehicle unparkVehicle(String tokenId) {
        if (!occupiedSpaces.containsKey(tokenId)) {
            throw new RuntimeException("Token ID not found on floor " + floorNumber);
        }
        VehicleSpace space = occupiedSpaces.remove(tokenId);
        availableSpaces.get(space.getVehicle().getType()).add(space.getSpaceNumber());
        return space.getVehicle();
    }

    public boolean containsVehicle(String tokenId) {
        return occupiedSpaces.containsKey(tokenId);
    }

    public void displayStatus() {
        System.out.println("Floor " + floorNumber + ":");
        for (Map.Entry<String, Queue<Integer>> entry : availableSpaces.entrySet()) {
            System.out.println("  Vehicle Type: " + entry.getKey() +
                    ", Available: " + entry.getValue().size() +
                    ", Occupied: " + (occupiedSpaces.size() - entry.getValue().size()));
        }
    }
}
