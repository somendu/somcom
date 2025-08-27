package com.example.langfeatures.sealed;

// A non-sealed class allows any other class to extend it,
// breaking the "sealed" chain.
public non-sealed class MotorVehicle implements Vehicle {
    private final String registrationNumber;
    public MotorVehicle(String registrationNumber) { this.registrationNumber = registrationNumber; }
    @Override public String getRegistrationNumber() { return registrationNumber; }
}
