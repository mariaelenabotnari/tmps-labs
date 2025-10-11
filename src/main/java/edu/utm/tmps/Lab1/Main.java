package edu.utm.tmps.Lab1;

import edu.utm.tmps.Lab1.cakes.CheeseCake;
import edu.utm.tmps.Lab1.cakes.ChocolateCake;
import edu.utm.tmps.Lab1.cakes.WeddingCake;
import edu.utm.tmps.Lab1.enums.CheeseTypesEnum;
import edu.utm.tmps.Lab1.enums.ChocolateTypeEnum;
import edu.utm.tmps.Lab1.enums.DecorationsEnum;
import edu.utm.tmps.Lab1.enums.FlavorsEnum;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        BakeryService bakery = new BakeryService();

        ArrayList<DecorationsEnum> chocolateCakeDecoration = new ArrayList<DecorationsEnum>();
        chocolateCakeDecoration.add(DecorationsEnum.CHOCOLATE_ICING);
        chocolateCakeDecoration.add(DecorationsEnum.FRESH_FRUIT);
        ChocolateCake chocolateCake = new ChocolateCake(ChocolateTypeEnum.CHOCOLATE_MINT, FlavorsEnum.BLUEBERRY_LEMON, chocolateCakeDecoration);

        CheeseCake cheeseCake = new CheeseCake(CheeseTypesEnum.COTTAGE_CHEESE, FlavorsEnum.RED_VELVET);

        ArrayList<DecorationsEnum> weddingCakeDecorations = new ArrayList<>();
        weddingCakeDecorations.add(DecorationsEnum.VANILLA_ICING);
        weddingCakeDecorations.add(DecorationsEnum.FRESH_FRUIT);
        weddingCakeDecorations.add(DecorationsEnum.CHOCOLATE_ICING);
        WeddingCake weddingCake = new WeddingCake(FlavorsEnum.RED_VELVET, weddingCakeDecorations, 4);

        bakery.prepareCake(chocolateCake);
        bakery.prepareCake(cheeseCake);
        bakery.prepareCake(weddingCake);
    }
}