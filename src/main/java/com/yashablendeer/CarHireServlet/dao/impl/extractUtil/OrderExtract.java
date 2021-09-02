package com.yashablendeer.CarHireServlet.dao.impl.extractUtil;

import com.yashablendeer.CarHireServlet.dao.impl.OrderDaoImpl;
import com.yashablendeer.CarHireServlet.model.Car;
import com.yashablendeer.CarHireServlet.model.Order;
import com.yashablendeer.CarHireServlet.model.Status;
import com.yashablendeer.CarHireServlet.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class OrderExtract {
    public static Order extractOrder(ResultSet rs) throws SQLException {
        Order order = new Order();

        order.setId(rs.getLong("order_id"));
        order.setDescription(rs.getString("description"));
        order.setOrderPrice(rs.getLong("price"));
        order.setPassport(rs.getString("user_passport"));
        order.setStatus(Status.valueOf(rs.getString("status")));
        order.setPayStatus(Status.valueOf(rs.getString("pay_status")));
        order.setWithDriver(rs.getBoolean("with_driver"));
        order.setStartTime(rs.getTimestamp("start_time").toLocalDateTime());
        order.setEndTime(rs.getTimestamp("end_time").toLocalDateTime());

        return order;
    }
}
