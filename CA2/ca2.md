## CA_02
## **Abstract class `Bird`**
```java
abstract class Bird{

    boolean canSing;

    Bird(){

    }

    abstract void sing();

    boolean sings(){
        return false;
    };
    
}
```
The abstract Bird class is a Super class in this example. Because the class is delcared asbtract, this means that this class is treated as a blueprint for all subclasses to be made from. A direct instance of `Bird` can not be instantiated but subclasses of type `Bird` such as `Flying Bird` can. This is a standard way of using a superclass but it might be useful to create a `Bird` interface instead
```java
interface Bird{

    abstract void sing();

    boolean sings();
}
```
This way subclasses can deal with more specific traits. Such as implementing the `canSing()` method and returning `true` or `false` depending on the type of subclass. Also, as Interfaces can not have a state, we can't declare a variable such as `boolean canSing` without declaring it as final. We can pass this variable into subclasses too for betetr encapsulation and inheritance. 
```java
abstract class FlyingBird implements Bird{

    boolean canSing = true;

    public boolean sings(){
        return canSing;
    }
    ...
} 

abstract class FlightlessBird implements Bird(){

    boolean canSing = false;

    public boolean sings(){
        return canSing;
    }
    ...
}

```
## 2. **Abstract class `FlyingBird`**
```java
abstract class FlyingBird extends Bird{

    FlyingBird(){

    }

    void sing(){
        System.out.println("Tweet");
    }

    boolean flys(){
        return true;
    }
} 
```
The `FlyingBird` abstract class `extends` the `Bird` class. While it is still abstract it provides a concrete implementation of the `sing()` method from `Bird`. In the superclass `Bird`, there is the method `sings()` which is set to return false. This could be used as a defualt so that the implemented subclasses can update this method to return true depending on the type of `Bird`. The `FlyingBird` class also introduces a new `boolean` method called `flys()`, which returns true but like stated above this might be more clean and readable if it was introduced in the `Bird` interface.
```java
Interface Bird{

    boolean flys();
    ...
}
```
And then implemented in the sublcasses. 
```java
asbtract class FlyingBird implements Bird{

    boolean canFly = true;

    public boolean flys(){
        return canFly;
    }
}
```
This supports consistency, inheritance and readability throughout the class heirarchy/

## 3. **Abstract class `FlightlessBird`**
```
abstract class FlightlessBird extends Bird{

    FlightlessBird(){

    }

    void sing(){
        System.out.println("Squawk");
    }
}
```
The other direct subclass of Bird is the `FlightlessBird` class. it would be a good idea for this class to also have the `boolean` method `flys()`. The reasoning for the `flys()` class would be to check if any subclasses made from these classes had the "ability" to fly. eg.
```java
BlackBird bb = new BlackBird();

System.out.println(bb.flys());
```
This way, a client could check if the `BlackBird` class has the "ability" to fly and keeps a level of encapsulation between classes. 

## 4. **class `BlackBird`**
```java
class BlackBird extends FlyingBird{

    @Override
    void sing(){
        System.out.println("TweetTweet");
    }
}
```
A subclass of the `FlyingBird` class is the `Blackbird` class. In this example we can see it `Overrides` the superclass' `sing()` method and updates the print statement. This shows an example of how inheritance works between super and sub classes. Overridding like this makes
```java
class Emu extends FlightlessBird{

    @Override
    void sing(){
        System.out.println("SquawkSquawk");
    }
}
```
This Overridding of a method can be used to increase specificity in subclasses. It would also be possible to keep the `sing()` method more abstract within the abstract superclass by not giving it concrete functionality, or keeping it fully abstract until it is implemented by the sublcass.
```java
abstract class FlyingBird implements Bird{

    public void sing(){

    }

    // or

    public abstract void sing();

}
```
## 5. **class `Emu`**
```java
class Emu extends FlightlessBird{

    @Override
    public void sing(){
        System.out.println("SquawkSquawk");
    }
}
```
The `Emu` class `extends` the `FlightlessBird` class which means it is a subclass. The `extends` keyword is a way of giving a subclass all the characteristics and behaviours of the super class. Again we see that the `sing()` method is Overridden so as to give the `Emu` subclass it's own qualities while still re-using code from it's super class.
The problem in the code shown is that both `BlacBird` and `Emu` don't vary a huge amount apart from some boolean true or false variables. 
```java
class Emu extends FlightlessBird{

    int speed = 60;

    public void run(){
        System.out.println("Emu is running at a speed of " + speed);
    }

    public void setSpeed(int aSpeed){

        speed = aSpeed;
    }
}
```
Giving `FlightlessBird` it's own methods and variables supports the idea that there should be a class heirarchy of `Bird`. If there is no real difference in subclasses, there could possibly be a quicker and more understandable way to go about the `Bird` class without the need for so many subclasses and unnecessary inheritance. 
```java
abstract class Bird{

    abstract void sing();

}

class FlyingBird extends Bird{

    public void sing(){
        System.out.println("TweetTweet");
    }
}
```
This keeps the code more concise and readable. Over-use of classes can increase complexity, performance and can be harder to maintain as programs grow in size.
## 6. **public class `BirdMaker`** 
```java
public class BirdMaker{

    public static void main(String[] args) {
        
        Bird bb = new BlackBird();
        Bird emu = new Emu();

    }
}
```
The public class `BirdMaker` acts as a class to perform the program's `Main` method tasks. `BirdMaker` is directly related to the Superclass Bird along with the two subclasses `BlackBird` and `Emu` as it is the class where the Bird superclass is eventually implemented and used to create subclasses and perform actions with. Classes such as this can be used to test out the class heirarchy as it is simple to create instances and used methods here. 
```java
        b.sing();
        emu.sing();

        BlackBird fb = new BlackBird();
        System.out.println(fb.flys());
```
Here we can also create new methods involving our `Bird` heirarchy.
```java
public void FlyingBirdSong(){

    FlyingBird[] birdList = new FlyingBird[10];

    for(int i=0;i<birdList.length;i++){
        birdList[i] = new FlyingBird();
    }
    
    for(FlyingBird fb : birdList){
        fb.sing();
    }
}
```

