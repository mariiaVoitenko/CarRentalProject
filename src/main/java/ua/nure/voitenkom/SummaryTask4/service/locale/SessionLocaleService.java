package ua.nure.voitenkom.SummaryTask4.service.locale;

import ua.nure.voitenkom.SummaryTask4.Attributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;

public class SessionLocaleService extends AbstractLocaleService {

    public SessionLocaleService(List<String> locales, Locale defaultLocale) {
        super(locales, defaultLocale);
    }

    @Override
    public void saveLocale(Locale locale, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setAttribute(Attributes.USER_LOCALE, locale);
    }

    @Override
    protected Locale getLocaleFromStorage(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (Locale) session.getAttribute(Attributes.USER_LOCALE);
    }

}
