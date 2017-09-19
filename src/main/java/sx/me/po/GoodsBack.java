package sx.me.po;

import javax.persistence.*;

/**
 * Created by sx on 2017/7/11.
 */
@Entity
@Table(name = "goods_back", schema = "wuliu", catalog = "")
public class GoodsBack {
    private int goodBackId;
    private WayBill wayBillByWayBillId;

    @Id
    @Column(name = "good_back_id", nullable = false)
    public int getGoodBackId() {
        return goodBackId;
    }

    public void setGoodBackId(int goodBackId) {
        this.goodBackId = goodBackId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoodsBack goodsBack = (GoodsBack) o;

        if (goodBackId != goodsBack.goodBackId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return goodBackId;
    }

    @ManyToOne
    @JoinColumn(name = "way_bill_id", referencedColumnName = "wayBill_id", nullable = false)
    public WayBill getWayBillByWayBillId() {
        return wayBillByWayBillId;
    }

    public void setWayBillByWayBillId(WayBill wayBillByWayBillId) {
        this.wayBillByWayBillId = wayBillByWayBillId;
    }
}
