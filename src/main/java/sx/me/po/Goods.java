package sx.me.po;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by sx on 2017/7/11.
 */
@Entity
public class Goods {
    private int goodId;
    private String goodName;
    private int goodRuleType;
    private String goodNumType;
    private int goodNum;
    private Double goodCount;
    private Double goodBaojia;
    private Collection<MarketInfo> marketInfosByGoodId;
    private Collection<WayBill> wayBillsByGoodId;

    @Id
    @Column(name = "good_id", nullable = false)
    public int getGoodId() {
        return goodId;
    }

    public void setGoodId(int goodId) {
        this.goodId = goodId;
    }

    @Basic
    @Column(name = "good_name", nullable = false, length = 20)
    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    @Basic
    @Column(name = "good_rule_type", nullable = false)
    public int getGoodRuleType() {
        return goodRuleType;
    }

    public void setGoodRuleType(int goodRuleType) {
        this.goodRuleType = goodRuleType;
    }

    @Basic
    @Column(name = "good_num_type", nullable = false, length = 2)
    public String getGoodNumType() {
        return goodNumType;
    }

    public void setGoodNumType(String goodNumType) {
        this.goodNumType = goodNumType;
    }

    @Basic
    @Column(name = "good_num", nullable = false)
    public int getGoodNum() {
        return goodNum;
    }

    public void setGoodNum(int goodNum) {
        this.goodNum = goodNum;
    }

    @Basic
    @Column(name = "good_count", nullable = true, precision = 0)
    public Double getGoodCount() {
        return goodCount;
    }

    public void setGoodCount(Double goodCount) {
        this.goodCount = goodCount;
    }

    @Basic
    @Column(name = "good_baojia", nullable = true, precision = 0)
    public Double getGoodBaojia() {
        return goodBaojia;
    }

    public void setGoodBaojia(Double goodBaojia) {
        this.goodBaojia = goodBaojia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Goods goods = (Goods) o;

        if (goodId != goods.goodId) return false;
        if (goodRuleType != goods.goodRuleType) return false;
        if (goodNum != goods.goodNum) return false;
        if (goodName != null ? !goodName.equals(goods.goodName) : goods.goodName != null) return false;
        if (goodNumType != null ? !goodNumType.equals(goods.goodNumType) : goods.goodNumType != null) return false;
        if (goodCount != null ? !goodCount.equals(goods.goodCount) : goods.goodCount != null) return false;
        if (goodBaojia != null ? !goodBaojia.equals(goods.goodBaojia) : goods.goodBaojia != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = goodId;
        result = 31 * result + (goodName != null ? goodName.hashCode() : 0);
        result = 31 * result + goodRuleType;
        result = 31 * result + (goodNumType != null ? goodNumType.hashCode() : 0);
        result = 31 * result + goodNum;
        result = 31 * result + (goodCount != null ? goodCount.hashCode() : 0);
        result = 31 * result + (goodBaojia != null ? goodBaojia.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "goodsByMarketGoodid")
    public Collection<MarketInfo> getMarketInfosByGoodId() {
        return marketInfosByGoodId;
    }

    public void setMarketInfosByGoodId(Collection<MarketInfo> marketInfosByGoodId) {
        this.marketInfosByGoodId = marketInfosByGoodId;
    }

    @OneToMany(mappedBy = "goodsByBillGoodId")
    public Collection<WayBill> getWayBillsByGoodId() {
        return wayBillsByGoodId;
    }

    public void setWayBillsByGoodId(Collection<WayBill> wayBillsByGoodId) {
        this.wayBillsByGoodId = wayBillsByGoodId;
    }

    public Goods(String goodName, int goodRuleType, String goodNumType, int goodNum, Double goodCount, Double goodBaojia) {
        this.goodName = goodName;
        this.goodRuleType = goodRuleType;
        this.goodNumType = goodNumType;
        this.goodNum = goodNum;
        this.goodCount = goodCount;
        this.goodBaojia = goodBaojia;
    }


    public Goods() {
    }
}
