interface Bird{

    abstract void sing();

    boolean sings();
}

abstract class FlyingBird implements Bird{

    boolean canSing = true;

    FlyingBird(){

    }

    public abstract void sing();

    public boolean sings(){
        return canSing;
    }

    boolean flys(){
        return true;
    }
} 
abstract class FlightlessBird implements Bird{

    boolean canSing = false;

    FlightlessBird(){}

    public boolean sings(){
        return canSing;
    }

    public void sing(){}
}

class BlackBird extends FlyingBird{

    public void sing(){
        System.out.println("TweetTweet");
    }
}

class Emu extends FlightlessBird{

    @Override
    public void sing(){
        System.out.println("SquawkSquawk");
    }
}

public class BirdMaker{

    public static void main(String[] args) {
        
        Bird bb = new BlackBird();
        Bird emu = new Emu();

        bb.sing();
        emu.sing();

        BlackBird fb = new BlackBird();
        System.out.println(fb.flys());
        
    }
}

