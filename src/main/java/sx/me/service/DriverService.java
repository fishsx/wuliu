package sx.me.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;
import sx.me.po.DriverInfo;

import java.sql.Date;
import java.util.List;

/**
 * Created by sx on 2017/7/14.
 */
@Service
public class DriverService {
    @Autowired
    private HibernateTemplate hibernateTemplate;
    @Autowired
    private CompanyService companyService;

    public boolean addDriver(DriverInfo driverInfo){
        hibernateTemplate.save(driverInfo);
        return true;
    }

    public List<DriverInfo> findAllDriver(){
        List<DriverInfo> data = (List<DriverInfo>) hibernateTemplate.find("from DriverInfo ");
        return data.isEmpty() ? null :data;
    }

    public DriverInfo findDriverById(int driverId){
        List<DriverInfo> data = (List<DriverInfo>) hibernateTemplate.find("from DriverInfo where driverId = ?",driverId);
        return data.isEmpty() ? null : data.get(0);
    }

    public List<DriverInfo> findDriverBy_Name_license_company(String info){
        String hql1 = "from DriverInfo where driverLicense = ? or  driverName like ? or companyByDriverCompany.companyName like ? ";
        List<DriverInfo> data = (List<DriverInfo>) hibernateTemplate.find(hql1,info,'%'+info+'%','%'+info+'%');
        return data.isEmpty() ? null : data;
    }

    public List<DriverInfo> findDriver_kongxian(){
        List<DriverInfo> data = (List<DriverInfo>) hibernateTemplate.find("from DriverInfo where driverState = 0");
        return data.isEmpty() ? null : data;
    }


    //state状态 -2离职 -1休假 0空闲 1工作中
    public boolean updateDriver(int driverId, String name, String sex, Date birthday,String licenseType,String license,int companyId,int state){
        DriverInfo driver = findDriverById(driverId);
        driver.setDriverName(name);
        driver.setDriverSex(sex);
        driver.setDriverBirthday(birthday);
        driver.setDriverLicenseType(licenseType);
        driver.setDriverLicense(license);
        driver.setCompanyByDriverCompany(companyService.findByCompanyById(companyId));
        driver.setDriverState(state);
        hibernateTemplate.update(driver);
        return true;

    }
    public boolean updateDriverState(int driverId,int state){
        DriverInfo driver = findDriverById(driverId);
        driver.setDriverState(state);
        hibernateTemplate.update(driver);
        return true;
    }


    public boolean delDriver(int driverId){
        int res = hibernateTemplate.bulkUpdate("delete from DriverInfo where driverId = ?",driverId);
        return res > 0;
    }
}
