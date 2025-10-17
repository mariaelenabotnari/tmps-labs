package edu.utm.tmps.Lab2.factory;

import edu.utm.tmps.Lab2.models.Plug;
import edu.utm.tmps.Lab2.models.PlugSpecification;

public interface PlugFactory {
    Plug createPlug();
    PlugSpecification createSpecification();
}
