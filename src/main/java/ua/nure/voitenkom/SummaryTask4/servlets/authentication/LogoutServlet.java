package ua.nure.voitenkom.SummaryTask4.servlets.authentication;

import ua.nure.voitenkom.SummaryTask4.util.PageNames;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ua.nure.voitenkom.SummaryTask4.validation.ValidationManager.isNotNull;

public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (isNotNull(session)) {
            session.invalidate();
        }
        response.sendRedirect(PageNames.MAIN_PAGE);
    }
}
