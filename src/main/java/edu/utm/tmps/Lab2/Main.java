package edu.utm.tmps.Lab2;

public class Main {
    public static void main(String[] args) {
        // Abstract Factory
        PlugFactory typeBPlugFactory = new TypeBPlugFactory();

        // Factory Method internally uses Builder to construct plug
        Plug typeBPlug = typeBPlugFactory.createPlug();
        typeBPlug.produce();
        typeBPlug.displayDetails();

        // Abstract Factory creates Specification
        PlugSpecification typeBPlugSpec = typeBPlugFactory.createSpecification();
        typeBPlugSpec.displaySpecifications();
    }
}
