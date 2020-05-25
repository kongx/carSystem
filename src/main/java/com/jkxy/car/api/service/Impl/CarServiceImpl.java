package com.jkxy.car.api.service.Impl;

import com.jkxy.car.api.dao.CarDao;
import com.jkxy.car.api.pojo.BuyCarVO;
import com.jkxy.car.api.pojo.Car;
import com.jkxy.car.api.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("carService")
public class CarServiceImpl implements CarService {
    @Autowired
    private CarDao carDao;

    @Override
    public List<Car> findAll() {
        return carDao.findAll();
    }

    @Override
    public Car findById(int id) {
        return carDao.findById(id);
    }

    @Override
    public List<Car> findByCarName(String carName) {
        return carDao.findByCarName(carName);
    }

    @Override
    public void deleteById(int id) {
        carDao.deleteById(id);
    }

    @Override
    public void updateById(Car car) {
        carDao.updateById(car);
    }

    @Override
    public void insertCar(Car car) {
        carDao.insertCar(car);
    }

    @Override
    public String buyCar(BuyCarVO buyCarVO) {
        String result;
        int inventory=0;
        if(buyCarVO != null){
            if(buyCarVO.getCarName()!=null && buyCarVO.getCarName()!=""){
                inventory=carDao.selectCarInventoryByCarName(buyCarVO.getCarName());
            }
        }
        int newInventory=inventory-buyCarVO.getBuyNum();
        if(newInventory >=0){
            carDao.updateCarInventory(newInventory,buyCarVO.getCarName());
            result="成功购买"+buyCarVO.getBuyNum()+"辆"+buyCarVO.getCarName();
        }else {
            result="库存不足，购车失败！";
        }
        return result;
    }

    @Override
    public List<Car> findLikeCarName(String carName) {
        return carDao.findLikeCarName(carName);
    }
}
