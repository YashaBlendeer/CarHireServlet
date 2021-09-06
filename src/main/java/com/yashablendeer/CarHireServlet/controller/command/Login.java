package com.yashablendeer.CarHireServlet.controller.command;

import com.yashablendeer.CarHireServlet.model.User;
import com.yashablendeer.CarHireServlet.service.UserService;
import com.yashablendeer.CarHireServlet.utils.BCrypt;

import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;
import java.util.Optional;

public class Login implements Command {
    private final UserService userService;

    public Login(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        if (request.getMethod().equals("GET")) {
            return "WEB-INF/login.jsp";
        }

//        TODO check user is logged
        Optional<User> user;

        try {
            user = userService.findByUserName(request.getParameter("userName"));

            boolean correctPass = userService.checkPasswordMatching(request.getParameter("password"), user.get());
            if (user.isPresent() && correctPass) {
                System.out.println("user in login: " + user.get());
                CommandSecurity.addUserSession(request, user.get());
                return "redirect:/home";
            }

            if (user.isPresent() && !correctPass) {
                request.setAttribute("wrongPassword", "Password is not correct");
                System.out.println("Password is not correct");
                return "WEB-INF/login.jsp";
            }

        }catch (NoSuchElementException ex) {
            request.setAttribute("wrongUserName", "Username not found");
            System.out.println("Username not found");
            return "WEB-INF/login.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            return "WEB-INF/login.jsp";
        }
        return "WEB-INF/login.jsp";

    }
}
