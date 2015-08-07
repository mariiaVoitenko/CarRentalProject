package ua.nure.voitenkom.SummaryTask4.servlets;

import ua.nure.voitenkom.SummaryTask4.Attributes;
import ua.nure.voitenkom.SummaryTask4.db.entity.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static ua.nure.voitenkom.SummaryTask4.validation.ValidationManager.isNotNull;

public abstract class AuthenticationServlet extends HttpServlet {

    protected void authorize(User user, HttpServletRequest request) {
        HttpSession session = request.getSession();
        setUserInfoAttributes(user, session);
    }

    private void setUserInfoAttributes(User user, HttpSession session) {
        session.setAttribute(Attributes.USER_ID, user.getId());
        session.setAttribute(Attributes.FULL_NAME, user.getFullName());
        session.setAttribute(Attributes.PHOTO, user.getPhotoPath());
    }

    protected Integer getAuthUserId(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return isNotNull(session) ? (Integer) session.getAttribute(Attributes.USER_ID) : null;
    }
}
