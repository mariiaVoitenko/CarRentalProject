package ua.nure.voitenkom.SummaryTask4.db.entity;

public class MajorityClass extends SimpleEntity {

    public MajorityClass(int id, String name) {
        super(id, name);
    }

    public MajorityClass(String name) {
        super(name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MajorityClass{");
        sb.append("id='").append(this.getId()).append('\'');
        sb.append("name='").append(this.getName()).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
