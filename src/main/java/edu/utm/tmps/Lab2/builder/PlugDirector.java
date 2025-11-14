package edu.utm.tmps.Lab2.builder;

public class PlugDirector {
    private static final PlugDirector instance = new PlugDirector();
    private Integer plugBCount = 0;
    private Integer plugFCount = 0;
    private Integer plugGCount = 0;

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

        switch (builder) {
            case TypeBPlugBuilder typeBPlugBuilder -> plugBCount++;
            case TypeFPlugBuilder typeFPlugBuilder -> plugFCount++;
            case TypeGPlugBuilder typeGPlugBuilder -> plugGCount++;
            default -> {
            }
        }
    }

    public Integer getPlugBCount() {
        return plugBCount;
    }

    public void setPlugBCount(Integer plugBCount) {
        this.plugBCount = plugBCount;
    }

    public Integer getPlugFCount() {
        return plugFCount;
    }

    public void setPlugFCount(Integer plugFCount) {
        this.plugFCount = plugFCount;
    }

    public Integer getPlugGCount() {
        return plugGCount;
    }

    public void setPlugGCount(Integer plugGCount) {
        this.plugGCount = plugGCount;
    }
}
