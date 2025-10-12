package edu.utm.tmps.Lab2;

public class TypeBPlugFactory implements PlugFactory {
    public Plug createPlug() {
        Builder builder = new TypeBPlugBuilder();
        PlugDirector director = new PlugDirector();
        director.construct(builder);
        return builder.getPlug();
    }
    public PlugSpecification createSpecification() {
        return new TypeBPlugSpecification();
    }
}
