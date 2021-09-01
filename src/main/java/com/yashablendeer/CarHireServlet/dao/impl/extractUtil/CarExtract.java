package com.yashablendeer.CarHireServlet.dao.impl.extractUtil;

import com.yashablendeer.CarHireServlet.model.Car;
import com.yashablendeer.CarHireServlet.model.Role;
import com.yashablendeer.CarHireServlet.model.Status;
import com.yashablendeer.CarHireServlet.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarExtract {
    public static Car extractCar(ResultSet rs) throws SQLException {
        Car car = new Car();

        car.setId(rs.getLong("car_id"));
        car.setCarMark(rs.getString("mark"));
        car.setCarName(rs.getString("name"));
        car.setCarPrice(rs.getLong("price"));
        car.setCarQuality(rs.getString("quality"));
        car.setStatus(Status.valueOf(rs.getString("status")));

        return car;
    }
}
