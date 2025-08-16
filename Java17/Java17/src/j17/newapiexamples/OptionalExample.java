package j17.newapiexamples;

import java.util.Optional;

public class OptionalExample {

    public static void main(String[] args) {

        Optional<String> primary = Optional.empty();
        Optional<String> secondary = Optional.of("Secondary Value");

        String value = primary.or(() -> secondary).orElse("Default Value");
        System.out.println("Value: " + value);
    }
}
