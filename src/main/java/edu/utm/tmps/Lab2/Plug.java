package edu.utm.tmps.Lab2;

public abstract class Plug {
    String name;
    String pinShape;
    String standardVoltage;
    String maxCurrent;
    String groundingMechanism;
    String safetyFeature;

    public abstract void produce();

    public void displayDetails() {
        System.out.println("Plug details:");
        System.out.println("  Name: " + this.name);
        System.out.println("  Pin Shape: " + this.pinShape);
        System.out.println("  Standard Voltage: " + this.standardVoltage);
        System.out.println("  Max Current: " + this.maxCurrent);
        System.out.println("  Grounding Mechanism: " + this.groundingMechanism);
        System.out.println("  Safety Feature: " + this.safetyFeature);
    }

}
