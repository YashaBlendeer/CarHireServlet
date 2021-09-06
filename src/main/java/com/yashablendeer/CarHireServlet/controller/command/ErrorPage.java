package com.yashablendeer.CarHireServlet.controller.command;

import javax.servlet.http.HttpServletRequest;

public class ErrorPage implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        if (request.getMethod().equals("GET")) {
            System.out.println("returned error");
            return "WEB-INF/error.jsp";
        }
        return "WEB-INF/error.jsp";
    }
}
