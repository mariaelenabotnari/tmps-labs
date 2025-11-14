package edu.utm.tmps.Lab2;

import edu.utm.tmps.Lab2.builder.PlugDirector;
import edu.utm.tmps.Lab2.factory.PlugFactory;
import edu.utm.tmps.Lab2.factory.TypeBPlugFactory;
import edu.utm.tmps.Lab2.factory.TypeFPlugFactory;
import edu.utm.tmps.Lab2.factory.TypeGPlugFactory;
import edu.utm.tmps.Lab2.models.Plug;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PlugDirector director =  PlugDirector.getInstance();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("0. Exit.");
            System.out.println("1. Type B Plug for North America.");
            System.out.println("2. Type F Plug for Europe (with no security measures).");
            System.out.println("3. Type G Plug for UK/Ireland (with no security measures).");
            System.out.println("4. Show production statistics.");

            System.out.println("Choose the type of plug to be produced (1 - 3) or choose to display the statistics (4):");
            int option = scanner.nextInt();

            if (option == 0) {
                System.out.println("Exiting...");
                System.exit(0);
            }

            switch (option) {
                case 1 -> {
                    PlugFactory typeBPlugFactory = new TypeBPlugFactory();
                    Plug typeBPlug = typeBPlugFactory.createPlug();
                    typeBPlug.produce();
                    typeBPlug.displayDetails();
                }

                case 2 -> {
                    PlugFactory typeFPlugFactory = new TypeFPlugFactory();
                    Plug typeFPlug = typeFPlugFactory.createPlug();
                    typeFPlug.produce();
                    typeFPlug.displayDetails();
                }

                case 3 -> {
                    PlugFactory typeGPlugFactory = new TypeGPlugFactory();
                    Plug typeGPlug = typeGPlugFactory.createPlug();
                    typeGPlug.produce();
                    typeGPlug.displayDetails();
                }

                case 4 -> {
                    System.out.println("\nProduction statistics:");
                    System.out.println("Type B plugs: " + director.getPlugBCount());
                    System.out.println("Type F plugs: " + director.getPlugFCount());
                    System.out.println("Type G plugs: " + director.getPlugGCount());
                }
            }
        }
    }
}
