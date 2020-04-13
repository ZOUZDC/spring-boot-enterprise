package zdc.enterprise.config.Formatter;

import org.springframework.format.Formatter;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalDateFormatter implements Formatter<LocalDate> {

    @Override
    public String print(LocalDate localDate, Locale locale) {
        return localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
    @Override
    public LocalDate parse(String s, Locale locale) {
        if(StringUtils.isEmpty(s)){
            return null;
        }
        return LocalDate.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
