package lambda.util;

public class StringUtil {

    public static boolean isLower(String value){
        for (var v : value.toCharArray()){
            if (!Character.isLowerCase(v)){
                return false;
            }
        }
        return true;
    }
}
