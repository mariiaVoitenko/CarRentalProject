package ua.nure.voitenkom.SummaryTask4.formbean;

/**
 * Special class to proceed data when user authenticates
 *
 * @author Mariia Voitenko
 */
public class LoginFormBean {

    private String login;
    private String password;

    public LoginFormBean(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
