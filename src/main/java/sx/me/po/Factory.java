package sx.me.po;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by sx on 2017/7/11.
 */
@Entity
public class Factory {
    private int factoryId;
    private String factoryName;
    private String factoryLeader;
    private String factoryPhone;
    private String factoryEmail;
    private String factoryAddress;
    private Collection<WayBill> wayBillsByFactoryId;

    @Id
    @Column(name = "factory_id", nullable = false)
    public int getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(int factoryId) {
        this.factoryId = factoryId;
    }

    @Basic
    @Column(name = "factory_name", nullable = false, length = 30)
    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    @Basic
    @Column(name = "factory_leader", nullable = false, length = 20)
    public String getFactoryLeader() {
        return factoryLeader;
    }

    public void setFactoryLeader(String factoryLeader) {
        this.factoryLeader = factoryLeader;
    }

    @Basic
    @Column(name = "factory_phone", nullable = true, length = 20)
    public String getFactoryPhone() {
        return factoryPhone;
    }

    public void setFactoryPhone(String factoryPhone) {
        this.factoryPhone = factoryPhone;
    }

    @Basic
    @Column(name = "factory_email", nullable = true, length = 30)
    public String getFactoryEmail() {
        return factoryEmail;
    }

    public void setFactoryEmail(String factoryEmail) {
        this.factoryEmail = factoryEmail;
    }

    @Basic
    @Column(name = "factory_address", nullable = false, length = 50)
    public String getFactoryAddress() {
        return factoryAddress;
    }

    public void setFactoryAddress(String factoryAddress) {
        this.factoryAddress = factoryAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Factory factory = (Factory) o;

        if (factoryId != factory.factoryId) return false;
        if (factoryName != null ? !factoryName.equals(factory.factoryName) : factory.factoryName != null) return false;
        if (factoryLeader != null ? !factoryLeader.equals(factory.factoryLeader) : factory.factoryLeader != null)
            return false;
        if (factoryPhone != null ? !factoryPhone.equals(factory.factoryPhone) : factory.factoryPhone != null)
            return false;
        if (factoryEmail != null ? !factoryEmail.equals(factory.factoryEmail) : factory.factoryEmail != null)
            return false;
        if (factoryAddress != null ? !factoryAddress.equals(factory.factoryAddress) : factory.factoryAddress != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = factoryId;
        result = 31 * result + (factoryName != null ? factoryName.hashCode() : 0);
        result = 31 * result + (factoryLeader != null ? factoryLeader.hashCode() : 0);
        result = 31 * result + (factoryPhone != null ? factoryPhone.hashCode() : 0);
        result = 31 * result + (factoryEmail != null ? factoryEmail.hashCode() : 0);
        result = 31 * result + (factoryAddress != null ? factoryAddress.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "factoryByBillFactoryId")
    public Collection<WayBill> getWayBillsByFactoryId() {
        return wayBillsByFactoryId;
    }

    public void setWayBillsByFactoryId(Collection<WayBill> wayBillsByFactoryId) {
        this.wayBillsByFactoryId = wayBillsByFactoryId;
    }

    public Factory() {
    }

    public Factory(String factoryName, String factoryLeader, String factoryPhone, String factoryEmail, String factoryAddress) {
        this.factoryName = factoryName;
        this.factoryLeader = factoryLeader;
        this.factoryPhone = factoryPhone;
        this.factoryEmail = factoryEmail;
        this.factoryAddress = factoryAddress;
    }
}
