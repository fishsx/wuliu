package sx.me.po;

import javax.persistence.*;

/**
 * Created by sx on 2017/7/11.
 */
@Entity
@Table(name = "goods_damage", schema = "wuliu", catalog = "")
public class GoodsDamage {
    private int damageId;
    private String damageReason;
    private Double damageMoney;
    private WayBill wayBillByBillId;

    @Id
    @Column(name = "damage_id", nullable = false)
    public int getDamageId() {
        return damageId;
    }

    public void setDamageId(int damageId) {
        this.damageId = damageId;
    }

    @Basic
    @Column(name = "damage_reason", nullable = false, length = 300)
    public String getDamageReason() {
        return damageReason;
    }

    public void setDamageReason(String damageReason) {
        this.damageReason = damageReason;
    }

    @Basic
    @Column(name = "damage_money", nullable = true, precision = 0)
    public Double getDamageMoney() {
        return damageMoney;
    }

    public void setDamageMoney(Double damageMoney) {
        this.damageMoney = damageMoney;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoodsDamage that = (GoodsDamage) o;

        if (damageId != that.damageId) return false;
        if (damageReason != null ? !damageReason.equals(that.damageReason) : that.damageReason != null) return false;
        if (damageMoney != null ? !damageMoney.equals(that.damageMoney) : that.damageMoney != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = damageId;
        result = 31 * result + (damageReason != null ? damageReason.hashCode() : 0);
        result = 31 * result + (damageMoney != null ? damageMoney.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "bill_id", referencedColumnName = "wayBill_id", nullable = false)
    public WayBill getWayBillByBillId() {
        return wayBillByBillId;
    }

    public void setWayBillByBillId(WayBill wayBillByBillId) {
        this.wayBillByBillId = wayBillByBillId;
    }

    public GoodsDamage(String damageReason, Double damageMoney, WayBill wayBillByBillId) {
        this.damageReason = damageReason;
        this.damageMoney = damageMoney;
        this.wayBillByBillId = wayBillByBillId;
    }

    public GoodsDamage() {
    }
}
