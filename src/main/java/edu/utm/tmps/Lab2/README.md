# Laboratory Work Nr.2: Creational Design Patterns 

---

# Theory

In software engineering, creational design patterns focus on how objects are created in a program. Instead of creating objects directly using the new keyword everywhere, these patterns provide different ways to control, reuse, or simplify object creation. They help reduce dependencies between classes and make the code easier to extend when new types of objects are added. Without these patterns, programs can become difficult to maintain, because many parts of the code would need to change whenever the creation process of an object changes. Creational patterns solve this by separating the logic of how an object is created from how it is used.

These patterns include several common approaches such as Singleton, Builder, Factory Method, Abstract Factory, Prototype, and Object Pooling. Each of them is used for different needs. For example, the Singleton pattern makes sure only one object of a class exists, the Builder pattern allows step-by-step construction of complex objects, and the Factory patterns manage how and which specific objects are created. By using these design patterns, programs become more organized, flexible, and easier to test and maintain, since the creation logic is isolated and well structured.

---

# Objectives
1) Study and understand the Creational Design Patterns. 
2) Choose a domain, define its main classes/models/entities and choose the appropriate instantiation mechanisms. 
3) Use some creational design patterns for object instantiation in a sample project.

---
# Implementation

The **factory method design pattern** provides a way to create objects without specifying their exact classes. Instead of creating objects directly with the `new` keyword, the pattern defines a common interface for creating them, and lets each subclass decide which object to create. This makes it easier to introduce new types of products without changing the main code that uses them. It also helps keep the code clean and flexible, because the logic for creating objects is separated from the logic that uses those objects. In this project, the factory method pattern is used to create different types of plugs in a consistent way.

The pattern is implemented through the `PlugFactory` interface, which declares the `createPlug()` method. Each concrete factory class, such as `TypeBPlugFactory`, `TypeFPlugFactory`, and `TypeGPlugFactory`, implements this interface and creates a specific type of plug. Inside each factory, the `Builder` and `PlugDirector` classes are used to handle the detailed construction steps of each plug. This way, the main program can request different plug types through the same interface, without knowing how they are built. For example, the code below shows how the client creates and uses a plug with the factory method.

```java
public class Main {
    public static void main(String[] args) {
        PlugFactory typeBFactory = new TypeBPlugFactory();

        Plug typeBPlug = typeBFactory.createPlug();
        typeBPlug.produce();
        typeBPlug.displayDetails();
    }
}
```

---

The **builder design pattern** separates the construction of a complex object from its representation. It allows creating an object step by step while keeping the construction process and the final structure independent. The pattern uses a builder class that contains methods for setting up different parts of the product and a director class that controls the order of the building steps. This makes the code easier to read, reduces duplication, and helps create different variations of the same object with the same construction process.

In the project, the builder pattern was applied using the `Builder` interface, the `TypeBPlugBuilder` class, and the `PlugDirector` class. The `Builder` interface defines the steps required to build a plug, such as `buildName()`, `buildPinShape()`, `buildStandardVoltage()`, and others. The `TypeBPlugBuilder` class implements these methods to assign specific values to the fields of the `Plug` object. The `PlugDirector` class then calls these methods in a specific order through the `construct()` method. When the builder finishes, the `getPlug()` method returns the fully built `Plug` object. This shows how the builder pattern was used to create a Type B plug in a clear and organized way, separating the object’s construction process from its representation.

```java
public interface Builder {
    void buildName();
    void buildPinShape();
    void buildStandardVoltage();
    void buildMaxCurrent();
    void buildGroundingMechanism();
    void buildSafetyFeature();
    Plug getPlug();
}

public class TypeBPlugBuilder implements Builder {
    private Plug plug;

    public TypeBPlugBuilder() {
        plug = new Plug();
    }

    public void buildName() {
        plug.name = "NEMA 5-15";
    }

    public void buildPinShape() {
        plug.pinShape = "2 Flat Parallel Blades and 1 Round Ground Pin";
    }

    public void buildStandardVoltage() {
        plug.standardVoltage = "Low Voltage (120V)";
    }

    public void buildMaxCurrent() {
        plug.maxCurrent = "15 A";
    }

    public void buildGroundingMechanism() {
        plug.groundingMechanism = "Dedicated Round Pin (must be longer to connect first)";
    }

    public void buildSafetyFeature() {
        plug.safetyFeature = "Polarization (Neutral blade is wider than the hot blade)";
    }

    public Plug getPlug() {
        return plug;
    }
}

public class PlugDirector {
    public void construct(Builder builder) {
        builder.buildName();
        builder.buildPinShape();
        builder.buildStandardVoltage();
        builder.buildMaxCurrent();
        builder.buildGroundingMechanism();
        builder.buildSafetyFeature();
    }
}
```
---

