package sx.me.po;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by sx on 2017/7/11.
 */
@Entity
@Table(name = "traffic_bill", schema = "wuliu", catalog = "")
public class TrafficBill {
    private int trafficBillId;
    private String startPlace;
    private String finalPlace;
    private Timestamp startDate;
    private Timestamp finalDate;
    private WayBill wayBillByWayBillId;
    private DriverInfo driverInfoByDriver;
    private CarInfo carInfoByCar;

    @Id
    @Column(name = "trafficBill_id", nullable = false)
    public int getTrafficBillId() {
        return trafficBillId;
    }

    public void setTrafficBillId(int trafficBillId) {
        this.trafficBillId = trafficBillId;
    }

    @Basic
    @Column(name = "start_place", nullable = false,length = 50)
    public String getStartPlace() {
        return startPlace;
    }

    public void setStartPlace(String startPlace) {
        this.startPlace = startPlace;
    }

    @Basic
    @Column(name = "final_place", nullable = false,length = 50)
    public String getFinalPlace() {
        return finalPlace;
    }

    public void setFinalPlace(String finalPlace) {
        this.finalPlace = finalPlace;
    }

    @Basic
    @Column(name = "start_date", nullable = true)
    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "final_date", nullable = true)
    public Timestamp getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Timestamp finalDate) {
        this.finalDate = finalDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrafficBill that = (TrafficBill) o;

        if (trafficBillId != that.trafficBillId) return false;
        if (startPlace != null ? !startPlace.equals(that.startPlace) : that.startPlace != null) return false;
        if (finalPlace != null ? !finalPlace.equals(that.finalPlace) : that.finalPlace != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (finalDate != null ? !finalDate.equals(that.finalDate) : that.finalDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = trafficBillId;
        result = 31 * result + (startPlace != null ? startPlace.hashCode() : 0);
        result = 31 * result + (finalPlace != null ? finalPlace.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (finalDate != null ? finalDate.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "wayBill_id", referencedColumnName = "wayBill_id", nullable = true)
    public WayBill getWayBillByWayBillId() {
        return wayBillByWayBillId;
    }

    public void setWayBillByWayBillId(WayBill wayBillByWayBillId) {
        this.wayBillByWayBillId = wayBillByWayBillId;
    }

    @ManyToOne
    @JoinColumn(name = "driver", referencedColumnName = "driver_id", nullable = true)
    public DriverInfo getDriverInfoByDriver() {
        return driverInfoByDriver;
    }

    public void setDriverInfoByDriver(DriverInfo driverInfoByDriver) {
        this.driverInfoByDriver = driverInfoByDriver;
    }

    @ManyToOne
    @JoinColumn(name = "car", referencedColumnName = "car_id", nullable = true)
    public CarInfo getCarInfoByCar() {
        return carInfoByCar;
    }

    public void setCarInfoByCar(CarInfo carInfoByCar) {
        this.carInfoByCar = carInfoByCar;
    }

    public TrafficBill(String startPlace, String finalPlace, Timestamp startDate, Timestamp finalDate, WayBill wayBillByWayBillId, DriverInfo driverInfoByDriver, CarInfo carInfoByCar) {
        this.startPlace = startPlace;
        this.finalPlace = finalPlace;
        this.startDate = startDate;
        this.finalDate = finalDate;
        this.wayBillByWayBillId = wayBillByWayBillId;
        this.driverInfoByDriver = driverInfoByDriver;
        this.carInfoByCar = carInfoByCar;
    }

    public TrafficBill() {
    }
}
