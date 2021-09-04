package com.yashablendeer.CarHireServlet.dao.impl;

import com.yashablendeer.CarHireServlet.util.DBUtil;
import com.yashablendeer.CarHireServlet.dao.CarDao;
import com.yashablendeer.CarHireServlet.dao.OrderDao;
import com.yashablendeer.CarHireServlet.dao.UserDao;

public class DaoFactory {
    public static UserDao getUserDao() {
        return new UserDaoImpl(DBUtil.getConnection());
    }

    public static CarDao getCarDao() {
        return new CarDaoImpl(DBUtil.getConnection());
    }

    public static OrderDao getOrderDao() {
        return new OrderDaoImpl(DBUtil.getConnection());
    }
}
