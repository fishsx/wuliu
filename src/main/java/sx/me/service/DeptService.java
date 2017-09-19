package sx.me.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;
import sx.me.po.Dept;
import sx.me.tools.isNum;
import java.util.List;

/**
 * Created by sx on 2017/7/10.
 */
@Service
public class DeptService {

    @Autowired
    private HibernateTemplate hibernateTemplate;


    public boolean addDept(Dept dept){
        hibernateTemplate.save(dept);
        return true;
    }
    public boolean updateDept(int deptId,String deptName){
        Dept dept = findDeptById(deptId);
        dept.setDeptName(deptName);
        hibernateTemplate.update(dept);
        return true;
    }
    public boolean delDept(int deptId){
        int flag = hibernateTemplate.bulkUpdate("delete from Dept where deptId = ?",deptId);
        return flag > 0;
    }

    public List<Dept> findAllDept(){
        List<Dept> data = (List<Dept>) hibernateTemplate.find("from Dept ");
        return data.isEmpty()? null : data;
    }

    public List<Dept> findDeptBy_Id_Name(String info){
        List<Dept> data = null;
        if(isNum.isNumeric(info)){
            data = (List<Dept>) hibernateTemplate.find("from Dept where deptId = ? ",Integer.parseInt(info));
        }
        else {
            data = (List<Dept>) hibernateTemplate.find("from Dept where deptName = ?",info);
        }
        return data.isEmpty() ? null : data;
    }

    public Dept findDeptById(int deptId){
        List<Dept> data = (List<Dept>) hibernateTemplate.find("from Dept where deptId = ?",deptId);
        return data.isEmpty()? null : data.get(0);
    }



}
