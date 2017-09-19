package sx.me.po;

import javax.persistence.*;

/**
 * Created by sx on 2017/7/11.
 */
@Entity
@Table(name = "goods_peiqian", schema = "wuliu", catalog = "")
public class GoodsPeiqian {
    private int peiqianId;
    private double peiqianMoney;
    private int peiqianState;
    private WayBill wayBillByWayBillId;

    @Id
    @Column(name = "peiqian_id", nullable = false)
    public int getPeiqianId() {
        return peiqianId;
    }

    public void setPeiqianId(int peiqianId) {
        this.peiqianId = peiqianId;
    }

    @Basic
    @Column(name = "peiqian_money", nullable = false, precision = 0)
    public double getPeiqianMoney() {
        return peiqianMoney;
    }

    public void setPeiqianMoney(double peiqianMoney) {
        this.peiqianMoney = peiqianMoney;
    }

    @Basic
    @Column(name = "peiqian_state", nullable = false)
    public int getPeiqianState() {
        return peiqianState;
    }

    public void setPeiqianState(int peiqianState) {
        this.peiqianState = peiqianState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoodsPeiqian that = (GoodsPeiqian) o;

        if (peiqianId != that.peiqianId) return false;
        if (Double.compare(that.peiqianMoney, peiqianMoney) != 0) return false;
        if (peiqianState != that.peiqianState) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = peiqianId;
        temp = Double.doubleToLongBits(peiqianMoney);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + peiqianState;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "way_bill_id", referencedColumnName = "wayBill_id", nullable = false)
    public WayBill getWayBillByWayBillId() {
        return wayBillByWayBillId;
    }

    public void setWayBillByWayBillId(WayBill wayBillByWayBillId) {
        this.wayBillByWayBillId = wayBillByWayBillId;
    }

    public GoodsPeiqian(double peiqianMoney, int peiqianState, WayBill wayBillByWayBillId) {
        this.peiqianMoney = peiqianMoney;
        this.peiqianState = peiqianState;
        this.wayBillByWayBillId = wayBillByWayBillId;
    }

    public GoodsPeiqian() {
    }
}
