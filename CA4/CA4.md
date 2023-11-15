## **CA04**
### Some of the content for this CA has been discussed using the chat.openai.com website, which can be seen here - https://chat.openai.com/share/34f41b89-6ea1-45f1-b3c2-9e60c1bbcd89
## 1. **the static keyword and it's effect in Java**
```java
class Main{

    static String name;

    public static void main(String args[]){
        ...
        ...
    }
}
```
The `static` keyword in Java indicates that the variable or method that follows the static description, belongs to the Class itself and not to individual instances. Static variables and methods are only loaded to a program once and then shared by all following instances. Note that `Employee.empNum` is a `static reference` to the `empNum` variable. This is how static variables are accessed which shows that they belong solely to the class `Employee`.
```java
class Employee{

    static int empNum = 1000;

    public Employee(){

        empNum++;
    }
}

public class Main{

    public static void main(String args[]){

        System.out.println("Employee Number: " + Employee.empNum);
        Employee e1 = new Employee();
        System.out.println("Employee Number: " + Employee.empNum);
        Employee e2 = new Employee();
        System.out.println("Employee Number: " + Employee.empNum);
    }
}
```
In this code, on creation of a new `Employee`, the `empNum` variable is increased each time and will result in incrementing numbers being printed to the console.
```java
1000
1001
1002
```
Where as if this was not a static variable, each instance of `Employee` would have their own `empNum`. Note that `empNum` is accessed through the individual instances of Employee as we can no longer use the `Employee` static reference because the `main` method is `static` and can not reference a non-static variable.
```java 
class Employee{
    int empNum = 1000
}
public class Main{
    public static void main(String args[]){

        Employee e1 = new Employee();
        System.out.println("Employee Number: " + e1.empNum);
        Employee e2 = new Employee();
        System.out.println("Employee Number: " + e2.empNum);
    }
}
```
This will result in the same `empNumber` being printed to the screen.
```java
1001
1001
```
## 2. **the essential parts of a simple Singleton (non-threaded) in Java**
```java
class Manager{

    private static Manager mng;

    private Manager(){
        System.out.println("Manager Created");
    }

    public static Manager getManager(){
        if(mng == null){
            mng = new Manager();
        }
        return mng;
    }
}
```
The Singleton Pattern is a way to guarantee that there will only be exactly one instance of a class. It also provides encapsulation for the class and makes it useable to a client witrhout them changing their own code. 
The essential parts of a Singleton Pattern are as follow.

A static member variable
```java 
private static Manager mng;
```
This instance is used to be a reference to the unique instance of the class that is created and can be assigned either at the first load-in of the class or within the constructor of the class.

A private or protected `Constructor`
```java
private Manager(){
    System.out.println("Instance of Manager created");
}
```
We can use a very basic `constructor` with a print statement in order to log when the instance was created.
If a client tries to create an instance of class `Manager` by using a 'normal' constructor format at assignment, this will result in a compile error stating the constructor is not visible. 
```java
public static void main(String args[]){

    Manager mng1 = new Manager();   // Compile Error

}
```

Instead, the next essential part of the `Singleton Pattern` is used to create the sole instance. Which is the use of a `public` 'getter' for instantiation.

A 'getter' method 
```java
public static Manager getManager(){
        
        return mng;
    }
```
By using a 'getter' to create the instance of `Manager`, we ensure it's unique creation along with encapsulation.
```java
public static void main(String[] args) {
        
    Manager mng1 = Manager.getManager();
        
}
```
This method being `public` also means the client is free to use this whenever the `Manager` class needs to be loaded into the program. 

A `null` check.

```java
 public static Manager getManager(){
        if(mng == null){
            mng = new Manager();
        }
        return mng;
    }
```
The insertion of a check into the 'getter' checks to see if the `Manager`'s instance of `mng` has been instantiated or is in fact `null`.

