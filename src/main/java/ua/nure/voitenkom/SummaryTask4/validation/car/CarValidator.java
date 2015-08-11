package ua.nure.voitenkom.SummaryTask4.validation.car;

import ua.nure.voitenkom.SummaryTask4.formbean.CarFormBean;
import ua.nure.voitenkom.SummaryTask4.validation.IValidator;

import java.util.HashMap;
import java.util.Map;

public class CarValidator implements IValidator<CarFormBean> {

    @Override
    public Map<String, String> validate(CarFormBean carFormBean) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap = validateModel(carFormBean.getModel(), errorMap);
        errorMap = validateSitsCount(carFormBean.getSitsCount(), errorMap);
        errorMap = validateLuggage(carFormBean.getBigLuggageCount(), errorMap);
        errorMap = validateLuggage(carFormBean.getSmallLuggageCount(), errorMap);
        errorMap = validateDoorsCount(carFormBean.getDoorsCount(), errorMap);
        return errorMap;
    }

    private Map<String, String> validateModel(String model, Map<String, String> errorMap) {
        if (model.isEmpty()) {
            errorMap.put("error", "Model name must be filled");
        }
        return errorMap;
    }

    private Map<String, String> validateSitsCount(int sitsCount, Map<String, String> errorMap) {
        if (sitsCount < 1 || sitsCount > 10) {
            String message = "Sits count must be more than 2 and less than 10";
            writeMessage(message, errorMap);
        }
        return errorMap;
    }

    private Map<String, String> validateLuggage(int luggage, Map<String, String> errorMap) {
        if (luggage > 10) {
            String message = "Luggage count must be less than 10";
            writeMessage(message, errorMap);
        }
        return errorMap;
    }

    private Map<String, String> validateDoorsCount(int doors, Map<String, String> errorMap) {
        if (doors < 2 || doors > 5) {
            String message = "Doors count must be less than 5 and more than 2";
            writeMessage(message, errorMap);
        }
        return errorMap;
    }

    private void writeMessage(String message, Map<String, String> errorMap) {
        String error = errorMap.get("error");
        if (error != null) {
            errorMap.put("error", error + message);
        } else {
            errorMap.put("error", message);
        }
    }
}
