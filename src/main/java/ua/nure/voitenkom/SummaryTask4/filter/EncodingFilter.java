package ua.nure.voitenkom.SummaryTask4.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Maria on 05.08.2015.
 */
@WebFilter(urlPatterns = "/*")
public class EncodingFilter extends BaseFilter {

    private static final String DEFAULT_ENCODING = "UTF-8";

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(DEFAULT_ENCODING);
        chain.doFilter(request, response);
    }
}
