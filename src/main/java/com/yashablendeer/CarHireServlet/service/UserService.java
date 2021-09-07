package com.yashablendeer.CarHireServlet.service;

import com.yashablendeer.CarHireServlet.dao.UserDao;
import com.yashablendeer.CarHireServlet.dao.impl.DaoFactory;
import com.yashablendeer.CarHireServlet.model.User;

import java.util.List;
import java.util.Optional;

public class UserService {
    private final UserDao dao = DaoFactory.getUserDao();

    public Optional<User> findUserById (long id) {
        return dao.findUserById(id);
    }

    public List<User> findAllUsers() {
        return dao.findAll();
    }

    public Optional<User> findByUserName(String userName){
        return dao.findByUserName(userName);
    }

    public boolean createUser(User user) {
        return dao.addUser(user);
    }

    public boolean checkPasswordMatching(String password, User user) {
        return dao.checkPasswordMatching(password, user);
    }

    public boolean banHandler(long id) {
        return dao.banHandler(id);
    }
}
