package Hash;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HashMap1 {
    public static void main(String args[]) {
        // Creation
        HashMap<String, Integer> map = new HashMap<>();

        // Insertion
        map.put("India", 120);
        map.put("US", 30);
        map.put("China", 150);

        System.out.println("Initial map: " + map);

        // Updating value for "China"
        map.put("China", 180);
        System.out.println("After updating China's value: " + map);

        // Searching
        if (map.containsKey("Indonesia")) {
            System.out.println("Key is present in the map");
        } else {
            System.out.println("Key is not present in the map");
        }

        System.out.println("Value for 'China': " + map.get("China")); // key exists
        System.out.println("Value for 'Indonesia': " + map.get("Indonesia")); // key doesn't exist, returns null

        // Iteration (1)
        System.out.println("\nIteration using entrySet:");
        for (Map.Entry<String, Integer> e : map.entrySet()) {
            System.out.println(e.getKey() + " = " + e.getValue());
        }

        // Iteration (2)
        System.out.println("\nIteration using keySet:");
        Set<String> keys = map.keySet();
        for (String key : keys) {
            System.out.println(key + " = " + map.get(key));
        }

        // Removing
        map.remove("China");
        System.out.println("\nAfter removing 'China': " + map);
    }
}
