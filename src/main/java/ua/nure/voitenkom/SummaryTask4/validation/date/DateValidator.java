package ua.nure.voitenkom.SummaryTask4.validation.date;

import ua.nure.voitenkom.SummaryTask4.validation.IValidator;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mariia Voitenko
 */
public class DateValidator implements IValidator<Date> {

    @Override
    public Map<String, String> validate(Date date) {
        Map<String, String> errorMap = new HashMap<>();
        if (date.before(new Date())) {
            errorMap.put("error", "You can't rent cars in past time");
        }
        return errorMap;
    }

}
