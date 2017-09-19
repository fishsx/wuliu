package sx.me.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;
import sx.me.po.Goods;

import java.util.List;

/**
 * Created by sx on 2017/7/13.
 */
@Service
public class GoodsService {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    public int addGoods(Goods goods){
        return (int) hibernateTemplate.save(goods);
    }

    public Goods findgoodsById(int goodId){
        List<Goods> data = (List<Goods>) hibernateTemplate.find("from Goods where goodId = ?",goodId);
        return data.isEmpty() ? null : data.get(0);
    }

    public boolean updateGoodsBaojia(int goodId,double baojia){
        Goods goods = findgoodsById(goodId);
        goods.setGoodBaojia(baojia);
        hibernateTemplate.update(goods);
        return true;
    }
}
