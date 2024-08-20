package system.management.location;

public class Location {
    private String locationName;
    private String address;

    public Location(String locationName, String address) {
        this.locationName = locationName;
        this.address = address;
    }

    public String getLocationName() {
        return locationName;
    }
    public String getAddress() {
        return address;
    }
}
