package Replit;

public class CA01_03 {
    


  public static void main(String[] args) {
    //Demo-code to create a Person that HAS-A Cat that HAS-A Fish
    //And a single println() that prints as shown in the instructions
  }
}

class Fish{
  
}

class Cat{

  Fish fish;
}

class Person{

  String name;
  Cat cat;

  public Person(String name){

    this.name = name;
  }

  public Person(String name, Cat cat){
    this(name);
    this.cat = cat;
    
  }

  public void setCat(Cat cat){

    this.cat = cat;
  }

  
}


