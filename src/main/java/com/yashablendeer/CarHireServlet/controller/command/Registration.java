package com.yashablendeer.CarHireServlet.controller.command;

import com.yashablendeer.CarHireServlet.model.User;
import com.yashablendeer.CarHireServlet.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class Registration implements Command{

    private final UserService userService;

    public Registration(UserService userService) {
        this.userService = userService;
    }


    @Override
    public String execute(HttpServletRequest request) {
        if (request.getMethod().equals("GET")) {
            return "WEB-INF/registration.jsp";
        }

        User user = new User();
        user.setName(request.getParameter("name"));
        user.setLastName(request.getParameter("lastName"));
        user.setUserName(request.getParameter("userName"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));

//        Todo register validation
        try {
            System.out.println("try to register user");
            System.out.println(user);
            userService.createUser(user);

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/registration";
        }
        return "redirect:/registration";
    }
}