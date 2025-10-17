package edu.utm.tmps.Lab2.builder;

import edu.utm.tmps.Lab2.models.Plug;

public interface Builder {
    void buildName();
    void buildPinShape();
    void buildStandardVoltage();
    void buildMaxCurrent();
    void buildGroundingMechanism();
    void buildSafetyFeature();

    Plug getPlug();
}
