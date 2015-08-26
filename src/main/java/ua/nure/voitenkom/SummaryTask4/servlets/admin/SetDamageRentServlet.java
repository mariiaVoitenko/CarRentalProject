package ua.nure.voitenkom.SummaryTask4.servlets.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.voitenkom.SummaryTask4.db.entity.Check;
import ua.nure.voitenkom.SummaryTask4.db.entity.Damage;
import ua.nure.voitenkom.SummaryTask4.db.entity.DamageCheck;
import ua.nure.voitenkom.SummaryTask4.db.entity.Rent;
import ua.nure.voitenkom.SummaryTask4.formbean.CarFormBean;
import ua.nure.voitenkom.SummaryTask4.service.ServiceConstant;
import ua.nure.voitenkom.SummaryTask4.service.account.MailService;
import ua.nure.voitenkom.SummaryTask4.service.car.ICarService;
import ua.nure.voitenkom.SummaryTask4.service.check.ICheckService;
import ua.nure.voitenkom.SummaryTask4.service.damage.IDamageService;
import ua.nure.voitenkom.SummaryTask4.service.damagecheck.IDamageCheckService;
import ua.nure.voitenkom.SummaryTask4.service.pdf.IPDFService;
import ua.nure.voitenkom.SummaryTask4.service.rent.IRentService;
import ua.nure.voitenkom.SummaryTask4.service.user.IUserService;
import ua.nure.voitenkom.SummaryTask4.util.Attributes;
import ua.nure.voitenkom.SummaryTask4.util.Mappings;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mariia Voitenko
 */
public class SetDamageRentServlet extends AdminServlet {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationServlet.class);
    private IRentService rentService;
    private IDamageCheckService damageCheckService;
    private ICheckService checkService;
    private IDamageService damageService;
    private IUserService userService;
    private ICarService carService;
    private IPDFService pdfService;
    private String host;
    private String port;
    private String userEmail;
    private String password;

    @Override
    public void init() throws ServletException {
        rentService = (IRentService) getServletContext().getAttribute(ServiceConstant.RENT_SERVICE_CONTEXT);
        damageCheckService = (IDamageCheckService) getServletContext().getAttribute(ServiceConstant.DAMAGE_CHECK_SERVICE_CONTEXT);
        checkService = (ICheckService) getServletContext().getAttribute(ServiceConstant.CHECK_SERVICE_CONTEXT);
        damageService = (IDamageService) getServletContext().getAttribute(ServiceConstant.DAMAGE_SERVICE_CONTEXT);
        userService = (IUserService) getServletContext().getAttribute(ServiceConstant.USER_SERVICE_CONTEXT);
        carService = (ICarService) getServletContext().getAttribute(ServiceConstant.CAR_SERVICE_CONTEXT);
        pdfService = (IPDFService) getServletContext().getAttribute(ServiceConstant.PDF_SERVICE_CONTEXT);
        host = getServletContext().getInitParameter(ServiceConstant.HOST_PARAM);
        port = getServletContext().getInitParameter(ServiceConstant.PORT_PARAM);
        userEmail = getServletContext().getInitParameter(ServiceConstant.USER_EMAIL_PARAM);
        password = getServletContext().getInitParameter(ServiceConstant.USER_PASSWORD_PARAM);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!checkManagerRole(request, response)) return;

        int id = Integer.parseInt(request.getParameter(Attributes.ID));
        Rent rent = rentService.selectById(id);
        int checkId = rent.getCheckId();

        String[] damages = request.getParameterValues(Attributes.DAMAGE);
        int[] damagesIds = new int[damages.length];

        int total = getTotal(checkId, damages, damagesIds);

        rentService.updateFinishedState(id);
        Check check = checkService.selectById(checkId);
        checkService.updateSum(check, total);

        List<Damage> damageInformation = getDamageNames(damagesIds);
        String path = createPDF(rent, checkId, damageInformation);
        sendMail(rent, path);

        logger.debug("Rent with id {} was finished", id);
        response.sendRedirect(Mappings.ADMIN_MAPPING + Mappings.RETURNED_CARS_MAPPING);
    }

    private List<Damage> getDamageNames(int[] damagesIds) {
        List<Damage> damageInformation = new ArrayList<>();
        for (int i = 0; i < damagesIds.length; i++) {
            damageInformation.add(damageService.findById(damagesIds[i]));
        }
        return damageInformation;
    }

    private int getTotal(int checkId, String[] damages, int[] damagesIds) {
        int total = 0;
        for (int i = 0; i < damages.length; i++) {
            damagesIds[i] = Integer.parseInt(damages[i]);
            DamageCheck damageCheck = new DamageCheck(damagesIds[i], checkId);
            damageCheckService.insert(damageCheck);
            int damageSum = damageService.selectSumById(damagesIds[i]);
            total += damageSum;
        }
        return total;
    }

    private String createPDF(Rent rent, int checkId, List<Damage> damageInformation) {
        CarFormBean carFormBean = carService.getFullCarInformationById(rent.getCarId());
        String fileName = pdfService.createFileName(rent.getUserId(), checkId);
        String path = pdfService.createPath(fileName);
        pdfService.createDamagePdf(path, carFormBean, damageInformation);
        return path;
    }

    private void sendMail(Rent rent, String path) {
        String login = userService.selectById(rent.getUserId()).getLogin();
        try {
            MailService.sendEmailWithDocument(host, port, login, userEmail, password, path);
        } catch (MessagingException e) {
            logger.error("Unable to send mail");
        }
    }

}
