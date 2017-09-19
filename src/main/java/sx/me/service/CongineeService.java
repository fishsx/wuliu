package sx.me.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;
import sx.me.po.Consignee;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sx on 2017/7/13.
 */
@Service
public class CongineeService {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    public int addConsignee(Consignee consignee){
        return (int) hibernateTemplate.save(consignee);


    }
    public Consignee findLastConsignee(){
        List<Consignee> data = (List<Consignee>) hibernateTemplate.find("from Consignee c ");
        return data.isEmpty()? null : data.get(data.size()-1);
    }


    public Consignee findConsigneeById(int consigneeId){
        List<Consignee> data = (List<Consignee>) hibernateTemplate.find("from Consignee where consigneeId = ?",consigneeId);
        return data.isEmpty()? null : data.get(0);
    }
}
