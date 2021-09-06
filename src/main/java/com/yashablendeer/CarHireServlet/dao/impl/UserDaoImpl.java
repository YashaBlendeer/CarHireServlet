package com.yashablendeer.CarHireServlet.dao.impl;

import com.yashablendeer.CarHireServlet.utils.BCrypt;
import com.yashablendeer.CarHireServlet.dao.UserDao;
import com.yashablendeer.CarHireServlet.dao.impl.extractUtil.UserExtract;
import com.yashablendeer.CarHireServlet.model.Role;
import com.yashablendeer.CarHireServlet.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.yashablendeer.CarHireServlet.utils.DBQueries.*;

public class UserDaoImpl implements UserDao {

    private Connection connection;
//    private UserExtract userExtract = new UserExtract();

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean addUser(User entity) {
        try (PreparedStatement ps = connection.prepareStatement(ADD_USER)) {
            ps.setBoolean(1, true);
            ps.setString(2, entity.getEmail());
            ps.setString(3, entity.getLastName());
            ps.setString(4, entity.getName());
            ps.setString(5, BCrypt.hashpw(entity.getPassword(), BCrypt.gensalt(12)));
            ps.setString(6, entity.getUserName());
            ps.setString(7, String.valueOf(Role.USER));
            ps.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
//            logger.severe(ex.getMessage());
            return false;
        }
        return true;
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

    public boolean checkPasswordMatching(String passwordToCheck, User user) {
        return BCrypt.checkpw(passwordToCheck, user.getPassword());
    }
}