## 3. **a singleton with 'eager' initialisation compared to 'lazy' initialisation**
The unique class instance can be assigned to the `mng` reference from either within the constructor (known as lazy loading)
```java
public static Manager getManager(){
        if(mng == null){
            mng = new Manager();
        }
        return mng;
    }
``` 
or else upon loading of the `Manager` class (known as Eager loading)
```java
class Manager{

    private static Manager mng = new Manager();
    ...
    ...
}
```
With a small program like this, it can be difficulat to see the pros and cons of these two types of initialisation. If we picture a much larger, more memory intensive program we will start to see the difference. 
```java
public static void main(String args[]){

    String name = "";
     int age = 0, num = 0, wage = 0;

    Scanner sc = new Scanner(System.in);

    Employee[] empArr = new Employee[1500];

    for(int i=0;9<emrAr.length;i++){

        empArr[i] = new Employee(sc.nextLine());
        empArr[i].setNum(sc.nextInt());
        empArr[i].setWage(sc.nextInt());
    }
    ...
    ...
    // multiple method calls and loop
}

```
In more complex code like this, it might be better to create only initialise a `Manager` when the instance is needed.
```java
if(manager == null && empArr[i].getClass() == Employee){
    manager = Manager.getManager();
}
```
This way, `manager` will be instantiated only when needed and every time is it assigned to a refernce, we are assigning the same `Manager` object. 

On the other hand when we look at Eager loading, benefits could be that upon loading of the program, an instance of `Manager` is loaded and readily available for the client's use. 
```java

public static void main(String args[]){

    Manager m = Manager.getManager();
    Office o1 = new Office();
    Office o2 = new Office();
    Timetable t1 = new Timetable();
    Timetable t2 = new Timetable();

}
```
This shows how all the tool for the client could be easily useable upon opening the program. This mehtod of Eager loading loans itself to the client's code being more understandable as it clearly displays the creation of an instance of `Manager` which can be utilised.
```java
    m.getEmployeeList();
    emp1.setManager(m);
```
Problems with Eager loading are again related to larger scaled programs where the overhead of loading in a `Manager` upon startup might be detrimental to the program's startup time. 
```java
    class Manager{

        Hashmap empMap = new Hashmap();
        Timetable[][] timeArr = new Timetable[1000][100];
        ...
        ...
    }
```
If, for instance, the `Manager` class itself had many instances of `Lists`, `Maps` and `Arrays` to load and fill upon startup, we can see how this might be memory intensive upon start up of a program as the program itself might have many classes to load also. 
```java
public static void main(String args[]){

    ...
    ...         // Contents of a large program

    Manager m1 = Manager.getManager();  // Load at Client's discretion
}
```
Instead it might be preferable for a Client to only instantiate an instance of a `Manager` when needed. This can potentially save on memory in applications where there might not be a need for a Singleton Class. 

## 4. **a thread-safe singleton approach 1: using 'synchronized' block**

One problem that the Singleton Approach is vunerable to is multi-threaded programs. The potential danger of using Threads is that both Threads can access the Singleton's 'getter' and check if the instance variable is `null`. 
```java
public static void main(String args[]){

    Thread t1 = new Thread("T1");
    Thread t2 = new Thread("T2");

    t1.start();
    t2.start();
}
```
This makes it possible that both checks return as true as by the time `t1` and `t2` enter the `getManager` method, the instance `mng` will be `null`.
```java
public static Manager getManager(){ // t1 and t2 enter and check here

        if(mng == null){          // returns 'true' for both
            mng = new Manager();   // two instances are created
        }
        return mng;
    }
```
This causing two instances of `Manager` to be created. 

