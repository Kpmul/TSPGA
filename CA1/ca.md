## CA01
### Some of the content for this CA has been discussed using the chat.openai.com website, which can be seen here - https://chat.openai.com/share/2553fff4-333f-44fc-9790-571488e8b20e

## 1. **`abstract class Pet` as compared to just `class Pet`**
```java
abstract class Pet{ 

	String name;
	...
	...
	}
	Pet p1 = new Pet(); 	// Compile Error
```
Sub-class `Pets` will be created with the attributes given in the abstract class. 
Compared to: 
```	java
class Pet(){
	
	String name;
	...
	...
	}    
	Pet p1 = new Pet(); 	// Runs 
```
By making this class `abstract` we are setting the Pet class as a kind of blueprint that all subclasses will be made from. No instance of a `Pet` can be made, just instances of subclasses of type `Pet`. This enforces abstraction and code reusability. 

## 2. **Data-types for `name`, `gender` and `sound`** 
```java
    String name;
	String gender;
	String sound; 
```
A class that has these attributes is fine. However, if all the types are `String`, this opens up the possibility of
these variables being set to anything (In `String` form). It might be worth using `Enums` to control the possibilities of what these variables can be set to. 

```java
public enum Gender{
		MALE, FEMALE, NONBINARY;
	}	
```
Enums could be used by declaring set Genders and assigning them
```java
    Gender m = Gender.MALE;
```
## 3. **`Pet(...)` constructor options for client programmers**
```java
Pet(String name, String gender, String sound) {	
    this.name = name;
    this.gender = gender;
    this.sound = sound;
}
```
This constructor will work fine but it as it is the sole constructor this means there is no	default `constructor` for this class. It is sometimes useful to create a 'no-args' `constructor` in a super class so that sub-classes can be created without having to create their own 'no-args' `constructor`
```java
Pet(){

}
```
It might be also useful to create variations of `constructors` for a `class` so that the client has options on how many `variables` to set on creation of a `Pet`
```java
Pet(String name){
	this.name = name; 
    }

Pet(String gender){
	this.gender = gender; 
    }
```
## 4. **`abstract String makeSound()` compared to just `String makeSound()` or `void makeSound()`**
```java
asbtract String makeSound();
```
`abstract` methods can not have method bodies. `abtract` methods must be inherited and given functionality by a `subclass`. 
```java
public String makeSound(){
    return "Roar"; 
    }
```
Without the 'abstract' keyword, methods can have method bodies and functionality. This method returns a String.
```java
public void makeSound(){
    System.out.println("Roar"); 
    }
```
Whereas this method has a return type of `void` which means nothing is returned. `void` methods can be used to print to the screen or set a variable type instead of returning a variable.

## 5. **Adding attribute `FoodThing food` with the introduction of `class FoodThing` from which `Bone` and `Fish` are derived.**

```java
class FoodThing{
    }
```
As each `Pet` has an attribute of either a `Bone` or a `Fish`, it might make sense to create a class called `FoodThing` from which both these attributes extend as `subclasses`. This generally makes the code easier to read as each `Pet` now has it's own `FoodThing` which can be either a `Fish` or `Bone`.
```java
class Bone extends FoodThing{

}

class Fish extends FoodThing{

}
```
More attributes can then be given to `bone` as it now has it's own `class`
```java
class Cat{
	
	FoodThing food = new Bone(); 

    FoodThing getFood(){
        return food;
    }
```
A general `getFood` method is now easy to re-use within each `Pet` `sub-class` and makes for cleaner code. This also allows for easier crossover of types. `Dog` could have `Fish` and `Cat` could have `Bone`.

## 6. **`Bone bone;` with line `bone = new Bone();` inside Dog(...) as compared to `Bone bone = new Bone();`**
```java
Bone bone;
```
Is known as a lazy loading of a variable. Variable is usually instantiated later on in the code, possibly in a constructor
```java
Dog(){
    bone = new Bone();
    name = "Fido";
}
```
```java
Bone bone = new Bone();
```
Is know as eager loading of a variable. Assigment of variable is done when class is loaded for the first time.
Bone is loaded upfront and ready to use from the program start. 
```java
System.out.prinln(dog.bone);
```

## 7. **`Dog(...)` constructor options for client programmers**
```java
Dog(){
    name = "no-name";
    gender = "NA";
    sound = "...";
}
```
Having a `no-args constructor` for the `Dog` class would provide quick and easy setting of `default-values` for every `Dog` instance created.
```java
Dog(String name, String gender, String sound){
    super(name, gender, sound); 
    }
```
Because the `Dog` superclass (`Pet`) provides a `constructor` that takes these parameters, the `super` call can be used to call the `Pet constructor` and pass in the given parameters. This is a great examples of code re-use and keep code clean and very readable.
```java
Dog(String aName){
    name = aName;
    gender = "NA";
    sound = "..."; 
    }
```
It could also be useful to make individual variations of `constructors` in order for client ease of use of the `Dog` class. This would set one variable to a preferred value and the other variables to a possible `default value`.

