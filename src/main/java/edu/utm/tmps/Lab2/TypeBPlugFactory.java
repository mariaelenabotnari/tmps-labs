package edu.utm.tmps.Lab2;

public class TypeBPlugFactory implements PlugFactory {
    public Plug createPlug() {
        return new TypeBPlug();
    }
    public PlugSpecification createSpecification() {
        return new TypeBPlugSpecification();
    }
}
