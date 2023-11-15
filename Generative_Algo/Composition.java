
import java.util.Arrays;
import java.util.Random;

public class Composition implements GAController {
    
    private static Melody[] melodies;               // Private and hidden array for use only by the Composition class
    Random random = new Random();

    Composition(int numOfMels){                     // Constructor that takes in a number of melodies and creates a composition
        melodies = new Melody[numOfMels];
        createComp();
    }

    Composition(){                                  // No-args Constructor that creates a composition of 8 melodies    
        this(8);                                    // Use of this supporting DRY-code
    }

    public void createComp(){                       // Fills an array of melodies(Composition)
        
        for(int i = 0;i<melodies.length;i++){
            melodies[i] = new Melody();
        }
    }   

    public void run(){                              // Inherited from the GAController class for future GA operations

    }

    public String toString(){                       // To String to print out full 'Composition'
        return Arrays.deepToString(melodies);
    }
}
