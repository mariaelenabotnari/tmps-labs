package edu.utm.tmps.Lab2.builder;

import edu.utm.tmps.Lab2.models.Plug;
import edu.utm.tmps.Lab2.models.TypeFPlug;

public class TypeFPlugBuilder implements Builder {
    private final Plug typeFPlug;

    public TypeFPlugBuilder() {
        typeFPlug = new TypeFPlug();
    }

    @Override
    public void buildName() {
        typeFPlug.setName("CEE 7/4 (Schuko)");
    }

    @Override
    public void buildPinShape() {
        typeFPlug.setPinShape("2 Round Pins (4.8 mm diameter)");
    }

    @Override
    public void buildStandardVoltage() {
        typeFPlug.setStandardVoltage("High Voltage (230 V)");
    }

    @Override
    public void buildMaxCurrent() {
        typeFPlug.setMaxCurrent("16 A");
    }

    @Override
    public void buildGroundingMechanism() {
        typeFPlug.setGroundingMechanism("Two side clips on the plug contact the socket clips");
    }

    @Override
    public void buildSafetyFeature() {
        typeFPlug.setSafetyFeature("None (Plugs can be inserted in two orientations)\n");
    }

    @Override
    public Plug getPlug() {
        return typeFPlug;
    }
}
