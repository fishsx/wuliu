package sx.me.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import sx.me.po.*;
import sx.me.service.*;

import java.util.List;

/**
 * Created by sx on 2017/7/13.
 */
@Controller
@RequestMapping("/shouhuo/")
public class shouhuoControl {

    @Autowired
    private FactoryService factoryService;
    @Autowired
    private TrafficService trafficService;
    @Autowired
    private CongineeService congineeService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private BillService billService;
    @Autowired
    private CarService carService;
    @Autowired
    private DriverService driverService;

    @RequestMapping(value = "addFactory.do",method = RequestMethod.POST)
    public ModelAndView addFactory(String name,String sheng,String shi,String qu,String jie,String leader,String email,String phone){
        String address =  sheng+"-" +shi+ "-"+qu+ "-"+jie;
        Factory factory = new Factory(name,leader,phone,email,address);
        boolean res = factoryService.addFactory(factory);
        ModelAndView modelAndView = new ModelAndView("view/shouhuoPage/shouhuoReg");
        return res ? modelAndView.addObject("state",200) :
                modelAndView.addObject("state",500);
    }

    @RequestMapping(value = "addBill.do",method = RequestMethod.POST)
    public ModelAndView addBill(int factoryId,String companyName,String name,String phone,String sheng,String shi,String qu,String jie,
                                String goodname,int goodruletype,String goodnumtype,int goodnum,double goodcount){
        String final_address =  sheng+"-" +shi+ "-"+qu+ "-"+jie;
        Factory factory = factoryService.findFactoryById(factoryId);
        TrafficBill trafficBill  = new TrafficBill(factory.getFactoryAddress(),final_address,null,null,null,null,null);
        int trafficBill_id = trafficService.addTrafficBill(trafficBill);

        Consignee consignee = new Consignee(name,companyName,final_address,phone);
        int conginee_id = congineeService.addConsignee(consignee);

        Goods goods = new Goods(goodname,goodruletype,goodnumtype,goodnum,goodcount,-1.00);
        int goods_id = goodsService.addGoods(goods);

        WayBill wayBill = new WayBill(factory.getFactoryAddress(),final_address,-1.00,-1.00,-1.00,-1,-1,-1,trafficBill_id,factory,
                                congineeService.findConsigneeById(conginee_id),goodsService.findgoodsById(goods_id));
        int bill_id = billService.addBill(wayBill);
        trafficBill.setWayBillByWayBillId(wayBill);
        trafficService.updateTrafficBill(trafficBill);
        ModelAndView modelAndView = new ModelAndView("view/shouhuoPage/succAddBill");
        modelAndView.addObject("bill",wayBill);
        return modelAndView;


    }

    @RequestMapping(value = "getBillInfo.do",method = RequestMethod.GET)
    public ModelAndView getBillInfo(int billId){
        WayBill data =  billService.findBillById(billId);
        return new ModelAndView("view/shouhuoPage/goodsConfirm").addObject("bill",data);
    }

    @RequestMapping(value = "updateBillState.do",method = RequestMethod.POST)
    public  ModelAndView updateBillState(int billId){
        billService.updateBillState(billId,2);
        billService.updateBillPayType(billId,1);
        int trafficId = billService.findBillById(billId).getBillTrafficId();
        TrafficBill trafficBill = trafficService.findTrafficById(trafficId).get(0);
        carService.updateCarState(trafficBill.getCarInfoByCar().getCarId(),0);
        driverService.updateDriverState(trafficBill.getDriverInfoByDriver().getDriverId(),0);
        return new ModelAndView("view/shouhuoPage/searchBill").addObject("state",200);

    }

    @RequestMapping(value = "getBillInfo2.do",method = RequestMethod.GET)
    public ModelAndView getBillInfo2(int billId,int showInfo){
        WayBill data =  billService.findBillById(billId);
        GoodsDamage dmgInfo = billService.findGoodDamageById(billId);
        ModelAndView modelAndView = new ModelAndView("view/shouhuoPage/goodDamageReg");
        modelAndView.addObject("showInfo",showInfo);
        modelAndView.addObject("bill",data);
        modelAndView.addObject("dmgInfo",dmgInfo);
        return modelAndView;
    }


    @RequestMapping(value = "addGoodDamageReg.do",method = RequestMethod.POST)
    public ModelAndView addGoodDamageReg(int billId,String reason,Double money){
        WayBill wayBill = billService.findBillById(billId);
        GoodsDamage goodsDamage = new GoodsDamage(reason,money,wayBill);
        billService.addGoodDamage(goodsDamage);
        ModelAndView modelAndView = new ModelAndView("view/shouhuoPage/searchBill2");
        modelAndView.addObject("state",200);
        return modelAndView;
    }

}