The **Singleton design principle** is used to make sure that only one object of a certain class exists during the whole program. Instead of creating many objects of the same type, a single shared instance is created once and then reused whenever needed. This helps keep control over how that class is used and prevents problems that can happen when multiple objects try to manage the same process or data. It is often used for classes that control important actions or configurations that should stay the same everywhere in the program. The Singleton principle uses a private constructor to stop the creation of new objects from outside the class and provides a static method to return the single instance.

In the project, the Singleton principle was applied inside the `PlugDirector` class. This class is responsible for controlling the process of building plug objects, so it makes sense to have only one instance managing all the construction steps. The implementation looks like this:

```java
private static final PlugDirector instance = new PlugDirector();

private PlugDirector() {}

public static PlugDirector getInstance() {
    return instance;
}
```

The `private static final` variable ensures that only one `PlugDirector` object is created when the class is first loaded. The private constructor stops anyone from creating a new one manually. Then, the `getInstance()` method is used to access this single object. This Singleton instance is used in the factory class to create plugs, for example in `TypeBPlugFactory`:

```java
PlugDirector director = PlugDirector.getInstance();
director.construct(builder);
```

By doing this, every plug type built in the system uses the same `PlugDirector` instance, which keeps the building process consistent and controlled. This makes the structure cleaner, avoids duplication, and ensures that only one director manages how all plugs are constructed.

---
# Results

````
Producing Type B plug.
Plug details:
  Name: NEMA 5-15
  Pin Shape: 2 Flat Parallel Blades and 1 Round Ground Pin
  Standard Voltage: Low Voltage (120V)
  Max Current: 15 A
  Grounding Mechanism: Dedicated Round Pin (must be longer to connect first)
  Safety Feature: Polarization (Neutral blade is wider than the hot blade)
Type B Plugs are produced in North America. Specs: low voltage, 2 flat parallel blades, uninsulated.

Producing Type F Plug.
Plug details:
  Name: CEE 7/4 (Schuko)
  Pin Shape: 2 Round Pins (4.8 mm diameter)
  Standard Voltage: High Voltage (230 V)
  Max Current: 16 A
  Grounding Mechanism: Two side clips on the plug contact the socket clips
  Safety Feature: None (Plugs can be inserted in two orientations)
Type F Plugs are produced in Europe. Specs: high voltage, 2 round pins, uninsulated (no sleeve).

Producing Type G plug.
Plug details:
  Name: BS 1363
  Pin Shape: 3 Large Rectangular Prongs arranged in a triangle
  Standard Voltage: High Voltage (230 V)
  Max Current: 13 A
  Grounding Mechanism: Large Rectangular Pin (must be longer to open shutters)
  Safety Feature: Mandatory Fuse inside the plug; socket has safety shutters
Type G Plugs are produced in UK/Ireland. Specs: high voltage, 3 large rectangular prongs, pins are partially insulated (sleeved).
````

---

# Conclusion

During this laboratory work, the main goal was to understand and apply creational design patterns in practice. These patterns helped organize the process of creating objects in a clear and flexible way. By using patterns such as Factory Method, Builder, and Singleton, the creation of objects became more controlled and independent from the rest of the program. This made the code easier to modify and extend in the future without affecting other parts of the system. The work also showed how important it is to separate object creation from object usage to keep the design simple and maintainable.

Through this project, the connection between theory and practice became clearer. Each pattern served a specific purpose: the Factory pattern managed which type of plug was created, the Builder controlled how each plug was assembled step by step, and the Singleton ensured only one instance of the PlugDirector existed. Together, these design patterns created a well-structured and reusable codebase. The laboratory work successfully demonstrated how creational design patterns can simplify complex systems, improve flexibility, and support cleaner software architecture.