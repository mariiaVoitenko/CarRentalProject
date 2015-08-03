package ua.nure.voitenkom.SummaryTask4.db.entity;

/**
 * Created by Maria on 30.07.2015.
 */
public class Color extends SimpleEntity {

    public Color(int id, String name) {
        super(id, name);
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
