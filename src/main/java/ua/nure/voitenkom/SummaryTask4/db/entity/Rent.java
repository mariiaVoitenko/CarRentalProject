package ua.nure.voitenkom.SummaryTask4.db.entity;

/**
 * @author MariiaVoitenko
 */

import java.sql.Timestamp;

public class Rent extends Entity {

    private boolean isDriven;
    private int carId;
    private int userId;
    private int declineId;
    private int checkId;
    private Timestamp startDate;
    private Timestamp endDate;
    private boolean isReturned;
    private boolean isApproved;
    private boolean isFinished;

    public Rent(int id, boolean isDriven, int carId, int userId, int declineId, int checkId, Timestamp startDate,
                Timestamp endDate, boolean isReturned, boolean isApproved, boolean isFinished) {
        this.setId(id);
        this.carId = carId;
        this.isDriven = isDriven;
        this.userId = userId;
        this.declineId = declineId;
        this.checkId = checkId;
        this.startDate = new Timestamp(startDate.getTime());
        this.endDate = new Timestamp(endDate.getTime());
        this.isReturned = isReturned;
        this.isApproved = isApproved;
        this.isFinished = isFinished;
    }

    public Rent(boolean isDriven, int carId, int userId, int checkId, Timestamp startDate, Timestamp endDate) {
        this.carId = carId;
        this.isDriven = isDriven;
        this.userId = userId;
        this.checkId = checkId;
        this.startDate = new Timestamp(startDate.getTime());
        this.endDate = new Timestamp(endDate.getTime());
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
        this.startDate = new Timestamp(startDate.getTime());
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = new Timestamp(endDate.getTime());
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void setIsReturned(boolean isReturned) {
        this.isReturned = isReturned;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setIsApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setIsFinished(boolean isFinished) {
        this.isFinished = isFinished;
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
        sb.append(", isReturned=").append(isReturned);
        sb.append(", isApproved=").append(isApproved);
        sb.append(", isFinished=").append(isFinished);
        sb.append('}');
        return sb.toString();
    }

}
