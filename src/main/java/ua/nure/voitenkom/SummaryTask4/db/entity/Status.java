package ua.nure.voitenkom.SummaryTask4.db.entity;

/**
 * @author MariiaVoitenko
 */
public class Status extends SimpleEntity {

    public Status(int id, String name) {
        super(id, name);
    }

    public Status(String name) {
        super(name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Status{");
        sb.append("id='").append(this.getId()).append('\'');
        sb.append("name='").append(this.getName()).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
