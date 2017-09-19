package sx.me.po;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by sx on 2017/7/11.
 */
@Entity
@Table(name = "traffic_price", schema = "wuliu", catalog = "")
public class TrafficPrice {
    private int trafficePriceId;
    private int startPlace;
    private int finalPlace;
    private Timestamp trafficTime;
    private int ruleFangliang;
    private int ruleDunliang;

    @Id
    @Column(name = "traffice_price_id", nullable = false)
    public int getTrafficePriceId() {
        return trafficePriceId;
    }

    public void setTrafficePriceId(int trafficePriceId) {
        this.trafficePriceId = trafficePriceId;
    }

    @Basic
    @Column(name = "start_place", nullable = false)
    public int getStartPlace() {
        return startPlace;
    }

    public void setStartPlace(int startPlace) {
        this.startPlace = startPlace;
    }

    @Basic
    @Column(name = "final_place", nullable = false)
    public int getFinalPlace() {
        return finalPlace;
    }

    public void setFinalPlace(int finalPlace) {
        this.finalPlace = finalPlace;
    }

    @Basic
    @Column(name = "traffic_time", nullable = false)
    public Timestamp getTrafficTime() {
        return trafficTime;
    }

    public void setTrafficTime(Timestamp trafficTime) {
        this.trafficTime = trafficTime;
    }

    @Basic
    @Column(name = "rule_fangliang", nullable = false)
    public int getRuleFangliang() {
        return ruleFangliang;
    }

    public void setRuleFangliang(int ruleFangliang) {
        this.ruleFangliang = ruleFangliang;
    }

    @Basic
    @Column(name = "rule_dunliang", nullable = false)
    public int getRuleDunliang() {
        return ruleDunliang;
    }

    public void setRuleDunliang(int ruleDunliang) {
        this.ruleDunliang = ruleDunliang;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrafficPrice that = (TrafficPrice) o;

        if (trafficePriceId != that.trafficePriceId) return false;
        if (startPlace != that.startPlace) return false;
        if (finalPlace != that.finalPlace) return false;
        if (ruleFangliang != that.ruleFangliang) return false;
        if (ruleDunliang != that.ruleDunliang) return false;
        if (trafficTime != null ? !trafficTime.equals(that.trafficTime) : that.trafficTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = trafficePriceId;
        result = 31 * result + startPlace;
        result = 31 * result + finalPlace;
        result = 31 * result + (trafficTime != null ? trafficTime.hashCode() : 0);
        result = 31 * result + ruleFangliang;
        result = 31 * result + ruleDunliang;
        return result;
    }
}
