package Hash;
import java.util.*;

public class HashMapCode {
    // Custom implementation of HashMap using Generics
    static class HashMap<K, V> {
        private class Node {
            K key;
            V value;

            public Node(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }

        private int n; // Current number of elements
        private int N; // Number of buckets
        private LinkedList<Node>[] buckets; // Array of linked lists for chaining

        // Constructor
        @SuppressWarnings("unchecked")
        public HashMap() {
            this.N = 4; // Initial bucket size
            this.buckets = new LinkedList[4]; // Create an array of LinkedLists
            for (int i = 0; i < 4; i++) {
                this.buckets[i] = new LinkedList<>();
            }
        }

        // Hash function to convert key to bucket index
        private int hashFunction(K key) {
            return Math.abs(key.hashCode()) % N; // Get the absolute index to avoid negative values
        }

        // Search for the key in the linked list at bucket index bi
        private int searchInLL(K key, int bi) {
            LinkedList<Node> ll = buckets[bi];

            for (int i = 0; i < ll.size(); i++) {
                if (ll.get(i).key.equals(key)) { // Use equals() for object comparison
                    return i;
                }
            }
            return -1; // Key not found
        }

        // Rehashing function to increase bucket size when load factor exceeds threshold
        @SuppressWarnings("unchecked")
        private void rehash() {
            LinkedList<Node>[] oldBuckets = buckets;
            buckets = new LinkedList[N * 2]; // Double the bucket size
            N = N * 2; // Update N

            for (int i = 0; i < N; i++) {
                buckets[i] = new LinkedList<>();
            }

            // Reinsert all elements from old buckets into new buckets
            for (int i = 0; i < oldBuckets.length; i++) {
                LinkedList<Node> ll = oldBuckets[i];
                for (int j = 0; j < ll.size(); j++) {
                    Node node = ll.get(j);
                    put(node.key, node.value);
                }
            }
        }

        // Insert or update a key-value pair in the HashMap
        public void put(K key, V value) {
            int bi = hashFunction(key); // Get bucket index
            int di = searchInLL(key, bi); // Find index in the linked list

            if (di == -1) {
                buckets[bi].add(new Node(key, value)); // Insert new node if key doesn't exist
                n++;
            } else {
                Node node = buckets[bi].get(di); // Update the existing key's value
                node.value = value;
            }

            double lambda = (double) n / N;
            if (lambda > 2.0) {
                rehash(); // Perform rehashing if load factor exceeds 2.0
            }
        }

        // Check if a key exists in the HashMap
        public boolean containsKey(K key) {
            int bi = hashFunction(key);
            int di = searchInLL(key, bi);

            return di != -1;
        }

        // Remove a key-value pair from the HashMap
        public V remove(K key) {
            int bi = hashFunction(key);
            int di = searchInLL(key, bi);

            if (di == -1) {
                return null; // Key not found
            } else {
                Node node = buckets[bi].remove(di); // Remove the node
                n--;
                return node.value;
            }
        }

        // Get the value associated with the key
        public V get(K key) {
            int bi = hashFunction(key);
            int di = searchInLL(key, bi);

            if (di == -1) {
                return null; // Key not found
            } else {
                Node node = buckets[bi].get(di);
                return node.value;
            }
        }

        // Get a list of all keys in the HashMap
        public ArrayList<K> keyset() {
            ArrayList<K> keys = new ArrayList<>();

            for (int i = 0; i < buckets.length; i++) {
                LinkedList<Node> ll = buckets[i];
                for (int j = 0; j < ll.size(); j++) {
                    Node node = ll.get(j);
                    keys.add(node.key);
                }
            }
            return keys;
        }

        // Check if the HashMap is empty
        public boolean isEmpty() {
            return n == 0;
        }
    }

    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        
        // Insertion of elements
        map.put("India", 198);
        map.put("China", 200);
        map.put("US", 50);

        // Displaying all key-value pairs
        ArrayList<String> keys = map.keyset();
        for (int i = 0; i < keys.size(); i++) {
            System.out.println(keys.get(i) + " " + map.get(keys.get(i)));
        }

        // Removing an element
        map.remove("India");
        System.out.println("After removing 'India': " + map.get("India"));
    }
}
