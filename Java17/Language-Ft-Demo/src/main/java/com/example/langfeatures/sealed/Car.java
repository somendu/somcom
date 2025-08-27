package com.example.langfeatures.sealed;

// A final class cannot be extended further.
public final class Car implements Vehicle {
    private final String registrationNumber;
    public Car(String registrationNumber) { this.registrationNumber = registrationNumber; }
    @Override public String getRegistrationNumber() { return registrationNumber; }
}
