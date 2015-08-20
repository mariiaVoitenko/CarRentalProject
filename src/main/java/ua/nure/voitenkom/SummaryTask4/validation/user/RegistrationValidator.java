package ua.nure.voitenkom.SummaryTask4.validation.user;

import ua.nure.voitenkom.SummaryTask4.formbean.LoginFormBean;
import ua.nure.voitenkom.SummaryTask4.formbean.RegistrationFormBean;
import ua.nure.voitenkom.SummaryTask4.validation.IValidator;

import java.util.HashMap;
import java.util.Map;

import static ua.nure.voitenkom.SummaryTask4.validation.ValidationManager.isEmpty;

public class RegistrationValidator implements IValidator<RegistrationFormBean> {

    @Override
    public Map<String, String> validate(RegistrationFormBean registrationFormBean) {
        LoginValidator loginValidator = new LoginValidator();
        Map<String, String> errorMap = new HashMap<>();
        loginValidator.validateLogin(registrationFormBean.getLogin(), errorMap);
        loginValidator.validatePassword(registrationFormBean.getPassword(), errorMap);
        checkEmptyFullName(registrationFormBean.getFullName(),errorMap);
        checkShortFullName(registrationFormBean.getFullName(),errorMap);
        return errorMap;
    }

    private Map<String, String> checkEmptyFullName(String fullName, Map<String, String> errorMap) {
        if (isEmpty(fullName)) {
            errorMap.put("fullName", "empty fullName.");
        }
        return errorMap;
    }

    private Map<String, String> checkShortFullName(String fullName, Map<String, String> errorMap) {
        if (fullName.length()<5) {
            String error = errorMap.get("fullName");
            errorMap.put("fullName", error + "short fullName.");
        }
        return errorMap;
    }
}