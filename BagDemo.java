import java.util.HashMap;
import java.util.Map;

class Bag<T> {
    // Internal frequency map: element -> how many times it appears
    private final Map<T, Integer> freqMap = new HashMap<>();
    // Tracks the total number of elements including duplicates
    private int totalSize = 0;

    /**
     * Adds one occurrence of the given item to the bag.
     * @param item element to add (must not be null)
     * @throws IllegalArgumentException if item is null
     */
    public void add(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Null items are not allowed in Bag.");
        }
        int current = freqMap.getOrDefault(item, 0);
        freqMap.put(item, current + 1);
        totalSize++;
    }

    /**
     * Removes one occurrence of the given item from the bag, if present.
     * If the item does not exist, this method does nothing.
     * @param item element to remove
     */
    public void remove(T item) {
        Integer current = freqMap.get(item);
        if (current == null) {
            return; // item not present; nothing to remove
        }
        if (current > 1) {
            freqMap.put(item, current - 1);
        } else {
            freqMap.remove(item);
        }
        totalSize--;
    }

    /**
     * Returns true if the bag contains at least one occurrence of the item.
     * @param item element to check
     * @return true if present; false otherwise
     */
    public boolean contains(T item) {
        return freqMap.containsKey(item);
    }

    /**
     * Returns how many times the item occurs in the bag.
     * @param item element to count
     * @return non-negative occurrence count
     */
    public int count(T item) {
        return freqMap.getOrDefault(item, 0);
    }

    /**
     * @return the total number of elements stored (including duplicates)
     */
    public int size() {
        return totalSize;
    }

    /**
     * @return true if the bag is empty; false otherwise
     */
    public boolean isEmpty() {
        return totalSize == 0;
    }

    /**
     * Returns a human-friendly view like:
     * { apple x 3, banana x 2, orange x 1 }
     */
    @Override
    public String toString() {
        if (freqMap.isEmpty()) return "{ }";
        StringBuilder sb = new StringBuilder();
        sb.append("{ ");
        boolean first = true;
        for (Map.Entry<T, Integer> e : freqMap.entrySet()) {
            if (!first) sb.append(", ");
            sb.append(e.getKey()).append(" x ").append(e.getValue());
            first = false;
        }
        sb.append(" }");
        return sb.toString();
    }
}

public class BagDemo {
    public static void main(String[] args) {
        // 1) Create an instance of Bag<String>
        Bag<String> bag = new Bag<>();

        // 2) Add several elements, including duplicates
        bag.add("apple");
        bag.add("banana");
        bag.add("apple");
        bag.add("orange");
        bag.add("banana");
        bag.add("apple");

        // 3) Print the bag contents
        System.out.println("Initial bag: " + bag);
        System.out.println("Total size (with duplicates): " + bag.size());
        System.out.println();

        // 4) Test contains for a few elements
        System.out.println("Contains 'apple'? " + bag.contains("apple"));
        System.out.println("Contains 'grape'? " + bag.contains("grape"));
        System.out.println();

        // 5) Test count for a few elements
        System.out.println("Count('apple') = " + bag.count("apple"));
        System.out.println("Count('banana') = " + bag.count("banana"));
        System.out.println("Count('grape')  = " + bag.count("grape"));
        System.out.println();

        // 6) Remove one occurrence of an element
        bag.remove("banana");

        // 7) Print the bag contents again
        System.out.println("After removing one 'banana': " + bag);
        System.out.println("Total size (with duplicates): " + bag.size());
        System.out.println();

        // 8) Test contains for the removed element
        System.out.println("Contains 'banana'? " + bag.contains("banana"));

        // 9) Test count for the removed element
        System.out.println("Count('banana') = " + bag.count("banana"));
    }
}
