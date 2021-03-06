package com.yashablendeer.CarHireServlet.controller;

import com.yashablendeer.CarHireServlet.controller.command.*;
import com.yashablendeer.CarHireServlet.controller.command.admin.*;
import com.yashablendeer.CarHireServlet.controller.command.user.CarOrder;
import com.yashablendeer.CarHireServlet.service.CarService;
import com.yashablendeer.CarHireServlet.service.OrderService;
import com.yashablendeer.CarHireServlet.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@WebServlet(name = "ControllerServlet", urlPatterns = "/")
public class Controller extends HttpServlet {
    private Map<String, Command> commands = new HashMap<>();
    private UserService userService = new UserService();
    private CarService carService = new CarService();
    private OrderService orderService = new OrderService();
    public Controller() {
    }

    @Override
    public void init(ServletConfig servletConfig){

        servletConfig.getServletContext()
                .setAttribute("loggedUsers", new HashSet<String>());

        commands.put("/", (req) -> "/login.jsp");

        commands.put("/registration",
                new Registration(userService));
        commands.put("/login",
                new Login(userService));
        commands.put("/logout",
                new Logout());
        commands.put("/home",
                new Home(userService));
        commands.put("/error",
                new ErrorPage());

        commands.put("/carPage",
                new CarPage(carService));
        commands.put("/carAdd",
                new CarAdd(carService));
        commands.put("/carUpdate",
                new CarUpdate(carService));
        commands.put("/carDelete",
                new CarDelete(carService));

        commands.put("/allUsers",
                new AllUsers(userService));
        commands.put("/banHandler",
                new BanHandler(userService));
        commands.put("/managerHandler",
                new ManagerRoleHandler(userService));

        commands.put("/carOrder",
                new CarOrder(carService, orderService));
        commands.put("/allOrders",
                new AllOrders(carService, orderService));

    }

    @Override
    protected void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        Command command = commands.get(path);
        String page = command.execute(request);
        if(page.contains("redirect:")){

            response.sendRedirect(page.replace("redirect:", ""));

        }
        else {
            request.getRequestDispatcher(page).forward(request, response);
        }

    }
}
