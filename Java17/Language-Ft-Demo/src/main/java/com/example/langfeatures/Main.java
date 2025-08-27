package com.example.langfeatures;

import com.example.langfeatures.records.Book;
import com.example.langfeatures.sealed.*;
import java.time.Month;

public class Main {

    /**
     * Uses a pattern matching switch to describe a Vehicle.
     * This demonstrates type patterns and guarded patterns (`when`).
     */
    public static String getVehicleDescription(Vehicle v) {
        return switch (v) {
            // Type pattern: `Car c`
            case Car c -> "A car with reg: " + c.getRegistrationNumber();
            // Guarded pattern: `when` clause
            case Truck t when t.getPayloadCapacity() > 5000 -> "A large truck: " + t.getRegistrationNumber();
            case Truck t -> "A standard truck: " + t.getRegistrationNumber();
            // Default is not needed if the sealed interface permits are fully covered.
            // If MotorVehicle were sealed, and we covered all its permits, default would be a compile error.
            // But since MotorVehicle is non-sealed, we need a default.
            default -> "Some other motor vehicle.";
        };
    }

    /**
     * Uses a switch expression with multi-label cases.
     */
    public static String getQuarter(Month month) {
        return switch (month) {
            case JANUARY, FEBRUARY, MARCH -> "Q1";
            case APRIL, MAY, JUNE -> "Q2";
            case JULY, AUGUST, SEPTEMBER -> "Q3";
            case OCTOBER, NOVEMBER, DECEMBER -> "Q4";
        };
    }

    public static void main(String[] args) {
        // --- Record Demonstration ---
        System.out.println("--- Records ---");
        Book classic = new Book("Pride and Prejudice", "Jane Austen", 1813);
        Book modern = Book.createModern("The Midnight Library", "Matt Haig");
        System.out.println(classic.getCitation());
        System.out.println("Title of classic: " + classic.title()); // Accessor method
        System.out.println("Modern book: " + modern); // toString() is auto-generated

        // --- Sealed Types and Pattern Matching ---
        System.out.println("\n--- Sealed Types & Pattern Matching ---");
        Vehicle car = new Car("CAR-123");
        Vehicle smallTruck = new Truck("TRUCK-456", 4000);
        Vehicle largeTruck = new Truck("TRUCK-789", 8000);
        System.out.println(getVehicleDescription(car));
        System.out.println(getVehicleDescription(smallTruck));
        System.out.println(getVehicleDescription(largeTruck));

        // --- Switch Expression ---
        System.out.println("\n--- Switch Expressions ---");
        System.out.println("June is in: " + getQuarter(Month.JUNE));
        System.out.println("December is in: " + getQuarter(Month.DECEMBER));
    }
}
