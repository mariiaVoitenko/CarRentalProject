package ua.nure.voitenkom.SummaryTask4.db.entity;

/**
 * @author MariiaVoitenko
 */
public class Brand extends SimpleEntity {

    public Brand(int id, String name) {
        super(id, name);
    }

    public Brand(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Brand{" + "id='" + this.getId() + '\'' + "name='" + this.getName() + '\'' + '}';
    }

}
