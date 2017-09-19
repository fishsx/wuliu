package sx.me.po;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by sx on 2017/7/11.
 */
@Entity
@Table(name = "way_bill", schema = "wuliu", catalog = "")
public class WayBill {
    private int wayBillId;
    private String billStartPlace;
    private String billFinalPlace;
    private Double billYunfei;
    private Double billShaoqian;
    private Double billPeiqian;
    private int billPayType;
    private int billPayState;
    private int billState;
    private int billTrafficId;
    private Collection<GoodsBack> goodsBacksByWayBillId;
    private Collection<GoodsDamage> goodsDamagesByWayBillId;
    private Collection<GoodsPeiqian> goodsPeiqiansByWayBillId;
    private Collection<GoodsShaoqian> goodsShaoqiansByWayBillId;
    private Collection<TrafficBill> trafficBillsByWayBillId;
    private Factory factoryByBillFactoryId;
    private Consignee consigneeByBillPerson;
    private Goods goodsByBillGoodId;

    @Id
    @Column(name = "wayBill_id", nullable = false)
    public int getWayBillId() {
        return wayBillId;
    }

    public void setWayBillId(int wayBillId) {
        this.wayBillId = wayBillId;
    }

    @Basic
    @Column(name = "bill_start_place", nullable = false, length = 50)
    public String getBillStartPlace() {
        return billStartPlace;
    }

    public void setBillStartPlace(String billStartPlace) {
        this.billStartPlace = billStartPlace;
    }

    @Basic
    @Column(name = "bill_final_place", nullable = false, length = 50)
    public String getBillFinalPlace() {
        return billFinalPlace;
    }

    public void setBillFinalPlace(String billFinalPlace) {
        this.billFinalPlace = billFinalPlace;
    }

    @Basic
    @Column(name = "bill_yunfei", nullable = true, precision = 0)
    public Double getBillYunfei() {
        return billYunfei;
    }

    public void setBillYunfei(Double billYunfei) {
        this.billYunfei = billYunfei;
    }

    @Basic
    @Column(name = "bill_shaoqian", nullable = true, precision = 0)
    public Double getBillShaoqian() {
        return billShaoqian;
    }

    public void setBillShaoqian(Double billShaoqian) {
        this.billShaoqian = billShaoqian;
    }

    @Basic
    @Column(name = "bill_peiqian", nullable = true, precision = 0)
    public Double getBillPeiqian() {
        return billPeiqian;
    }

    public void setBillPeiqian(Double billPeiqian) {
        this.billPeiqian = billPeiqian;
    }

    @Basic
    @Column(name = "bill_pay_type", nullable = false, length = 255)
    public int getBillPayType() {
        return billPayType;
    }

    public void setBillPayType(int billPayType) {
        this.billPayType = billPayType;
    }

    @Basic
    @Column(name = "bill_pay_state", nullable = false)
    public int getBillPayState() {
        return billPayState;
    }

    public void setBillPayState(int billPayState) {
        this.billPayState = billPayState;
    }

    @Basic
    @Column(name = "bill_state", nullable = false)
    public int getBillState() {
        return billState;
    }

    public void setBillState(int billState) {
        this.billState = billState;
    }

    @Basic
    @Column(name = "bill_traffic_id", nullable = false)
    public int getBillTrafficId() {
        return billTrafficId;
    }

    public void setBillTrafficId(int billTrafficId) {
        this.billTrafficId = billTrafficId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WayBill wayBill = (WayBill) o;

        if (wayBillId != wayBill.wayBillId) return false;
        if (billPayState != wayBill.billPayState) return false;
        if (billState != wayBill.billState) return false;
        if (billTrafficId != wayBill.billTrafficId) return false;
        if (billStartPlace != null ? !billStartPlace.equals(wayBill.billStartPlace) : wayBill.billStartPlace != null)
            return false;
        if (billFinalPlace != null ? !billFinalPlace.equals(wayBill.billFinalPlace) : wayBill.billFinalPlace != null)
            return false;
        if (billYunfei != null ? !billYunfei.equals(wayBill.billYunfei) : wayBill.billYunfei != null) return false;
        if (billShaoqian != null ? !billShaoqian.equals(wayBill.billShaoqian) : wayBill.billShaoqian != null)
            return false;
        if (billPeiqian != null ? !billPeiqian.equals(wayBill.billPeiqian) : wayBill.billPeiqian != null) return false;
        if (billPayType != wayBill.billPayType) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = wayBillId;
        result = 31 * result + (billStartPlace != null ? billStartPlace.hashCode() : 0);
        result = 31 * result + (billFinalPlace != null ? billFinalPlace.hashCode() : 0);
        result = 31 * result + (billYunfei != null ? billYunfei.hashCode() : 0);
        result = 31 * result + (billShaoqian != null ? billShaoqian.hashCode() : 0);
        result = 31 * result + (billPeiqian != null ? billPeiqian.hashCode() : 0);
        result = 31 * result + billPayType;
        result = 31 * result + billPayState;
        result = 31 * result + billState;
        result = 31 * result + billTrafficId;
        return result;
    }

