package sx.me.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;
import sx.me.po.CarInfo;
import sx.me.po.Company;

import java.util.List;

/**
 * Created by sx on 2017/7/14.
 */
@Service
public class CarService {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    public boolean addCar(CarInfo carInfo){
        hibernateTemplate.save(carInfo);
        return true;
    }
    //state -1停运中 0待命中 1作业中
    public boolean updateCar(int carId, String carType, int fangliang, int dunliang, String chepai, String carLicense, int state, Company company){
        CarInfo car = findCarById(carId);
        car.setCarType(carType);
        car.setCarFangliang(fangliang);
        car.setCarDunliang(dunliang);
        car.setCarChepai(chepai);
        car.setCarDriverLicense(carLicense);
        car.setCarState(state);
        car.setCompanyByCarCompany(company);
        hibernateTemplate.update(car);
        return true;

    }

    public boolean updateCarState(int carId,int state){
        CarInfo car = findCarById(carId);
        car.setCarState(state);
        hibernateTemplate.update(car);
        return true;
    }

    public CarInfo findCarById(int carId){
        List<CarInfo> data = (List<CarInfo>) hibernateTemplate.find("from CarInfo where carId = ?",carId);
        return data.isEmpty() ? null : data.get(0);
    }

    public List<CarInfo> findCarBy_chepai_license_company(String info){

        String hql1 = "from CarInfo c where c.carChepai = ? or c.carDriverLicense = ? or c.companyByCarCompany.companyName like ?";
        List<CarInfo> data =(List<CarInfo>) hibernateTemplate.find(hql1,info,info,'%'+info+'%');
        return data.isEmpty() ? null : data;

    }

    public List<CarInfo> findAllCar(){
        List<CarInfo> data = (List<CarInfo>) hibernateTemplate.find("from CarInfo ");
        return data.isEmpty() ? null : data;
    }

    public List<CarInfo> findCar_kongxian(){
        List<CarInfo> data = (List<CarInfo>) hibernateTemplate.find("from CarInfo where carState = 0");
        return data.isEmpty() ? null : data;
    }

    public boolean delCar(int carId){
        int res = hibernateTemplate.bulkUpdate("delete from CarInfo c where c.carId = ?",carId);
        return res > 0;
    }
}
