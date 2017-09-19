package sx.me.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;
import sx.me.po.Role;
import sx.me.po.User;
import sx.me.tools.isNum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sx on 2017/5/22.
 */
@Service
public class UserService {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Autowired
    private CompanyService companyService;
    @Autowired
    private DeptService deptService;

    public boolean addUser(User user){

        hibernateTemplate.save(user);
        return true;
    }
    public boolean updateUser(int oldId,String name,String tname,String pwd,String phone,int companyId,int deptId){
        User user = findUserById(oldId);
        user.setUname(name);
        user.setTname(tname);
        user.setUpwd(pwd);
        user.setCompanyByCompanyId(companyService.findByCompanyById(companyId));
        user.setDeptByDeptId(deptService.findDeptById(deptId));
        hibernateTemplate.update(user);
        return true;
    }
    public boolean updateUser(User user){
        hibernateTemplate.update(user);
        return true;
    }

    public List<User> findUserBy_Id_Name(String info){
        String hql1 = "from User where uid = ? ";
        String hql2 = "from User where tname like ?";
        List<User> data = null;
        if(isNum.isNumeric(info)){
            data = (List<User>) hibernateTemplate.find(hql1,Integer.parseInt(info));
        }
        else {
            data = (List<User>) hibernateTemplate.find(hql2,'%'+info+'%');
        }

        return data.isEmpty()? null : data;
    }

    public User login(String uname,String upwd,int rootId){
        User myUser = findUserByUname(uname);
        if(myUser!=null){
            if(myUser.getUpwd().equals(upwd) && findRootByUnameANDRoot(uname,rootId)){
                return myUser;
        }
        }
        return null;
    }

    public List<User> findAllUser(){
        List<User> data = (List<User>) hibernateTemplate.find("from User");
        return data.isEmpty()? null : data;
    }

    public User findUserById(int uid){
        List<User> data = (List<User>) hibernateTemplate.find("from User where uid = ?",uid);
        return data.isEmpty()? null : data.get(0);
    }

    public User findUserByUname(String uname){
        String hql = "from User u where u.uname = ?";
        List<?> data = hibernateTemplate.find(hql,uname);
        return (!data.isEmpty()? (User)data.get(0) : null);
    }

//————————————————————————————————————————————————————role——————————————————————————————————————————————————————————————

    public boolean findRootByUnameANDRoot (String uName,int roleRoot){
        String hql = " from User u ,Role r,RoleUser ru where u.uname =? and r.roleRoot= ? ";
        List<?> data = hibernateTemplate.find(hql,uName,roleRoot);
        return !data.isEmpty();
    }
    public Role findRoleByRid(int rid){
        List<Role> data = (List<Role>) hibernateTemplate.find("from Role where roleId = ?",rid);
        return data.isEmpty() ? null : data.get(0);
    }
    public List<User> allUserRole(){
        List<User> data = (List<User>) hibernateTemplate.find("select distinct r.userByUserId from RoleUser r");
        return (!data.isEmpty()?data : null);
    }

    public List<Role> findRoleByUser(User user){
        String hql = "select ru.roleByRoleId from RoleUser ru where ru.userByUserId = ?";
        List<Role> data =(List<Role>) hibernateTemplate.find(hql,user);
        return (!data.isEmpty()?data : null);
    }

    public List<Role> allStateRole(){
        List<Role> data = (List<Role>) hibernateTemplate.find(" from Role ");
        return (!data.isEmpty()?data : null);
    }

    public List<?> allUserANDRoleRootList() {
        List<User> userList = allUserRole();
        List<Map> data = new ArrayList<>();

        if (userList != null) {
            for (User user : userList) {
                Map map = new HashMap();
                List<User> data_user = new ArrayList<>();
                data_user.add(user);
                map.put("user",data_user);
                map.put("root",findRoleByUser(user));
                data.add(map);
            }

            return data;
        }
        else
            return null;
    }

    public boolean delUser(int uid){
        int res = hibernateTemplate.bulkUpdate("delete from User where uid = ?",uid);
        return res > 0;
    }
}
