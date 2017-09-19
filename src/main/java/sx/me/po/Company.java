package sx.me.po;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by sx on 2017/7/11.
 */
@Entity
public class Company {
    private int companyId;
    private String companyName;
    private String companyAddress;
    private String companyLeader;
    private String companyPhone;
    private Collection<CarInfo> carInfosByCompanyId;
    private Collection<DriverInfo> driverInfosByCompanyId;
    private Collection<User> usersByCompanyId;

    @Id
    @Column(name = "company_id", nullable = false)
    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    @Basic
    @Column(name = "companyName", nullable = false, length = 20)
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Basic
    @Column(name = "companyAddress", nullable = false, length = 50)
    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    @Basic
    @Column(name = "companyLeader", nullable = false, length = 20)
    public String getCompanyLeader() {
        return companyLeader;
    }

    public void setCompanyLeader(String companyLeader) {
        this.companyLeader = companyLeader;
    }

    @Basic
    @Column(name = "companyPhone", nullable = false, length = 20)
    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        if (companyId != company.companyId) return false;
        if (companyName != null ? !companyName.equals(company.companyName) : company.companyName != null) return false;
        if (companyAddress != null ? !companyAddress.equals(company.companyAddress) : company.companyAddress != null)
            return false;
        if (companyLeader != null ? !companyLeader.equals(company.companyLeader) : company.companyLeader != null)
            return false;
        if (companyPhone != null ? !companyPhone.equals(company.companyPhone) : company.companyPhone != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = companyId;
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        result = 31 * result + (companyAddress != null ? companyAddress.hashCode() : 0);
        result = 31 * result + (companyLeader != null ? companyLeader.hashCode() : 0);
        result = 31 * result + (companyPhone != null ? companyPhone.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "companyByCarCompany")
    public Collection<CarInfo> getCarInfosByCompanyId() {
        return carInfosByCompanyId;
    }

    public void setCarInfosByCompanyId(Collection<CarInfo> carInfosByCompanyId) {
        this.carInfosByCompanyId = carInfosByCompanyId;
    }

    @OneToMany(mappedBy = "companyByDriverCompany")
    public Collection<DriverInfo> getDriverInfosByCompanyId() {
        return driverInfosByCompanyId;
    }

    public void setDriverInfosByCompanyId(Collection<DriverInfo> driverInfosByCompanyId) {
        this.driverInfosByCompanyId = driverInfosByCompanyId;
    }

    @OneToMany(mappedBy = "companyByCompanyId")
    public Collection<User> getUsersByCompanyId() {
        return usersByCompanyId;
    }

    public void setUsersByCompanyId(Collection<User> usersByCompanyId) {
        this.usersByCompanyId = usersByCompanyId;
    }

    public Company(String companyName, String companyAddress, String companyLeader, String companyPhone) {
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.companyLeader = companyLeader;
        this.companyPhone = companyPhone;
    }

    public Company() {
    }
}
