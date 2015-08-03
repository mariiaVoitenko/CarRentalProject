package ua.nure.voitenkom.SummaryTask4.db.entity;

/**
 * Created by Maria on 30.07.2015.
 */
public class DamageCheck extends Entity {

    private int checkId;
    private int damageId;

    public DamageCheck(int damageId, int checkId, int id) {
        this.setId(id);
        this.checkId = checkId;
        this.damageId = damageId;
    }

    public int getCheckId() {
        return checkId;
    }

    public void setCheckId(int checkId) {
        this.checkId = checkId;
    }

    public int getDamageId() {
        return damageId;
    }

    public void setDamageId(int damageId) {
        this.damageId = damageId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DamageCheck{");
        sb.append("id=").append(this.getId());
        sb.append("damageId=").append(damageId);
        sb.append(", checkId=").append(checkId);
        sb.append('}');
        return sb.toString();
    }
}
