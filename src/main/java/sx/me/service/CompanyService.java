package sx.me.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;
import sx.me.po.Company;
import sx.me.po.Dept;
import sx.me.tools.isNum;
import java.util.List;

/**
 * Created by sx on 2017/5/22.
 */
@Service
public class CompanyService {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    public boolean addCompany(Company company){
        hibernateTemplate.save(company);
        return true;
    }

    public boolean updateCompany(int companyId,String companyName,String companyAddress,
                                 String companyLeader,String companyPhone){
    Company company = findByCompanyById(companyId);
    company.setCompanyName(companyName);
    company.setCompanyAddress(companyAddress);
    company.setCompanyLeader(companyLeader);
    company.setCompanyPhone(companyPhone);
    hibernateTemplate.update(company);
    return true;
    }
    public Company findByCompanyById(int companyId){

        List<Company> data = (List<Company>) hibernateTemplate.find("from Company where companyId = ?",companyId);
        return data.isEmpty()? null : data.get(0);
    }

    public List<Company> findCompanyById_Name(String info){
        String hql1 = "from Company where companyId = ? ";
        String hql2 = "from Company where companyName like ?";
        List<Company> data = null;
        if(isNum.isNumeric(info)){
            data = (List<Company>) hibernateTemplate.find(hql1,Integer.parseInt(info));
        }
        else {
            data = (List<Company>) hibernateTemplate.find(hql2,'%'+info+'%');
        }

        return data.isEmpty()? null : data;

    }

    public List<Company> findAllCompany(){
        List<Company> data = (List<Company>) hibernateTemplate.find("from Company ");
        return data.isEmpty()? null : data;
    }

    public boolean delCompany(int companyId){
        int flag = hibernateTemplate.bulkUpdate("delete from Company where companyId = ?",companyId);
        return flag > 0;
    }

    public List<Dept> allDept(){
        List<Dept> data = (List<Dept>) hibernateTemplate.find("from Dept ");
        return data.isEmpty()? null : data;
    }
}
