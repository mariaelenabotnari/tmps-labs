package edu.utm.tmps.Lab1;

import edu.utm.tmps.Lab1.interfaces.Bakeable;
import edu.utm.tmps.Lab1.interfaces.Decoratable;
import edu.utm.tmps.Lab1.interfaces.Layerable;

public class BakeryService {


    public void prepareCake(Bakeable cake) {
        System.out.println("Making preparations.");
        cake.bake();

        if (cake instanceof Decoratable) {
            ((Decoratable) cake).decorate();
        }

        if (cake instanceof Layerable) {
            ((Layerable) cake).addLayers();
        }

        System.out.println("Cake is done!\n");
    }
}
