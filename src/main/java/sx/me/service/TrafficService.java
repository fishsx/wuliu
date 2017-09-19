package sx.me.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;
import sx.me.po.TrafficBill;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sx on 2017/7/13.
 */
@Service
public class TrafficService {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public int addTrafficBill(TrafficBill trafficBill){
        return (int) hibernateTemplate.save(trafficBill);

    }

    public List<TrafficBill> findTrafficById(int trafficId){
        List<TrafficBill> data = (List<TrafficBill>) hibernateTemplate.find("from TrafficBill where trafficBillId = ?",trafficId);
        return data.isEmpty() ? null : data;
    }

    public List<TrafficBill> findAllTrafficBill(){
        List<TrafficBill> data = (List<TrafficBill>) hibernateTemplate.find("from TrafficBill");
        return data.isEmpty() ? null : data;
    }
    public List<TrafficBill> findTrafficBill_NoCarDriver(){
        List<TrafficBill> data = (List<TrafficBill>) hibernateTemplate.find("from TrafficBill where carInfoByCar = null or driverInfoByDriver = null");
        return data.isEmpty() ? null : data;
    }

    public boolean updateTrafficBill(TrafficBill trafficBill){
        hibernateTemplate.update(trafficBill);
        return true;
    }

    public List<TrafficBill> findTrafficBill_peisong(){
        List<TrafficBill> data = (List<TrafficBill>) hibernateTemplate.find("from TrafficBill where carInfoByCar !=null and driverInfoByDriver != null ");
        return data.isEmpty() ? null :data;
    }

    public boolean delTrafficBill(int trafficBillId){
        int res = hibernateTemplate.bulkUpdate("delete from TrafficBill where trafficBillId = ?",trafficBillId);
        return res > 0;
    }


}
