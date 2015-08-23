package ua.nure.voitenkom.SummaryTask4.db.converter;

import ua.nure.voitenkom.SummaryTask4.criteria.Criteria;

public interface ISQLBuilder {

    String getSQL(Criteria criteria);

}
