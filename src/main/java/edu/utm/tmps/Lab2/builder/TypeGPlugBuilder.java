package edu.utm.tmps.Lab2.builder;

import edu.utm.tmps.Lab2.models.Plug;
import edu.utm.tmps.Lab2.models.TypeGPlug;

public class TypeGPlugBuilder implements Builder {
    private final Plug typeGPlug;

    public TypeGPlugBuilder() {
        typeGPlug = new TypeGPlug();
    }

    @Override
    public void buildName() {
        typeGPlug.setName("BS 1363");
    }

    @Override
    public void buildPinShape() {
        typeGPlug.setPinShape("3 Large Rectangular Prongs arranged in a triangle");
    }

    @Override
    public void buildStandardVoltage() {
        typeGPlug.setStandardVoltage("High Voltage (230 V)");
    }

    @Override
    public void buildMaxCurrent() {
        typeGPlug.setMaxCurrent("13 A");
    }

    @Override
    public void buildGroundingMechanism() {
        typeGPlug.setGroundingMechanism("Large Rectangular Pin (must be longer to open shutters)");
    }

    @Override
    public void buildSafetyFeature() {
        typeGPlug.setSafetyFeature("Mandatory Fuse inside the plug; socket has safety shutters\n");
    }

    @Override
    public Plug getPlug() {
        return typeGPlug;
    }
}
