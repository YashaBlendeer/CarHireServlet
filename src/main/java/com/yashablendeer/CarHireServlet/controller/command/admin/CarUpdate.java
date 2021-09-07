package com.yashablendeer.CarHireServlet.controller.command.admin;

import com.yashablendeer.CarHireServlet.controller.command.Command;
import com.yashablendeer.CarHireServlet.model.Car;
import com.yashablendeer.CarHireServlet.model.Status;
import com.yashablendeer.CarHireServlet.service.CarService;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class CarUpdate implements Command {
    private final CarService carService;

    public CarUpdate(CarService carService) {
        this.carService = carService;
    }
    @Override
    public String execute(HttpServletRequest request) {
        if (request.getMethod().equals("GET")) {
            Long carId = Long.parseLong(request.getParameter("carId"));

            System.out.println("carId: " + carId);

            try {
                request.setAttribute("carId", carId);
                request.setAttribute("carToUpdate", carService.findCarById(carId).orElseThrow(NoSuchElementException::new));
                request.setAttribute("currentStatus", carService.findCarById(carId).get().getStatus().name());
                request.setAttribute("allStatuses", List.of(Status.READY.name(), Status.REPAIR.name()));

            } catch (Exception e) {
                e.printStackTrace();
                return "redirect:/error";
            }
            return "WEB-INF/carUpdate.jsp";
        }

        try {
            Long carIdToUpd = Long.parseLong(request.getParameter("carId"));
            request.setAttribute("carId", carIdToUpd);
            Car receivedCar = carService.findCarById(carIdToUpd).orElseThrow(NoSuchElementException::new);
            receivedCar.setCarName(request.getParameter("carName"));
            receivedCar.setCarMark(request.getParameter("carMark"));
            receivedCar.setCarQuality(request.getParameter("carQuality"));
            receivedCar.setCarPrice(Long.parseLong(request.getParameter("carPrice")));
            receivedCar.setStatus(Status.valueOf(request.getParameter("carStatus")));
            carService.updateCar(receivedCar, carIdToUpd);

            request.setAttribute("currentStatus", receivedCar.getStatus().name());
            request.setAttribute("allStatuses", List.of(Status.READY.name(), Status.REPAIR.name()));
            request.setAttribute("carToUpdate", receivedCar);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error";
        }

        return "WEB-INF/carUpdate.jsp";
    }
}
