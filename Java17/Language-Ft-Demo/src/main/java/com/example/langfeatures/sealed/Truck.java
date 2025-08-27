package com.example.langfeatures.sealed;

// Truck can extend MotorVehicle because MotorVehicle is non-sealed.
public final class Truck extends MotorVehicle {
    private final int payloadCapacity;
    public Truck(String reg, int capacity) {
        super(reg);
        this.payloadCapacity = capacity;
    }
    public int getPayloadCapacity() { return payloadCapacity; }
}
