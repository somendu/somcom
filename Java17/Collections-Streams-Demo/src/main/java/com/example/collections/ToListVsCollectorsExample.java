package com.example.collections;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ToListVsCollectorsExample {
    public static void demonstrate() {
        System.out.println("--- 1. Stream.toList() vs. Collectors.toList() ---");

        // Modern .toList() returns an unmodifiable list.
        List<String> unmodifiableList = Stream.of("a", "b", "c").toList();
        System.out.println("Created with .toList(): " + unmodifiableList.getClass());
        try {
            unmodifiableList.add("d");
        } catch (UnsupportedOperationException e) {
            System.out.println("-> Attempt to modify list from .toList() threw UnsupportedOperationException (as expected).");
        }

        // Classic Collectors.toList() returns a mutable list (typically ArrayList).
        List<String> mutableList = Stream.of("a", "b", "c").collect(Collectors.toList());
        System.out.println("Created with Collectors.toList(): " + mutableList.getClass());
        try {
            mutableList.add("d");
            System.out.println("-> Successfully modified list from Collectors.toList(): " + mutableList);
        } catch (UnsupportedOperationException e) {
            System.out.println("Modification failed, which was not expected.");
        }
    }
}
