// class test{

//     public static void main(String[] args) {
        
//         PetFactory petFactory = new CatFactory();
//         Pet d1 = petFactory.getPet();
//         Pet d2 = petFactory.getPet();
//         d1.makeSound();
//         d2.setName("Frank");
//         d1.setFriend(d2);
//     }
// }

// class PetFactory{

//     Pet getPet(){
//         return new Pet();
//     }
// }

// class DogFactory extends PetFactory {

//     public Pet getPet(){
//         return new Dog();
//     }
// }

// class CatFactory extends PetFactory {
    
//     public Pet getPet(){
//         return new Cat();
//     }
// }

// abstract class Pet{

//     String name;

//     Pet(){}

//     Pet(String aName){}

//     abstract void makeSound();
//     public void setName(String aName){
//         name = aName;
//     }
//     abstract void setFriend(Pet p);
// }

// class Dog extends Pet{

//     Dog(){
//         super();
//     }
//     Dog(String aName){
        
//     }
//     public void makeSound(){

//     }
//     public void setName(String aName){

//     }
//     public void setFriend(Pet p){

//     }
// }
// class Cat extends Pet{

//     Cat(){
//         super();
//     }
//     Cat(String aName){
        
//     }
//     public void makeSound(){

//     }
//     public void setName(String aName){

//     }
//     public void setFriend(Pet p){

//     }
// }
    