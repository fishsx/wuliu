package sx.me.po;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by sx on 2017/7/11.
 */
@Entity
@Table(name = "driver_info", schema = "wuliu", catalog = "")
public class DriverInfo {
    private int driverId;
    private String driverName;
    private String driverSex;
    private Date driverBirthday;
    private String driverLicenseType;
    private String driverLicense;
    private int driverState;
    private Company companyByDriverCompany;
    private Collection<MarketInfo> marketInfosByDriverId;
    private Collection<TrafficBill> trafficBillsByDriverId;

    @Id
    @Column(name = "driver_id", nullable = false)
    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    @Basic
    @Column(name = "driver_name", nullable = false, length = 20)
    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    @Basic
    @Column(name = "driver_sex", nullable = false, length = 2)
    public String getDriverSex() {
        return driverSex;
    }

    public void setDriverSex(String driverSex) {
        this.driverSex = driverSex;
    }

    @Basic
    @Column(name = "driver_birthday", nullable = false)
    public Date getDriverBirthday() {
        return driverBirthday;
    }

    public void setDriverBirthday(Date driverBirthday) {
        this.driverBirthday = driverBirthday;
    }

    @Basic
    @Column(name = "driver_license_type", nullable = false, length = 10)
    public String getDriverLicenseType() {
        return driverLicenseType;
    }

    public void setDriverLicenseType(String driverLicenseType) {
        this.driverLicenseType = driverLicenseType;
    }

    @Basic
    @Column(name = "driver_license", nullable = false)
    public String getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense;
    }

    @Basic
    @Column(name = "driver_state", nullable = false)
    public int getDriverState() {
        return driverState;
    }

    public void setDriverState(int driverState) {
        this.driverState = driverState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DriverInfo that = (DriverInfo) o;

        if (driverId != that.driverId) return false;
        if (driverLicense != null ? !driverLicense.equals(that.driverLicense) : that.driverLicense != null) return false;
        if (driverState != that.driverState) return false;
        if (driverName != null ? !driverName.equals(that.driverName) : that.driverName != null) return false;
        if (driverSex != null ? !driverSex.equals(that.driverSex) : that.driverSex != null) return false;
        if (driverBirthday != null ? !driverBirthday.equals(that.driverBirthday) : that.driverBirthday != null)
            return false;
        if (driverLicenseType != null ? !driverLicenseType.equals(that.driverLicenseType) : that.driverLicenseType != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = driverId;
        result = 31 * result + (driverName != null ? driverName.hashCode() : 0);
        result = 31 * result + (driverSex != null ? driverSex.hashCode() : 0);
        result = 31 * result + (driverBirthday != null ? driverBirthday.hashCode() : 0);
        result = 31 * result + (driverLicenseType != null ? driverLicenseType.hashCode() : 0);
        result = 31 * result + (driverLicense != null ? driverLicense.hashCode() : 0);
        result = 31 * result + driverState;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "driver_company", referencedColumnName = "company_id", nullable = false)
    public Company getCompanyByDriverCompany() {
        return companyByDriverCompany;
    }

    public void setCompanyByDriverCompany(Company companyByDriverCompany) {
        this.companyByDriverCompany = companyByDriverCompany;
    }

    @OneToMany(mappedBy = "driverInfoByMarketDriver")
    public Collection<MarketInfo> getMarketInfosByDriverId() {
        return marketInfosByDriverId;
    }

    public void setMarketInfosByDriverId(Collection<MarketInfo> marketInfosByDriverId) {
        this.marketInfosByDriverId = marketInfosByDriverId;
    }

    @OneToMany(mappedBy = "driverInfoByDriver")
    public Collection<TrafficBill> getTrafficBillsByDriverId() {
        return trafficBillsByDriverId;
    }

    public void setTrafficBillsByDriverId(Collection<TrafficBill> trafficBillsByDriverId) {
        this.trafficBillsByDriverId = trafficBillsByDriverId;
    }

    public DriverInfo() {
    }

    public DriverInfo(String driverName, String driverSex, Date driverBirthday, String driverLicenseType, String driverLicense, int driverState, Company companyByDriverCompany) {
        this.driverName = driverName;
        this.driverSex = driverSex;
        this.driverBirthday = driverBirthday;
        this.driverLicenseType = driverLicenseType;
        this.driverLicense = driverLicense;
        this.driverState = driverState;
        this.companyByDriverCompany = companyByDriverCompany;
    }
}
