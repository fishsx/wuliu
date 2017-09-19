package sx.me.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import sx.me.po.GoodsDamage;
import sx.me.po.GoodsPeiqian;
import sx.me.po.TrafficBill;
import sx.me.po.WayBill;
import sx.me.service.*;
import sx.me.tools.dateTraf;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sx on 2017/7/15.
 */
@Controller
@RequestMapping("/bill/")
public class billControl {

    @Autowired
    private BillService billService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private TrafficService trafficService;
    @Autowired
    private CarService carService;
    @Autowired
    private DriverService driverService;

    @RequestMapping(value = "billGujia.do",method = RequestMethod.POST)
    public ModelAndView billGujia(int goodId,double baojia){
        goodsService.updateGoodsBaojia(goodId,baojia);
        return new ModelAndView("view/piaowuPage/billWeigujia")
                .addObject("state",200);
    }

    @RequestMapping(value = "getBillInfo.do",method = RequestMethod.GET)
    public ModelAndView getBillInfo(int billId){
        WayBill bill = billService.findBillById(billId);
        return new ModelAndView("view/piaowuPage/billGujia")
                .addObject("bill",bill);
    }

    @RequestMapping(value = "getTrafficBillInfo.do",method = RequestMethod.GET)
    public ModelAndView getTrafficBillInfo(int trafficBillId){
        TrafficBill data = trafficService.findTrafficById(trafficBillId).get(0);
        WayBill bill = billService.findBillByTrafficId(trafficBillId).get(0);
        return new ModelAndView("view/piaowuPage/addTrafficBill")
                        .addObject("trafficBill",data).addObject("bill",bill);

    }

    @RequestMapping(value = "updateTrafficBill_CarDriver.do",method = RequestMethod.POST)
    public ModelAndView updateCarDriver(int trafficBillId, int carId, int driverId, String startDate, String finalDate){
        TrafficBill data = trafficService.findTrafficById(trafficBillId).get(0);
        data.setCarInfoByCar(carService.findCarById(carId));
        data.setDriverInfoByDriver(driverService.findDriverById(driverId));

        startDate += ":00";
        startDate = startDate.replace("T"," ");
        Timestamp start = Timestamp.valueOf(startDate);
        finalDate += ":00";
        finalDate = finalDate.replace("T"," ");
        Timestamp end = Timestamp.valueOf(finalDate);

        data.setStartDate(start);
        data.setFinalDate(end);
        trafficService.updateTrafficBill(data);
        carService.updateCarState(carId,1);
        driverService.updateDriverState(driverId,1);
        return new ModelAndView("view/piaowuPage/indexTrafficBill").addObject("state",200);
    }
    @RequestMapping(value = "delTrafficBill.do",method = RequestMethod.GET)
    public ModelAndView delTrafficBill(int trafficBillId){
        boolean res = trafficService.delTrafficBill(trafficBillId);
        ModelAndView modelAndView = new ModelAndView("view/piaowuPage/indexTrafficBill");
        return res ? modelAndView.addObject("state",200) :
                modelAndView.addObject("state",500);
    }
    //开始配送
    @RequestMapping(value = "startPeisong.do",method = RequestMethod.GET)
    public ModelAndView startPeisong (int trafficBillId){
        WayBill bill = billService.findBillByTrafficId(trafficBillId).get(0);
        billService.updateBillState(bill.getWayBillId(),1);
        return new ModelAndView("view/peisongPage/marketPeisong");
    }

    //货票冲减
    @RequestMapping(value = "chongjianBill.do",method = RequestMethod.GET)
    public ModelAndView chongjianBill(int billId){
        billService.updateBillState(billId,3);
        return new ModelAndView("view/piaowuPage/billChongjian");
    }
    //货票收讫
    @RequestMapping(value = "shouqiBill.do",method = RequestMethod.GET)
    public ModelAndView shouqiBill(int billId){
        billService.updateBillPayState(billId,1);
        return new ModelAndView("view/caiwuPage/yushou");
    }

    @RequestMapping(value = "getBillInfo_peifu.do",method = RequestMethod.GET)
    public ModelAndView getBillInfo_peifu(int billId,int showInfo){
        WayBill wayBill =  billService.findBillById(billId);
        GoodsPeiqian goodsPeiqian = billService.findBill_peifu_byId(billId);
        GoodsDamage goodsDamage = billService.findGoodDamageById(billId);
        ModelAndView modelAndView = new ModelAndView("view/piaowuPage/peifuReg2");
        modelAndView.addObject("bill",wayBill);
        modelAndView.addObject("bill_peiqian",goodsPeiqian);
        modelAndView.addObject("showInfo",showInfo);
        modelAndView.addObject("dmgInfo",goodsDamage);
        return modelAndView;
    }

    //赔付登记
    @RequestMapping(value = "goodPeiqian.do",method = RequestMethod.POST)
    public ModelAndView goodPeiqian(int billId,double money){
        GoodsPeiqian goodsPeiqian = new GoodsPeiqian(money,0,billService.findBillById(billId));
        billService.addGoodPeiqian(goodsPeiqian);
        WayBill bill = billService.findBillById(billId);
        bill.setBillPeiqian(money);
        billService.updateBillInfo(bill);
        return new ModelAndView("view/piaowuPage/peifuReg").addObject("state",200);
    }

    //财务赔付相应金额
    @RequestMapping(value = "caiwuPeiqian.do",method = RequestMethod.GET)
    public ModelAndView caiwuPeiqian(int billId){
        GoodsPeiqian goodsPeiqian = billService.findBill_peifu_byId(billId);
        goodsPeiqian.setPeiqianState(1);
        billService.updateGoodPeiqian(goodsPeiqian);
        WayBill bill = billService.findBillById(billId);
        bill.setBillPeiqian(0.00);
        billService.updateBillInfo(bill);
        return new ModelAndView("view/caiwuPage/peifu").addObject("state",200);
    }
    //核算查看详情
    @RequestMapping(value = "getBillInfo_hesuan.do",method = RequestMethod.GET)
    public ModelAndView getBillInfo_hesuan(int billId){
        WayBill wayBill =  billService.findBillById(billId);
        GoodsPeiqian goodsPeiqian = billService.findBill_peifu_byId(billId);
        GoodsDamage goodsDamage = billService.findGoodDamageById(billId);
        ModelAndView modelAndView = new ModelAndView("view/caiwuPage/showInfoDetail");
        modelAndView.addObject("bill",wayBill);
        modelAndView.addObject("bill_peiqian",goodsPeiqian);
        modelAndView.addObject("dmgInfo",goodsDamage);
        return modelAndView;
    }

}
