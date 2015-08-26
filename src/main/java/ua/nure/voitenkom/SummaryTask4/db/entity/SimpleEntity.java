package ua.nure.voitenkom.SummaryTask4.db.entity;

/**
 * @author MariiaVoitenko
 */
public abstract class SimpleEntity extends Entity {

    private String name;

    public SimpleEntity(int id, String name) {
        this.setId(id);
        this.name = name;
    }

    public SimpleEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
