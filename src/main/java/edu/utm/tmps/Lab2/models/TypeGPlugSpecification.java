package edu.utm.tmps.Lab2.models;

public class TypeGPlugSpecification implements PlugSpecification {
    @Override
    public void displaySpecifications() {
        System.out.println("Type G Plugs are produced in UK/Ireland. " +
                "Specs: high voltage, 3 large rectangular prongs, pins are partially insulated (sleeved).");
    }
}
