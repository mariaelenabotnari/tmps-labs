package edu.utm.tmps.Lab2;

import edu.utm.tmps.Lab2.models.PlugSpecification;
import edu.utm.tmps.Lab2.factory.PlugFactory;
import edu.utm.tmps.Lab2.factory.TypeBPlugFactory;
import edu.utm.tmps.Lab2.factory.TypeFPlugFactory;
import edu.utm.tmps.Lab2.factory.TypeGPlugFactory;
import edu.utm.tmps.Lab2.models.Plug;

public class Main {
    public static void main(String[] args) {
        // Abstract factories that produce different plug types
        PlugFactory typeBPlugFactory = new TypeBPlugFactory();
        PlugFactory typeFPlugFactory = new TypeFPlugFactory();
        PlugFactory typeGPlugFactory = new TypeGPlugFactory();

        // Factory Method that internally uses Builder to construct plugs of type B
        Plug typeBPlug = typeBPlugFactory.createPlug();
        typeBPlug.produce();
        typeBPlug.displayDetails();

        // Abstract Factory that creates Specification for Plug Type B
        PlugSpecification typeBPlugSpec = typeBPlugFactory.createSpecification();
        typeBPlugSpec.displaySpecifications();

        // Factory Method that internally uses Builder to construct Plugs of Type F
        Plug typeFPlug = typeFPlugFactory.createPlug();
        typeFPlug.produce();
        typeFPlug.displayDetails();

        // Abstract Factory that creates Specification for Plug Type F
        PlugSpecification typeFPlugSpec = typeFPlugFactory.createSpecification();
        typeFPlugSpec.displaySpecifications();

        // Factory Method that internally uses Builder to construct plugs of type F
        Plug typeGPlug = typeGPlugFactory.createPlug();
        typeGPlug.produce();
        typeGPlug.displayDetails();

        // Abstract Factory that creates Specification for Plug Type F
        PlugSpecification typeGPlugSpec = typeGPlugFactory.createSpecification();
        typeGPlugSpec.displaySpecifications();

    }
}
