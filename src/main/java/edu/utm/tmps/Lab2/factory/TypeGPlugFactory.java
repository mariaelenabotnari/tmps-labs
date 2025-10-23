package edu.utm.tmps.Lab2.factory;

import edu.utm.tmps.Lab2.builder.Builder;
import edu.utm.tmps.Lab2.builder.PlugDirector;
import edu.utm.tmps.Lab2.models.Plug;
import edu.utm.tmps.Lab2.builder.TypeGPlugBuilder;

public class TypeGPlugFactory implements PlugFactory{
    @Override
    public Plug createPlug() {
        Builder builder = new TypeGPlugBuilder();
        PlugDirector director = PlugDirector.getInstance();
        director.construct(builder);
        return builder.getPlug();
    }
}
