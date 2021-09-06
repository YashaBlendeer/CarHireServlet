package com.yashablendeer.CarHireServlet.dao.impl;

import com.yashablendeer.CarHireServlet.dao.OrderDao;
import com.yashablendeer.CarHireServlet.dao.impl.extractUtil.CarExtract;
import com.yashablendeer.CarHireServlet.dao.impl.extractUtil.OrderExtract;
import com.yashablendeer.CarHireServlet.dao.impl.extractUtil.UserExtract;
import com.yashablendeer.CarHireServlet.model.Order;
import com.yashablendeer.CarHireServlet.model.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.yashablendeer.CarHireServlet.utils.DBQueries.*;

public class OrderDaoImpl implements OrderDao {

    Connection connection;

    public OrderDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean addOrder(Order order) {
        try (PreparedStatement ps = connection.prepareStatement(ADD_ORDER)) {
            ps.setString(1, "");
            ps.setTimestamp(2, Timestamp.valueOf(order.getStartTime()));
            ps.setTimestamp(3, Timestamp.valueOf(order.getEndTime()));
            ps.setLong(4, order.getOrderPrice());
            ps.setString(5, order.getPassport());
            ps.setString(6, String.valueOf(Status.UNPAYED));
            ps.setString(7, String.valueOf(Status.READY));
            ps.setBoolean(8, order.getWithDriver());
            ps.setLong(9, 1);
            ps.setLong(10, 2);
//            ps.setLong(9, order.getCar().getId());
//            ps.setLong(10, order.getUser().getId());
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
//            logger.severe(ex.getMessage());
        }
        return true;
    }

    @Override
    public boolean addOrder(Order order, long carId, long userId) {
        try (PreparedStatement ps = connection.prepareStatement(ADD_ORDER)) {
            ps.setString(1, "");
            ps.setTimestamp(2, Timestamp.valueOf(order.getStartTime()));
            ps.setTimestamp(3, Timestamp.valueOf(order.getEndTime()));
            ps.setLong(4, order.getOrderPrice());
            ps.setString(5, order.getPassport());
            ps.setString(6, String.valueOf(Status.UNPAYED));
            ps.setString(7, String.valueOf(Status.READY));
            ps.setBoolean(8, order.getWithDriver());
            ps.setLong(9, carId);
            ps.setLong(10, userId);
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
//            logger.severe(ex.getMessage());
        }
        return true;
    }

    @Override
    public Optional<Order> findById(long id) {
        Optional<Order> optOrder = null;
        Order order = new Order();
        try (PreparedStatement ps1 = connection.prepareStatement(FIND_ORDER_BY_ID);
             PreparedStatement ps2 = connection.prepareStatement(FIND_CAR_BY_ID);
             PreparedStatement ps3 = connection.prepareStatement(FIND_USER_BY_ID)) {
            ps1.setLong(1, id);
            ResultSet rs1 = ps1.executeQuery();
            if (rs1.next()) {
                order = OrderExtract.extractOrder(rs1);

                ps2.setLong(1, rs1.getLong("car_id"));
                ResultSet rs2 = ps2.executeQuery();
                if(rs2.next()) {
                    order.setCar(CarExtract.extractCar(rs2));
                }

                ps3.setLong(1, rs1.getLong("user_id"));
                ResultSet rs3 = ps3.executeQuery();
                if(rs3.next()) {
                    order.setUser(UserExtract.extractUser(rs3));
                }

                optOrder = Optional.ofNullable(order);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
//            logger.severe(ex.getMessage());
        }
        return optOrder;
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        Order currentOrder = new Order();
        try (PreparedStatement ps = connection.prepareStatement(FIND_ALL_ORDERS);
             PreparedStatement ps2 = connection.prepareStatement(FIND_CAR_BY_ID);
             PreparedStatement ps3 = connection.prepareStatement(FIND_USER_BY_ID)) {
            ResultSet rs1 = ps.executeQuery();
            while (rs1.next()) {
                currentOrder = OrderExtract.extractOrder(rs1);

                ps2.setLong(1, rs1.getLong("car_id"));
                ResultSet rs2 = ps2.executeQuery();
                if(rs2.next()) {
                    currentOrder.setCar(CarExtract.extractCar(rs2));
                }

                ps3.setLong(1, rs1.getLong("user_id"));
                ResultSet rs3 = ps3.executeQuery();
                if(rs3.next()) {
                    currentOrder.setUser(UserExtract.extractUser(rs3));
                }
                
                orders.add(currentOrder);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
//            logger.severe(ex.getMessage());
        }
        return orders;
    }

    @Override
    public List<Order> findAllByCarId(long id) {
        List<Order> orders = new ArrayList<>();
        Order currentOrder = new Order();
        try (PreparedStatement ps1 = connection.prepareStatement(FIND_ORDERS_BY_CAR_ID);
             PreparedStatement ps2 = connection.prepareStatement(FIND_CAR_BY_ID);
             PreparedStatement ps3 = connection.prepareStatement(FIND_USER_BY_ID)) {
            ps1.setLong(1, id);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                currentOrder = OrderExtract.extractOrder(rs1);

                ps2.setLong(1, rs1.getLong("car_id"));
                ResultSet rs2 = ps2.executeQuery();
                if(rs2.next()) {
                    currentOrder.setCar(CarExtract.extractCar(rs2));
                }

                ps3.setLong(1, rs1.getLong("user_id"));
                ResultSet rs3 = ps3.executeQuery();
                if(rs3.next()) {
                    currentOrder.setUser(UserExtract.extractUser(rs3));
                }

                orders.add(currentOrder);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
//            logger.severe(ex.getMessage());
        }
        return orders;
    }

    @Override
    public boolean deleteById(long id) {
        try(PreparedStatement ps = connection.prepareStatement(DELETE_ORDER_BY_ID)){
            ps.setLong(1, id);
            ps.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
            return true;
//            logger.severe(ex.getMessage());
        }
        return false;
    }

    @Override
    public void deleteByCarId(long id) {

    }
}
