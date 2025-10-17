package edu.utm.tmps.Lab2.factory;

import edu.utm.tmps.Lab2.builder.Builder;
import edu.utm.tmps.Lab2.builder.PlugDirector;
import edu.utm.tmps.Lab2.models.PlugSpecification;
import edu.utm.tmps.Lab2.models.Plug;
import edu.utm.tmps.Lab2.builder.TypeGPlugBuilder;
import edu.utm.tmps.Lab2.models.TypeGPlugSpecification;

public class TypeGPlugFactory implements PlugFactory{
    @Override
    public Plug createPlug() {
        Builder builder = new TypeGPlugBuilder();
        PlugDirector director = PlugDirector.getInstance();
        director.construct(builder);
        return builder.getPlug();
    }

    @Override
    public PlugSpecification createSpecification() {
        return new TypeGPlugSpecification();
    }
}
