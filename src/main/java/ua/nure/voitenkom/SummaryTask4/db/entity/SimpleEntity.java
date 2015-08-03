package ua.nure.voitenkom.SummaryTask4.db.entity;

/**
 * Created by Maria on 30.07.2015.
 */
public class SimpleEntity extends Entity{

    private String name;

    public SimpleEntity(){
        super();
    }

    public SimpleEntity(int id,String name) {
        this.setId(id);
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SimpleEntity{");
        sb.append("id='").append(this.getId()).append('\'');
        sb.append("name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
