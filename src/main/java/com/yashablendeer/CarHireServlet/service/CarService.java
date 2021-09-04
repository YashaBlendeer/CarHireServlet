package com.yashablendeer.CarHireServlet.service;

import com.yashablendeer.CarHireServlet.dao.CarDao;
import com.yashablendeer.CarHireServlet.dao.impl.DaoFactory;
import com.yashablendeer.CarHireServlet.model.Car;

import java.util.List;
import java.util.Optional;

public class CarService {
    CarDao dao = DaoFactory.getCarDao();

    public Optional<Car> findCarById(long id) {
        return dao.findById(id);
    }

    public List<Car> findAllByCarMark(String carMark) {
        return dao.findAllByCarMark(carMark);
    }

    public List<Car> findAllByCarQuality(String carQuality) {
        return dao.findAllByCarQuality(carQuality);
    }

    public List<Car> findAll() {
        return dao.findAll();
    }

    public boolean deleteCarById(long id) {
        return dao.deleteById(id);
    }

    public boolean addCar(Car car) {
        return dao.addCar(car);
    }
}
