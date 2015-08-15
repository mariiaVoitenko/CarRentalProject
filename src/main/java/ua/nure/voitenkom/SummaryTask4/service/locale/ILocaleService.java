package ua.nure.voitenkom.SummaryTask4.service.locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public interface ILocaleService {

    Locale getLocale(HttpServletRequest request);

    void saveLocale(Locale locale, HttpServletRequest request, HttpServletResponse response);

}
