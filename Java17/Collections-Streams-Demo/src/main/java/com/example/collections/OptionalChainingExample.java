package com.example.collections;

import java.util.Optional;

public class OptionalChainingExample {

    // A method that might or might not find a user.
    public static Optional<String> findUserById(int id) {
        return id == 1 ? Optional.of("Alice") : Optional.empty();
    }

    public static void demonstrate() {
        System.out.println("\n--- 4. Optional Chaining ---");

        // --- Case 1: User found ---
        System.out.println("Searching for user with ID 1:");
        findUserById(1)
                .map(String::toUpperCase) // Transform the value if present.
                .ifPresentOrElse(
                        name -> System.out.println("Found user: " + name),
                        () -> System.out.println("User not found.")
                );

        // --- Case 2: User not found ---
        System.out.println("\nSearching for user with ID 2:");
        String userName = findUserById(2)
                .orElse("DefaultUser"); // Provide a default value.
        System.out.println("Resulting user: " + userName);
    }
}
