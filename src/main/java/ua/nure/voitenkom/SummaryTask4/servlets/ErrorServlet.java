package ua.nure.voitenkom.SummaryTask4.servlets;

import ua.nure.voitenkom.SummaryTask4.util.Attributes;
import ua.nure.voitenkom.SummaryTask4.util.PageNames;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Mariia Voitenko
 */
public class ErrorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(Attributes.MESSAGE, "Sorry, something went wrong. Please redo your actions again");
        request.getRequestDispatcher(PageNames.ERROR_PAGE).forward(request, response);
    }

}
