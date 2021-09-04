package com.yashablendeer.CarHireServlet.util;

import com.yashablendeer.CarHireServlet.dao.impl.CarDaoImpl;
import com.yashablendeer.CarHireServlet.dao.impl.OrderDaoImpl;
import com.yashablendeer.CarHireServlet.dao.impl.RepairDaoImpl;
import com.yashablendeer.CarHireServlet.dao.impl.UserDaoImpl;
import com.yashablendeer.CarHireServlet.model.Car;
import com.yashablendeer.CarHireServlet.model.Order;
import com.yashablendeer.CarHireServlet.model.User;

import java.sql.Connection;
import java.time.LocalDateTime;

public class Test {
    public static void main (String[] args) {
        Connection conn = DBUtil.getConnection();
        UserDaoImpl userImpl = new UserDaoImpl(conn);
        CarDaoImpl carImpl = new CarDaoImpl(conn);
        OrderDaoImpl orderImpl = new OrderDaoImpl(conn);
        RepairDaoImpl repairImpl = new RepairDaoImpl(conn);

        User userToAdd = new User();
        userToAdd.setEmail("nono@gmail.com");
        userToAdd.setLastName("Pimpo");
        userToAdd.setName("Pimpovich");
        userToAdd.setPassword("pimpopimpo");
        userToAdd.setUserName("pimpo");
//        userImpl.addUser(userToAdd);
//        System.out.println(userImpl.findUserById(2));
//        System.out.println(userImpl.findAll());
//        System.out.println(userImpl.findByUserName("pimpo"));
        Car carToAdd = new Car();
        carToAdd.setCarMark("Chevrolet");
        carToAdd.setCarName("Spark");
        carToAdd.setCarQuality("A");
        carToAdd.setCarPrice(90);
//        carImpl.addCar(carToAdd);
        System.out.println(carImpl.findAllByCarMark("Chevrolet"));

        Order orderToAdd = new Order();
        orderToAdd.setStartTime(LocalDateTime.now());
        orderToAdd.setEndTime(LocalDateTime.now().plusHours(5));
        orderToAdd.setOrderPrice(300);
        orderToAdd.setPassport("000000");
        orderToAdd.setWithDriver(true);
        orderToAdd.setCar(carToAdd);
        orderToAdd.setUser(userToAdd);
//        orderImpl.addOrder(orderToAdd);
        System.out.println(orderImpl.findById(2));




//        System.out.println(repairImpl.findById(73));
//        System.out.println(repairImpl.findAll());
//        System.out.println(repairImpl.findRepairByOrderId(72));



//        orderImpl.deleteById(96);
//        System.out.println(orderImpl.findAll());
//        System.out.println(orderImpl.findById(72));
//        System.out.println(orderImpl.findAllByCarId(31));

//        System.out.println(userImpl.findUserById(3));
//        System.out.println(userImpl.findAll());
//        System.out.println(userImpl.findByUserName("hh"));
//        System.out.println(carImpl.findById(69));
//        System.out.println(carImpl.findAllByCarMark("bmw"));
//        System.out.println(carImpl.findAllByCarQuality("F"));
//        carImpl.deleteById(69);
//        System.out.println(carImpl.findAll());

    }


}
