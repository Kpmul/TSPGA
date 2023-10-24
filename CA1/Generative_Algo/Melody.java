package Generative_Algo;
import java.util.Random;

class Melody implements GAController{

    Random random = new Random();
    static final char[] notes = {'a','A','b','c','C','d','D','e','f','F','g','G'};  // Sequence to represent all musical notes
    private char[] sequence = new char[20];             // Encapsulated char[], only accessible through getters and setters below

    Melody(){               
        for(int i=0; i<sequence.length;i++){            // no-args constructor that randomly creates melody
            int rand = random.nextInt(notes.length);    // No need to alwats pass in a String of 'Notes'
            sequence[i] = notes[rand];
        }
    };                                              

    Melody(String characters){                          // Constructor that takes in a String and sets the sequence variable as a 'Gene' is being created            
        sequence = characters.toCharArray();            // Variations of Constructors to help Client ease-of-use    
    }                                                   

    public char[] getMelody(){                        // Getter method to return the code variable of the Gene
        return sequence;
    }

    public void setMelody(String characters){         // As it is possible to constuct a Gene without passing in a String
                                            
        sequence = characters.toCharArray();        // it is necessary to have a setter method to update the Gene's code variable
    }

    public void run(){                              // Inherited from the GAController class for future GA operations
    }

    @Override                                       // Override of the toString method to print out the sequence of the Object
    public String toString(){                       
        return String.valueOf(sequence);
    }
}
