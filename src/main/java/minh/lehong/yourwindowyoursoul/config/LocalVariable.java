package minh.lehong.yourwindowyoursoul.config;

import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class LocalVariable {
    Locale locale;

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
