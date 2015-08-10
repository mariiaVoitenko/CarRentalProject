package ua.nure.voitenkom.SummaryTask4.servlets.admin;

import ua.nure.voitenkom.SummaryTask4.Attributes;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static ua.nure.voitenkom.SummaryTask4.validation.ValidationManager.isNotNull;

public abstract class AdminServlet extends HttpServlet {

    protected Integer getRoleId(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return isNotNull(session) ? (Integer) session.getAttribute(Attributes.ROLE_ID) : null;
    }
}
