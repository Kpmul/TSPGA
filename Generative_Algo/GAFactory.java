
// Implemented a new 'GAFactory' class that takes care of the creation of 
// Melodies and Compositions.
// As the generative algorithm is currently small, this works as a basis
// for a Factory and gives me room to expand on any extra class that will 
// be added in the future. As the project grows I may have to use a larger scale Factory
// like an Abstract factory so as to encapsulate more methods and class instatiation


public class GAFactory {            
    
    public Melody createMelody(){
        return new Melody();
    }

    public Melody createMelody(String characters){
        return new Melody(characters);
    }

    public Composition createComposition(){
        return new Composition();
    }

    public Composition createComposition(int numOfMels){
        return new Composition(numOfMels);
    }
}
