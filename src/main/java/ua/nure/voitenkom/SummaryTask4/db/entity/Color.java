package ua.nure.voitenkom.SummaryTask4.db.entity;

/**
 * @author MariiaVoitenko
 */
public class Color extends SimpleEntity {

    public Color(int id, String name) {
        super(id, name);
    }

    public Color(String name) {
        super(name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Color{");
        sb.append("id='").append(this.getId()).append('\'');
        sb.append("name='").append(this.getName()).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
