package ua.nure.voitenkom.SummaryTask4.formbean;

public class RegistrationFormBean extends LoginFormBean{

    private String passportNumber;
    private String fullName;
    private String photoPath;

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public RegistrationFormBean(String login, String password, String passportNumber, String fullName, String photoPath) {
        super(login, password);
        this.passportNumber = passportNumber;
        this.fullName = fullName;
        this.photoPath = photoPath;
    }
}
