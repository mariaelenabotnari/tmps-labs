package edu.utm.tmps.Lab2;

public abstract class Plug {
    String name;
    String pinShape;
    String standardVoltage;
    String maxCurrent;
    String groundingMechanism;
    String safetyFeature;

    public abstract void produce();

}
