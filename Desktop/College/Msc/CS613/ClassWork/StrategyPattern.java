public class StrategyPattern{

    public static void main(String[] args) {
        
        Character c = new King();
        c.fight();
        Character q = new Queen();
        q.fight();

    }
}

interface WeaponBehaviour {
    
    void useWeapon();
}

class Sword implements WeaponBehaviour{

    @Override
    public void useWeapon() {
        System.out.println("Sword Swipe");
    }
}

class Knife implements WeaponBehaviour{

    @Override
    public void useWeapon() {
        System.out.println("Knife Swipe");
    }
}

abstract class Character{

    WeaponBehaviour w;

    public void setWeapon(WeaponBehaviour w){
        this.w = w;
    }

    public void fight(){
        w.useWeapon();
    }
}

class King extends Character{

    public King(){
        setWeapon(new Sword());
    }
}

class Queen extends Character{

    public Queen(){
        setWeapon(new Knife());
    }
}

class Troll extends Character{

    public Troll(){
        setWeapon(new Sword());
    }
}

class Knight extends Character{

    public Knight(){
        setWeapon(new Knife());
    }
    
}


// What things are absolutely necessary for a strategy pattern?
// 1. An interface that defines the strategy
// 2. Concrete classes that implement the strategy
// 3. A class that uses the strategy
// 4. A way to set the strategy in the class that uses it
// 5. A way to use the strategy in the class that uses it

