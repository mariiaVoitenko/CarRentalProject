package ua.nure.voitenkom.SummaryTask4.service.locale;

import ua.nure.voitenkom.SummaryTask4.service.ServiceConstant;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

import static ua.nure.voitenkom.SummaryTask4.validation.ValidationManager.isNull;

/**
 * @author Mariia Voitenko
 */
public class LocaleService implements ILocaleService {

    private final int expiry;
    private final List<String> applicationLocales;
    private final Locale defaultLocale;

    public LocaleService(List<String> locales, Locale defaultLocale, int expiry) {
        this.applicationLocales = locales;
        this.defaultLocale = defaultLocale;
        this.expiry = expiry;
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

    @Override
    public void saveLocale(Locale locale, HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie(ServiceConstant.COOKIES_LOCALE, locale.getLanguage());
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(expiry);
        response.addCookie(cookie);
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

    private Locale getLocaleFromStorage(HttpServletRequest request) {
        Cookie cookie = getCookie(request, ServiceConstant.COOKIES_LOCALE);
        return cookie != null ? new Locale(cookie.getValue()) : null;
    }

    private Cookie getCookie(HttpServletRequest request, String name) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }

}
