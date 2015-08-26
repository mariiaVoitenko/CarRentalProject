package ua.nure.voitenkom.SummaryTask4.db.entity;

/**
 * @author MariiaVoitenko
 */
public class Role extends SimpleEntity {

    public Role(int id, String name) {
        super(id, name);
    }

    public Role(String name) {
        super(name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Role{");
        sb.append("id='").append(this.getId()).append('\'');
        sb.append("name='").append(this.getName()).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
