package com.yashablendeer.CarHireServlet.dao.impl;

import com.yashablendeer.CarHireServlet.dao.CarDao;
import com.yashablendeer.CarHireServlet.dao.impl.extractUtil.CarExtract;
import com.yashablendeer.CarHireServlet.dao.impl.extractUtil.UserExtract;
import com.yashablendeer.CarHireServlet.model.Car;
import com.yashablendeer.CarHireServlet.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.yashablendeer.CarHireServlet.Util.DBQueries.*;

public class CarDaoImpl implements CarDao {

    Connection connection;

    public CarDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Car> findById(long id) {
        Optional<Car> car = Optional.ofNullable(null);
        try (PreparedStatement ps = connection.prepareStatement(FIND_CAR_BY_ID)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                car = Optional.of(CarExtract.extractCar(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
//            logger.severe(ex.getMessage());
        }
        return car;
    }

    @Override
    public List<Car> findAllByCarMark(String carMark) {
        List<Car> cars = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(FIND_CARS_BY_MARK)) {
            ps.setString(1, carMark);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cars.add(CarExtract.extractCar(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
//            logger.severe(ex.getMessage());
        }
        return cars;
    }

    @Override
    public List<Car> findAllByCarQuality(String carQuality) {
        List<Car> cars = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(FIND_CARS_BY_QUALITY)) {
            ps.setString(1, carQuality);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cars.add(CarExtract.extractCar(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
//            logger.severe(ex.getMessage());
        }
        return cars;
    }

    @Override
    public List<Car> findAll() {
        List<Car> cars = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(FIND_ALL_CARS)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cars.add(CarExtract.extractCar(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
//            logger.severe(ex.getMessage());
        }
        return cars;
    }

    @Override
    public void deleteById(long id) {
        try(PreparedStatement ps=connection.prepareStatement(DELETE_CAR_BY_ID)){
            ps.setLong(1, id);
            ps.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
//            logger.severe(ex.getMessage());
        }
    }


}
