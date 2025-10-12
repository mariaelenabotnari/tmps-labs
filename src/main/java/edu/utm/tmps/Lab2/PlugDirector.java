package edu.utm.tmps.Lab2;

public class PlugDirector {
    public void construct(Builder builder) {
        builder.buildName();
        builder.buildPinShape();
        builder.buildStandardVoltage();
        builder.buildMaxCurrent();
        builder.buildGroundingMechanism();
        builder.buildSafetyFeature();
    }
}
