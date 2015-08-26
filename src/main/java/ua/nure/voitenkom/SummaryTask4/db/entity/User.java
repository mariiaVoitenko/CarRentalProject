package ua.nure.voitenkom.SummaryTask4.db.entity;

import java.sql.Timestamp;

/**
 * @author MariiaVoitenko
 */
public class User extends Entity {

    private String fullName;
    private boolean isBlocked;
    private boolean isRegistered;
    private String registrationToken;
    private String passportNumber;
    private int roleId;
    private String photoPath;
    private String password;
    private String login;
    private Timestamp registrationTime;

    public User(int id, String fullName, boolean isBlocked, boolean isRegistered, String registrationToken,
                String passportNumber, int roleId, String photoPath, String password, String login,
                Timestamp registrationTime) {
        this.setId(id);
        this.fullName = fullName;
        this.isBlocked = isBlocked;
        this.isRegistered = isRegistered;
        this.registrationToken = registrationToken;
        this.passportNumber = passportNumber;
        this.roleId = roleId;
        this.photoPath = photoPath;
        this.password = password;
        this.login = login;
        this.registrationTime = new Timestamp(registrationTime.getTime());
    }

    public User(String fullName, String registrationToken, String passportNumber, int roleId,
                String password, String login, Timestamp registrationTime) {
        this.fullName = fullName;
        this.registrationToken = registrationToken;
        this.passportNumber = passportNumber;
        this.roleId = roleId;
        this.password = password;
        this.login = login;
        this.registrationTime = new Timestamp(registrationTime.getTime());
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public boolean isRegistered() {
        return isRegistered;
    }

    public void setIsRegistered(boolean isRegistered) {
        this.isRegistered = isRegistered;
    }

    public String getRegistrationToken() {
        return registrationToken;
    }

    public void setRegistrationToken(String registrationToken) {
        this.registrationToken = registrationToken;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Timestamp getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Timestamp registrationTime) {
        this.registrationTime = new Timestamp(registrationTime.getTime());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id='").append(this.getId()).append('\'');
        sb.append("fullName='").append(fullName).append('\'');
        sb.append(", isBlocked=").append(isBlocked);
        sb.append(", isRegistered=").append(isRegistered);
        sb.append(", registrationToken='").append(registrationToken).append('\'');
        sb.append(", passportNumber='").append(passportNumber).append('\'');
        sb.append(", roleId=").append(roleId);
        sb.append(", photoPath='").append(photoPath).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", login='").append(login).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
