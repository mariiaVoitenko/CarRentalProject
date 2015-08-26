package ua.nure.voitenkom.SummaryTask4.formbean;

/**
 * Special class proceed data when user registers
 *
 * @author Mariia Voitenko
 */
public class RegistrationFormBean extends LoginFormBean {

    private String passportNumber;
    private String fullName;
    private String repeatedPassword;

    public String getRepeatedPassword() {
        return repeatedPassword;
    }

    public void setRepeatedPassword(String repeatedPassword) {
        this.repeatedPassword = repeatedPassword;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }    public RegistrationFormBean(String login, String password, String passportNumber, String fullName, String repeatedPassword) {
        super(login, password);
        this.passportNumber = passportNumber;
        this.fullName = fullName;
        this.repeatedPassword = repeatedPassword;
    }

}
