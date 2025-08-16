package j17.newapiexamples;

import java.util.List;
import java.util.stream.Stream;

public class StreamToListExample {
    /**
     * Demonstrates Stream.toList() returning an immutable List
     * replacing older Collectors.toList().
     */
    public static void main(String[] args) {
        List<String> list = Stream.of("A", "B", "C")
                .toList();

        System.out.println(list); // Output: [A, B, C]

        // list.add("D"); // Throws UnsupportedOperationException
    }
}
