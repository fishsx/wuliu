package sx.me.po;

import javax.persistence.*;

/**
 * Created by sx on 2017/7/11.
 */
@Entity
@Table(name = "goods_shaoqian", schema = "wuliu", catalog = "")
public class GoodsShaoqian {
    private int shaoqianId;
    private double shaoqianMoney;
    private int shaoqianState;
    private WayBill wayBillByWayBillId;

    @Id
    @Column(name = "shaoqian_id", nullable = false)
    public int getShaoqianId() {
        return shaoqianId;
    }

    public void setShaoqianId(int shaoqianId) {
        this.shaoqianId = shaoqianId;
    }

    @Basic
    @Column(name = "shaoqian_money", nullable = false, precision = 0)
    public double getShaoqianMoney() {
        return shaoqianMoney;
    }

    public void setShaoqianMoney(double shaoqianMoney) {
        this.shaoqianMoney = shaoqianMoney;
    }

    @Basic
    @Column(name = "shaoqian_state", nullable = false)
    public int getShaoqianState() {
        return shaoqianState;
    }

    public void setShaoqianState(int shaoqianState) {
        this.shaoqianState = shaoqianState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoodsShaoqian that = (GoodsShaoqian) o;

        if (shaoqianId != that.shaoqianId) return false;
        if (Double.compare(that.shaoqianMoney, shaoqianMoney) != 0) return false;
        if (shaoqianState != that.shaoqianState) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = shaoqianId;
        temp = Double.doubleToLongBits(shaoqianMoney);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + shaoqianState;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "wayBill_Id", referencedColumnName = "wayBill_id", nullable = false)
    public WayBill getWayBillByWayBillId() {
        return wayBillByWayBillId;
    }

    public void setWayBillByWayBillId(WayBill wayBillByWayBillId) {
        this.wayBillByWayBillId = wayBillByWayBillId;
    }
}
