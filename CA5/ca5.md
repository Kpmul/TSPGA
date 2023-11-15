## **CA05**
## 1. Simple Factory
**parallel hierarchies**

In a `simple Factory` there is a heirarchy on the side of the `Product` that being created
```java
class Pet{} // super
class Dog extends Pet{} // sub
class Cat extends Pet{} // sub
```
But there is not a full heirarchy on the side of the `PetFactory` as the creation of a `Pet` is handled within the `getPet` method, which exists in a single class of `PetFactory`. 
```java
class PetFactory{

    public Pet getPet(String aName){
        return aName == null ? new Pet() : new Pet(aName);
    }
}   // no sub-classes
```

**how it works to create a product**

In a simple factory class, a single `Factory` is used for the creation of an instance. In this example, the `getPet()` method takes a `String` and depending on the contents, decides what subclass to `return` 
```java
class PetFactory{

    public Pet getPet(String petType){

        if(petType.equals("Dog")){
            return new Dog();
        }
        else if(petType.equals("Cat")){
            return new Cat();
        }
        return new Pet();
    }
}
```
We can then call this in the main method when we want to create an instance of a class. 
```java
public static void main(String[] args) {
        
        PetFactory petFactory = new PetFactory();
        Pet p1 = petFactory.getPet("Dog");
    }
```
First an instance of a `PetFactory` is created and then used to call it's `getPet()` method. The type of `Pet` created is dependant on the `String` parameter passed into the `getPet()` method 

***Product and 'concrete' Product***

In this example, we can refer to the `Pet` class as the `Product` as it will be the `super class` for the various `sub-classes` that will be made in the `PetFactory`.
```java
class Pet{
    String name;
    int height;
}
```
It will be used to define what makes a `Pet` and will hold the shared instance variables for all sub-classes. In terms of a simple factory pattern, sub-classes can be looked at as `Concrete Products`. This is because it is the result of instantiation by using a `Factory`.
```java
Pet p1 = petFactory.getPet("Dog");
```
Calls the 'PetFactory'
```java
if(petType == "Dog"){
    return new Dog();   
}
```
returns a call to the Dog constructor
```java
p1.setName("Frank");
p1.setFriend();
```
a `Concrete Product` of type `Dog` is now usable in the main method.

***How to identify this variant of factory as distinct from another***

The unique identifiers for a simple `Factory` are that it is generally used as a basic pattern for `encapsulating` the creation of class instances via a single `Factory` and also that it is implemented itself as an instance in order to access the `Factory` for creation. 
```java
class PetFactory{}  // Single factory 

class Pet{} // Product
class Dog extends Pet{} // Concrete Product
```
Implementing the factory only has to be done once in a program. 
```java
class Main{

    PetFactory petFactory = new PetFactory();
    
    public static void main(String args[]){

        Pet p1 = petFactory.getPet("Cat");
    }
}
```
It's instance is then used to call the `PetFactory` methods as needed. 

## 2. Static Factory

***parallel hierarchies***

Much like in the standard simple `Factory Pattern`, the parallel heirarchies are very similar if not the same for a simple `Static Factory Pattern`.
```java
class Pet{} // Super-class
class Dog extends Pet{} // Sub-Class
class Cat extends Pet{} // Sub-Class
```
The only difference is within the single Factory itself.
```java
class PetFactory{

    public static Pet getPet(String aName){
        return aName == null ? new Pet() : new Pet(aName);
    }
}   // no sub-classes
```
The `Static Factory Pattern`, is still classified as a `Simple Factory Pattern`. It's differences do not lie in the class heirarchies

***how it works to create a product***

