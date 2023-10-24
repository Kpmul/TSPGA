package Replit;
class Main {
  public static void main(String[] args) {
    
    Cat cat = new Cat("mimi", new Fish());

    System.out.println(cat);
  }
}

class Fish{

  String fish = "}=@>";

  public String toString(){

    return fish;
  }
  
}

class Cat{

  String name = "";
  Fish fish;

  public Cat(Fish aFish){

    fish = aFish;
  }

  public Cat(String aName, Fish aFish){

    name = aName;
    fish = aFish;
  }

  public void setFood(Fish aFish){

    fish = aFish;
  }

  public String toString(){

    return "Cat: " + name + "\nHas: " + new Fish();
  }
  
}

