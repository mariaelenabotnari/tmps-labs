package edu.utm.tmps.Lab1.cakes;

import edu.utm.tmps.Lab1.enums.ChocolateTypeEnum;
import edu.utm.tmps.Lab1.enums.DecorationsEnum;
import edu.utm.tmps.Lab1.enums.FlavorsEnum;
import edu.utm.tmps.Lab1.interfaces.Bakeable;
import edu.utm.tmps.Lab1.interfaces.Decoratable;

import java.util.ArrayList;

public class ChocolateCake implements Bakeable, Decoratable {
    private ChocolateTypeEnum chocolateType;
    private FlavorsEnum flavor;
    private ArrayList<DecorationsEnum> decorations;

    public ChocolateCake(ChocolateTypeEnum chocolateType, FlavorsEnum flavor,
                         ArrayList<DecorationsEnum> decorations) {
        this.chocolateType = chocolateType;
        this.flavor = flavor;
        this.decorations = decorations;
    }

    public ChocolateTypeEnum getChocolateType() {
        return chocolateType;
    }

    public void setChocolateType(ChocolateTypeEnum chocolateType) {
        this.chocolateType = chocolateType;
    }

    public FlavorsEnum getFlavors() {
        return flavor;
    }

    public void setFlavors(FlavorsEnum flavor) {
        this.flavor = flavor;
    }

    public ArrayList<DecorationsEnum> getDecorations() {
        return decorations;
    }

    public void setDecorations(ArrayList<DecorationsEnum> decorations) {
        this.decorations = decorations;
    }

    @Override
    public void bake() {
        System.out.println("Baking the chocolate layer for the cake. " +
                "The type of chocolate used: " + this.chocolateType.toString() + ". " +
                "The flavor of the cake being baked: " + this.flavor.toString());
    }

    @Override
    public void decorate() {
        System.out.println("Decorating the cake with: " + this.decorations.toString());
    }
}
