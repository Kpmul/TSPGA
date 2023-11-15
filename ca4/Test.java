package CA4;
public class Test{

    public static void main(String[] args) {
        
        Manager m1 = Manager.getManager();
        Manager m2 = Manager.getManager();
        Manager m3 = Manager.getManager();

        System.out.println(m1);
        System.out.println(m1);
        System.out.println(m1);
       
    }
}


class Manager{  
    
    private Manager(){
        System.out.println("Employee Created");
    }

    private static class instanceHolder{
        private static final Manager mng = new Manager();
    }
    
    public static Manager getManager(){
        return instanceHolder.mng;
    }
}  