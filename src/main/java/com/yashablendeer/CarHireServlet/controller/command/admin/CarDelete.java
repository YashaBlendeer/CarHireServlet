package com.yashablendeer.CarHireServlet.controller.command.admin;

import com.yashablendeer.CarHireServlet.controller.command.Command;
import com.yashablendeer.CarHireServlet.service.CarService;

import javax.servlet.http.HttpServletRequest;

public class CarDelete implements Command {
    private final CarService carService;

    public CarDelete(CarService carService) {
        this.carService = carService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Long carId = Long.parseLong(request.getParameter("carId"));
        try {
            carService.deleteCarById(carId);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error";
        }
        return "redirect:/carPage";
    }
}
