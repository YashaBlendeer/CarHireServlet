package com.yashablendeer.CarHireServlet.dao;

import com.yashablendeer.CarHireServlet.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarDao {
    Optional<Car> findById(long id);
    List<Car> findAllByCarMark(String carMark);
    List<Car> findAllByCarQuality(String carQuality);

    //TODO upd car??
    void deleteById(long id);
    List<Car> findAll();

}
