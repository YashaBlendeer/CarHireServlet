package com.yashablendeer.CarHireServlet.controller.command.user;

import com.yashablendeer.CarHireServlet.controller.command.Command;
import com.yashablendeer.CarHireServlet.model.Car;
import com.yashablendeer.CarHireServlet.model.Order;
import com.yashablendeer.CarHireServlet.model.User;
import com.yashablendeer.CarHireServlet.service.CarService;
import com.yashablendeer.CarHireServlet.service.OrderService;
import com.yashablendeer.CarHireServlet.service.UserService;
import com.yashablendeer.CarHireServlet.utils.Validations;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.NoSuchElementException;

public class CarOrder implements Command {

    private final UserService userService;
    private final CarService carService;
    private final OrderService orderService;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public CarOrder(UserService userService, CarService carService, OrderService orderService) {
        this.userService = userService;
        this.carService = carService;
        this.orderService = orderService;
    }


    @Override
    public String execute(HttpServletRequest request) {
        if (request.getMethod().equals("GET")) {
            Long carId = Long.parseLong(request.getParameter("carId"));

            System.out.println("carId: " + carId);

            try {
                request.setAttribute("carId", carId);
                request.setAttribute("carToOrder",
                        carService.findCarById(carId).orElseThrow(NoSuchElementException::new));

            } catch (Exception e) {
                e.printStackTrace();
                return "redirect:/error";
            }
            return "WEB-INF/carOrder.jsp";
        }

        try {
            Long carIdToOrder = Long.parseLong(request.getParameter("carId"));
            request.setAttribute("carId", carIdToOrder);
            Car receivedCar = carService.findCarById(carIdToOrder).orElseThrow(NoSuchElementException::new);
            User user = (User) request.getSession().getAttribute("user");
            Order order = new Order();

            boolean dateNotAvailable = Validations.checkDateAvailability(receivedCar,
                    LocalDateTime.parse(request.getParameter("startTime")),
                    LocalDateTime.parse(request.getParameter("endTime")));

            if (!dateNotAvailable) {
                System.out.println("inside if in carOrder");
                order.setUser(user);
                order.setCar(receivedCar);
                order.setWithDriver(Boolean.valueOf(request.getParameter("withDriver")) == null ? false : true);
                order.setPassport(request.getParameter("passport"));
                order.setOrderPrice(Long.parseLong(request.getParameter("orderPrice")));
                order.setStartTime(LocalDateTime.parse(request.getParameter("startTime")));
                order.setEndTime( LocalDateTime.parse(request.getParameter("endTime")));
                orderService.addOrder(order, carIdToOrder, user.getId());
                System.out.println(order);
            }

            System.out.println("with driver: " + request.getParameter("withDriver"));
            System.out.println("price: " + Long.parseLong(request.getParameter("orderPrice")));

            request.setAttribute("carToOrder", receivedCar);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error";

        }
        return "WEB-INF/carOrder.jsp";

    }
}
