package sx.me.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import sx.me.po.Role;
import sx.me.po.User;
import sx.me.service.CompanyService;
import sx.me.service.DeptService;
import sx.me.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sx on 2017/5/22.
 */
@Controller
@RequestMapping("/user/")
public class UserControl {
    @Autowired
    private UserService userService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private DeptService deptService;



    @RequestMapping(value = "loginCheck", method = RequestMethod.POST)
    public String checkLogin(String uname, String upwd, int role_id,HttpSession session) {
        User user = userService.login(uname, upwd, role_id);
        Role role = userService.findRoleByRid(role_id);
        if (user != null) {
            session.setAttribute("user",user);
            session.setAttribute("role",role);
            return "redirect:/index.jsp" ;
        } else
            return ("redirect:/login.jsp");

    }

    @RequestMapping(value = "userQuit.do",method = RequestMethod.GET)
    public String userQuit(HttpSession session){
        session.removeAttribute("user");
        session.removeAttribute("role");
        return "redirect:/login.jsp";
    }
    @RequestMapping(value = "updatePwd.do",method = RequestMethod.POST)
    public ModelAndView updatePwd(int uid,String pwd){
        User user = userService.findUserById(uid);
        user.setUpwd(pwd);
        userService.updateUser(user);
        return new ModelAndView("view/ziliaoPage/indexCompany").addObject("state",200);
    }
//    @RequestMapping(value = "InfoPage/RootManage",method = RequestMethod.GET)
//    public ModelAndView editRoot(){
//        List<?> data = userService.allUserANDRoleRootList();
//
//        List<Role> role = userService.allStateRole();
//        ModelAndView modelAndView =new ModelAndView("InfoPage/RootManage");
//        modelAndView.addObject("roleUserANDRootList",data);
//        modelAndView.addObject("role",role);
//        return modelAndView;
//    }

    @RequestMapping(value = "addUser.do",method = RequestMethod.POST)
    public ModelAndView addUser(String name,String tname,String pwd,String phone,int companyId,int deptId ){
        User user = new User(name,tname,pwd,phone,companyService.findByCompanyById(companyId),deptService.findDeptById(deptId));
        userService.addUser(user);
        ModelAndView modelAndView = new ModelAndView("view/ziliaoPage/indexUser");
        modelAndView.addObject("state",200);
        return modelAndView;
    }
    @RequestMapping(value = "getUserInfo.do",method = RequestMethod.GET)
    public ModelAndView getUserInfo(int userId){
        User user = userService.findUserById(userId);
        ModelAndView modelAndView = new ModelAndView("view/ziliaoPage/updateUser");
        modelAndView.addObject("user",user);
        return modelAndView;
    }

    @RequestMapping(value = "updateUser.do",method = RequestMethod.POST)
    public ModelAndView updateUser(int oldId,String name,String tname,String pwd,String phone,int companyId,int deptId,String isSearch){
        boolean res = userService.updateUser(oldId,name,tname,pwd,phone,companyId,deptId);
        ModelAndView modelAndView_index = new ModelAndView("view/ziliaoPage/indexUser");
        ModelAndView modelAndView_search = new ModelAndView("view/ziliaoPage/searchUser");
        if(isSearch.equals("")){
            return res ? modelAndView_index.addObject("state",200)
                    : modelAndView_index.addObject("state",500);        }
        else {
            return res ? modelAndView_search.addObject("state",200)
                    : modelAndView_search.addObject("state",500);
        }
    }

    @RequestMapping(value = "delUser.do",method = RequestMethod.GET)
    public ModelAndView delUser(int userId,String isSearch){
        boolean res = userService.delUser(userId);
        ModelAndView modelAndView_index = new ModelAndView("view/ziliaoPage/indexUser");
        ModelAndView modelAndView_search = new ModelAndView("view/ziliaoPage/searchUser");
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