package ua.nure.voitenkom.SummaryTask4.formbean;

import ua.nure.voitenkom.SummaryTask4.db.entity.Check;
import ua.nure.voitenkom.SummaryTask4.db.entity.Decline;
import ua.nure.voitenkom.SummaryTask4.db.entity.User;

public class RentFormBean {

    private int id;
    private boolean isDriven;
    private CarFormBean car;
    private Check check;
    private Decline decline;
    private String startDate;
    private String endDate;
    private User user;
    private boolean isReturned;

    public RentFormBean(boolean isDriven, CarFormBean car, Check check, Decline decline, String startDate, String endDate, boolean isReturned, int id) {
        this.isDriven = isDriven;
        this.car = car;
        this.check = check;
        this.decline = decline;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isReturned = isReturned;
        this.id = id;
    }

    public RentFormBean(boolean isDriven, CarFormBean car, Check check, Decline decline, String startDate, String endDate, User user, int id, boolean isReturned) {
        this.isDriven = isDriven;
        this.car = car;
        this.check = check;
        this.decline = decline;
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
        this.id = id;
        this.isReturned = isReturned;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isDriven() {
        return isDriven;
    }

    public void setIsDriven(boolean isDriven) {
        this.isDriven = isDriven;
    }

    public CarFormBean getCar() {
        return car;
    }

    public void setCar(CarFormBean car) {
        this.car = car;
    }

    public Check getCheck() {
        return check;
    }

    public void setCheck(Check check) {
        this.check = check;
    }

    public Decline getDecline() {
        return decline;
    }

    public void setDecline(Decline decline) {
        this.decline = decline;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void setIsReturned(boolean isReturned) {
        this.isReturned = isReturned;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RentFormBean{");
        sb.append("id=").append(id);
        sb.append(", isDriven=").append(isDriven);
        sb.append(", car=").append(car);
        sb.append(", check=").append(check);
        sb.append(", decline=").append(decline);
        sb.append(", startDate='").append(startDate).append('\'');
        sb.append(", endDate='").append(endDate).append('\'');
        sb.append(", user=").append(user);
        sb.append(", isReturned=").append(isReturned);
        sb.append('}');
        return sb.toString();
    }
}
