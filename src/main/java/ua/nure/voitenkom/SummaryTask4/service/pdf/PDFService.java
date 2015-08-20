package ua.nure.voitenkom.SummaryTask4.service.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.db.entity.Check;
import ua.nure.voitenkom.SummaryTask4.db.entity.Damage;
import ua.nure.voitenkom.SummaryTask4.db.entity.Rent;
import ua.nure.voitenkom.SummaryTask4.formbean.CarFormBean;
import static ua.nure.voitenkom.SummaryTask4.util.DateManager.timestampToString;
import java.io.FileOutputStream;
import java.util.*;
import java.util.List;

public class PDFService implements IPDFService {

    private final String folder;
    public static final String FORMAT = "pdf";
    private static final Logger logger = LoggerFactory.getLogger(PDFService.class);
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);

    public PDFService(String folder) {
        this.folder = folder;
    }

    @Override
    public String createFileName(int userId, int checkId) {
        return "user=" + userId + " & " + "check=" + checkId;
    }

    @Override
    public String createPath(String file) {
        return folder + "/" + file + "." + FORMAT;
    }

    @Override
    public void createPdf(String path, CarFormBean carFormBean, List<Damage> damages) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(path));
            document.open();
            addMetaData(document);
            addTitle(document, carFormBean);
            addContent(document, damages);
            document.close();
            logger.debug("PDF was created");
        } catch (Exception e) {
            logger.debug("Failed to create PDF");
        }
    }

    @Override
    public void createPdf(String path, CarFormBean carFormBean, Rent rent, Check check) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(path));
            document.open();
            addMetaData(document);
            addTitle(document, carFormBean);
            addContent(document, rent, check);
            document.close();
            logger.debug("PDF was created");
        } catch (Exception e) {
            logger.debug("Failed to create PDF");
        }
    }

    private void addMetaData(Document document) {
        document.addTitle("Check");
        document.addSubject("Check payment");
        document.addAuthor("Car Rental");
        document.addCreator("Car Rental");
    }

    private void addTitle(Document document, CarFormBean carFormBean) throws DocumentException {
        Paragraph preface = new Paragraph();
        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Check", catFont));

        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Car: ", redFont));
        preface.add(new Paragraph("Brand: " + carFormBean.getBrandName(), subFont));
        preface.add(new Paragraph("Model: " + carFormBean.getModel(), subFont));
        preface.add(new Paragraph("Color: " + carFormBean.getColorName(), subFont));
        preface.add(new Paragraph("Price: " + carFormBean.getPrice() + " / day", subFont));

        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Report generated by: Car Rental Management Department" + ", " + new Date(), smallBold));

        document.add(preface);
    }

    private void addContent(Document document, List<Damage> damages) throws DocumentException {
        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 1);
        createDamageTable(paragraph, damages);
        document.add(paragraph);
    }

    private void addContent(Document document, Rent rent, Check check) throws DocumentException {
        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 1);
        paragraph.add(new Paragraph("Rent time: " + timestampToString(rent.getStartDate()) + " - " + timestampToString(rent.getEndDate()), subFont));
        paragraph.add(new Paragraph("Total: " + check.getSum(), redFont));
        document.add(paragraph);
    }

    private void createDamageTable(Paragraph paragraph, List<Damage> damages) throws BadElementException {
        PdfPTable table = new PdfPTable(2);

        PdfPCell c1 = new PdfPCell(new Phrase("Damage"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Sum"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);

        int total = 0;
        for (Damage damage : damages) {
            int sum = damage.getSum();
            table.addCell(damage.getName());
            table.addCell(sum + "");
            total += sum;
        }
        table.addCell("Total: ");
        table.addCell(total + "");
        paragraph.add(table);
    }


    private void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

}
