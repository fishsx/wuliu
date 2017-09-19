package sx.me.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sx on 2017/6/30.
 */
public class isNum {
    public  static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
}
