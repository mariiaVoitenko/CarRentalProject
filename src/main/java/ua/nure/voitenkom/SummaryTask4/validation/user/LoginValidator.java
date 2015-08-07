package ua.nure.voitenkom.SummaryTask4.validation.user;

import ua.nure.voitenkom.SummaryTask4.formbean.LoginFormBean;
import ua.nure.voitenkom.SummaryTask4.validation.IValidator;

import java.util.HashMap;
import java.util.Map;

import static ua.nure.voitenkom.SummaryTask4.validation.ValidationManager.ifMail;
import static ua.nure.voitenkom.SummaryTask4.validation.ValidationManager.ifNumber;
import static ua.nure.voitenkom.SummaryTask4.validation.ValidationManager.isEmpty;

public class LoginValidator implements IValidator<LoginFormBean> {

    @Override
    public Map<String, String> validate(LoginFormBean loginFormBean) {
        Map<String, String> errorMap = new HashMap<>();
        validateLogin(loginFormBean.getLogin(), errorMap);
        validatePassword(loginFormBean.getPassword(), errorMap);
        return errorMap;
    }

    private void validateLogin(String login, Map<String, String> errorMap) {
        if (isEmpty(login) && !ifMail(login) && !ifNumber(login)) {
            errorMap.put("login", "wrongLogin");
        }
    }

    private void validatePassword(String password, Map<String, String> errorMap) {
        if (isEmpty(password) && password.length() < 8) {
            errorMap.put("login", "wrongPassword");
        }
    }
}
