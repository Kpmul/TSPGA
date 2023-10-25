public class StaticPractice {
    static String name;
    static int getNum(){
        return 2;
    }
    public static void main(String[] args) {
        
        StaticPractice m = new StaticPractice();
        System.out.println(name);

        System.out.println(m.getNum());
        System.out.println(StaticPractice.getNum());
        System.out.println(getNum());
    }
}
