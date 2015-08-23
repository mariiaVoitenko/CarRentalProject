package ua.nure.voitenkom.SummaryTask4.filter;

import ua.nure.voitenkom.SummaryTask4.service.ServiceConstant;
import ua.nure.voitenkom.SummaryTask4.service.locale.ILocaleService;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Locale;

@WebFilter("/*")
public class LocaleFilter extends BaseFilter {

    private ILocaleService localeService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        localeService = (ILocaleService) filterConfig.getServletContext().getAttribute(ServiceConstant.LOCALE_SERVICE_CONTEXT);
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        final Locale lang = localeService.getLocale(request);
        HttpServletRequest langRequest = new LocaleRequestWrapper(lang, request);
        localeService.saveLocale(lang, request, response);
        chain.doFilter(langRequest, response);
    }

    private static class LocaleRequestWrapper extends HttpServletRequestWrapper {

        private final Locale lang;

        public LocaleRequestWrapper(Locale lang, HttpServletRequest request) {
            super(request);
            this.lang = lang;
        }

        @Override
        public Locale getLocale() {
            return lang;
        }

        @Override
        public Enumeration<Locale> getLocales() {
            return new Enumeration<Locale>() {
                private boolean next = true;

                @Override
                public boolean hasMoreElements() {
                    return next;
                }

                @Override
                public Locale nextElement() {
                    next = false;
                    return lang;
                }
            };
        }

    }

}