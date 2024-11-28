package parking;

public class Vehicle {
    private final String type;
    private final String registrationNumber;
    private final String color;
    private final long entryTimestamp;
    private final String tokenId;

    public Vehicle(String type, String registrationNumber, String color, long entryTimestamp) {
        this.type = type;
        this.registrationNumber = registrationNumber;
        this.color = color;
        this.entryTimestamp = entryTimestamp;
        this.tokenId = generateToken();
    }

    private String generateToken() {
        return type + "-" + registrationNumber + "-" + System.nanoTime();
    }

    public String getType() {
        return type;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public long getEntryTimestamp() {
        return entryTimestamp;
    }

    public String getTokenId() {
        return tokenId;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "type='" + type + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", color='" + color + '\'' +
                ", tokenId='" + tokenId + '\'' +
                '}';
    }
}
