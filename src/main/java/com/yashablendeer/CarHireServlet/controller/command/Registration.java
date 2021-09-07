package com.yashablendeer.CarHireServlet.controller.command;

import com.yashablendeer.CarHireServlet.model.User;
import com.yashablendeer.CarHireServlet.service.UserService;
import com.yashablendeer.CarHireServlet.utils.Validations;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

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

        try {
            Optional<User> foundUser = userService.findByUserName(request.getParameter("userName"));
            if(foundUser.isEmpty() && !Validations.doesPasswordExist(request.getParameter("password"))){
                User user = new User();
                user.setName(request.getParameter("name"));
                user.setLastName(request.getParameter("lastName"));
                user.setUserName(request.getParameter("userName"));
                user.setEmail(request.getParameter("email"));
                user.setPassword(request.getParameter("password"));

                userService.createUser(user);
                request.removeAttribute("userAlreadyExists");

            } else if(foundUser.isPresent()) {
                request.setAttribute("userAlreadyExists", "User with such username already exists");
                return "WEB-INF/registration.jsp";
            } else if(Validations.doesPasswordExist(request.getParameter("password"))){
                request.setAttribute("passAlreadyExists", "Such password already exists");
                return "WEB-INF/registration.jsp";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/registration";
        }
        return "redirect:/registration";
    }
}