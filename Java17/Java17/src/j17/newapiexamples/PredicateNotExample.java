package j17.newapiexamples;

import java.util.List;
import java.util.function.Predicate;

public class PredicateNotExample {
    public static void main(String[] args) {
        // Example usage of Predicate.not
        List<String> names = java.util.List.of("Alice", "Bob", "Charlie", "David");

        // Predicate to check if a name starts with 'A'
         Predicate<String> startsWithA = name -> name.startsWith("A");

        // Using Predicate.not to filter names that do not start with 'A'
         List<String> filteredNames = names.stream()
                .filter(java.util.function.Predicate.not(startsWithA))
                .toList();

        List<String> filteredNamesReverse = names.stream()
                .filter(startsWithA)
                .toList();

        System.out.println(filteredNames); // Output: [Bob, Charlie, David]
        System.out.println(filteredNamesReverse); // Output: [Alice]
    }
}