As the method for creating instances within the `PetFactory` is now a `static` method, this means we have to use the `getPet()` method differently in our `main`.
```java
class PetFactory{

    public static Pet getPet(String petType){

        if(petType == "Dog"){
            return new Dog();
        }
        else if(petType == "Cat"){
            return new Cat();
        }
        return new Pet();
    }
}
```
Here we can see that there is no need to create an instance of `PetFactory`.
```java
public static void main(String[] args) {
    
        Pet p1 = PetFactory.getPet("Dog");
    }
```
Instead we call the `getPet()` method using the `PetFactory` class itself. This is because the `getPet()` method is static and therefore is not attatched to a single instance created from the class. The `getPet()` method belongs to the class itself.
```java
public static Pet getPet(String petType){...    //static 
```
While it is still possible to create an instance of `PetFactory` and call the `getPet()` method, it is important to note that this is a Java work-around that tells the compiler that the instance is treated the same as if it was called by the class. 
```java
public static void main(String[] args) {

        PetFactory petFactory = new PetFactory();
        Pet p1 = petFactory.getPet("Dog");
    }   // not recommended for static factory pattern
```
***Product and 'concrete' Product***

As the main difference in the simple and static `Factory Patterns`, is based around the method for creating the `Concrete Products` being `static`, we don't see a change in the `Product` and `Concrete Product` when compared to a `Simple Factory Pattern`. 
```java
public static getPet(){
    ...
}
```
The `Product` and `Concrete Product` remain the same as the `static` keyword only affects the method of instatiation and possibly other methods attatched to the `PetFactory` class.
```java
class PetFactory{

    static int petCount = 0;

    public static Pet getPet(String petType){

        if(petType.equals("Dog")){
            petCount++;
            return new Dog();
        }
        else if(petType.equals("Cat")){
            petCount++;
            return new Cat();
        }
        petCount++;
        return new Pet();
    }

    public static String showPetCount(){
        return "pets created = " + petCount;
    }
}

public static void main(String[] args) {
    
        Pet p1 = PetFactory.getPet("Dog");
        System.out.println(PetFactory.showPetCount());
    }
```
***How to identify this variant of factory as distinct from Simple Factory***

As both these classes are treated as `Simple Factory Patterns`, they share a lot of commonalities apart from the method of instantiation of the `Concrete Product`.
It is common for static methods to have broader names when it comes to methods for creating instances.
```java
// simple factory uses the 'getter' naming convention
public Pet getPet(String petType){...}
// static factory sometimes use different naming
public static Pet createPet(String petType){...}
```
## 3. Factory Method

***parallel hierarchies***

When using a standard `Factory Pattern`, it is much easier to see the parallel heirarchies as there is substantial heirarchies on both sides. We can see the `Product` heirarchy is the same as it usually is
```java
class Pet{} // super
class Dog extends Pet{} // sub
class Cat extends Pet{} // sub
```
But now the `Factory` heirarchy has more elements that make it 'parallel' the `Product` side.
```java
class PetFactory{}  // super
class DogFactory extends PetFactory{}   // sub  
class CatFactory extends PetFactory{}   // sub
```
We can see that now the `PetFactory` class can have multiple `sub-classes`.

***how it works to create a product***
```java
public static void main(String args[]){

    Pet d1 = new Dog();
    Pet d2 = new Dog();
    d1.makeSound();
    d2.setName("Frank");
    d1.setFriend(d2);
}
``` 
If a program needs all instances of `Dog` changed to `Cat`, both which are types of `Pet`, this involves changing all lines of code with the `Dog` keyword to `Cat`. This results in multiple class dependencies.
```java
    Pet d1 = new Cat(); // have to make sure each 
    Pet d2 = new Cat(); // use of 'Dog' is correctly changed
    d1.makeSound();
    d2.setName("Frank");
    d1.setFriend(d2);
```
Instead we can create 'Factories' for each class that will handle the creation of these instances by `Overriding` the super class' `getPet()` method. 
```java
class PetFactory{

    Pet getPet(){
        return new Pet();
    }
}

class DogFactory extends PetFactory {

    public Pet getPet(){
        return new Dog();
    }
}

class CatFactory extends PetFactory {
    
    public Pet getPet(){
        return new Cat();
    }
}
```
This way we will only need to change the implementation of the `PetFactory` one single time.
```java
public static void main(String[] args) {
    
    // single change in code
    PetFactory petFactory = new CatFactory();   

    Pet d1 = petFactory.getPet();
    Pet d2 = petFactory.getPet();
    d1.makeSound();
    d2.setName("Frank");
    d1.setFriend(d2);
}
```

