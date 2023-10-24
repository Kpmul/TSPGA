public class test{

    public static void main(String[] args) {
        
    }
}

abstract class Bird{

    abstract void sing();

}

class FlyingBird extends Bird{

    public void sing(){
        System.out.println("TweetTweet");
    }
}

interface Duck{

    void quack();
}

