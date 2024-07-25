package system.management.util;

public class Checker {

    private Checker(){ }

    public static boolean isValid(String data){
        return data != null && !data.isEmpty();
    }
}
