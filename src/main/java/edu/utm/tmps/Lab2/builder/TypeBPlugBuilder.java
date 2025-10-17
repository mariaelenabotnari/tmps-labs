package edu.utm.tmps.Lab2.builder;

import edu.utm.tmps.Lab2.models.Plug;
import edu.utm.tmps.Lab2.models.TypeBPlug;

public class TypeBPlugBuilder implements Builder {
    private final Plug typeBPlug;

    public TypeBPlugBuilder() {
        typeBPlug = new TypeBPlug();
    }

    @Override
    public void buildName() {
        typeBPlug.setName("NEMA 5-15");
    }

    @Override
    public void buildPinShape() {
        typeBPlug.setPinShape("2 Flat Parallel Blades and 1 Round Ground Pin");
    }

    @Override
    public void buildStandardVoltage() {
        typeBPlug.setStandardVoltage("Low Voltage (120V)");
    }

    @Override
    public void buildMaxCurrent() {
        typeBPlug.setMaxCurrent("15 A");
    }

    @Override
    public void buildGroundingMechanism() {
        typeBPlug.setGroundingMechanism("Dedicated Round Pin (must be longer to connect first)");
    }

    @Override
    public void buildSafetyFeature() {
        typeBPlug.setSafetyFeature("Polarization (Neutral blade is wider than the hot blade)");
    }

    @Override
    public Plug getPlug() {
        return typeBPlug;
    }
}