    @OneToMany(mappedBy = "wayBillByWayBillId")
    public Collection<GoodsBack> getGoodsBacksByWayBillId() {
        return goodsBacksByWayBillId;
    }

    public void setGoodsBacksByWayBillId(Collection<GoodsBack> goodsBacksByWayBillId) {
        this.goodsBacksByWayBillId = goodsBacksByWayBillId;
    }

    @OneToMany(mappedBy = "wayBillByBillId")
    public Collection<GoodsDamage> getGoodsDamagesByWayBillId() {
        return goodsDamagesByWayBillId;
    }

    public void setGoodsDamagesByWayBillId(Collection<GoodsDamage> goodsDamagesByWayBillId) {
        this.goodsDamagesByWayBillId = goodsDamagesByWayBillId;
    }

    @OneToMany(mappedBy = "wayBillByWayBillId")
    public Collection<GoodsPeiqian> getGoodsPeiqiansByWayBillId() {
        return goodsPeiqiansByWayBillId;
    }

    public void setGoodsPeiqiansByWayBillId(Collection<GoodsPeiqian> goodsPeiqiansByWayBillId) {
        this.goodsPeiqiansByWayBillId = goodsPeiqiansByWayBillId;
    }

    @OneToMany(mappedBy = "wayBillByWayBillId")
    public Collection<GoodsShaoqian> getGoodsShaoqiansByWayBillId() {
        return goodsShaoqiansByWayBillId;
    }

    public void setGoodsShaoqiansByWayBillId(Collection<GoodsShaoqian> goodsShaoqiansByWayBillId) {
        this.goodsShaoqiansByWayBillId = goodsShaoqiansByWayBillId;
    }

    @OneToMany(mappedBy = "wayBillByWayBillId")
    public Collection<TrafficBill> getTrafficBillsByWayBillId() {
        return trafficBillsByWayBillId;
    }

    public void setTrafficBillsByWayBillId(Collection<TrafficBill> trafficBillsByWayBillId) {
        this.trafficBillsByWayBillId = trafficBillsByWayBillId;
    }

    @ManyToOne
    @JoinColumn(name = "bill_factory_id", referencedColumnName = "factory_id", nullable = false)
    public Factory getFactoryByBillFactoryId() {
        return factoryByBillFactoryId;
    }

    public void setFactoryByBillFactoryId(Factory factoryByBillFactoryId) {
        this.factoryByBillFactoryId = factoryByBillFactoryId;
    }

    @ManyToOne
    @JoinColumn(name = "bill_person", referencedColumnName = "consignee_id", nullable = false)
    public Consignee getConsigneeByBillPerson() {
        return consigneeByBillPerson;
    }

    public void setConsigneeByBillPerson(Consignee consigneeByBillPerson) {
        this.consigneeByBillPerson = consigneeByBillPerson;
    }

    @ManyToOne
    @JoinColumn(name = "bill_goodID", referencedColumnName = "good_id", nullable = false)
    public Goods getGoodsByBillGoodId() {
        return goodsByBillGoodId;
    }

    public void setGoodsByBillGoodId(Goods goodsByBillGoodId) {
        this.goodsByBillGoodId = goodsByBillGoodId;
    }

    public WayBill(String billStartPlace, String billFinalPlace, Double billYunfei, Double billShaoqian, Double billPeiqian, int billPayType, int billPayState, int billState, int billTrafficId, Factory factoryByBillFactoryId, Consignee consigneeByBillPerson, Goods goodsByBillGoodId) {
        this.billStartPlace = billStartPlace;
        this.billFinalPlace = billFinalPlace;
        this.billYunfei = billYunfei;
        this.billShaoqian = billShaoqian;
        this.billPeiqian = billPeiqian;
        this.billPayType = billPayType;
        this.billPayState = billPayState;
        this.billState = billState;
        this.billTrafficId = billTrafficId;
        this.factoryByBillFactoryId = factoryByBillFactoryId;
        this.consigneeByBillPerson = consigneeByBillPerson;
        this.goodsByBillGoodId = goodsByBillGoodId;
    }

    public WayBill() {
    }
}
