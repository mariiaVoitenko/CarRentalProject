package ua.nure.voitenkom.SummaryTask4.service.locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;

public class CookieLocaleService extends AbstractLocaleService {

    private static final String COOKIES_LOCALE = "locale";
    private final int expiry;

    public CookieLocaleService(List<String> locales, Locale defaultLocale, int expiry) {
        super(locales, defaultLocale);
        this.expiry = expiry;
    }

    @Override
    protected Locale getLocaleFromStorage(HttpServletRequest request) {
        Cookie cookie = getCookie(request, COOKIES_LOCALE);
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

    @Override
    public void saveLocale(Locale locale, HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie(COOKIES_LOCALE, locale.getLanguage());
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(expiry);
        response.addCookie(cookie);
    }

}
