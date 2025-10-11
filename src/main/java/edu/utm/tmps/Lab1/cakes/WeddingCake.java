package edu.utm.tmps.Lab1.cakes;

import edu.utm.tmps.Lab1.enums.DecorationsEnum;
import edu.utm.tmps.Lab1.enums.FlavorsEnum;
import edu.utm.tmps.Lab1.interfaces.Bakeable;
import edu.utm.tmps.Lab1.interfaces.Decoratable;
import edu.utm.tmps.Lab1.interfaces.Layerable;

import java.util.ArrayList;

public class WeddingCake implements Bakeable, Decoratable, Layerable {
    private FlavorsEnum flavor;
    private ArrayList<DecorationsEnum> decorations;
    private Integer nrLayers;

    public WeddingCake(FlavorsEnum flavor, ArrayList<DecorationsEnum> decorations, Integer nrLayers) {
        if (nrLayers < 1 || nrLayers > 5) {
            throw new IllegalArgumentException("Number of layers must be between 1 and 5.");
        }

        this.flavor = flavor;
        this.decorations = decorations;
        this.nrLayers = nrLayers;
    }

    public FlavorsEnum getFlavor() {
        return flavor;
    }

    public void setFlavor(FlavorsEnum flavor) {
        this.flavor = flavor;
    }

    public ArrayList<DecorationsEnum> getDecorations() {
        return decorations;
    }

    public void setDecorations(ArrayList<DecorationsEnum> decorations) {
        this.decorations = decorations;
    }

    public Integer getNrLayers() {
        return nrLayers;
    }

    public void setNrLayers(Integer nrLayers) {
        if (nrLayers < 1 || nrLayers > 5) {
            throw new IllegalArgumentException("Number of layers must be between 1 and 5.");
        }
        this.nrLayers = nrLayers;
    }

    @Override
    public void bake() {
        System.out.println("Baking the wedding cake. Flavor chosen: " + this.flavor.toString());
    }

    @Override
    public void decorate() {
        System.out.println("Adding the decorations: " + this.decorations.toString());
    }

    @Override
    public void addLayers() {
        System.out.println("Stacking and stabilizing each tier carefully. The customer wants " + this.nrLayers + " layers.");
    }
}
