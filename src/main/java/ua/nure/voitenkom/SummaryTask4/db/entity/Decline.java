package ua.nure.voitenkom.SummaryTask4.db.entity;

/**
 * @author MariiaVoitenko
 */
public class Decline extends SimpleEntity {

    public Decline(int id, String name) {
        super(id, name);
    }

    public Decline(String name) {
        super(name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Decline{");
        sb.append("id='").append(this.getId()).append('\'');
        sb.append("name='").append(this.getName()).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
