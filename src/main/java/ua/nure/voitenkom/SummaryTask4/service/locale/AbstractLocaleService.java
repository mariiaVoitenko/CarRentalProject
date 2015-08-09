package ua.nure.voitenkom.SummaryTask4.service.locale;

import ua.nure.voitenkom.SummaryTask4.service.ServiceConstant;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import static ua.nure.voitenkom.SummaryTask4.validation.ValidationManager.isNull;

public abstract class AbstractLocaleService implements LocaleService {

    private final List<String> applicationLocales;
    private final Locale defaultLocale;

    public AbstractLocaleService(List<String> locales, Locale defaultLocale) {
        this.applicationLocales = locales;
        this.defaultLocale = defaultLocale;
    }

    @Override
    public Locale getLocale(HttpServletRequest request) {
        String lang = request.getParameter(ServiceConstant.LANG_PARAM);
        if (isLocaleExists(lang)) {
            return new Locale(lang);
        }
        Locale userLocale = getLocaleFromStorage(request);
        if (isNull(userLocale) || !isLocaleExists(userLocale.getLanguage())) {
            userLocale = getAcceptedLocale(request);
        }
        return userLocale == null ? defaultLocale : userLocale;
    }

    private boolean isLocaleExists(String lang) {
        return applicationLocales.contains(lang);
    }

    private Locale getAcceptedLocale(HttpServletRequest request) {
        Locale userLocale = null;
        Enumeration<Locale> locales = request.getLocales();
        while (locales.hasMoreElements()) {
            Locale locale = locales.nextElement();
            if (isLocaleExists(locale.getCountry())) {
                userLocale = locale;
                break;
            }
        }
        return userLocale;
    }

    protected abstract Locale getLocaleFromStorage(HttpServletRequest request);
}
