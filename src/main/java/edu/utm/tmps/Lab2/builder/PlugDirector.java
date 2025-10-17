package edu.utm.tmps.Lab2.builder;

public class PlugDirector {
    private static final PlugDirector instance = new PlugDirector();

    private PlugDirector() {}

    public static PlugDirector getInstance() {
        return instance;
    }

    public void construct(Builder builder) {
        builder.buildName();
        builder.buildPinShape();
        builder.buildStandardVoltage();
        builder.buildMaxCurrent();
        builder.buildGroundingMechanism();
        builder.buildSafetyFeature();
    }
}
