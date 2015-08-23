package ua.nure.voitenkom.SummaryTask4.db.converter;

import ua.nure.voitenkom.SummaryTask4.criteria.Criteria;
import ua.nure.voitenkom.SummaryTask4.db.StatementsContainer;

import static ua.nure.voitenkom.SummaryTask4.validation.ValidationManager.isNotNull;

public class SQLBuilder implements ISQLBuilder {

    @Override
    public String getSQL(Criteria criteria) {
        StringBuilder stringBuilder = new StringBuilder(StatementsContainer.SQL_SELECT_CARFORMBEAN_FIELDS);
        stringBuilder = appendBrand(criteria,stringBuilder);
        stringBuilder = appendClassType(criteria, stringBuilder);
        stringBuilder = appendSortingType(criteria, stringBuilder);
        return stringBuilder.toString();
    }

    private StringBuilder appendBrand(Criteria criteria, StringBuilder stringBuilder){
        if (isNotNull(criteria.getBrand())) {
            stringBuilder.append(" AND ");
            stringBuilder.append(criteria.getBrand()).append(" = ").append(criteria.getBrandValue());
        }
        return stringBuilder;
    }

    private StringBuilder appendClassType(Criteria criteria, StringBuilder stringBuilder){
        if (isNotNull(criteria.getClassType())) {
            stringBuilder.append(" AND ");
            stringBuilder.append(criteria.getClassType()).append(" = ").append(criteria.getClassValue());
        }
        return stringBuilder;
    }

    private StringBuilder appendSortingType(Criteria criteria, StringBuilder stringBuilder){
        if (isNotNull(criteria.getSortType())) {
            stringBuilder.append(" ORDER BY ");
            stringBuilder.append(criteria.getSortColumn()).append(" ").append(criteria.getSortType());
        }
        return stringBuilder;
    }

}
