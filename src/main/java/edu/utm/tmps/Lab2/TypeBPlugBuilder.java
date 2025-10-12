package edu.utm.tmps.Lab2;

public class TypeBPlugBuilder implements Builder {
    private Plug typeBPlug;

    public TypeBPlugBuilder() {
        typeBPlug = new TypeBPlug();
    }

    @Override
    public void buildName() {
        typeBPlug.name = "NEMA 5-15";
    }

    @Override
    public void buildPinShape() {
        typeBPlug.pinShape = "2 Flat Parallel Blades and 1 Round Ground Pin";
    }

    @Override
    public void buildStandardVoltage() {
        typeBPlug.standardVoltage = "Low Voltage (120V)";
    }

    @Override
    public void buildMaxCurrent() {
        typeBPlug.maxCurrent = "15 A";
    }

    @Override
    public void buildGroundingMechanism() {
        typeBPlug.groundingMechanism = "Dedicated Round Pin (must be longer to connect first)";
    }

    @Override
    public void buildSafetyFeature() {
        typeBPlug.safetyFeature = "Polarization (Neutral blade is wider than the hot blade)";
    }

    @Override
    public Plug getPlug() {
        return typeBPlug;
    }
}
