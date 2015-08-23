package ua.nure.voitenkom.SummaryTask4.db.entity;

public class Brand extends SimpleEntity {

    public Brand(int id, String name) {
        super(id, name);
    }

    @Override
    public String toString() {
        return "Brand{" + "id='" + this.getId() + '\'' + "name='" + this.getName() + '\'' + '}';
    }

}
