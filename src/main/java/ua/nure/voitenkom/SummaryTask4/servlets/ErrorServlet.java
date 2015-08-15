package ua.nure.voitenkom.SummaryTask4.servlets;

import ua.nure.voitenkom.SummaryTask4.Attributes;
import ua.nure.voitenkom.SummaryTask4.PageNames;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorServlet extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(Attributes.MESSAGE, "Sorry, something went wrong. Please redo your actions again");
        RequestDispatcher requestDispatcher = request
                .getRequestDispatcher(PageNames.ERROR_PAGE);
        requestDispatcher.forward(request, response);
    }

}
