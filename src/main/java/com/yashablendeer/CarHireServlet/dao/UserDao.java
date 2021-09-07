package com.yashablendeer.CarHireServlet.dao;

import com.yashablendeer.CarHireServlet.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    boolean addUser(User user);
    Optional<User> findUserById(long id);
    List<User> findAll();
    Optional<User> findByUserName(String userName);
    boolean checkPasswordMatching(String passToCheck, User user);
    boolean banHandler(long id);
    boolean managerHandler(long id);
}
