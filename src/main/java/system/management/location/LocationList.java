package system.management.location;

import java.util.ArrayList;
import java.util.List;

public class LocationList {
    private static List<Location> locations = new ArrayList<Location>();

    public static void add(Location location) {
        locations.add(location);
    }

    public static List<Location> getLocations() {
        return locations;
    }

    public static void delLocation(Location location) {
        locations.remove(location);
    }
}
