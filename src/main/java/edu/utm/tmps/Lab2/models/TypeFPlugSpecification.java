package edu.utm.tmps.Lab2.models;

public class TypeFPlugSpecification implements PlugSpecification {
    @Override
    public void displaySpecifications() {
        System.out.println("Type F Plugs are produced in Europe. " +
                "Specs: high voltage, 2 round pins, uninsulated (no sleeve).\n");
    }
}