***Product and 'concrete' Product***

Whlie implementing the `Factory Pattern`, we refer to certain classes as the `Product` and the `Concrete Product`. In this code, we are using the class `Pet` as the `Product`. The `Product` can usually be created as an `interface` or `abstract`, but this isn't always neccessary. The `Product` acts as a blueprint for subclasses to be made from.
```java
abstract class Pet{

    String name;

    Pet(){}

    Pet(String aName){}

    abstract void makeSound();
    public void setName(String aName){
        name = aName;
    }
    abstract void setFriend(Pet p);
}
```
While the `Concrete Product` is where the creation of each instance is handled. This is done in subclasses that are called by the factories `getPet()` method. Depending on what type of 'factory' is calling it, the `getPet()` method is `Overriden`. 
```java

class DogFactory extends PetFactory {

    public Pet getPet(){   //Overidden 'getPet()' from 'Pet'
        return new Dog();  // Calls concrete 'Dog' constructor
    }
}

class Dog extends Pet{

    Dog(){
        super();    // Calls super 'Pet' constructor
    }
    Dog(String aName){...}

    public void makeSound(){...}
    public void setName(String aName){...}
    public void setFriend(Pet p){...}
}
```

***How to identify this variant of factory as distinct from Simple/Static Factory***

The main points that distinguish the `Factory Pattern` from the single and static versions are the larger `Factory` heirarchy where `Concrete Creators` extend an abstract `Super-Class`.
```java
abstract class PetFactory{}  // super
class DogFactory extends PetFactory{}   // sub  
class CatFactory extends PetFactory{}   // sub
```
This overall parallel heirarchy is the main difference as when the client implements both the `Product` and the `Creator`, it works in a similar fashion to a `Simple Factory Pattern`.
```java
public static void main(String args[]){

    PetFactory petFactory = new CatFactory();
    Pet p1 = petFactory.getPet();
    p1.setName("Rupert");
}
```
It is also possible to have both an instance of `DogFactory` and `CatFactory` as there are `Creator` `sub-classes` for the `PetFactory` class. 
```java
    PetFactory catFactory = new CatFactory();
    PetFactory dogFactory = new DogFactory();
```
While this is possible with a `Simple Factory` also, a standard `Factory Pattern` makes it easier to introduce more products as it is just a case of adding a new `sub-class` and does not interfere with the inner-working code of classes.
```java
// adding a new product with standard pattern
public class BirdFactory extends PetFactory{...}

// adding a new product with a simple(or static) pattern
class PetFactory{

    public Pet getPet(String petType){

        if(petType.equals("Dog")){
            return new Dog();
        }
        else if(petType.equals("Cat")){
            return new Cat();
        }
        else if(petType.equals("Bird")){
            return new Bird();  
        }   // Not best practice adding to already tested code
        return new Pet();
    }
}

class Bird extends Pet{...}
    // A lot more code to be added overall

```
## 4. Abstract Factory

***parallel hierarchies***

In the `Abstract Factory Pattern` there are definite parallel hierarchies. Much like the standard `Factory Pattern` there exists multiple uses of `inheritance` but now with a focus on creating `families` instead of single factories dedicated to making a single `product`.
```java
interface PetFactory{}
interface FourLegPetFactory implements PetFactory{}
interface FlyingPetFactory implements PetFactory{}
interface ReptilePetFactory implements PetFactory{}

interface Pet{}
class Dog implements Pet{}
class Bird implements Pet{}
class BeardedDragon implements Pet{}
```
In the `Abstract Factory` hierarchy we can see that it might suit a larger more scaled up program that needs to use multiple classes. The `PetFactory` interface acts as the blueprint for more specialised types of factories with the intent to make families of `Products`, while the `Product` side keeps the `Pet` interface as the blueprint for concrete pet classes.

***how it works to create a product***

