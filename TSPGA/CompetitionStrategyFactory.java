// Factory for creating CompetitionStrategy objects
// So far only using two types of 'Selection' or 'Competition' strategies - Tournament and Random
// See the actual methods in their own clases e.g TournamentCompetition.java and RandomCompetition.java

interface CompetitionStrategyFactory{
    
    CompetitionStrategy createCompetitionStrategy();
}

class TournamentCompetitionFactory implements CompetitionStrategyFactory {  // Tournament 

    public CompetitionStrategy createCompetitionStrategy() {

        return new TournamentCompetition();
    }
}

class RandomCompetitionFactory implements CompetitionStrategyFactory {  // Random

    public CompetitionStrategy createCompetitionStrategy() {
        
        return new RandomCompetition();
    }
}




