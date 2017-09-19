package sx.me.po;

import com.alibaba.druid.sql.visitor.functions.Char;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by sx on 2017/7/11.
 */
@Entity
@Table(name = "car_info", schema = "wuliu", catalog = "")
public class CarInfo {
    private int carId;
    private String carType;
    private int carFangliang;
    private int carDunliang;
    private String carChepai;
    private String carDriverLicense;
    private int carState;
    private Company companyByCarCompany;
    private Collection<MarketInfo> marketInfosByCarId;
    private Collection<TrafficBill> trafficBillsByCarId;

    @Id
    @Column(name = "car_id", nullable = false)
    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    @Basic
    @Column(name = "car_type", nullable = false)
    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    @Basic
    @Column(name = "car_fangliang", nullable = false)
    public int getCarFangliang() {
        return carFangliang;
    }

    public void setCarFangliang(int carFangliang) {
        this.carFangliang = carFangliang;
    }

    @Basic
    @Column(name = "car_dunliang", nullable = false)
    public int getCarDunliang() {
        return carDunliang;
    }

    public void setCarDunliang(int carDunliang) {
        this.carDunliang = carDunliang;
    }

    @Basic
    @Column(name = "car_chepai", nullable = false, length = 20)
    public String getCarChepai() {
        return carChepai;
    }

    public void setCarChepai(String carChepai) {
        this.carChepai = carChepai;
    }

    @Basic
    @Column(name = "car_driver_license", nullable = false, length = 30)
    public String getCarDriverLicense() {
        return carDriverLicense;
    }

    public void setCarDriverLicense(String carDriverLicense) {
        this.carDriverLicense = carDriverLicense;
    }

    @Basic
    @Column(name = "car_state", nullable = false)
    public int getCarState() {
        return carState;
    }

    public void setCarState(int carState) {
        this.carState = carState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarInfo carInfo = (CarInfo) o;

        if (carId != carInfo.carId) return false;
        if (carType != null ? !carType.equals(carInfo.carType) : carInfo.carType != null) return false;
        if (carFangliang != carInfo.carFangliang) return false;
        if (carDunliang != carInfo.carDunliang) return false;
        if (carState != carInfo.carState) return false;
        if (carChepai != null ? !carChepai.equals(carInfo.carChepai) : carInfo.carChepai != null) return false;
        if (carDriverLicense != null ? !carDriverLicense.equals(carInfo.carDriverLicense) : carInfo.carDriverLicense != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = carId;
        result = 31 * result + (carType != null ? carType.hashCode() : 0);
        result = 31 * result + carFangliang;
        result = 31 * result + carDunliang;
        result = 31 * result + (carChepai != null ? carChepai.hashCode() : 0);
        result = 31 * result + (carDriverLicense != null ? carDriverLicense.hashCode() : 0);
        result = 31 * result + carState;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "car_company", referencedColumnName = "company_id", nullable = false)
    public Company getCompanyByCarCompany() {
        return companyByCarCompany;
    }

    public void setCompanyByCarCompany(Company companyByCarCompany) {
        this.companyByCarCompany = companyByCarCompany;
    }

    @OneToMany(mappedBy = "carInfoByMarketCar")
    public Collection<MarketInfo> getMarketInfosByCarId() {
        return marketInfosByCarId;
    }

    public void setMarketInfosByCarId(Collection<MarketInfo> marketInfosByCarId) {
        this.marketInfosByCarId = marketInfosByCarId;
    }

    @OneToMany(mappedBy = "carInfoByCar")
    public Collection<TrafficBill> getTrafficBillsByCarId() {
        return trafficBillsByCarId;
    }

    public void setTrafficBillsByCarId(Collection<TrafficBill> trafficBillsByCarId) {
        this.trafficBillsByCarId = trafficBillsByCarId;
    }

    public CarInfo(String carType, int carFangliang, int carDunliang, String carChepai, String carDriverLicense, int carState, Company companyByCarCompany) {
        this.carType = carType;
        this.carFangliang = carFangliang;
        this.carDunliang = carDunliang;
        this.carChepai = carChepai;
        this.carDriverLicense = carDriverLicense;
        this.carState = carState;
        this.companyByCarCompany = companyByCarCompany;
    }

    public CarInfo() {
    }
}
