package com.yashablendeer.CarHireServlet.dao.impl;

import com.yashablendeer.CarHireServlet.dao.UserDao;
import com.yashablendeer.CarHireServlet.dao.impl.extractUtil.UserExtract;
import com.yashablendeer.CarHireServlet.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.yashablendeer.CarHireServlet.Util.DBQueries.*;

public class UserDaoImpl implements UserDao {

    private Connection connection;
//    private UserExtract userExtract = new UserExtract();

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }
    
    @Override
    public Optional<User> findUserById(long id) {
        Optional<User> user = Optional.ofNullable(null);
        try (PreparedStatement ps = connection.prepareStatement(FIND_USER_BY_ID)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = Optional.of(UserExtract.extractUser(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
//            logger.severe(ex.getMessage());
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(FIND_ALL_USERS)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                users.add(UserExtract.extractUser(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
//            logger.severe(ex.getMessage());
        }
        return users;
    }

    @Override
    public Optional<User> findByUserName(String userName) {
        Optional<User> user = Optional.ofNullable(null);
        try (PreparedStatement ps = connection.prepareStatement(FIND_USER_BY_USERNAME)) {
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = Optional.of(UserExtract.extractUser(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
//            logger.severe(ex.getMessage());
        }
        return user;
    }
}
