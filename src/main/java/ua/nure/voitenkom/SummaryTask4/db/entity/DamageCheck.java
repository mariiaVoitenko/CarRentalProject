package ua.nure.voitenkom.SummaryTask4.db.entity;

/**
 * Created by Maria on 30.07.2015.
 */
public class DamageCheck extends Entity {

    private Check check;
    private Damage damage;

    public Check getCheck() {
        return check;
    }

    public void setCheck(Check check) {
        this.check = check;
    }

    public Damage getDamage() {
        return damage;
    }

    public void setDamage(Damage damage) {
        this.damage = damage;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DamageCheck{");
        sb.append("id=").append(this.getId());
        sb.append("damageId=").append(damage.getId());
        sb.append(", checkId=").append(check.getId());
        sb.append('}');
        return sb.toString();
    }
}
