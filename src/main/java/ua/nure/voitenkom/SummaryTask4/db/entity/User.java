package ua.nure.voitenkom.SummaryTask4.db.entity;

/**
 * Created by Maria on 30.07.2015.
 */
public class User extends Entity{

    private String fullName;
    private boolean isBlocked;
    private boolean isRegistered;
    private String registrationToken;
    private String passportNumber;
    private int roleId;
    private String photoPath;
    private String password;

    public User(int id, String fullName, boolean isBlocked, boolean isRegistered, String registrationToken, String passportNumber, int roleId, String photoPath, String password) {
        this.setId(id);
        this.fullName = fullName;
        this.isBlocked = isBlocked;
        this.isRegistered = isRegistered;
        this.registrationToken = registrationToken;
        this.passportNumber = passportNumber;
        this.roleId = roleId;
        this.photoPath = photoPath;
        this.password = password;
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
        sb.append('}');
        return sb.toString();
    }
}
