package ua.nure.voitenkom.SummaryTask4.listener;

import ua.nure.voitenkom.SummaryTask4.service.ServiceConstant;
import ua.nure.voitenkom.SummaryTask4.service.locale.ILocaleService;
import ua.nure.voitenkom.SummaryTask4.service.locale.LocaleService;
import ua.nure.voitenkom.SummaryTask4.util.Attributes;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static ua.nure.voitenkom.SummaryTask4.validation.ValidationManager.isNull;

/**
 * @author Mariia Voitenko
 */
@WebListener
public class LocaleServiceContextListener implements ServletContextListener {

    private int cookieTimeout;

    @Override
    public void contextInitialized(ServletContextEvent context) {
        String localesParams = context.getServletContext().getInitParameter(Attributes.APP_LOCALES);
        String defaultLocale = context.getServletContext().getInitParameter(Attributes.DEFAULT_LOCALE);
        if (isNull(localesParams) || isNull(defaultLocale)) {
            throw new IllegalArgumentException("No Locales");
        }
        List<String> appLocales = new ArrayList<>();
        Collections.addAll(appLocales, localesParams.split(","));
        cookieTimeout = Integer.parseInt(context.getServletContext().getInitParameter(Attributes.COOKIE_LANG_TIMEOUT));
        ILocaleService localeService = getLocaleService(appLocales, new Locale(defaultLocale));
        context.getServletContext().setAttribute(ServiceConstant.LOCALE_SERVICE_CONTEXT, localeService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

    private ILocaleService getLocaleService(List<String> appLocales, Locale defaultLocale) {
        return new LocaleService(appLocales, defaultLocale, cookieTimeout);
    }

}
