
// My idea was to create a Genetic Algorithm that created musical pieces. 
// I used a 'Melody' class that works in a similar fasion to the normal 'Gene' class 
// and instead of a 'Population' class I created a 'Composition' class.
// The compositon class groups multiple melodies with a possible idea being to eventually mutate 
// melodies in order to return a target piece of music. 

public class GeneticAlgorithm {
    
    public static void main(String[] args) {
        
        // New cleaner main method for creating instances of meoldies and compositions using the GAFactory class
        GAFactory gaFactory = new GAFactory();          
    
        Melody melody = gaFactory.createMelody();
        System.out.println(melody);
        melody.setMelody("aabfgbGA");
        System.out.println(melody);

        Composition composition = gaFactory.createComposition();
        System.out.println(composition);
    }
}
