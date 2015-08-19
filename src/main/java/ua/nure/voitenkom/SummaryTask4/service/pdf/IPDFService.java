package ua.nure.voitenkom.SummaryTask4.service.pdf;

public interface IPDFService {

    public void createPdf(String file);

    String createFileName(int userId, int checkId);

    String createPath(String file);

}
