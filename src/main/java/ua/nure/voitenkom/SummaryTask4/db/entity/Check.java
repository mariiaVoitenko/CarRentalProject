package ua.nure.voitenkom.SummaryTask4.db.entity;

/**
 * @author MariiaVoitenko
 */
public class Check extends Entity {

    private int sum;
    private boolean isPayed;

    public Check(int id, int sum, boolean isPayed) {
        this.setId(id);
        this.sum = sum;
        this.isPayed = isPayed;
    }

    public Check(int sum, boolean isPayed) {
        this.sum = sum;
        this.isPayed = isPayed;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public boolean isPayed() {
        return isPayed;
    }

    public void setIsPayed(boolean isPayed) {
        this.isPayed = isPayed;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Check{");
        sb.append("id=").append(this.getId());
        sb.append("sum=").append(sum);
        sb.append(", isPayed=").append(isPayed);
        sb.append('}');
        return sb.toString();
    }

}
