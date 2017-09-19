package sx.me.po;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by sx on 2017/7/11.
 */
@Entity
public class Consignee {
    private int consigneeId;
    private String consigneeName;
    private String consigneeCompany;
    private String consigneeAddress;
    private String consigneePhone;
    private Collection<MarketInfo> marketInfosByConsigneeId;
    private Collection<WayBill> wayBillsByConsigneeId;

    @Id
    @Column(name = "consignee_id", nullable = false)
    public int getConsigneeId() {
        return consigneeId;
    }

    public void setConsigneeId(int consigneeId) {
        this.consigneeId = consigneeId;
    }

    @Basic
    @Column(name = "consignee_name", nullable = false, length = 20)
    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    @Basic
    @Column(name = "consignee_company", nullable = false)
    public String getConsigneeCompany() {
        return consigneeCompany;
    }

    public void setConsigneeCompany(String consigneeCompany) {
        this.consigneeCompany = consigneeCompany;
    }

    @Basic
    @Column(name = "consignee_address", nullable = false, length = 50)
    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }

    @Basic
    @Column(name = "consignee_phone", nullable = false, length = 20)
    public String getConsigneePhone() {
        return consigneePhone;
    }

    public void setConsigneePhone(String consigneePhone) {
        this.consigneePhone = consigneePhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Consignee consignee = (Consignee) o;

        if (consigneeId != consignee.consigneeId) return false;
        if (consigneeCompany != consignee.consigneeCompany) return false;
        if (consigneeName != null ? !consigneeName.equals(consignee.consigneeName) : consignee.consigneeName != null)
            return false;
        if (consigneeAddress != null ? !consigneeAddress.equals(consignee.consigneeAddress) : consignee.consigneeAddress != null)
            return false;
        if (consigneePhone != null ? !consigneePhone.equals(consignee.consigneePhone) : consignee.consigneePhone != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = consigneeId;
        result = 31 * result + (consigneeName != null ? consigneeName.hashCode() : 0);
        result = 31 * result + (consigneeCompany != null ? consigneeCompany.hashCode() : 0);
        result = 31 * result + (consigneeAddress != null ? consigneeAddress.hashCode() : 0);
        result = 31 * result + (consigneePhone != null ? consigneePhone.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "consigneeByMarketConsignee")
    public Collection<MarketInfo> getMarketInfosByConsigneeId() {
        return marketInfosByConsigneeId;
    }

    public void setMarketInfosByConsigneeId(Collection<MarketInfo> marketInfosByConsigneeId) {
        this.marketInfosByConsigneeId = marketInfosByConsigneeId;
    }

    @OneToMany(mappedBy = "consigneeByBillPerson")
    public Collection<WayBill> getWayBillsByConsigneeId() {
        return wayBillsByConsigneeId;
    }

    public void setWayBillsByConsigneeId(Collection<WayBill> wayBillsByConsigneeId) {
        this.wayBillsByConsigneeId = wayBillsByConsigneeId;
    }

    public Consignee(String consigneeName, String consigneeCompany, String consigneeAddress, String consigneePhone) {
        this.consigneeName = consigneeName;
        this.consigneeCompany = consigneeCompany;
        this.consigneeAddress = consigneeAddress;
        this.consigneePhone = consigneePhone;
    }


    public Consignee() {
    }
}
