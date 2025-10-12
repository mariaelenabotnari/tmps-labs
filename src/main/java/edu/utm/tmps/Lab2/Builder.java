package edu.utm.tmps.Lab2;

public interface Builder {
    void buildName();
    void buildPinShape();
    void buildStandardVoltage();
    void buildMaxCurrent();
    void buildGroundingMechanism();
    void buildSafetyFeature();

    Plug getPlug();
}
