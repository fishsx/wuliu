package sx.me.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;
import sx.me.po.Goods;
import sx.me.po.GoodsDamage;
import sx.me.po.GoodsPeiqian;
import sx.me.po.WayBill;

import java.util.List;
import sx.me.tools.isNum;

/**
 * Created by sx on 2017/7/13.
 */
@Service
public class BillService {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public int addBill(WayBill wayBill){
        return (int) hibernateTemplate.save(wayBill);
    }
    public WayBill findLastBill(){
        List<WayBill> data = (List<WayBill>) hibernateTemplate.find(" from WayBill w");
        return data.isEmpty()? null : data.get(data.size()-1);
    }
    public boolean updateBillInfo(WayBill newBill){
        hibernateTemplate.update(newBill);
        return true;
    }

    public List<WayBill> allBill(){
        List<WayBill> data = (List<WayBill>) hibernateTemplate.find("from WayBill ");
        return data.isEmpty() ? null :data;

    }

    public WayBill findBillById(int billId){
        List<WayBill> data = (List<WayBill>) hibernateTemplate.find("from WayBill where wayBillId = ? ",billId);
        return data.isEmpty() ? null : data.get(0);
    }

    public List<WayBill> findBillBy_ID_tID(String info){
        if  (info!=null && isNum.isNumeric(info)){
            List<WayBill> data = (List<WayBill>) hibernateTemplate.find("from WayBill  where wayBillId = ? or billTrafficId = ?",Integer.parseInt(info),Integer.parseInt(info));
            return data.isEmpty()? null : data;
        }
        else return null;
    }
    public List<WayBill> findBillByTrafficId(int trafficId){
        List<WayBill> data = (List<WayBill>) hibernateTemplate.find("from WayBill where billTrafficId = ?",trafficId);
        return data.isEmpty() ? null : data;
    }
    //查找所有已收讫待完成货票
    public List<WayBill> findBillShouqi(){
        List<WayBill> data = (List<WayBill>) hibernateTemplate.find("from WayBill where billPayState = 1 and billState = 2");
        return data.isEmpty() ? null : data;
    }
    //查找所有已收讫货票
    public List<WayBill> findAllBillShouqi(){
        List<WayBill> data = (List<WayBill>) hibernateTemplate.find("from WayBill where billPayState = 1");
        return data.isEmpty() ? null : data;
    }
    //查找所有未收讫货票
    public List<WayBill> findBill_weishouqi(){
        List<WayBill> data = (List<WayBill>) hibernateTemplate.find("from WayBill where billPayState != 1");
        return data.isEmpty() ? null : data;
    }

    //-1未发货 1已发货 2已收货 3已完成
    public boolean updateBillState(int billId,int stateCode){
        WayBill bill = findBillById(billId);
        bill.setBillState(stateCode);
        hibernateTemplate.update(bill);
        return true;
    }

    //支付方式 -1未支付 1支付完
    public boolean updateBillPayState(int billId,int stateCode){
        WayBill bill = findBillById(billId);
        bill.setBillPayState(stateCode);
        hibernateTemplate.update(bill);
        return true;
    }
    //结算方式 -1提付 1收讫
    public boolean updateBillPayType(int billId,int state){
        WayBill bill = findBillById(billId);
        bill.setBillPayType(state);
        hibernateTemplate.update(bill);
        return true;
    }


    //货损登记
    public boolean addGoodDamage(GoodsDamage goodsDamage){
        hibernateTemplate.save(goodsDamage);
        return true;
    }
    //根据id查询货损记录
    public GoodsDamage findGoodDamageById(int billId){
        List<GoodsDamage> data = (List<GoodsDamage>) hibernateTemplate.find("from GoodsDamage where wayBillByBillId.wayBillId = ?",billId);
        return data.isEmpty() ? null :data.get(0);
    }

    //显示所有未估价货票（生成货票时，默认设置估价为-1，将保价为-1的货票查询出即可）
    public List<WayBill> findBill_weigujia(){
        List<WayBill> data = (List<WayBill>) hibernateTemplate.find("from WayBill w where w.goodsByBillGoodId.goodBaojia = -1");
        return data.isEmpty() ? null :data;
    }

    //赔付登记
    public boolean addGoodPeiqian(GoodsPeiqian goodsPeiqian){
        hibernateTemplate.save(goodsPeiqian);
        return true;
    }
    //赔付修改信息
    public boolean updateGoodPeiqian(GoodsPeiqian goodsPeiqian){
        hibernateTemplate.update(goodsPeiqian);
        return true;
    }

    //显示所有货损记录
    public List<GoodsDamage> findAllGoodDamage(){
        List<GoodsDamage> data = (List<GoodsDamage>) hibernateTemplate.find("from GoodsDamage");
        return data.isEmpty() ? null : data;
    }

    //显示所有赔付记录的货票
    public List<GoodsPeiqian> findBill_peifu(){
        List<GoodsPeiqian> data = (List<GoodsPeiqian>) hibernateTemplate.find("from GoodsPeiqian ");
        return data.isEmpty() ? null : data;
    }
    //根据id查找赔付记录
    public GoodsPeiqian findBill_peifu_byId(int billId){
        List<GoodsPeiqian> data = (List<GoodsPeiqian>) hibernateTemplate.find("from GoodsPeiqian where wayBillByWayBillId.wayBillId = ?",billId);
        return data.isEmpty() ? null : data.get(0);
    }
    //根据id查找货损货票
    public List<GoodsDamage> findBill_damage_byId(int billId){
        List<GoodsDamage> data = (List<GoodsDamage>) hibernateTemplate.find("from GoodsDamage where wayBillByBillId.wayBillId = ?",billId);
        return data.isEmpty() ? null : data;
    }
}
