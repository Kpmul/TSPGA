public class Singleton {
    
    public static void main(String[] args) {
        
        DBConnector dbc1 = DBConnector.getConnector();            // Class only loaded when class name is used for the first time
        System.out.println(dbc1);

        DBConnector dbc2 = DBConnector.getConnector();            // will have the same memory address as dbc1
        System.out.println(dbc2);
    }
}

class DBConnector{  // make a singleton

    private static DBConnector _instance = new DBConnector();     // static instance is assigned new Object

    private DBConnector(){                                        // private constructor
        System.out.println("DBConnector created");
    }

    static DBConnector getConnector(){                            // return _instance which is newly created DBConnector object
        return _instance;
    }
}
