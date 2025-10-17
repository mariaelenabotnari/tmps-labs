package edu.utm.tmps.Lab2.factory;

import edu.utm.tmps.Lab2.models.Plug;
import edu.utm.tmps.Lab2.builder.TypeBPlugBuilder;
import edu.utm.tmps.Lab2.builder.Builder;
import edu.utm.tmps.Lab2.builder.PlugDirector;
import edu.utm.tmps.Lab2.models.PlugSpecification;
import edu.utm.tmps.Lab2.models.TypeBPlugSpecification;

public class TypeBPlugFactory implements PlugFactory {
    @Override
    public Plug createPlug() {
        Builder builder = new TypeBPlugBuilder();
        PlugDirector director = PlugDirector.getInstance();
        director.construct(builder);
        return builder.getPlug();
    }

    @Override
    public PlugSpecification createSpecification() {
        return new TypeBPlugSpecification();
    }
}
