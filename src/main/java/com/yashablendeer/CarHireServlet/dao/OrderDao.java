package com.yashablendeer.CarHireServlet.dao;

import com.yashablendeer.CarHireServlet.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderDao {
    boolean addOrder(Order order);
    boolean addOrder(Order order, long carId, long userId);
    Optional<Order> findById(long id);
    List<Order> findAll();
    List<Order> findAllByCarId(long id);

    boolean deleteById(long id);
    void deleteByCarId(long id);

}
