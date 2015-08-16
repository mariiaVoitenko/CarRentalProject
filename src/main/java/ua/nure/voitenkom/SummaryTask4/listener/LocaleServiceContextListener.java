package ua.nure.voitenkom.SummaryTask4.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.util.Attributes;
import ua.nure.voitenkom.SummaryTask4.service.ServiceConstant;
import ua.nure.voitenkom.SummaryTask4.service.locale.CookieLocaleService;
import ua.nure.voitenkom.SummaryTask4.service.locale.ILocaleService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static ua.nure.voitenkom.SummaryTask4.validation.ValidationManager.isNull;

@WebListener
public class LocaleServiceContextListener implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger(LocaleServiceContextListener.class);
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
        String localeServiceType = context.getServletContext().getInitParameter(Attributes.LANG_FILTER_TYPE);
        ILocaleService localeService = getLocaleService(localeServiceType, appLocales, new Locale(defaultLocale));
        context.getServletContext().setAttribute(ServiceConstant.LOCALE_SERVICE_CONTEXT, localeService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

    private ILocaleService getLocaleService(String localeServiceType, List<String> appLocales, Locale defaultLocale) {
        logger.debug("Config Locale Type: {}", localeServiceType);
        if (isNull(localeServiceType)) {
            logger.warn("Can't define locale type. Set Default locale type [Cookie]");
            return new CookieLocaleService(appLocales, defaultLocale, cookieTimeout);
        }
        switch (localeServiceType) {
            case "Cookie":
                return new CookieLocaleService(appLocales, defaultLocale, cookieTimeout);
            default: {
                logger.error("Locale type doesn't exist");
                throw new IllegalStateException();
            }
        }
    }
}
