package ua.nure.voitenkom.SummaryTask4.db.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class Rent extends Entity {

    private boolean isDriven;
    private int carId;
    private int userId;
    private int declineId;
    private int checkId;
    private Timestamp startDate;
    private Timestamp endDate;

    public Rent(int id, boolean isDriven,  int carId, int userId, int declineId, int checkId, Timestamp startDate, Timestamp endDate) {
        this.setId(id);
        this.carId = carId;
        this.isDriven = isDriven;
        this.userId = userId;
        this.declineId = declineId;
        this.checkId = checkId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean isDriven() {
        return isDriven;
    }

    public void setIsDriven(boolean isDriven) {
        this.isDriven = isDriven;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDeclineId() {
        return declineId;
    }

    public void setDeclineId(int declineId) {
        this.declineId = declineId;
    }

    public int getCheckId() {
        return checkId;
    }

    public void setCheckId(int checkId) {
        this.checkId = checkId;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Rent{");
        sb.append("isDriven=").append(isDriven);
        sb.append(", carId=").append(carId);
        sb.append(", userId=").append(userId);
        sb.append(", declineId=").append(declineId);
        sb.append(", checkId=").append(checkId);
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append('}');
        return sb.toString();
    }
}