The abstract interfaces used are so that certain methods and traits are passed down to more specialised concrete factories. 
```java
interface PetFactory{

    Pet createPet();
}
interface FourLegPetFactory implements PetFactory{
    Pet createFourLegPet(); 
}
interface FlyingPetFactory implements PetFactory{
    Pet createFlyingPet(); 
}
interface ReptilePetFactory implements PetFactory{
    Pet createReptilePet(); 
}
```
Once these blueprints are made we can start to build families of factories
```java
class DogFactory implements FourLegPetFactory{}
class CatFactory implements FourLegPetFactory{}

class BirdFactory implements FlyinPetFactory{}
class BatFactory implements FlyingPetFactory{}

class LizardFactory implements ReptilePetFactory{}
class BeardedDragonFactory implements ReptilPetFactory{}
```
The methods that are passed down from the more abstract factories are then overriden to suit the concrete factories so as to create specific `Products`.
```java
class DogFactory implements FourLegPetFactory{

    @Override   // from PetFactory interface
    public Pet createPet(){
        return new Dog();
    }

    @Override   // from FourLegPet interface
    public Pet createFourLegPet(){
        return createPet();
    }
}
class CatFactory implements FourLegPetFactory{

    @Override   // from PetFactory interface
    public Pet createPet(){
        return new Cat();
    }

    @Override   // from FourLegPet interface
    public Pet createFourLegPet(){
        return createPet();
    }
}
```
These factories can then be used by the client to create specific `Products`.
```java
public static void main(String args[]){

    FourLegPetFactory flpf = new DogFactory();
    Pet pet1 = flpf.createFourLegPet();
    pet1.makeNoise(); 

    ReptilePetFactory rpf = new LizardFactory();
    Pet pet2 = rpf.createReptilePet();
    pet2.makeNoise();
}
```
***Product and 'concrete' Product***

In the `Abstract Factory Pattern` it is easy to distinguish between the `Product` and the `Concrete Product` as the abstraction and encapsulation makes the layout of the code clean and easy to understand. 
```java
interface Pet{  // Product
    void makeNoise();   // to be passed down to all 'Pets'
}
``` 
The `Concrete Product` then clearly implements and `Overrides` all the methods from the `Product`
```java
class Dog implements Pet{

    @Override
    public void makeNoise(){
        System.out.println("Woof!")
    }
}
class BeardedDragon implements Pet{

    @Override
    public void makeNoise(){
        System.out.println("....")
    }
}
```
The only difference with the `Abstract Factory Pattern` is how these `Concrete Products` are treated. They are treated as families by the parallel factories.
```java
// 'FourLegPetFactory' family
interface FourLegPetFactory implements PetFactory{}

// factories create family of concrete factories
class Dogfactory implements FourLegPetFactory{}
class Catfactory implements FourLegPetFactory{}
class Rabbitfactory implements FourLegPetFactory{}

// concrete factories create concrete Products
FourLegPetFactory flpf = new RabbitFactory();
Pet pet1 = flpf.createFourLegPet();
pet1.makeNoise(); 
```

***How to identify this variant of factory as distinct from Factory Method***

The main identifier of the `Abstract Factory Pattern` is the overall class hierarchy and use of inheritance throughout both the `Product` and the `Factory` classes. We could also use `abstract` classes for this.
```java
interface PetFactory{} 
abstract FlyingPetFactory implements PetFactory{}
class BatFactory extends FlyingPetFactory{}
```
The abstraction in order to create families is important for this pattern in particular and adding extra classes is made very easy as a new class just needs to be added to the desired family.
```java
abstract FlyingPetFactory implements PetFactory{}
class MosquitoFactory extends FlyingPetFactory{}
```
This keeps all classes nicely encapsulated and doesn't involve changing existing code. This adheres to the `Open/Closed Principle`, which states that a class should be open for extension but closed for modification. 

When comparing a `Simple Factory Pattern` to an `Abstract Factory Pattern` we can see that it is harder to use the `Open/Close Principle`, has extra classes being added invloves editing existing classes
```java
public Pet getPet(String petType){

        if(petType.equals("Dog")){
            return new Dog();
        }
        else if(petType.equals("Cat")){
            return new Cat();
        }
        else if(petType.equals("Bird")){
            return new Bird();  
        }   // Not best practice adding to already tested code
        return new Pet();
}
```
Whereas the `Abstract Factory Pattern` adds code by means of `inheritance`.
















































