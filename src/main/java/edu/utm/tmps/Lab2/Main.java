package edu.utm.tmps.Lab2;

public class Main {
    public static void main(String[] args) {
        // Creating Type B plug
        PlugFactory typeBPlugFactory = new TypeBPlugFactory();
        Plug typeBPlug = new TypeBPlug();
        Builder typeBPlugBuilder = new TypeBPlug();
        PlugSpecification typeBPlugSpecification = typeBPlugFactory.createSpecification();
        typeBPlug.produce();
        typeBPlugBuilder.getPlug();
        typeBPlugSpecification.displaySpecifications();
    }
}
