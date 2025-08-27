package com.example.langfeatures.sealed;

/**
 * A sealed interface permits only specific classes to implement it.
 * This enables exhaustive checks in switch statements.
 */
public sealed interface Vehicle permits Car, MotorVehicle {
    String getRegistrationNumber();
}
