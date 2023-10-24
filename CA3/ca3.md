## **CA03**
## 1. **What are the negatives/drawbacks with the approach to override a method to do nothing?**
```java
class Bird{

    void sing(){
        System.out.println("Tweet");
    }
}

class smallBird extends Bird{

    @Override
    void sing(){
        
    }
}
```
Firstly, overriding a method and reducing it's funcionality is an easy way to make the readability of code much less easy from a client's perspective.
```java
public static void main(String[] args) {
        
        Bird b1 = new Bird();
        Bird b2 = new SmallBird();
        
        b1.sing();
        b2.sing();
    }
```
This would only print "Tweet" but could be easily expected to give two print statements to any client using the `Bird` class. 
```java
public static void main(String[] args) {
    
        Bird b1 = new Bird();
        Bird b2 = new SmallBird();

        b1.sing();
        b2.sing();

        Bird[] obj = new Bird[10];

        for(int i = 0;i<obj.length;i++){
            if(Math.random() < 0.5){
                obj[i] = new Bird();
            }
            else{
                obj[i] = new SmallBird();
            }
            obj[i].sing();
        }
    }
```
Another more serious drawback would be in a case like this where both objects could potentially be assigned to an array and then the `sing()` method is called. This could leave to unexpected outputs and would be much harder to trace in a larger more complex program with more classes.

## 2. **What design issue results from trying to replace inheritance with the use of interfaces?**
```java
interface Flyable{

    void makeNoise();
}

class Bird implements Flyable{
    
    public void makeNoise(){
        System.out.println("Tweet");
    }
}

class Wasp implements Flyable{

    public void makeNoise(){
        System.out.println("Bzzz");
    }
}

class Bat implements Flyable{

    public void makeNoise(){
        System.out.println("Screeech");
    }
}
```
It might not always make sense to use interfaces over inheritance. In cases like this we see a lot of the repeated code as it is necessary for every subclass to implement the `makeNoise()` method.
```java
class Flyable{

    void makeNoise(){
        System.out.println("FlapFlap");
    }
}

class Bird extends Flyable{
    
}

class Wasp extends Flyable{

    @Override
    void makeNoise(){
        System.out.println("Bzzzz");
    };
}

class Bat extends Flyable{
  
}
```
In this example there is a more general form of the `makeNoise()` method that can be applied to more subclasses without having to repeat code. The method can then be Overridden to accomodate individual subclasses if needed. This makes for much clearer code with less repitition.

## 3. **What are the drawbacks/negatives with the approach to create specific sub-classes for specific behaviours?**

The dangers of creating specific sub-classes for specific behaviours are the chance of over-complicating your hierarchy and making it more and more difficult to find and fix problems as your program grows.
```java
interface Duck{
    void flys();        // 1 Behaviour
    void quacks();      // 1 Behaviour  
    void squeaks();     // 1 Behvaiour
}                       

abstract class QuackingDuck{
    public abstract void quacks();
}

abstract class NonQuackingDuck{
    public abstract void squeaks();
}
abstract class QuackingFlyingDuck{
    public abstract void flys();
    public abstract void quacks();  
}
abstract class SqueakingQuackingDuck{
    public abstract void squeaks();
    public abstract void quacks(); 
}
...
...
```
As you can see here, making subclasses in this manner could be a long process which causes much more confusion when it comes to maintaining this class heirarchy. 
```java
class Mallard extends QuackingFlyingDuck{
    public void flys("FlapFlap");       
    public void quacks("QuackQuack");   
}
class RedHead extends QuackingDuck{
    public void quacks("Quack");
}
class Rubber extends SqueakingDuck{
    public void squeaks("Squeak!");
}
class Decoy extends SilentDuck{
    public void quacks("...");
}
```
We can see from this example that the more subclasses made for small differences in "behaviour" can end up in the possibility of having more and more classes that inherit these slight changes in behaviour. A solution to this over-complicatied heirarchy is by giving the "behaviours" their own classes.

There is a basic formula (`N = 2n`) that explains the number of possible classes when using 'n' behaviours. The idea being that every behaviour can have two states, to be present or not present.
```java
    void flys();        // 1 Behaviour
    void quacks();      // 1 Behaviour  
    void squeaks();     // 1 Behvaiour
```
N = 2(3) = 8 Potential classes could be made to represent these behvaiours. But we can keep it simple by making three
```java
 
interface Behaviour{
    void doSomething();
}
class QuackBehaviour implements Behaviour{
    public void doSomething("Quack");
}
class FlyBehaviour implements Behaviour{
    public void doSomething("FlapFlap");
}
class SqueakBehaviour implements Behaviour{
    void doSomething("Squeak");
}
```
This way we can give a desired behaviour to an instance of a class on when constructed.
```java
class Mallard{
    private Behaviour behaviour;

    public Mallard(Behvaiour aBehaviour){
        behaviour = aBehaviour;
    }

    public void doBehaviour(){
        behavior.doSomething();
    }
}
```
Here we give the Mallard an instance of `Behaviour` and assign it using a constructor. We also have a method that can call the specific behaviour attached to the `Mallard` upon construction.
```java
public static void main(String args[]){

    Mallard mall = new Mallard(newquackBehaviour());

    mall.doBehaviour();
}
```
From using this strategy pattern we can see that all the behaviours are nicely encapsulated and easy to read and determine what does what. This also gives the added benefit of interchangeability and more functionality. 
```java
Mallard mall = new Mallard(new FlyBehaviour());
Decoy dec = new Decoy(new SqueakBehaviour());

dec.setBehaviour(new QuackBehaviour());
mall.getBehaviour();
```
 




