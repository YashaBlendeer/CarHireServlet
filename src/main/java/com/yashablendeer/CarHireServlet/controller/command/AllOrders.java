package com.yashablendeer.CarHireServlet.controller.command;

import com.yashablendeer.CarHireServlet.service.CarService;
import com.yashablendeer.CarHireServlet.service.OrderService;

import javax.servlet.http.HttpServletRequest;

public class AllOrders implements Command{

    private final CarService carService;
    private final OrderService orderService;

    public AllOrders(CarService carService, OrderService orderService) {
        this.carService = carService;
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("orders",  orderService.findAll());
        return "WEB-INF/allOrders.jsp";
    }
}
