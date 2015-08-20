package filter;

import org.junit.Before;
import org.junit.Test;
import org.powermock.api.support.membermodification.MemberModifier;
import ua.nure.voitenkom.SummaryTask4.filter.LocaleFilter;
import ua.nure.voitenkom.SummaryTask4.service.locale.ILocaleService;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

import static org.mockito.Mockito.*;

public class LocaleFilterTest {

    private LocaleFilter localeFilter;
    private ILocaleService localeService;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private FilterChain chain;

    @Before
    public void setUp() throws Exception {
        localeService = mock(ILocaleService.class);
        localeFilter = new LocaleFilter();
        MemberModifier.field(LocaleFilter.class, "localeService").set(localeFilter, localeService);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        chain = mock(FilterChain.class);
    }

    @Test
    public void testLocaleFilter() throws Exception {
        Locale locale = new Locale("ru");
        when(localeService.getLocale(request)).thenReturn(locale);
        localeFilter.doFilter(request, response, chain);
        verify(localeService).getLocale(request);
        verify(localeService).saveLocale(locale, request, response);
    }
}
