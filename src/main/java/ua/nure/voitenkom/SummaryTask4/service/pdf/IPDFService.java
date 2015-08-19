package ua.nure.voitenkom.SummaryTask4.service.pdf;

import ua.nure.voitenkom.SummaryTask4.db.entity.Check;
import ua.nure.voitenkom.SummaryTask4.db.entity.Damage;
import ua.nure.voitenkom.SummaryTask4.db.entity.Rent;
import ua.nure.voitenkom.SummaryTask4.formbean.CarFormBean;

import java.util.List;

public interface IPDFService {

    void createPdf(String path, CarFormBean carFormBean, List<Damage> damages);

    String createFileName(int userId, int checkId);

    String createPath(String file);

    void createPdf(String path, CarFormBean carFormBean, Rent rent, Check check);

}
