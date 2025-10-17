package edu.utm.tmps.Lab2.models;

public class TypeBPlugSpecification implements PlugSpecification {
    @Override
    public void displaySpecifications() {
        System.out.println("Type B Plugs are produced in North America. " +
                "Specs: low voltage, 2 flat parallel blades, uninsulated.\n");
    }
}
