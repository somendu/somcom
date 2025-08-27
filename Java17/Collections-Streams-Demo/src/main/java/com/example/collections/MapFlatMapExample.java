package com.example.collections;

import java.util.List;

public class MapFlatMapExample {

    record Person(String name, List<String> phoneNumbers, List<String> addressLine) {}

    public static void demonstrate() {
        System.out.println("\n--- 2. map() vs. flatMap() ---");
        Person alice = new Person("Alice", List.of("555-1234", "555-5678"), List.of("123 Main St", "Apt 4B"));
        Person bob = new Person("Bob", List.of("555-8765", "555-4321"), List.of("456 Elm St"));
        List<Person> people = List.of(alice, bob);

        // Using map() would give us a Stream<List<String>> - a stream of lists.
        List<List<String>> listOfPhoneLists = people.stream()
                .map(Person::phoneNumbers)
                .toList();
        System.out.println("Using map(): " + listOfPhoneLists);

        List<List<String>> listOfAddressLists = people.stream()
                .map(Person::addressLine)
                .toList();
        System.out.println("List of address lists using map(): " + listOfAddressLists);

        // Using flatMap() flattens the Stream<List<String>> into a single Stream<String>.
        List<String> allPhoneNumbers = people.stream()
                .flatMap(person -> person.phoneNumbers().stream())
                .toList();
        System.out.println("Using flatMap(): " + allPhoneNumbers);

        List<String> allAddresses = people.stream()
                .flatMap(person -> person.addressLine().stream())
                .toList();
        System.out.println("All addresses using flatMap(): " + allAddresses);
    }
}
