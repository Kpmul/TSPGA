package CA1;
// Pet.java
abstract class Pet {
    String name;
    String gender;
    String sound;

    Pet(String name, String gender, String sound) {
        this.name = name;
        this.gender = gender;
        this.sound = sound;
    }

    abstract String makeSound();
    
}

abstract class Person{

    String name;
    int age;
    double height;

    Person(){}

    Person(String aName, int anAge, double aHeight){

    } 

    abstract String getInfo();

}

// Dog.java
class Dog extends Pet {
    Bone bone;

    Dog(String name, String gender) {
        super(name, gender, "Woof");
        bone = new Bone();
    }

    String makeSound() {
        return "Woof";
    }

    Bone getBone() {
        return bone;
    }
}

// Cat.java
class Cat extends Pet {
    Fish fish;

    Cat(String name, String gender) {
        super(name, gender, "Meow");
        fish = new Fish();
    }

    String makeSound() {
        return "Meow";
    }

    Fish getFish() {
        return fish;
    }
}

// Bone.java
class Bone {
    String printString = "$==$";

    public String toString() {
        return printString;
    }
}

// Fish.java
class Fish {
    String printString = "}==*->";

    public String toString() {
        return printString;
    }
}

// PersonWithAPet.java
class PersonWithAPet extends Person {

    Pet pet;



    void setPet(Pet pet) {
        this.pet = pet;
    }
    public String getInfo(){
        return "Info";
    }

    public String toString() {
        return "Person's Pet: " + pet.toString();
    }
}

// Main.java
class Main {
    public static void main(String[] args) {
        Cat cat = new Cat("Whiskers", "Female");
        Dog dog = new Dog("Buddy", "Male");
        PersonWithAPet p1 = new PersonWithAPet();

        p1.setPet(dog);
        p1.getInfo();

        System.out.println(cat.fish);

        PersonWithAPet person1 = new PersonWithAPet();
        PersonWithAPet person2 = new PersonWithAPet();

        person1.setPet(cat);
        person2.setPet(dog);

        System.out.println(person1);
        System.out.println(person2);
        System.out.println(dog.bone);
    }
}
