package sx.me.jsonData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sx.me.po.*;
import sx.me.service.*;
import sx.me.tools.isNum;
import java.sql.Driver;
import java.util.List;

/**
 * Created by sx on 2017/7/11.
 */
@Controller
@RequestMapping(value = "/data/")
public class jsonData {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private UserService userService;
    @Autowired
    private FactoryService factoryService;
    @Autowired
    private BillService billService;
    @Autowired
    private CarService carService;
    @Autowired
    private DriverService driverService;
    @Autowired
    private TrafficService trafficService;



//————————————————————————————————————————————————————Company———————————————————————————————————————————————————————————

    @ResponseBody
    @RequestMapping(value = "allCompany.json")
    public List<Company> allCompany(){
        List<Company> data = companyService.findAllCompany();
        return data;
    }

    @ResponseBody
    @RequestMapping(value = "findCompany.json/{info}")
    public List<Company> findCompany(@PathVariable String info){
        List<Company> data = companyService.findCompanyById_Name(info);
        return data;
    }
//————————————————————————————————————————————————————User——————————————————————————————————————————————————————————————

    @ResponseBody
    @RequestMapping(value = "checkPwd.json",method = RequestMethod.POST)
    public boolean checkPwd( int uid, String oldpwd){
        User user = userService.findUserById(uid);
        return user.getUpwd().equals(oldpwd);
    }

    @ResponseBody
    @RequestMapping(value = "allUser.json")
    public List<User> allUser(){
        List<User> data = userService.findAllUser();
        return data;
    }

    @ResponseBody
    @RequestMapping(value = "findUser.json/{info}")
    public List<User> findUser(@PathVariable String info){
        List<User> data = userService.findUserBy_Id_Name(info);
        return data;
    }

    @ResponseBody
    @RequestMapping(value = "allDept.json")
    public List<Dept> allDept(){
        List<Dept> data = companyService.allDept();
        return data;
    }



    @ResponseBody
    @RequestMapping(value = "allFactory.json")
    public List<Factory> allFactory(){
        List<Factory> data = factoryService.allFactory();
        return data;
    }
//————————————————————————————————————————————————————Role——————————————————————————————————————————————————————————————

    @ResponseBody
    @RequestMapping(value = "allRole.json")
    public List<Role> allRole(){
        List<Role> data = userService.allStateRole();
        return data;
    }

    @ResponseBody
    @RequestMapping(value = "allRoot.json")
    public List<?> allRoot(){
        List<?> data = userService.allUserANDRoleRootList();
        return data;
    }

//————————————————————————————————————————————————————Bill——————————————————————————————————————————————————————————————


    @ResponseBody
    @RequestMapping(value = "allBill.json")
    public List<WayBill> allBill(){
        List<WayBill> data = billService.allBill();
        return data;
    }


    @ResponseBody
    @RequestMapping(value = "findBillById_Tid.json/{info}")
    public List<WayBill> findBill(@PathVariable String info){
        List<WayBill> data =  billService.findBillBy_ID_tID(info);
        return data;
    }

    @ResponseBody
    @RequestMapping(value = "findBill_weigujia.json")
    public List<WayBill> findBill_weigujia(){
        List<WayBill> data = billService.findBill_weigujia();
        return data;
    }

    //所有已收讫待完成货票
    @ResponseBody
    @RequestMapping(value = "findBill_shouqi.json")
    public List<WayBill> findBill_shouqi(){
        List<WayBill> data = billService.findBillShouqi();
        return data;
    }

    //所有已收讫货票
    @ResponseBody
    @RequestMapping(value = "findAllBill_yishouqi.json")
    public List<WayBill> findAllBill_yishouqi(){
        List<WayBill> data = billService.findAllBillShouqi();
        return data;
    }

    //所有未收讫货票
    @ResponseBody
    @RequestMapping(value = "findBill_weiShouqi.json")
    public List<WayBill> findBill_weiShouqi(){
        List<WayBill> data = billService.findBill_weishouqi();
        return data;
    }
    //所有赔付的货票
    @ResponseBody
    @RequestMapping(value = "findBill_peifu.json")
    public List<GoodsPeiqian> findBill_peifu(){
        List<GoodsPeiqian> data = billService.findBill_peifu();
        return data;
    }
    //所有货损的货票
    @ResponseBody
    @RequestMapping(value = "findBill_damage")
    public List<GoodsDamage> findBill_damage(){
        List<GoodsDamage> data = billService.findAllGoodDamage();
        return data;
    }
    //根据id查找货损货票
    @ResponseBody
    @RequestMapping(value = "findBill_damage_byId/{info}")
    public List<GoodsDamage> findBill_damage_byId(@PathVariable int info){
         List<GoodsDamage> data = billService.findBill_damage_byId(info);
         return data;
    }


//————————————————————————————————————————————————————TrafficBill———————————————————————————————————————————————————————


    @ResponseBody
    @RequestMapping(value = "allTrafficBill.json")
    public List<TrafficBill> allTrafficBill(){
        List<TrafficBill> data = trafficService.findAllTrafficBill();
        return data;
    }

    @ResponseBody
    @RequestMapping(value = "findTrafficBill_NoCarDriver.json")
    public List<TrafficBill> findTrafficBill_NoCarDriver(){
        List<TrafficBill> data = trafficService.findTrafficBill_NoCarDriver();
        return data;
    }

    @ResponseBody
    @RequestMapping(value = "findTrafficBillById.json/{info}")
    public List<TrafficBill> findTrafficBillById(@PathVariable String info){
         if(isNum.isNumeric(info)) {
             List<TrafficBill> data = trafficService.findTrafficById(Integer.parseInt(info));
             return data;
         }
         return null;
    }

    @ResponseBody
    @RequestMapping(value = "findTrafficBill_peisong.json")
    public List<TrafficBill> findTrafficBill_peisong(){
        List<TrafficBill> data = trafficService.findTrafficBill_peisong();
        return data;
    }

//————————————————————————————————————————————————————Car——————————————————————————————————————————————————————————————

    @ResponseBody
    @RequestMapping(value = "allCar.json")
    public List<CarInfo> allCar(){
        List<CarInfo> data = carService.findAllCar();
        return data;
    }

    @ResponseBody
    @RequestMapping(value = "findCar.json/{info}")
    public List<CarInfo> findCar(@PathVariable String info){
        List<CarInfo> data = carService.findCarBy_chepai_license_company(info);
        return data;
    }
    @ResponseBody
    @RequestMapping(value = "findCar_kongxian.json")
    public List<CarInfo> findCar_kongxian(){
        List<CarInfo> data = carService.findCar_kongxian();
        return data;
    }

//————————————————————————————————————————————————————Driver————————————————————————————————————————————————————————————
    @ResponseBody
    @RequestMapping(value = "allDriver.json")
    public List<DriverInfo> allDriver(){
       List<DriverInfo> data = driverService.findAllDriver();
       return data;
    }

    @ResponseBody
    @RequestMapping(value = "findDriver.json/{info}")
    public List<DriverInfo> findDriver(@PathVariable String info){
        List<DriverInfo> data = driverService.findDriverBy_Name_license_company(info);
        return data;
    }

    @ResponseBody
    @RequestMapping(value = "findDriver_kongxian.json")
    public List<DriverInfo> findDriver_kongxian(){
        List<DriverInfo> data = driverService.findDriver_kongxian();
        return data;
    }



}
