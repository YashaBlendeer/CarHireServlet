package com.yashablendeer.CarHireServlet.controller.command.admin;

import com.yashablendeer.CarHireServlet.controller.command.Command;
import com.yashablendeer.CarHireServlet.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class BanHandler implements Command {
    private final UserService userService;

    public BanHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        return null;
    }
}
