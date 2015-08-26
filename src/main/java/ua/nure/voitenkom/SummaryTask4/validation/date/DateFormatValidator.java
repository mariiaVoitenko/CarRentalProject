package ua.nure.voitenkom.SummaryTask4.validation.date;

import ua.nure.voitenkom.SummaryTask4.validation.IValidator;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Mariia Voitenko
 */
public class DateFormatValidator implements IValidator<String> {

    private static final String DATE_PATTERN =
            "^\\d{4}-\\d{2}-\\d{2}$";
    private Pattern pattern;
    private Matcher matcher;

    public DateFormatValidator() {
        pattern = Pattern.compile(DATE_PATTERN);
    }

    @Override
    public Map<String, String> validate(String date) {
        Map<String, String> errorMap = new HashMap<>();
        matcher = pattern.matcher(date);
        if (!matcher.matches()) {
            errorMap.put("error", "Date is not acceptable");
            return errorMap;
        }
        return errorMap;
    }
}
