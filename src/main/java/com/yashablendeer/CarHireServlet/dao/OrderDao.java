package com.yashablendeer.CarHireServlet.dao;

import com.yashablendeer.CarHireServlet.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderDao {
    Optional<Order> findById(long id);
    List<Order> findAll();
    List<Order> findAllByCarId(long id);

    void deleteById(long id);
    void deleteByCarId(long id);

}
