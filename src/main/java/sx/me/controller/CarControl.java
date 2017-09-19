package sx.me.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import sx.me.po.CarInfo;
import sx.me.service.CarService;
import sx.me.service.CompanyService;

/**
 * Created by sx on 2017/7/14.
 */
@Controller
@RequestMapping(value = "/car/")
public class CarControl {

    @Autowired
    private CarService carService;
    @Autowired
    private CompanyService companyService;

    @RequestMapping(value = "addCar.do",method = RequestMethod.POST)
    public ModelAndView addCar (String type,int fangliang,int dunliang,String chepai,String license,int companyId){
        CarInfo car = new CarInfo(type,fangliang,dunliang,chepai,license,0,companyService.findByCompanyById(companyId));
        carService.addCar(car);
        return new ModelAndView("view/peisongPage/indexCar").addObject("state",200);
    }

    @RequestMapping(value = "getCarInfo.do",method = RequestMethod.GET)
    public ModelAndView getCarInfo(int carId){
        CarInfo car = carService.findCarById(carId);
        ModelAndView modelAndView = new ModelAndView("view/peisongPage/updateCar");
        modelAndView.addObject("car",car);
        return modelAndView;
    }
    @RequestMapping(value = "updateCar.do",method = RequestMethod.POST)
    public ModelAndView updateCar(int oldId,String type,int fangliang,int dunliang,String chepai,String license,int state,int companyId,String isSearch){
        boolean res = carService.updateCar(oldId,type,fangliang,dunliang,chepai,license,state,companyService.findByCompanyById(companyId));
        ModelAndView modelAndView_index = new ModelAndView("view/peisongPage/indexCar");
        ModelAndView modelAndView_search = new ModelAndView("view/peisongPage/searchCar");
        if(isSearch.equals("")){
            return res ? modelAndView_index.addObject("state",200)
                    : modelAndView_index.addObject("state",500);        }
        else {
            return res ? modelAndView_search.addObject("state",200)
                    : modelAndView_search.addObject("state",500);
        }
    }
    @RequestMapping(value = "delCar.do" ,method = RequestMethod.GET)
    public ModelAndView delCar(int carId,String isSearch){
        boolean res = carService.delCar(carId);
        ModelAndView modelAndView_index = new ModelAndView("view/peisongPage/indexCar");
        ModelAndView modelAndView_search = new ModelAndView("view/peisongPage/searchCar");
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
