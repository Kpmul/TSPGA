package Genetic_Algo;

// My idea was to create a Genetic Algorithm that created musical pieces. 
// I used a 'Melody' class that works in a similar fasion to the normal 'Gene' class 
// and instead of a 'Population' class I created a 'Composition' class.
// The compositon class groups multiple melodies with a possible idea being to eventually mutate 
// melodies in order to return a target piece of music. 

public class GeneticAlgorithm {
    
    public static void main(String[] args) {
        
        Melody g = new Melody();                        // Create new Melody
        System.out.println(g);                          // Print Melody
        g.setMelody("aabfgbGA");                        // Set new Melody   
        System.out.println(g.getMelody());              // Get new Melody

        Composition comp = new Composition();           // Create Composition
        System.out.println(comp);                       // Print full Composition to the screen
    }
}
