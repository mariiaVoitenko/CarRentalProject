package ua.nure.voitenkom.SummaryTask4.validation.user;

import ua.nure.voitenkom.SummaryTask4.formbean.LoginFormBean;
import ua.nure.voitenkom.SummaryTask4.validation.IValidator;

import java.util.HashMap;
import java.util.Map;

import static ua.nure.voitenkom.SummaryTask4.validation.ValidationManager.*;

public class LoginValidator implements IValidator<LoginFormBean> {

    @Override
    public Map<String, String> validate(LoginFormBean loginFormBean) {
        Map<String, String> errorMap = new HashMap<>();
        validateLogin(loginFormBean.getLogin(), errorMap);
        validatePassword(loginFormBean.getPassword(), errorMap);
        return errorMap;
    }

    public Map<String, String> validateLogin(String login, Map<String, String> errorMap) {
        errorMap = checkEmptyLogin(login, errorMap);
        errorMap = checkMail(login, errorMap);
        errorMap = checkOnlyNumbers(login, errorMap);
        return errorMap;
    }

    public Map<String, String> validatePassword(String password, Map<String, String> errorMap) {
        errorMap = checkEmptyPassword(password, errorMap);
        errorMap = checkShortPassword(password, errorMap);
        return errorMap;
    }

    private Map<String, String> checkEmptyLogin(String login, Map<String, String> errorMap) {
        if (isEmpty(login)) {
            errorMap.put("login", "Empty Login.");
        }
        return errorMap;
    }

    private Map<String, String> checkMail(String login, Map<String, String> errorMap) {
        if (!ifMail(login)) {
            String error = errorMap.get("login");
            errorMap.put("login", error + "Login is not email.");
        }
        return errorMap;
    }

    private Map<String, String> checkOnlyNumbers(String login, Map<String, String> errorMap) {
        if (ifNumber(login)) {
            String error = errorMap.get("login");
            errorMap.put("login", error + "Login contains only numbers.");
        }
        return errorMap;
    }

    private Map<String, String> checkEmptyPassword(String password, Map<String, String> errorMap) {
        if (isEmpty(password)) {
            errorMap.put("password", "Empty password.");
        }
        return errorMap;
    }

    private Map<String, String> checkShortPassword(String password, Map<String, String> errorMap) {
        if (password.length() < 8) {
            String error = errorMap.get("password");
            errorMap.put("password", error + "Short password");
        }
        return errorMap;
    }

}