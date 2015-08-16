package ua.nure.voitenkom.SummaryTask4.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.SkipPageException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.text.DecimalFormat;

public class PriceFormattingTag extends SimpleTagSupport {

    private String format;
    private String price;

    public PriceFormattingTag() {
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public void doTag() throws JspException, IOException {
        try {
            double amount = Double.parseDouble(price);
            DecimalFormat formatter = new DecimalFormat(format);
            String formattedNumber = formatter.format(amount);
            getJspContext().getOut().write(formattedNumber);
        } catch (Exception e) {
            e.printStackTrace();
            // stop page from loading further by throwing SkipPageException
            throw new SkipPageException("Exception in formatting " + price
                    + " with format " + format);
        }
    }

}
