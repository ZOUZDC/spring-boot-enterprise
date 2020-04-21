package zdc.enterprise.config.formatter;

import org.springframework.format.Formatter;
import org.springframework.util.StringUtils;
import zdc.enterprise.constants.CustomException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

public class DateFormatter implements Formatter<Date> {
    //年月日
    String pattern_date ="^[\\d]{4}-[\\d]{1,2}-[\\d]{1,2}$";
    //年月日时分秒
    String pattern_datetime ="^[\\d]{4}-[\\d]{1,2}-[\\d]{1,2}[ ][\\d]{1,2}:[\\d]{1,2}:[\\d]{1,2}$";
    //秒
    String pattern_second ="^[\\d]{10}$";
    //毫秒
    String pattern_millsecond ="^[\\d]{13}$";

    SimpleDateFormat sdf_date =new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat  sdf_datetime =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public Date parse(String s, Locale locale) throws ParseException {

        if(StringUtils.isEmpty(s)){
            return null;
        }

        if (Pattern.matches(pattern_date,s)) {
            return sdf_date.parse(s);
        }
        if (Pattern.matches(pattern_datetime,s)) {
            return sdf_datetime.parse(s);
        }
        if (Pattern.matches(pattern_second,s)) {
            return new Date(Long.parseLong(s)*1000);
        }
        if (Pattern.matches(pattern_millsecond,s)) {
            return new Date(Long.parseLong(s));
        }
        throw  new CustomException("未能识别的日期格式");
    }

    @Override
    public String print(Date date, Locale locale) {
        return sdf_datetime.format(date);
    }
}
