package edu.utm.tmps.Lab2.models;

public abstract class Plug {
    private String name;
    private String pinShape;
    private String standardVoltage;
    private String maxCurrent;
    private String groundingMechanism;
    private String safetyFeature;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinShape() {
        return pinShape;
    }

    public void setPinShape(String pinShape) {
        this.pinShape = pinShape;
    }

    public String getStandardVoltage() {
        return standardVoltage;
    }

    public void setStandardVoltage(String standardVoltage) {
        this.standardVoltage = standardVoltage;
    }

    public String getMaxCurrent() {
        return maxCurrent;
    }

    public void setMaxCurrent(String maxCurrent) {
        this.maxCurrent = maxCurrent;
    }

    public String getGroundingMechanism() {
        return groundingMechanism;
    }

    public void setGroundingMechanism(String groundingMechanism) {
        this.groundingMechanism = groundingMechanism;
    }

    public String getSafetyFeature() {
        return safetyFeature;
    }

    public void setSafetyFeature(String safetyFeature) {
        this.safetyFeature = safetyFeature;
    }

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
