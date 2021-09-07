package com.yashablendeer.CarHireServlet.controller.command.admin;

import com.yashablendeer.CarHireServlet.controller.command.Command;
import com.yashablendeer.CarHireServlet.model.Car;
import com.yashablendeer.CarHireServlet.service.CarService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import static javax.swing.JOptionPane.showMessageDialog;

public class CarAdd implements Command {

    private final CarService carService;

    public CarAdd(CarService carService) {
        this.carService = carService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        if (request.getMethod().equals("GET")) {
//            request.getSession().removeAttribute("successMessage");
//            request.removeAttribute("successMessage");
            return "WEB-INF/carAdd.jsp";
        }

        Car car = new Car();
        car.setCarName(request.getParameter("carName"));
        car.setCarMark(request.getParameter("carMark"));
        car.setCarQuality(request.getParameter("carQuality"));
        car.setCarPrice(Long.parseLong(request.getParameter("carPrice")));

        try {
            carService.addCar(car);
//            request.getSession().setAttribute("successMessage", "Car was added successfully");

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/carAdd";

        }
        return "redirect:/carAdd";
    }
}
