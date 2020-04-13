package zdc.enterprise.config.Formatter;

import org.springframework.format.Formatter;
import org.springframework.util.StringUtils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalTimeFormatter implements Formatter<LocalTime> {
    @Override
    public String print(LocalTime localTime, Locale locale) {
        return localTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
    @Override
    public LocalTime parse(String s, Locale locale) {
        if(StringUtils.isEmpty(s)){
            return null;
        }
        return LocalTime.parse(s, DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
}
