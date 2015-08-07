package ua.nure.voitenkom.SummaryTask4.validation;

import java.io.Serializable;
import java.util.Map;

public interface IValidator<T> extends Serializable {

    Map<String, String> validate(T entity);

}
