package system.management.Work;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class WorkShiftOption {
    private final Map<String, ArrayList<Integer>> options;

    public WorkShiftOption() {
        options = new HashMap<>();
    }
    public WorkShiftOption(String key, Integer value) {
        options = new HashMap<>();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(value);
        options.put(key, list);
    }
}
