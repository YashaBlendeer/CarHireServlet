package com.yashablendeer.CarHireServlet.controller.command;

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
            System.out.println("returned home");
            return "WEB-INF/home.jsp";
        }
        return "WEB-INF/home.jsp";
    }
}
