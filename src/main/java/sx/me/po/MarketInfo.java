package sx.me.po;

import javax.persistence.*;

/**
 * Created by sx on 2017/7/11.
 */
@Entity
@Table(name = "market_info", schema = "wuliu", catalog = "")
public class MarketInfo {
    private int marketId;
    private double marketCost;
    private int marketState;
    private Goods goodsByMarketGoodid;
    private Consignee consigneeByMarketConsignee;
    private DriverInfo driverInfoByMarketDriver;
    private CarInfo carInfoByMarketCar;

    @Id
    @Column(name = "market_id", nullable = false)
    public int getMarketId() {
        return marketId;
    }

    public void setMarketId(int marketId) {
        this.marketId = marketId;
    }

    @Basic
    @Column(name = "market_cost", nullable = false, precision = 0)
    public double getMarketCost() {
        return marketCost;
    }

    public void setMarketCost(double marketCost) {
        this.marketCost = marketCost;
    }

    @Basic
    @Column(name = "market_state", nullable = false)
    public int getMarketState() {
        return marketState;
    }

    public void setMarketState(int marketState) {
        this.marketState = marketState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MarketInfo that = (MarketInfo) o;

        if (marketId != that.marketId) return false;
        if (Double.compare(that.marketCost, marketCost) != 0) return false;
        if (marketState != that.marketState) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = marketId;
        temp = Double.doubleToLongBits(marketCost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + marketState;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "market_goodid", referencedColumnName = "good_id", nullable = false)
    public Goods getGoodsByMarketGoodid() {
        return goodsByMarketGoodid;
    }

    public void setGoodsByMarketGoodid(Goods goodsByMarketGoodid) {
        this.goodsByMarketGoodid = goodsByMarketGoodid;
    }

    @ManyToOne
    @JoinColumn(name = "market_consignee", referencedColumnName = "consignee_id", nullable = false)
    public Consignee getConsigneeByMarketConsignee() {
        return consigneeByMarketConsignee;
    }

    public void setConsigneeByMarketConsignee(Consignee consigneeByMarketConsignee) {
        this.consigneeByMarketConsignee = consigneeByMarketConsignee;
    }

    @ManyToOne
    @JoinColumn(name = "market_driver", referencedColumnName = "driver_id", nullable = false)
    public DriverInfo getDriverInfoByMarketDriver() {
        return driverInfoByMarketDriver;
    }

    public void setDriverInfoByMarketDriver(DriverInfo driverInfoByMarketDriver) {
        this.driverInfoByMarketDriver = driverInfoByMarketDriver;
    }

    @ManyToOne
    @JoinColumn(name = "market_car", referencedColumnName = "car_id", nullable = false)
    public CarInfo getCarInfoByMarketCar() {
        return carInfoByMarketCar;
    }

    public void setCarInfoByMarketCar(CarInfo carInfoByMarketCar) {
        this.carInfoByMarketCar = carInfoByMarketCar;
    }
}
