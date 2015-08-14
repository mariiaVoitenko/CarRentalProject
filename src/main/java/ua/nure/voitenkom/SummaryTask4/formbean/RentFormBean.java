package ua.nure.voitenkom.SummaryTask4.formbean;

import ua.nure.voitenkom.SummaryTask4.db.entity.Check;
import ua.nure.voitenkom.SummaryTask4.db.entity.Decline;

public class RentFormBean {

    private boolean isDriven;
    private CarFormBean car;
    private Check check;
    private Decline  decline;
    private String startDate;
    private String endDate;

    public RentFormBean(boolean isDriven, CarFormBean car, Check check, Decline decline, String startDate, String endDate) {
        this.isDriven = isDriven;
        this.car = car;
        this.check = check;
        this.decline = decline;
        this.startDate = startDate;
        this.endDate = endDate;
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
}
