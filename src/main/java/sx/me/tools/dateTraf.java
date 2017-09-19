package sx.me.tools;

import java.text.SimpleDateFormat;

/**
 * Created by sx on 2017/6/28.
 */
public class dateTraf {
    public static java.sql.Date strToDate(String strDate) {
        String str = strDate;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        java.util.Date d = null;
        try {
            d = format.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.sql.Date date = new java.sql.Date(d.getTime());
        return date;
    }
    public static String date2TimeStamp(String date_str,String format){
                 try {
                         SimpleDateFormat sdf = new SimpleDateFormat(format);
                     return String.valueOf(sdf.parse(date_str).getTime()/1000);
                     } catch (Exception e) {
                         e.printStackTrace();
                     }
                 return "";
             }

}
