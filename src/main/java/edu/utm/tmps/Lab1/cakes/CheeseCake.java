package edu.utm.tmps.Lab1.cakes;

import edu.utm.tmps.Lab1.enums.CheeseTypesEnum;
import edu.utm.tmps.Lab1.enums.FlavorsEnum;
import edu.utm.tmps.Lab1.interfaces.Bakeable;

public class CheeseCake implements Bakeable {
    private CheeseTypesEnum cheeseType;
    private FlavorsEnum flavor;

    public CheeseCake(CheeseTypesEnum cheeseType, FlavorsEnum flavor) {
        this.cheeseType = cheeseType;
        this.flavor = flavor;
    }

    public CheeseTypesEnum getCheeseType() {
        return cheeseType;
    }

    public void setCheeseType(CheeseTypesEnum cheeseType) {
        this.cheeseType = cheeseType;
    }

    public FlavorsEnum getFlavor() {
        return flavor;
    }

    public void setFlavor(FlavorsEnum flavor) {
        this.flavor = flavor;
    }

    @Override
    public void bake() {
        System.out.println("Baking the base of the cheese cake. Type of cheese used: " +
                this.cheeseType.toString() + ". Flavor chosen: " + this.flavor.toString());
    }
}
