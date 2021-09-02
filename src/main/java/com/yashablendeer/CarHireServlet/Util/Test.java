package com.yashablendeer.CarHireServlet.Util;

import com.yashablendeer.CarHireServlet.dao.impl.CarDaoImpl;
import com.yashablendeer.CarHireServlet.dao.impl.OrderDaoImpl;
import com.yashablendeer.CarHireServlet.dao.impl.RepairDaoImpl;
import com.yashablendeer.CarHireServlet.dao.impl.UserDaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {
    public static void main (String[] args) {
        Connection conn = DBUtil.getConnection();
        UserDaoImpl userImpl = new UserDaoImpl(conn);
        CarDaoImpl carImpl = new CarDaoImpl(conn);
        OrderDaoImpl orderImpl = new OrderDaoImpl(conn);
        RepairDaoImpl repairImpl = new RepairDaoImpl(conn);

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