One way around this is the use of a `synchronized` block. 
```java
 public static Manager getManager(){
    
        synchronized(Manager.class){
            if(mng == null){
                mng = new Manager();
            }
        }
        return mng;
    }
```
By using the `synchronized` keyword inside the method, this means that only one thread can enter the block at a time. This is known as a synchronised block. 
```java
public static Manager getManager(){ 
    
        synchronized(Manager.class){    // t1 enters
            if(mng == null){            // returns true
                mng = new Manager();    // new instance is created
            }
        }
        return mng;             // returns new instance
    }
```
Only when `t1` exits, can t2 enter the block (order of thread is not determinable). 
```java
public static Manager getManager(){  
   
        synchronized(Manager.class){    // t2 enters
            if(mng == null){            // returns false
                mng = new Manager();    
            }
        }
        return mng;    // returns previously instantiated instance
    }
```
## 5. **a thread-safe singleton approach 2: using 'double-checked locking'**

The problem with this `synchronized` block is that every thread will eventually enter the block and perform a check for `null` even though we know there has been an initial instance created from the first thread. 

A way around this is to use the double-checked locking' approach in order to avoid this. This involves a prior check before entering into the check block. First `t1` will enter and create the instance 
```java
public static Manager getManager(){  
    
    if(mng == null){        // t1 performs check (returns true)
        synchronized(Manager.class){    // t1 enters
            if(mng == null){            // returns true
                mng = new Manager();    // unique instance created
            }
        }
        return mng;    // returns instance
        }
    }
```
Next `t2` will be diverted from entering the block because it fails the initial check.
```java
public static Manager getManager(){  
    
    if(mng == null){        // t2 performs check (returns false)
        synchronized(Manager.class){    
            if(mng == null){           
                mng = new Manager();    
            }
        }
        return mng;    
        }
    }                       // block is not entered 
```
The use of this 'double-check' can save the unnecessary use of memory and stop any possible slow performance, especially in a scaled up application with 'n' amount of threads. 

Another thing to note is that without the use of the `volatile` keyword a problem can arise from multiple threads not recognising the change in state of a variable by other threads. It is good practice to use this keyword on any variables that may change or reference different objects throughout the program.
```java
private static volatile Manager mng;
```
The variable `mng` is now protected from confusion between threads and will be updated for any following threads once it's state has changed. 

## 6. **a thread-safe singleton approach 3: using the Bill Pugh method (or similar other approach)**

While the 'double-checked locking' approach builds on the 'synchronized block' approach, we can build one step further to improve readability and performance. 
```java
class Manager{  
    
    private Manager(){
        System.out.println("Employee Created");
    }

    private static class instanceHolder{
        private static final Manager mng = new Manager();
    }
    
    public static Manager getManager(){
        return instanceHolder.mng;
    }
}       
```
Giving the `Manager` class it's own sublcass to act as a 'holder' for the unique instance to be created avoids any over complication and potential problems with multi-threading as the sub-class is only loaded into the program once. This means that every time that the `getManager()` method is called. It returns the already-created instance of `mng`, as the `instanceHolder` class is static along with the `mng` variable that it holds. 
```java
    private static class instanceHolder{
        private static final Manager mng = new Manager();
    }
```
To show that this instance will only be created once, it is possible to call the `getManager()` method multiple times.
```java
public static void main(String args[]){

    Manager m1 = Manager.getManager();
    Manager m2 = Manager.getManager();
    Manager m3 = Manager.getManager();
    Manager m4 = Manager.getManager();
    Manager m5 = Manager.getManager();
}
```
The resulting print to the console will be as follows
```java
Employee Created
```
Showing that the code above is only making five seperate references to one `Manager` Object. We can take this a step further and print the refernces to see the same memory address
```java
public static void main(String args[]){

    System.out.println(m1);
    System.out.println(m2);
    System.out.println(m3);
    System.out.println(m4);
    System.out.println(m5);
}
```
This will result in a print to the console such as
```java
CA4.Manager@5acf9800
CA4.Manager@5acf9800
CA4.Manager@5acf9800
CA4.Manager@5acf9800
CA4.Manager@5acf9800
```
The only restriction on the Bill Pugh method for the Singleton Pattern is that it does not support eager loading. The reason for this is because the nature of a `static` class is that it is only loaded once into memory upon it's first reference. The Bill Pugh method is designed with this idea in mind and is mostly used when lazy-loading is preferable.   



















