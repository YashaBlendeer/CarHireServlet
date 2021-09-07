package com.yashablendeer.CarHireServlet.controller.command;

import com.yashablendeer.CarHireServlet.model.User;
import com.yashablendeer.CarHireServlet.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class Home implements Command{
    private final UserService userService;

    public Home(UserService userService) {
        this.userService = userService;
    }


    @Override
    public String execute(HttpServletRequest request) {
        if (request.getMethod().equals("GET")) {
            User user = (User) request.getSession().getAttribute("user");
            request.setAttribute("welcome", "Welcome, " + user.getUserName() + "/" + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
            return "WEB-INF/home.jsp";
        }
        return "WEB-INF/home.jsp";
    }
}
