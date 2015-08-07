package ua.nure.voitenkom.SummaryTask4.db.entity;

public class Damage extends SimpleEntity {

    private int sum;

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public Damage(int id, String name, int sum) {
        super(id, name);
        this.sum = sum;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Damage{");
        sb.append("id='").append(this.getId()).append('\'');
        sb.append("name='").append(this.getName()).append('\'');
        sb.append("sum='").append(sum).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
