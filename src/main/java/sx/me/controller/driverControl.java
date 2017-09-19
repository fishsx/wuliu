package sx.me.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import sx.me.po.DriverInfo;
import sx.me.service.CompanyService;
import sx.me.service.DriverService;
import sx.me.tools.dateTraf;
/**
 * Created by sx on 2017/7/15.
 */
@Controller
@RequestMapping("/driver/")
public class driverControl {
    @Autowired
    private DriverService driverService;
    @Autowired
    private CompanyService companyService;

    @RequestMapping(value = "addDriver.do",method = RequestMethod.POST)
    public ModelAndView addDriver (String name,String sex,String birthday,String licenseType,String license,int companyId){
        DriverInfo driver = new DriverInfo(name,sex,dateTraf.strToDate(birthday),
                licenseType,license,0,companyService.findByCompanyById(companyId));
        driverService.addDriver(driver);
        return new ModelAndView("view/peisongPage/indexDriver").addObject("state",200);
    }

    @RequestMapping(value = "getDriverInfo.do",method = RequestMethod.GET)
    public ModelAndView getDriverInfo(int driverId){
        DriverInfo driver = driverService.findDriverById(driverId);
        ModelAndView modelAndView = new ModelAndView("view/peisongPage/updateDriver");
        modelAndView.addObject("driver",driver);
        return modelAndView;
    }
    @RequestMapping(value = "updateDriver.do",method = RequestMethod.POST)
    public ModelAndView updateDriver(int oldId,String name,String sex,String birthday,String licenseType,String license,int companyId,int state,String isSearch){
        boolean res = driverService.updateDriver(oldId,name,sex,dateTraf.strToDate(birthday),
                                        licenseType,license,companyId,state);
        ModelAndView modelAndView_index = new ModelAndView("view/peisongPage/indexDriver");
        ModelAndView modelAndView_search = new ModelAndView("view/peisongPage/searchDriver");
        if(isSearch.equals("")){
            return res ? modelAndView_index.addObject("state",200)
                    : modelAndView_index.addObject("state",500);        }
        else {
            return res ? modelAndView_search.addObject("state",200)
                    : modelAndView_search.addObject("state",500);
        }
    }
    @RequestMapping(value = "delDriver.do" ,method = RequestMethod.GET)
    public ModelAndView delDriver(int driverId,String isSearch){
        boolean res = driverService.delDriver(driverId);
        ModelAndView modelAndView_index = new ModelAndView("view/peisongPage/indexDriver");
        ModelAndView modelAndView_search = new ModelAndView("view/peisongPage/searchDriver");
        if(isSearch == null){
            return res ? modelAndView_index.addObject("state",200)
                    : modelAndView_index.addObject("state",500);
        }
        else {
            return res ?modelAndView_search.addObject("state",200)
                    : modelAndView_search.addObject("state",500);
        }
    }
    
    
}
