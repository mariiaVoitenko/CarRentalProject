package ua.nure.voitenkom.SummaryTask4.db.entity;

public class Entity {

    private int id;

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Entity{");
        sb.append("id=").append(id);
        sb.append('}');
        return sb.toString();
    }

    public void setId(int id) {
        this.id = id;
    }
}
