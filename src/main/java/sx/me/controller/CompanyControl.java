package sx.me.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import sx.me.po.Company;
import sx.me.service.CompanyService;

/**
 * Created by sx on 2017/5/22.
 */
@Controller
@RequestMapping("/company/")
public class CompanyControl {
    @Autowired
    private CompanyService companyService;


    @RequestMapping(value = "addCompany.do",method = RequestMethod.POST)
    public ModelAndView addCompanyAction(String name,String sheng,String shi,String qu,String jie,String leader,String phone){
        String address = sheng+"-" +shi+ "-"+qu+ "-"+jie;
        Company company = new Company(name,address,leader,phone);
        companyService.addCompany(company);
        ModelAndView mav_index = new ModelAndView("view/ziliaoPage/indexCompany");
        return mav_index.addObject("state",200);
    }
    @RequestMapping(value = "getCompanyInfo.do",method = RequestMethod.GET)
    public ModelAndView getCompanyInfo(int companyId){
        Company company = companyService.findByCompanyById(companyId);
        ModelAndView modelAndView = new ModelAndView("view/ziliaoPage/updateCompany");
        modelAndView.addObject("company",company);
        return modelAndView;
    }
    @RequestMapping(value = "updateCompany.do",method = RequestMethod.POST)
    public ModelAndView updateCompany(int oldId,String name,String sheng,String shi,String qu,String jie,String leader,String phone,String isSearch){
        String address =  sheng+"-" +shi+ "-"+qu+ "-"+jie;
        boolean res = companyService.updateCompany(oldId,name,address,leader,phone);
        ModelAndView modelAndView_index = new ModelAndView("view/ziliaoPage/indexCompany");
        ModelAndView modelAndView_search = new ModelAndView("view/ziliaoPage/searchCompany");
        if(isSearch.equals("")){
            return res ? modelAndView_index.addObject("state",200)
                    : modelAndView_index.addObject("state",500);        }
        else {
            return res ? modelAndView_search.addObject("state",200)
                    : modelAndView_search.addObject("state",500);
        }
    }
    @RequestMapping(value = "delCompany.do" ,method = RequestMethod.GET)
    public ModelAndView delCompany(int companyId,String isSearch){
        boolean res = companyService.delCompany(companyId);
        ModelAndView modelAndView_index = new ModelAndView("view/ziliaoPage/indexCompany");
        ModelAndView modelAndView_search = new ModelAndView("view/ziliaoPage/searchCompany");
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
