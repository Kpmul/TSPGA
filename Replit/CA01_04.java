package Replit;

public class CA01_04 {
    
    public static void main(String[] args) {
    
        Person p = new Person();
        Cat c = new Cat();
        p.setPet(c);
        System.out.println(p);
    }
}

    class Person{
    Pet pet;
    
        public void setPet(Pet pet){
        
            this.pet = pet;
        }
        
        public String toString(){
        
            return this.pet.name;
        } 
    }

    class Pet{

        String name;
      
        public Pet(String aName){
          name = aName;
        }
        public String toString(){
          return name;
        }
    }
      
    class Fish{
        
    }
    class Cat extends Pet{ 
      
        Fish fish;

        public Cat(){
            super("null");
        }
    }
