package com.yashablendeer.CarHireServlet.controller.command;

import com.yashablendeer.CarHireServlet.service.CarService;

import javax.servlet.http.HttpServletRequest;

public class CarPage implements Command{

    private final CarService carService;

    public CarPage(CarService carService) {
        this.carService = carService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("cars",  carService.findAll());
        return "WEB-INF/carPage.jsp";
    }
}
