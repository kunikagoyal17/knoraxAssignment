package parking;

public class VehicleSpace {
    private final int spaceNumber;
    private final Vehicle vehicle;

    public VehicleSpace(int spaceNumber, Vehicle vehicle) {
        this.spaceNumber = spaceNumber;
        this.vehicle = vehicle;
    }

    public int getSpaceNumber() {
        return spaceNumber;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}
