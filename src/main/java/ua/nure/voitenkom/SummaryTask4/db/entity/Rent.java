package ua.nure.voitenkom.SummaryTask4.db.entity;

/**
 * Created by Maria on 30.07.2015.
 */
public class Rent extends Entity {

    private boolean isDriven;
    private int days;
    private int carId;
    private int userId;
    private int declineId;
    private int checkId;

    public Rent(int id, boolean isDriven, int days, int carId, int userId, int declineId, int checkId) {
        this.setId(id);
        this.days = days;
        this.carId = carId;
        this.isDriven = isDriven;
        this.userId = userId;
        this.declineId = declineId;
        this.checkId = checkId;
    }

    public boolean isDriven() {
        return isDriven;
    }

    public void setIsDriven(boolean isDriven) {
        this.isDriven = isDriven;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Rent{");
        sb.append("id=").append(this.getId());
        sb.append("isDriven=").append(isDriven);
        sb.append(", days=").append(days);
        sb.append(", carId=").append(carId);
        sb.append(", userId=").append(userId);
        sb.append(", declineId=").append(declineId);
        sb.append(", checkId=").append(checkId);
        sb.append('}');
        return sb.toString();
    }
}
