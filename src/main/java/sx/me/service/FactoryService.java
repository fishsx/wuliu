package sx.me.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;
import sx.me.po.Factory;

import java.util.List;

/**
 * Created by sx on 2017/7/13.
 */
@Service
public class FactoryService {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public boolean addFactory(Factory factory){
        hibernateTemplate.save(factory);
        return true;
    }


    public List<Factory> allFactory(){
        List<Factory> data = (List<Factory>) hibernateTemplate.find("from Factory ");
        return data.isEmpty() ? null : data;
    }

    public Factory findFactoryById(int factoryId){
        List<Factory> data = (List<Factory>) hibernateTemplate.find("from Factory where factoryId = ?",factoryId);
        return data.isEmpty() ? null :data.get(0);
    }
}
