public class test2 {
    
        public static void main(String[] args) {
        
            Pet p1 = PetFactory.getPet("Dog");
            System.out.println(PetFactory.showPetCount());
        }

}

class Pet{}
class Dog extends Pet{}
class Cat extends Pet{}

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
