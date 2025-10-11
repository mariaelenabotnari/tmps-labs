# Laboratory Work Nr. 1: SOLID Principles

---

# Theory

The SOLID principles are a group of five important guidelines used in object-oriented programming to make software easier to maintain, understand, and extend. They include the Single Responsibility Principle, Open/Closed Principle, Liskov Substitution Principle, Interface Segregation Principle, and Dependency Inversion Principle. These principles help developers design systems where each class and component has a clear purpose and can be easily changed or reused without affecting the rest of the program. In this laboratory work, only three of them were applied (SRP, ISP, and DIP) to ensure that the code remains modular, flexible, and easy to modify. Following the SOLID principles improves code quality, reduces errors, and supports clean software architecture.

---

# Objectives 

The main goal of this laboratory work was to implement three of the SOLID principles in a simple object-oriented project.

---

# Implementation

I. **Single Responsibility Principle (SRP)**

The Single Responsibility Principle says that each class should have only one responsibility and one reason to change. This means a class should focus on doing one specific task instead of trying to handle many different things. This principle is important because it makes the code easier to understand, test, and modify. When each class is responsible for only one thing, it becomes simpler to make changes in the future without breaking other parts of the program. It also makes your project more organized, since each class has a clear purpose.

In my bakery project, this principle was applied by making every cake class responsible only for describing how that cake is prepared. For example, the ChocolateCake class is only responsible for describing how to bake and decorate a chocolate cake, while the CheeseCake class only explains how to bake a cheesecake. Each cake type has its own small, focused class that doesn’t do anything unrelated to its recipe. For instance:

````java
public class ChocolateCake implements Bakeable, Decoratable {
    private ChocolateTypeEnum chocolateType;
    private FlavorsEnum flavor;
    private ArrayList<DecorationsEnum> decorations;
    
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
````
This way, if I ever need to change how a cake is prepared, I only modify that one class and nothing else in the program is affected.


II. **Interface Segregation Principle (ISP)**

The Interface Segregation Principle means that a class should not be forced to implement methods that it does not need. Instead of having one big interface with many methods, it is better to have several small and specific interfaces. This principle is important because it makes the code cleaner and more flexible. When classes only depend on the methods they actually use, there are fewer chances for errors and less unnecessary code. It also makes it easier to add new classes later, since each one can choose exactly which interfaces it wants to implement.

In my code, I used several small interfaces to show this principle. For example, I created Bakeable, Decoratable, and Layerable as separate interfaces. Each one represents a different action that can be done to prepare the cake. Not all cakes need to be decorated and not all of them have multiple layers, so they only implement what they need. For example, the CheeseCake class implements only Bakeable, while the WeddingCake class implements all three because it needs to be baked, decorated, and it has multiple layers.

````java
public interface Bakeable { void bake(); }
public interface Decoratable { void decorate(); }
public interface Layerable { void addLayers(); }
````

By using several smaller interfaces, each cake type is more flexible and doesn’t depend on methods that are useless for it, which clearly follows the Interface Segregation Principle.

III. **Dependency Inversion Principle (DIP)**

The Dependency Inversion Principle says that high-level modules should not depend on low-level modules, but both should depend on abstractions. This helps make the code more flexible and less dependent on specific implementations. It means that your main classes should not directly create or use concrete objects, but instead should use interfaces or abstract classes. This principle is important because it allows you to easily change or add new types of objects in the future without modifying the main logic of the program.

In my project, the BakeryService class follows this principle by depending on the Bakeable interface instead of on specific cake classes like ChocolateCake or Cheesecake. This means that the bakery can prepare any cake, as long as it implements the Bakeable interface. Inside the BakeryService, I also used checks like ````instanceof Decoratable```` or ````instanceof Layerable```` to see if a cake can be decorated or layered before calling those methods. For example:

````java
public void prepareCake(Bakeable cake) {
    cake.bake();
    if (cake instanceof Decoratable) ((Decoratable) cake).decorate();
    if (cake instanceof Layerable) ((Layerable) cake).addLayers();
}

````

This design allows new cake types to be added in the future without changing the BakeryService code, which perfectly shows how the Dependency Inversion Principle helps keep the system flexible and easy to extend.

---

# Results 
````
Making preparations.
Baking the chocolate layer for the cake. The type of chocolate used: CHOCOLATE_MINT. The flavor of the cake being baked: BLUEBERRY_LEMON
Decorating the cake with: [CHOCOLATE_ICING, FRESH_FRUIT]
Cake is done!

Making preparations.
Baking the base of the cheese cake. Type of cheese used: COTTAGE_CHEESE. Flavor chosen: RED_VELVET
Cake is done!

Making preparations.
Baking the wedding cake. Flavor chosen: RED_VELVET
Adding the decorations: [VANILLA_ICING, FRESH_FRUIT, CHOCOLATE_ICING]
Stacking and stabilizing each tier carefully. The customer wants 4 layers.
Cake is done!
````

---

# Conclusion
The SOLID principles are very important in object-oriented programming because they help create clean, organized, and flexible code. When these principles are followed, each part of the program has a clear purpose and can be changed or improved without breaking other parts. This makes the software easier to maintain, test, and extend in the future. The SOLID principles also help developers avoid writing complicated code by encouraging smaller, more focused classes and interfaces.

Through this laboratory work, I learned how applying SOLID principles can greatly improve the structure of a project. By using SRP, ISP, and DIP in a simple bakery example, I was able to see how code becomes more readable and adaptable. Each class had one responsibility, each interface was small and specific, and dependencies were based on abstractions instead of concrete classes. These principles are essential for writing good-quality software that can grow and change easily over time.