## 8. **`super(name, gender, "Woof");`**
```java
Dog(String name, String gender) {	
    super(name, gender, "Woof");
    bone = new Bone();	
    }
```
While this `constructor` takes two of the three `instance variables` as parameters, it would be good to have variation for ease-of-use. This `constructor` will automatically set the inherited `Dog sound` to `"Woof"` which doesn't leave room for the possibility of the `Dog` sound needing to be `"Bark"`, for example. 
```java
Dog(String aName){
    name = aName;
}
Dog(String aGender){
    gender = aGender;
}
Dog(String aSound){
    sound = aSound;
}
```

## 9. **`getBone()` as compared with making attribute `Dog.bone` accessible for direct access**
```java
Bone getBone() {	
    return bone;
}
```
The reason for using a method called `getBone` as opposed to accessing an `instance variable` through an `object` eg. `Dog.bone`, is because giving this access to a client using this `class` can lead to potential problems eg.
```java
fido.bone = null;
```
Another example of this would be if the `bone` variable had to change it's name or type, this could be easily dealt with be changing the code of the `Getter` and not affecting the client code at all.
```java
public String getBone(){

    return Bone.printString
}
``` 
Taking these steps and providing `getters` and `setters` is a form of `encapsulation` and overall provides safer and more organised code.
```java
Bone getBone(){	
    System.out.println("Retreiving bone!");	
    return bone;	
    }
```
There is also the additional possibility to add extra code in order to do something when the client calls the `getter`.		

## 10. **worthy of a class? if so why?**
```java
class Bone {
    String printString = "$==$";

    public String toString() {
        return printString;
    }
}
```
Because the `Bone` (and `Fish`) classes are so small, it doesn't seem completely necessary to create seperate classes for each of them. As these classes seem to function as data containers attached to a form of `Pet` class, it could make more sense to treat them as such and the introduction of classes may overcomplicate the code.
```java
class Bone {		
    String printString = "$==$";
    String size;
    int weight;
    ...
}
```
A reason for giving `Bone` it's own class is that it would be easier to update and attributes and give it more detail. The development and separation of this class will increase the  `modularity` of the overall program. `Bone` and `Fish` having their own class means that they both `encapsulate` their own behaviours and potential future-added behaviours. These concepts of `modularity` and `encapsulation` promote the overall reusability of the code. 

## 11. **attribute `printString` - worth having this or not?**
```java
class PersonWithAPet { 
    ...	
    ...
    public String toString() {	
        return "Person's Pet: " + pet.toString(); 
        }	
}
```
In the code provided there doesn't seem to be a reason for the `Bone` and `Fish` to have a `toString` override that prints their `printString` variables. When a `PersonWithAPet` is printed, we can see there is a `toString` that prints a statment that shows `person's pet`. In order to access the `PersonWithAPet/Dog/Bone` for example, this would require a more in-depth `toString` return for the `person` object.
```java
Cat cat = new Cat("Whiskers", "Female");	
Dog dog = new Dog("Buddy", "Male");	
System.out.println(cat.fish);
System.out.println(dog.FoodThing)	
```
If, for instance you wanted to print the `Cat/Fish's printString`, this would require printing `cat.fish`, which doesn't make much sense as the client would have to know that the `Cat` has a `Fish`. If, on the other hand, the `Bone` and `Fish` were part of a `FoodThing` class, we could print `cat.FoodThing` in order to return the `String` that indicates to the client what `FoodThing` the `Cat` (or `Pet`) has. This scenario is the only reason I can see when the `printString` variable would be necessary.  

## 12. **Include any other points of your choosing**
```java
abstract class Person{	

    String name;
    int age;
    double height;

    Person(){

    }

    Person(String aName, int anAge, double aHeight){
        ...
        ...
    } 

    abstract String getInfo();
}
```
I think in order to leave this code open to futher improvement, an abstract `Person` class would be essential. Not only is the  `PersonWithAPet` class too specific, but it doesn't leave much room for improvement. Creating a `Person` class to which the `PersonWithAPet` class is a subclass works better for `modularity` and `encapsulation`. A `Person` can have it's own details and a `PersonWithAPet` can further elaborate on these details by adding a `Pet` and `overriding` the `showDetails` method to include the `Pet`. 
```java
class PersonWithAPet extends Person{
    ...
    ...
}


PersonWithAPet p1 = new PersonWithAPet();

        p1.setPet(dog);
        p1.getInfo();
```
I believe this code is very readable, and easy to expand on by any future users. I expect that if this code was to be made more	intricate it would require seperate files for each class and a `Tester` class to run operations and see if everything works together. Possible improvements could be variations on `constructors` with every class, along with the added `FoodThing` class. I see this code as being a good template for building OO code structures.




